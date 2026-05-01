package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Masa Ahmad 1231024

public class SearchPatient {
    private TableViewPatient viewPatient;

    public SearchPatient(TableViewPatient viewPatient) {
        this.viewPatient = viewPatient;
    }

    public void showWindow() {
        Stage staage = new Stage();
        staage.setTitle("Search Patient...");

        TextField serchPatentTXTField = new TextField();
        serchPatentTXTField.setPromptText("Enter Patient ID or Name");

        Button serchPatentBoton = new Button("Search");
        serchPatentBoton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");


        serchPatentBoton.setOnAction(e -> {
            String sarchTXtToserchPatentBoton = serchPatentTXTField.getText().trim();

            if (sarchTXtToserchPatentBoton.isEmpty()) {
                showAlert("ERROR!!", "Plz enter Patient ID!!!");
                return;
            }

            try {
                int patentIdForSerchBoton = Integer.parseInt(sarchTXtToserchPatentBoton);
                PatientNode foundpatentIdForSerchBoton = null;

                for (PatientNode patient : TableViewPatient.patientList) {
                    if (patient.getPatientId() == patentIdForSerchBoton) {
                    	foundpatentIdForSerchBoton = patient;
                        break;
                    }
                }

                if (foundpatentIdForSerchBoton != null) {
                    ObservableList<PatientNode> result = FXCollections.observableArrayList();
                    result.add(foundpatentIdForSerchBoton);
                    viewPatient.tableView.setItems(result);
                    
                    showAlert("Success", "Patient found: " + foundpatentIdForSerchBoton.getName()+ ","+ foundpatentIdForSerchBoton.getAge()+ ","+ foundpatentIdForSerchBoton.getCondition());
                    staage.close();
                } else {
                    showAlert("Error", "No patient found with ID: " + patentIdForSerchBoton);
                }

            } catch (NumberFormatException ex) {
                showAlert("Error", "Patient ID must be a number!");
            }
        });

  
        VBox vBoxSearchpatent= new VBox(10);
        vBoxSearchpatent.setPadding(new Insets(15));
        vBoxSearchpatent.setStyle("-fx-background-color: #4A5C7A;");

        Label searchLablSearchpatent = new Label("Search Patient:");
        searchLablSearchpatent.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");

        vBoxSearchpatent.getChildren().addAll(searchLablSearchpatent, serchPatentTXTField, serchPatentBoton);

        Scene sceneOfSearchPatent = new Scene(vBoxSearchpatent, 300, 180);
        staage.setScene(sceneOfSearchPatent);
        staage.show();
    }

    private void showAlert(String titleOfSearchPatent, String messageOfSearchPatent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleOfSearchPatent);
        alert.setHeaderText(null);
        alert.setContentText(messageOfSearchPatent);
        alert.showAndWait();
    }
}