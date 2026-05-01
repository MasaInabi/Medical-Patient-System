package application;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Masa Ahmad 1231024


public class UpdatePatient {
    private TableViewPatient viewPatient;

    public UpdatePatient(TableViewPatient viewPatient) {
        this.viewPatient = viewPatient;
        showSelectPatientWindow();
    }

    private void showSelectPatientWindow() {
        Stage stage = new Stage();
        stage.setTitle("Select patient to update");

        TextField patientIdTXT= new TextField();
        patientIdTXT.setPromptText("plz enter patient ID");

        Button selectPatentBOTton = new Button("Select");
        selectPatentBOTton.setOnAction(e -> {
            try {
                int patentId;
                patentId = Integer.parseInt(patientIdTXT.getText().trim());
                PatientNode SLCTPatent ;
                SLCTPatent = null;

                // البحث في الجدول
                for (PatientNode patientFOUND : TableViewPatient.patientList) {
                    if (patientFOUND.getPatientId() == patentId) {
                    	SLCTPatent = patientFOUND;
                        break;
                    }
                }

                if (SLCTPatent != null) {
                    stage.close();
                    showEditWindow(SLCTPatent);
                } else {
                    showAlert("Error", "No patient found with this ID!");
                }
            } catch (NumberFormatException excpPatientIDmustnumber) {
                showAlert("Error", "patient ID must be  number!!!!!!!!");
            }
        });

        HBox HBXSlctWNDW = new HBox(10);
        HBXSlctWNDW.getChildren().addAll(new Label("Patient ID:"), patientIdTXT);

        VBox vBoxInputAndButton = new VBox(10);
        vBoxInputAndButton.setPadding(new Insets(10));
        vBoxInputAndButton.getChildren().addAll(HBXSlctWNDW, selectPatentBOTton);

        Scene scene = new Scene(vBoxInputAndButton, 300, 150);
        stage.setScene(scene);
        stage.show();
    }

    private void showEditWindow(PatientNode patientOfUpdtWndw) {
        Stage stage = new Stage();
        stage.setTitle("Update patient");

        // التيكست فيلدات مع القيم الحالية
        TextField nameTXTFLD ;
        TextField agePatntTXTFLD;
        nameTXTFLD = new TextField(patientOfUpdtWndw.getName());
         agePatntTXTFLD = new TextField(String.valueOf(patientOfUpdtWndw.getAge()));
        
        ComboBox<String> CondtonCombBOX= new ComboBox<>();
        CondtonCombBOX.getItems().addAll("Diabetes", "Hypertension", "Asthma", "Heart Disease", "Chronic Pain");
        CondtonCombBOX.setValue(patientOfUpdtWndw.getCondition()); 

        Button updatePatntBotton = new Button("Update");
        updatePatntBotton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");

        updatePatntBotton.setOnAction(e -> {
            // بحدث الاسم
            if (!nameTXTFLD.getText().isEmpty()) {
             // بتحقق انو الاسم بس احرف فش ارقام
            	String nameOfPatnt;
                nameOfPatnt = nameTXTFLD.getText();
                boolean hasDigitOfPtnt;
                hasDigitOfPtnt= false;
                for (int i = 0; i < nameOfPatnt.length(); i++) {
                    if (Character.isDigit(nameOfPatnt.charAt(i))) {
                    	hasDigitOfPtnt = true;
                        break;
                    }
                }
                if (hasDigitOfPtnt) {
                    showAlert("Error", "Name cannot contain numbers!");
                    return;
                }
                patientOfUpdtWndw.setName(nameOfPatnt);
            }

            // بحدثث العمر
            try {
                if (!agePatntTXTFLD.getText().isEmpty()) {
                    int ageOfPatnt;
                    ageOfPatnt  = Integer.parseInt(agePatntTXTFLD.getText().trim());
                    if (ageOfPatnt < 0 || ageOfPatnt > 150) {
                        showAlert("Error", "Age must be between 0 and 150!");
                        return;
                    }
                    patientOfUpdtWndw.setAge(ageOfPatnt);
                }
            } catch (NumberFormatException excepAgemustnumber) {
                showAlert("Error", "Age must be a number!");
                return;
            }

            // بحدث الكندشنن
            if (CondtonCombBOX.getValue() != null) {
            	patientOfUpdtWndw.setCondition(CondtonCombBOX.getValue());
            }

            // بحدث اللنكد لستfgf
            TableViewPatient.patientManagement.updatePatient(
            	 patientOfUpdtWndw.getPatientId(),
            	patientOfUpdtWndw.getName(),
            	 patientOfUpdtWndw.getAge(),
            	patientOfUpdtWndw.getCondition()
            );

            viewPatient.updateTable();

        

            showAlert("Success", "Patient updated succesfully..!!");
            stage.close();
        });

        VBox vBoxEditPatient = new VBox(10);
        vBoxEditPatient.setPadding(new Insets(10));
        vBoxEditPatient.setStyle("-fx-background-color: #2C3E50;");

        Label nameOfPtntLabl = new Label("Name:");
        nameOfPtntLabl.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label ageOfPtntLabl = new Label("Age:");
        ageOfPtntLabl.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label conditionOfPtntLabl = new Label("Condition:");
        conditionOfPtntLabl.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        vBoxEditPatient.getChildren().addAll(nameOfPtntLabl, nameTXTFLD, ageOfPtntLabl, agePatntTXTFLD, conditionOfPtntLabl, CondtonCombBOX,  updatePatntBotton  );

        Scene scene = new Scene(vBoxEditPatient, 350, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String UpdatePatienttitle, String UpdatePatientmessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(UpdatePatienttitle);
        alert.setHeaderText(null);
        alert.setContentText(UpdatePatientmessage);
        alert.showAndWait();
    }
}


