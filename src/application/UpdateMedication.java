package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

//Masa Ahmad 1231024

public class UpdateMedication {
    private TableViewMedication tableViewMedication;

    public UpdateMedication(TableViewMedication tableViewMedication) {
        this.tableViewMedication = tableViewMedication;
        showSelectMedicationWindow();
    }

    private void showSelectMedicationWindow() {
        Stage stage = new Stage();
        stage.setTitle("Select medication to update....");

        TextField MedicatnIDFeld = new TextField();
        MedicatnIDFeld.setPromptText("plz entr medicetionID..");

        Button selecMedectontButon = new Button("select");
        selecMedectontButon.setOnAction(e -> {
            String MedicatnIDTXT = MedicatnIDFeld.getText().trim();
            if (MedicatnIDTXT.isEmpty()) {
                showAlert("Error", "Plz enter medicationID...");
                return;
            }

            int idOfUpdateMedication;
            try {
            	idOfUpdateMedication= Integer.parseInt(MedicatnIDTXT.replace("M",""));
            } catch (NumberFormatException excepInvaledmedicationID) {
                showAlert("Error", "Invaled medicationID !");
                return;
            }
            MedicationNode curentMedcationNode;
            // البحث عن الدواء
             curentMedcationNode = (MedicationNode) TableViewMedication.medicationManagement.medicationList.Front;
            MedicationNode MedicationSLCT = null;

            while (curentMedcationNode != null) {
                if (curentMedcationNode.getMedicationId() == idOfUpdateMedication) {
                	MedicationSLCT = curentMedcationNode;
                    break;
                }
                curentMedcationNode = curentMedcationNode.next;
            }

            if (MedicationSLCT != null) {
                stage.close();
                showEditWindow(MedicationSLCT);
            } else {
                showAlert("Error", "No medication found with this ID!");
            }
        });
        Label medicatonIDLBL = new Label("MedicationID:");
        medicatonIDLBL.setStyle("-fx-text-fill: white;");
        HBox HBoxSLCMED = new HBox(10,medicatonIDLBL, MedicatnIDFeld);
        VBox VBOXMEDC = new VBox(10, HBoxSLCMED, selecMedectontButon);
        VBOXMEDC.setPadding(new Insets(10));
        Scene scene = new Scene(VBOXMEDC, 350, 150);
        stage.setScene(scene);
        stage.show();
    }

    private void showEditWindow(MedicationNode medication) {
        Stage stage = new Stage();
        stage.setTitle("Update Medecation");

        TextField patientTXTField;
        TextField nameMidTXTField ;
        TextField dosagTXTField;
        TextField frequencyTXTField;
        patientTXTField = new TextField(String.valueOf(medication.getPatientID()));
         nameMidTXTField = new TextField(medication.getNameMedication());
         dosagTXTField = new TextField(medication.getDosage());
         frequencyTXTField = new TextField(medication.getFrequency());

       

         Label patientIDDLBL = new Label("PatientID:");
         patientIDDLBL.setStyle("-fx-text-fill: white;");

         Label nameMDCTLBL = new Label("Medication Name:");
         nameMDCTLBL.setStyle("-fx-text-fill: white;");

         Label dosagLBL = new Label("Dosage:");
         dosagLBL.setStyle("-fx-text-fill: white;");

         Label frequencLBL = new Label("Frequency:");
         frequencLBL.setStyle("-fx-text-fill: white;");

         
         
         
        Button updateButton;
         updateButton = new Button("Update");
        updateButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");

        updateButton.setOnAction(e -> {
            try {
                int patientID;
                patientID  = Integer.parseInt(patientTXTField.getText().trim());
                String namOfMedc ;
                String dosagOfMDC;
                String frequAncyOfMDC;
                namOfMedc = nameMidTXTField.getText().trim();
                dosagOfMDC = dosagTXTField.getText().trim();
                frequAncyOfMDC = frequencyTXTField.getText().trim();

                medication.setPatientID(patientID);
                medication.setNameMedication(namOfMedc);
                medication.setDosage(dosagOfMDC);
                medication.setFrequency(frequAncyOfMDC);

                tableViewMedication.updateTable();
                showAlert("Success", "Medication updated successfully!");
                stage.close();
            } catch (NumberFormatException excepPatientIDmustNumber) {
                showAlert("Error", "PatientID must be  number!");
            }
        });

        VBox VBXUpdateMedication = new VBox(10,patientIDDLBL, patientTXTField,nameMDCTLBL, nameMidTXTField,dosagLBL, dosagTXTField,frequencLBL, frequencyTXTField, updateButton );

        VBXUpdateMedication.setPadding(new Insets(15));
        VBXUpdateMedication.setStyle("-fx-background-color: #2C3E50;");

        Scene scene = new Scene(VBXUpdateMedication, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String UpdateMedicationtitl, String UpdateMedicationmesage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(UpdateMedicationtitl);
        alert.setHeaderText(null);
        alert.setContentText(UpdateMedicationmesage);
        alert.showAndWait();
    }
}