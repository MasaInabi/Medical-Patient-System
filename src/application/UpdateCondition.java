package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

//Masa Ahmad 1231024

public class UpdateCondition {
    private TableViewCondition viewConditions;

    public UpdateCondition(TableViewCondition viewConditions) {
        this.viewConditions = viewConditions;
        showSelectConditionWindow();
    }

    private void showSelectConditionWindow() {
        Stage stege = new Stage();
        stege.setTitle("Select Condition to Update");

        TextField conditionNameField = new TextField();
        conditionNameField.setPromptText("plz enter condeton mame..");

        Button selectOfCondButton = new Button("Select");
        selectOfCondButton.setOnAction(e -> {
            String nameOfCondeToFind = conditionNameField.getText().trim();

            if (nameOfCondeToFind.isEmpty()) {
                showAlert("Error", "Plz enter  condition name...");
                return;
            }
            ConditionNode curentCondtonNd ;
           ConditionNode slctCondton;
            // البحث في القائمة
           curentCondtonNd = (ConditionNode) TableViewCondition.conditionManagement.conditionList.Front;
           slctCondton = null;

            while (curentCondtonNd != null) {
                if (curentCondtonNd.nameCondition.equalsIgnoreCase(nameOfCondeToFind)) {
                	slctCondton = curentCondtonNd;
                    break;
                }
                curentCondtonNd = curentCondtonNd.next;
            }

            if (slctCondton != null) {
            	stege.close();
                showEditWindow(slctCondton);
            } else {
                showAlert("Error", "No condition found with this name!!!!");
            }
        });

        HBox HBOXconditionNameField = new HBox(10, new Label("Condition Name:"), conditionNameField);
        VBox VBOXshowSelectConditionWindow = new VBox(10, HBOXconditionNameField, selectOfCondButton);
        VBOXshowSelectConditionWindow.setPadding(new Insets(10));
        Scene sceene = new Scene(VBOXshowSelectConditionWindow, 350, 150);
        stege.setScene(sceene);
        stege.show();
    }

    private void showEditWindow(ConditionNode condition) {
        Stage stage = new Stage();
        stage.setTitle("Update Condition");

        TextField nameConditionTextField = new TextField(condition.nameCondition);

        // بحول الليستات  لنصوصص مفصولة بفواصل
        String treatmintText = TreatmentsString(condition);
        String medicatonsTxt = getMedicationsString(condition);
        String symptomsTxt = getSymptomsString(condition);

        TextField treatmentsTextField = new TextField(treatmintText);
        TextField medicationsTextField = new TextField(medicatonsTxt);
        TextField symptomsTextField = new TextField(symptomsTxt);

        Label CONDNameLbl = new Label("Condition Name:");
        CONDNameLbl.setStyle("-fx-text-fill: white;");

        Label TRTLbl = new Label("Treatments :");
        TRTLbl.setStyle("-fx-text-fill: white;");

        Label MEDECLbl = new Label("Medications :");
        MEDECLbl.setStyle("-fx-text-fill: white;");

        Label SYMPLbl = new Label("Symptoms :");
        SYMPLbl.setStyle("-fx-text-fill: white;");

        Button updateConditionButton = new Button("Update");
        updateConditionButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");

        updateConditionButton.setOnAction(e -> {
            // بحدثث الاسم
            if (!nameConditionTextField.getText().isEmpty()) {
            	String nameOfCond;
                 nameOfCond = nameConditionTextField.getText().trim();
                boolean hasDigitCnd = false;
                for (char charac : nameOfCond.toCharArray()) {
                    if (Character.isDigit(charac)) {
                    	hasDigitCnd = true;
                        break;
                    }
                }
                if (hasDigitCnd) {
                    showAlert("Eror!!", "Condetion name not contain numbers!!!!");
                    return;
                }
                condition.setNameCondition(nameOfCond);
            }

            // بحدثث العلاجات
            if (!treatmentsTextField.getText().isEmpty()) {
                condition.recommendedTreatments.RemoveAll();
                for (String treat : treatmentsTextField.getText().split(",")) {
                    condition.recommendedTreatments.addLast(new TreatmentNode(treat.trim()));
                }
            }

            // بحددث الاديوية
            if (!medicationsTextField.getText().isEmpty()) {
                condition.recommendedMedications.RemoveAll();
                for (String medec : medicationsTextField.getText().split(",")) {
                    condition.recommendedMedications.addLast(new MedicationNode(medec.trim()));
                }
            }

            // بحدثث العراض
            if (!symptomsTextField.getText().isEmpty()) {
                condition.symptoms.RemoveAll();
                for (String symp : symptomsTextField.getText().split(",")) {
                    condition.symptoms.addLast(new SymptomNode(symp.trim()));
                }
            }

            //  بدث التييبل 
            viewConditions.updateTable();

            showAlert("Success", "Condetion updat succesfully!!!");
            stage.close();
        });

        VBox VBOXAll = new VBox(10,CONDNameLbl, nameConditionTextField,TRTLbl, treatmentsTextField,MEDECLbl, medicationsTextField,SYMPLbl, symptomsTextField,updateConditionButton);
        VBOXAll.setPadding(new Insets(15));
        VBOXAll.setStyle("-fx-background-color: #2C3E50;");
        Scene sceneApdat = new Scene(VBOXAll, 400, 400);
        stage.setScene(sceneApdat);
        stage.show();
    }

    // بحول الليستاات لنصصوص مفصولة بفواصل
    private String TreatmentsString(ConditionNode node) {
        String resultgetTreatmentsString;
        resultgetTreatmentsString  = "";
        TreatmentNode curentTreetment;
         curentTreetment = (TreatmentNode) node.recommendedTreatments.Front;

        while (curentTreetment != null) {
        	resultgetTreatmentsString += curentTreetment.type;
            if (curentTreetment.next != null) resultgetTreatmentsString += ", ";
            curentTreetment = curentTreetment.next;
        }
        return resultgetTreatmentsString;
    }

    private String getMedicationsString(ConditionNode node) {
        String resultgetMedicationsString;
        resultgetMedicationsString = "";
        MedicationNode curentMedcations;
         curentMedcations = (MedicationNode) node.recommendedMedications.Front;

        while (curentMedcations != null) {
        	resultgetMedicationsString += curentMedcations.nameMedication;
            if (curentMedcations.next != null) resultgetMedicationsString += ", ";
            curentMedcations = curentMedcations.next;
        }
        return resultgetMedicationsString;
    }


    private String getSymptomsString(ConditionNode node) {
        String resultresultgetMedicationsString;
        resultresultgetMedicationsString = "";
        SymptomNode curentSymptm ;
         curentSymptm = (SymptomNode) node.symptoms.Front;

        while (curentSymptm != null) {
        	resultresultgetMedicationsString += curentSymptm.description;
            if (curentSymptm.next != null) resultresultgetMedicationsString += ", ";
            curentSymptm = curentSymptm.next;
        }
        return resultresultgetMedicationsString;
    }

    private void showAlert(String titlecurentTreetment, String messagecurentTreetment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titlecurentTreetment);
        alert.setHeaderText(null);
        alert.setContentText(messagecurentTreetment);
        alert.showAndWait();
    }
}










