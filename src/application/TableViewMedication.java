package application;

import java.util.Comparator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

//Masa Ahmad 1231024

public class TableViewMedication {
    public static MedicationManagement medicationManagement = new MedicationManagement(); // LinkedList manager

    Stage stege;
    static ObservableList<MedicationNode> medicationList = FXCollections.observableArrayList();
    public static TableView<MedicationNode> tableView;

    public TableViewMedication() { 
    	
    }

    public TableViewMedication(MedicationNode firstMedication) {
    	stege = new Stage();

        Label titleMedecatonManagmentLabel = new Label("Medication Management");
        titleMedecatonManagmentLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label DeletbyMedacatonIDLabel = new Label("Delete by MedicationID:");
        DeletbyMedacatonIDLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

        TextField DeletbyMedacatonIDTxteFeld = new TextField();
        DeletbyMedacatonIDTxteFeld.setPromptText("MedicationID");

        Button addMedicationButton = new Button("Add Medication");
        Button deleteMedicationButton = new Button("Delete");
        Button SortByMedicationName = new Button("Sort By Medication Name");
        Button backButton = new Button("Back");
        Button editMedicationButton = new Button("Edit");

        HBox buttonBox = new HBox(10, addMedicationButton, DeletbyMedacatonIDLabel, DeletbyMedacatonIDTxteFeld, deleteMedicationButton, SortByMedicationName, backButton, editMedicationButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setStyle("-fx-background-color: #2C3E50; -fx-border-color: white; -fx-border-width: 1;");

        tableView = new TableView<>();

        TableColumn<MedicationNode, String> medicationIDColumn = new TableColumn<>("MedicationID");
        medicationIDColumn.setCellValueFactory(c -> new SimpleStringProperty("M" + String.format("%03d", c.getValue().getMedicationId())));

        TableColumn<MedicationNode, String> patientIDColumn = new TableColumn<>("PatientID");
        patientIDColumn.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getPatientID())));

        TableColumn<MedicationNode, String> medicationNameColumn = new TableColumn<>("Medication Name");
        medicationNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNameMedication()));

        TableColumn<MedicationNode, String> dosagColumn = new TableColumn<>("Dosage");
        dosagColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDosage()));

        TableColumn<MedicationNode, String> frequencyColumn = new TableColumn<>("Frequency");
        frequencyColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFrequency()));

        tableView.getColumns().addAll(medicationIDColumn, patientIDColumn, medicationNameColumn, dosagColumn, frequencyColumn);
        tableView.setItems(medicationList);

        // ********************************************************* Button Actions **********************************************************************
        addMedicationButton.setOnAction(e -> {
            Stage addStageToAdBoton = new Stage();
            addStageToAdBoton.setTitle("Add Medication");

            Label patentIDLabel = new Label("PatientID:");
            patentIDLabel.setStyle("-fx-text-fill: white;");

            TextField patentIDTXTField = new TextField();

            Label nameMedcetionLabel = new Label("Medication Name:");
            nameMedcetionLabel.setStyle("-fx-text-fill: white;");

            TextField nameMedcetionTXTField = new TextField();

            Label dosegeLabel = new Label("Dosage:");
            dosegeLabel.setStyle("-fx-text-fill: white;");

            TextField dosegeTxtField = new TextField();

            Label fruquancyLabel = new Label("Frequency:");
            fruquancyLabel.setStyle("-fx-text-fill: white;");

            TextField fruquancyTXTField = new TextField();

            Button addBtn = new Button("Add");
            addBtn.setStyle("-fx-text-fill: white;");

            addBtn.setOnAction(ee -> {
                String patientText = patentIDTXTField.getText().trim();
                String nameText = nameMedcetionTXTField.getText().trim();
                String dosageText = dosegeTxtField.getText().trim();
                String frequencyText = fruquancyTXTField.getText().trim();

                if (patientText.isEmpty() || nameText.isEmpty() || dosageText.isEmpty() || frequencyText.isEmpty()) {
                    showAlert("Error", "Plz fill  all field!!!");
                    return;
                }

                int patientID;
                try { patientID = Integer.parseInt(patientText); } 
                catch (NumberFormatException ex) { showAlert("Error", "PatientID must be a number!"); return; }

                int newIDForPatent = medicationManagement.medicationList.size + 1;
                MedicationNode newMedication = new MedicationNode(nameText);
                newMedication.setMedicationId(newIDForPatent);
                newMedication.setPatientID(patientID);
                newMedication.setDosage(dosageText);
                newMedication.setFrequency(frequencyText);

                medicationManagement.addMedication(newMedication);
                updateTable();
                showAlert("Success", "Medicetion added successfuly!");
                addStageToAdBoton.close();
            });

            VBox VBoxOfAddBoton = new VBox(10, patentIDLabel, patentIDTXTField, nameMedcetionLabel, nameMedcetionTXTField, dosegeLabel, dosegeTxtField, fruquancyLabel, fruquancyTXTField, addBtn);
            VBoxOfAddBoton.setPadding(new Insets(10));
            VBoxOfAddBoton.setStyle("-fx-background-color: #2C3E50;");

            Scene scene = new Scene(VBoxOfAddBoton, 400, 350);
            addStageToAdBoton.setScene(scene);
            addStageToAdBoton.show();
        });

        deleteMedicationButton.setOnAction(e -> {
            String medicationIDText = DeletbyMedacatonIDTxteFeld.getText().trim();
            if (medicationIDText.isEmpty()) { showAlert("Error", "Please enter a MedicationID!"); return; }

            boolean removed = false;
            try {
                int id = Integer.parseInt(medicationIDText.replace("M",""));
                removed = medicationManagement.deleteMedicationsByPatientID(id);
            } catch (Exception ex) { removed = false; }

            for (int i = 0; i < medicationList.size(); i++) {
                if (("M" + String.format("%03d", medicationList.get(i).getMedicationId())).equalsIgnoreCase(medicationIDText)) {
                    medicationList.remove(i);
                    break;
                }
            }

            showAlert(removed ? "Succes" : "Error", removed ? "Midiction delet succesfully!!!!!!!" : "Medication ID not found!");
        });

        SortByMedicationName.setOnAction(e -> {
        	FXCollections.sort(medicationList, new Comparator<MedicationNode>() {
        	    @Override
        	    public int compare(MedicationNode MedicationNode1, MedicationNode MedicationNode2) {
        	        return new ReportComparatorClassToSort().SortMedicationByName(MedicationNode1, MedicationNode2);
        	    }
        	       });
        	});

        backButton.setOnAction(e -> stege.close());
        editMedicationButton.setOnAction(e -> { new UpdateMedication(this); });

        VBox VBoxOfTableViewMedication = new VBox(10, titleMedecatonManagmentLabel, buttonBox, tableView);
        VBoxOfTableViewMedication.setPadding(new Insets(10));
        VBoxOfTableViewMedication.setStyle("-fx-background-color: #2C3E50;");

        Scene sceneTable = new Scene(VBoxOfTableViewMedication, 900, 600);
        stege.setScene(sceneTable);
        stege.setTitle("Medication Library");
        stege.show();

        updateTable();
    }

    public static void updateTable() {
        if (tableView == null)
        	return; 

        ObservableList<MedicationNode> MedectionNodeForupdateTable = tableView.getItems();
        MedectionNodeForupdateTable.clear();

        MedicationNode curentupdateTable = (MedicationNode) medicationManagement.medicationList.Front;
        while (curentupdateTable != null) {
        	MedectionNodeForupdateTable.add(curentupdateTable);
        	curentupdateTable = curentupdateTable.next;
        }
    }

    private void showAlert(String titleTableViewMedication, String messageTableViewMedication) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleTableViewMedication);
        alert.setHeaderText(null);
        alert.setContentText(messageTableViewMedication);
        alert.showAndWait();
    }

    public void show() {
    	stege.show();
   }
}
