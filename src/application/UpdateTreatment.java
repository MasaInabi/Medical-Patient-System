package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Masa Ahmad 1231024


public class UpdateTreatment {
    private TableViewTreatment tableViewTreatment;

    public UpdateTreatment(TableViewTreatment tableViewTreatment) {
        this.tableViewTreatment = tableViewTreatment;
        showSelctTretmentWNDW();
    }

    private void showSelctTretmentWNDW() {
        Stage stage = new Stage();
        stage.setTitle("Select Treatment to Update");

        TextField TreatmentIDTXTFLDld = new TextField();
        TreatmentIDTXTFLDld.setPromptText("Enter TreatmentID");
      

        Button SLCTBOTN = new Button("Select");
        SLCTBOTN.setOnAction(e -> {
            String TreatmentIDTXT;
            TreatmentIDTXT  = TreatmentIDTXTFLDld.getText().trim();
            if (TreatmentIDTXT.isEmpty()) {
                showAlert("Error", "Please enter a TreatmentID.");
                return;
            }

            int TreatmntIDForParse;
            try {
            	TreatmntIDForParse= Integer.parseInt(TreatmentIDTXT.replace("T",""));
            } catch (NumberFormatException exception) {
                showAlert("Error", "Invalid TreatmentID... !!!");
                return;
            }

            // البحث عن العلاج
            TreatmentNode curentTRNTND;
            TreatmentNode SLCTTRMNT;

            curentTRNTND = (TreatmentNode) TableViewTreatment.treatmentManagement.treatmentList.Front;
            SLCTTRMNT  = null;

            while (curentTRNTND != null) {
                if (curentTRNTND.getTreatmentId() == TreatmntIDForParse) {
                	SLCTTRMNT = curentTRNTND;
                    break;
                }
                curentTRNTND = curentTRNTND.next;
            }

            if (SLCTTRMNT != null) {
                stage.close();
                showEditWindow(SLCTTRMNT);
            } else {
                showAlert("Error", "No treatment found with ths ID!");
            }
        });

        HBox HBXTRMNT = new HBox(10, new Label("TreatmentID:"), TreatmentIDTXTFLDld);
        VBox layout = new VBox(10, HBXTRMNT, SLCTBOTN);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout, 350, 150);
        stage.setScene(scene);
        stage.show();
    }

    private void showEditWindow(TreatmentNode treatmentOfApdt) {
        Stage stage = new Stage();
        stage.setTitle("Update Treatment");
        TextField TreatmentIDTXTFLDld = new TextField();
        TextField patientTXTFLD = new TextField(String.valueOf(treatmentOfApdt.getPatientID()));
        TextField typeTXTFLD = new TextField(treatmentOfApdt.getType());
        TextField startDatTXTFLD = new TextField(treatmentOfApdt.getStartDate());
        TextField endDatTXTFLD = new TextField(treatmentOfApdt.getEndDate());

        Label LBLTretmentID = new Label("TreatmentID:");
        LBLTretmentID.setStyle("-fx-text-fill: white;");

        Label LBLPatientID = new Label("PatientID:");
        LBLPatientID.setStyle("-fx-text-fill: white;");

        Label lLBLTreatmentType = new Label("Treatment Type:");
        lLBLTreatmentType.setStyle("-fx-text-fill: white;");

        Label LBLStartDate = new Label("Start Date :");
        LBLStartDate.setStyle("-fx-text-fill: white;");

        Label LBLEndDate = new Label("End Date :");
        LBLEndDate.setStyle("-fx-text-fill: white;");

        Button updatBTN= new Button("Update");
        updatBTN.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");

        updatBTN.setOnAction(e -> {
            try {
                int patientIDParse;
                patientIDParse = Integer.parseInt(patientTXTFLD.getText().trim());
                String typeOfTrtmnt;
                String startDateTrtmnt;
                String endDateTrtmnt;

                typeOfTrtmnt = typeTXTFLD.getText().trim();
                startDateTrtmnt = startDatTXTFLD.getText().trim();
                endDateTrtmnt = endDatTXTFLD.getText().trim();

                treatmentOfApdt.setPatientID(patientIDParse);
                treatmentOfApdt.setType(typeOfTrtmnt);
                treatmentOfApdt.setStartDate(startDateTrtmnt);
                treatmentOfApdt.setEndDate(endDateTrtmnt);

                tableViewTreatment.updateTable();
                showAlert("Success", "Treatment updated succesfuly!!!");
                stage.close();
            } catch (NumberFormatException exatientIDmustnumber) {
                showAlert("Error", "PatientID must be a number!");
            }
        });

        HBox HBXTRMNT = new HBox(10, LBLTretmentID, TreatmentIDTXTFLDld);

        VBox VBXtxtFld = new VBox(10,
        		LBLPatientID, patientTXTFLD,
        		lLBLTreatmentType, typeTXTFLD,
        		LBLStartDate, startDatTXTFLD,
        		LBLEndDate, endDatTXTFLD,
            updatBTN
        );


        VBXtxtFld.setPadding(new Insets(15));
        VBXtxtFld.setStyle("-fx-background-color: #2C3E50;");

        Scene scene = new Scene(VBXtxtFld, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String UpdateTreatmenttitle, String UpdateTreatmentmessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(UpdateTreatmenttitle);
        alert.setHeaderText(null);
        alert.setContentText(UpdateTreatmentmessage);
        alert.showAndWait();
    }
}
