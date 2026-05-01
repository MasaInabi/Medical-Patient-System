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


public class TableViewTreatment {
    public static TreatmentManagement treatmentManagement = new TreatmentManagement(); // LinkedList manager

    Stage stage;
    static ObservableList<TreatmentNode> treatmentList = FXCollections.observableArrayList();
    public TableView<TreatmentNode> tableView;

    public TableViewTreatment() { }

    public TableViewTreatment(TreatmentNode firstTreatment) {
        stage = new Stage();

        Label titleLabel = new Label("Treatment Management");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label deletebyTreatmentIDLabel = new Label("Delete by TreatmentID:");
        deletebyTreatmentIDLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

        TextField deletebyTreatmentIDTxtField = new TextField();
        deletebyTreatmentIDTxtField.setPromptText("TreatmentID");

        Button addTreatmentButton = new Button("Add Treatment");
        Button deleteTreatmentButton = new Button("Delete");
        Button sortByName = new Button("sortName");
        Button sortByStartDate = new Button("sortByStartDate");
        Button sortByEndDate = new Button("sortByEndDate");

        Button backButton = new Button("Back");
        Button editTreatmentButton = new Button("Edit");

        HBox buttonBoxOfTreatment = new HBox(10, addTreatmentButton, deletebyTreatmentIDLabel, deletebyTreatmentIDTxtField, deleteTreatmentButton, sortByName, backButton, editTreatmentButton,sortByStartDate,sortByEndDate);
        buttonBoxOfTreatment.setPadding(new Insets(10));
        buttonBoxOfTreatment.setStyle("-fx-background-color: #2C3E50; -fx-border-color: white; -fx-border-width: 1;");

        tableView = new TableView<>();

        TableColumn<TreatmentNode, String> treatmentIDColumn = new TableColumn<>("TreatmentID");
        treatmentIDColumn.setCellValueFactory(clmn -> new SimpleStringProperty("T" + String.format("%03d", clmn.getValue().getTreatmentId())));

        TableColumn<TreatmentNode, String> patientIDColumn = new TableColumn<>("PatientID");
        patientIDColumn.setCellValueFactory(clmn -> new SimpleStringProperty(String.valueOf(clmn.getValue().getPatientID())));

        TableColumn<TreatmentNode, String> treatmentTypeColumn = new TableColumn<>("TreatmentType");
        treatmentTypeColumn.setCellValueFactory(clmn -> new SimpleStringProperty(clmn.getValue().getType()));

        TableColumn<TreatmentNode, String> startDateColumn = new TableColumn<>("StartDate");
        startDateColumn.setCellValueFactory(clmn -> new SimpleStringProperty(clmn.getValue().getStartDate()));

        TableColumn<TreatmentNode, String> endDateColumn = new TableColumn<>("EndDate");
        endDateColumn.setCellValueFactory(clmn -> new SimpleStringProperty(clmn.getValue().getEndDate()));

        tableView.getColumns().addAll(treatmentIDColumn, patientIDColumn, treatmentTypeColumn, startDateColumn, endDateColumn);
        tableView.setItems(treatmentList);

        // ======================= Button Actions =========================

        addTreatmentButton.setOnAction(e -> {
            Stage addStageOfaddTreatmentButton = new Stage();
            addStageOfaddTreatmentButton.setTitle("Add Treatment");

            Label patientLabel = new Label("PatientID:");
            TextField patientIDTXTField = new TextField();

            Label TreatmnttypeLabel = new Label("Treatment Type:");
            TextField TreatmnttypeTXTField = new TextField();

            Label startLabel = new Label("Start Date (YYYY-MM-DD):");
            TextField startField = new TextField();

            Label endDatLabel = new Label("End Date:");
            TextField endDatTXTField = new TextField();

            Button addBOTON = new Button("Add");
            addBOTON.setStyle("-fx-background-color: #2C3E50; -fx-text-fill: white;");

            addBOTON.setOnAction(ee -> {
                String patientText = patientIDTXTField.getText().trim();
                String typeText = TreatmnttypeTXTField.getText().trim();
                String startText = startField.getText().trim();
                String endText = endDatTXTField.getText().trim();

                if (patientText.isEmpty() || typeText.isEmpty() || startText.isEmpty() || endText.isEmpty()) {
                    showAlert("Error", "Please fill in all fields!");
                    return;
                }

                int patientID;
                try { patientID = Integer.parseInt(patientText); } 
                catch (NumberFormatException ex) { showAlert("Error", "PatientID must be a number!"); return; }

                int newID = treatmentManagement.treatmentList.size + 1;
                TreatmentNode newTreatment = new TreatmentNode(typeText);
                newTreatment.setTreatmentId(newID);
                newTreatment.setPatientID(patientID);
                newTreatment.setStartDate(startText);
                newTreatment.setEndDate(endText);

                treatmentManagement.addTreatment(newTreatment);
                updateTable();
                showAlert("Success", "Treatment added successfully!");
                addStageOfaddTreatmentButton.close();
            });

            VBox VBXOfAddBoton = new VBox(10, patientLabel, patientIDTXTField, TreatmnttypeLabel, TreatmnttypeTXTField, startLabel, startField, endDatLabel, endDatTXTField, addBOTON);
            VBXOfAddBoton.setPadding(new Insets(10));
            VBXOfAddBoton.setStyle("-fx-background-color: #2C3E50;");

            Scene sceneOfAddButton = new Scene(VBXOfAddBoton, 400, 350);
            addStageOfaddTreatmentButton.setScene(sceneOfAddButton);
            addStageOfaddTreatmentButton.show();
        });

        deleteTreatmentButton.setOnAction(e -> {
            String treatmentIDText = deletebyTreatmentIDTxtField.getText().trim();
            if (treatmentIDText.isEmpty()) { showAlert("Error", "Please enter a TreatmentID!"); return; }

            boolean removedTreatmant = false;
            try {
                int id = Integer.parseInt(treatmentIDText.replace("T",""));
                removedTreatmant = treatmentManagement.deleteTreatment(id);
            } catch (Exception ex) { removedTreatmant = false; }

            for (int i = 0; i < treatmentList.size(); i++) {
                if (("T" + String.format("%03d", treatmentList.get(i).getTreatmentId())).equalsIgnoreCase(treatmentIDText)) {
                    treatmentList.remove(i);
                    break;
                }
            }

            showAlert(removedTreatmant ? "Success!" : "Error!!", removedTreatmant ? "Treatment delete succesfully!" : "TreatmentID not found!");
        });

        sortByName.setOnAction(e -> { 
        	FXCollections.sort(treatmentList, new Comparator<TreatmentNode>() {
        	    @Override
        	    public int compare(TreatmentNode TreatmentNode1, TreatmentNode TreatmentNode2) {
        	        return new ReportComparatorClassToSort().sortTreatmentByNameButton(TreatmentNode1,TreatmentNode2);
        	    }
        	});
        	});
        
        sortByStartDate.setOnAction(e -> { 
        	FXCollections.sort(treatmentList, new Comparator<TreatmentNode>() {
        	    @Override
        	    public int compare(TreatmentNode TreatmentNodestd1, TreatmentNode TreatmentNodestd2) {
        	        return new ReportComparatorClassToSort().sortByStartDate(TreatmentNodestd1, TreatmentNodestd2);
        	    }
        	});  
        	});
        
        sortByEndDate.setOnAction(e -> { 
        	FXCollections.sort(treatmentList, new Comparator<TreatmentNode>() {
        	    @Override
        	    public int compare(TreatmentNode TreatmentNodeend1, TreatmentNode TreatmentNodeend2) {
        	        return new ReportComparatorClassToSort().sortByEndDate(TreatmentNodeend1, TreatmentNodeend2);
        	    }
        	});
        	});
        backButton.setOnAction(e -> stage.close());
        editTreatmentButton.setOnAction(e -> { new UpdateTreatment(this); });

        VBox VBxTableViewTreatment = new VBox(10, titleLabel, buttonBoxOfTreatment, tableView);
        VBxTableViewTreatment.setPadding(new Insets(10));
        VBxTableViewTreatment.setStyle("-fx-background-color: #2C3E50;");

        Scene sceneTableViewTreatment = new Scene(VBxTableViewTreatment, 900, 600);
        stage.setScene(sceneTableViewTreatment);
        stage.setTitle("Treatment Library");
        stage.show();

        updateTable();
    }

    public void updateTable() {
        ObservableList<TreatmentNode> TreatmentNodeForupdateTable = tableView.getItems();
        TreatmentNodeForupdateTable.clear();

        TreatmentNode curentupdateTable = (TreatmentNode) treatmentManagement.treatmentList.Front;
        while (curentupdateTable != null) {
        	TreatmentNodeForupdateTable.add(curentupdateTable);
        	curentupdateTable = curentupdateTable.next;
        }
    }

    private void showAlert(String titleTableViewTreatment, String messageTableViewTreatment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleTableViewTreatment);
        alert.setHeaderText(null);
        alert.setContentText(messageTableViewTreatment);
        alert.showAndWait();
    }

    public void show() {
    	stage.show(); 
    	}
}
