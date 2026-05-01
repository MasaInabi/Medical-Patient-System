package application;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Masa Ahmad 1231024

public class DeletePatient {

    private TableViewPatient viewPatintTabl; 

    public DeletePatient(TableViewPatient viewPatintTabl) {
        this.viewPatintTabl = viewPatintTabl;
    }

    public void showWindowForPatint() {
        Stage steege = new Stage();
        steege.setTitle("Delete Patient");

        TextField patintIDFeldTx = new TextField();
        patintIDFeldTx.setPromptText("Enter Patient ID");

        Button deletBottonForPatent = new Button("Delete");
        deletBottonForPatent.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white;");

        
        
        deletBottonForPatent.setOnAction(e -> {
            try {
                int patintIDParsInt = Integer.parseInt(patintIDFeldTx.getText().trim());
                
                //حذف من اللنكد ليست
                boolean removdPatintFromLnkedLst = TableViewPatient.patientManagement.deletePatientt(patintIDParsInt);
                
                for (int i = 0; i < TableViewPatient.patientList.size(); i++) {
                    if (TableViewPatient.patientList.get(i).getPatientId() == patintIDParsInt) {
                        TableViewPatient.patientList.remove(i);
                        break; 
                    }
                }
                
                if (removdPatintFromLnkedLst) {
                	showAlertForDeletPatent("Error", "No patient found For ths ID: "+ patintIDParsInt );
                } else {
                	showAlertForDeletPatent("Success", "Patient delete succesfuly!"+ patintIDParsInt);

                }
                
            } catch (NumberFormatException ex) {
            	showAlertForDeletPatent("Error", "Patient ID must  a number!");
            }
        });

        VBox VBOxForElementForPatint = new VBox(10);
        VBOxForElementForPatint.setPadding(new Insets(15));
        VBOxForElementForPatint.getChildren().addAll(new Label("Patient ID:"), patintIDFeldTx, deletBottonForPatent);

        Scene sceneForDeletPatnt = new Scene(VBOxForElementForPatint, 280, 150);
        steege.setScene(sceneForDeletPatnt);
        steege.setTitle("Delete Patient");
        steege.show();
    }

    private void showAlertForDeletPatent(String titleForDltPatnt, String messagForDltPatent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleForDltPatnt);
        alert.setHeaderText(null);
        alert.setContentText(messagForDltPatent);
        alert.showAndWait();
    }
}
