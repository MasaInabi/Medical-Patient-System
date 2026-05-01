package application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


//Masa Ahmad 1231024

public class AddPatient {
    Stage stege;
    private TableViewPatient viePatent;

    public AddPatient(TableViewPatient viePatent) {
        this.viePatent = viePatent;
        stege = new Stage();


        HBox HBOXBtton = new HBox(15);
        Button ClearButon = new Button("Clear"); 
        Button AddDetalsButon = new Button("Add Details"); 
        Button BakButton = new Button("Back"); 

        HBOXBtton.setAlignment(Pos.CENTER_LEFT); 
        HBOXBtton.setPadding(new Insets(20)); 
        HBOXBtton.getChildren().addAll(BakButton, AddDetalsButon, ClearButon);

        GridPane gridPaneForLblAndTxt = new GridPane();
        gridPaneForLblAndTxt.setVgap(10);
        gridPaneForLblAndTxt.setHgap(10);
        gridPaneForLblAndTxt.setPadding(new Insets(20));

        Label PatentIDLabel = new Label("Patient ID:");
        PatentIDLabel.setStyle("-fx-text-fill: white;");

        TextField PatentDTextField = new TextField();
        PatentIDLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: bold;"); 

        Label NamePatentLabl = new Label("Name Patient:");
        NamePatentLabl.setStyle("-fx-text-fill: white;");

        TextField NamePatientTextField = new TextField();
        NamePatientTextField.setStyle("-fx-text-fill: black; -fx-background-color: white;"); // لون النص أسود وخلفية بيضاء


        Label AgeForPatntLabl = new Label("Age:");
        AgeForPatntLabl.setStyle("-fx-text-fill: white;");

        TextField AgePatientTextField = new TextField();
        AgeForPatntLabl.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

        Label ConditonaLabl = new Label("Condition:");
        ConditonaLabl.setStyle("-fx-text-fill: white;");

        TextField ConditionTextField = new TextField();
        ConditonaLabl.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        
        gridPaneForLblAndTxt.add(PatentIDLabel, 0, 0);
        gridPaneForLblAndTxt.add(PatentDTextField, 1, 0);
        gridPaneForLblAndTxt.add(NamePatentLabl, 0, 1);
        gridPaneForLblAndTxt.add(NamePatientTextField, 1, 1);
        gridPaneForLblAndTxt.add(AgeForPatntLabl, 0, 2);
        gridPaneForLblAndTxt.add(AgePatientTextField, 1, 2);
        gridPaneForLblAndTxt.add(ConditonaLabl, 0, 3);
        gridPaneForLblAndTxt.add(ConditionTextField, 1, 3);

     

        VBox VBoxLabels = new VBox(15);
        VBoxLabels.getChildren().addAll(gridPaneForLblAndTxt);

        BorderPane bp = new BorderPane();
   
        VBox all = new VBox(20);
        all.setPadding(new Insets(20));
        all.getChildren().addAll(HBOXBtton, VBoxLabels);
        all.setStyle("-fx-background-color: #2C3E50;"); 
        bp.setCenter(all);

        Scene scene = new Scene(bp, 450, 450);
        stege.setScene(scene);

   //=======================================================  Action Of BOtn ===========================================================
        
        BakButton.setOnAction(e -> {
            stege.close();
            viePatent.updateTable();    
        });


        ClearButon.setOnAction(e -> {
        	PatentDTextField.clear();
        	NamePatientTextField.clear();
        	AgePatientTextField.clear();
        	ConditionTextField.clear();
        });


        AddDetalsButon.setOnAction(e -> {
        	String patentIdTxte = PatentDTextField.getText();
            String namForPatentS = NamePatientTextField.getText();
            String ageForPatntTextS = AgePatientTextField.getText();
            String condEtonForPatntS = ConditionTextField.getText();

            // بتحقق اذا كل الحقول مليانة
            if (patentIdTxte.isEmpty() || namForPatentS.isEmpty() || ageForPatntTextS.isEmpty() || condEtonForPatntS.isEmpty()) {
                showAlert1("Error!!", "Please fill in all Text feld!", Alert.AlertType.WARNING);
                return;
            }

            //بتحقق انو الايدي والعمر ارقام بس
            if (!isValidNumber(patentIdTxte) || !isValidNumber(ageForPatntTextS)) {
                showAlert1("Error!!!", "Patient ID and Age must  only numbers", Alert.AlertType.ERROR);
                return;
            }

            // بتحقق انو الاسم بس احرف
            for (int i = 0; i < namForPatentS.length(); i++) {
                if (Character.isDigit(namForPatentS.charAt(i))) {
                    showAlert1("Error!!", "Name cannot contain numbrs", Alert.AlertType.ERROR);
                    return;
                }
            }

            try {
                // بحول النصوص لارقام
                int patentIdParsIntToInteger = Integer.parseInt(patentIdTxte);
                int ageParseIntgr = Integer.parseInt(ageForPatntTextS);

                // دخلي العمر منطقي
                if (ageParseIntgr < 0 || ageParseIntgr > 150) {
                    showAlert1("Error", "Age must be betwen 0 and 150", Alert.AlertType.ERROR);
                    return;
                }

                //بتحقق اذا الايدي مكرر!
                PatientNode IsAlredyexesting = TableViewPatient.patientManagement.searchPatient(patentIdParsIntToInteger);
                if (IsAlredyexesting != null) {
                    showAlert1("Error", "Patient ID " + patentIdParsIntToInteger + " id is already exists!", Alert.AlertType.ERROR);
                    showAlert1("Error", "Id is alredy  exists ", Alert.AlertType.ERROR);

                    return;
                }

                // بعمب مريض جديد
                PatientNode newPatientMadeByMe = new PatientNode(patentIdParsIntToInteger, namForPatentS, ageParseIntgr, condEtonForPatntS);

             // بضيف المريض
                TableViewPatient.patientManagement.addPatient(newPatientMadeByMe);

                // بحدث التيبل
                viePatent.updateTable();

                showAlert1("Success", "Patient added succesfuly!", Alert.AlertType.INFORMATION);

           

            } catch (NumberFormatException ex) {
                showAlert1("Error", "Patent ID and Age must be numbrs", Alert.AlertType.ERROR);
            }
        });
    }
    
//--------------------------------------------------------- METHDS---------------------------------------------------------------------------
    
        // ميثود عشان اتحقق انو بس فيتو ارقام
         private boolean isValidNumber(String valueIsNmber) {
            for (int i = 0; i < valueIsNmber.length(); i++) {
                if (!Character.isDigit(valueIsNmber.charAt(i))) {
                  return false;
              }
          }
          return true;
    }

    
    
    private void showAlert1(String titleOfAlertAddPatnt, String messageOfAlertAddPatnt, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titleOfAlertAddPatnt);
        alert.setContentText(messageOfAlertAddPatnt);
        alert.showAndWait();
    }

    public void show() {
        stege.show();
    }
}












