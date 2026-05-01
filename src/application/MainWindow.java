package application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCombination;

//Masa Ahmad 1231024

public class MainWindow {
    
	DoublyLinkedList patientList = new DoublyLinkedList();
	Stage steege;
    
    public MainWindow() {
        steege = new Stage();
        
        // ------------------------------------------------------ MenuBar --------------------------------------------------------------
        MenuBar menuBarForMainWindow = new MenuBar();
        
        Menu patientsMenu = new Menu("Patients");
        MenuItem UpdatePatient = new MenuItem("Update Patients");
        MenuItem addPatient = new MenuItem("Add Patient");
        MenuItem searchPatient = new MenuItem("Search Patient");
        MenuItem deletePatient = new MenuItem("Delete Patient");
        
        UpdatePatient.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        addPatient.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        patientsMenu.getItems().addAll(UpdatePatient, addPatient, searchPatient, deletePatient);
        
        Menu conditionsMenuForMainWindw = new Menu("Conditions");
        MenuItem UpdateCondition = new MenuItem("Update Conditions");
        MenuItem DeleteCondition = new MenuItem("Delete Conditions");

        MenuItem addConditionItem = new MenuItem("Add Condition");
        MenuItem viewPatientsByConditionItem = new MenuItem("View Patients by Condition");
        conditionsMenuForMainWindw.getItems().addAll(UpdateCondition,DeleteCondition ,addConditionItem, viewPatientsByConditionItem);
        
        Menu treatmentsMenu = new Menu("Treatments");
        MenuItem addTreatment = new MenuItem("Add Treatment");
        MenuItem UpdateTreatments = new MenuItem("UpdateTreatments");
        MenuItem removeTreatment = new MenuItem("Remove Treatment");     
        treatmentsMenu.getItems().addAll(addTreatment, UpdateTreatments, removeTreatment);
        
        Menu reportsMenu = new Menu("Reports");
        MenuItem conditionSummaryItem = new MenuItem("Condition Summary Report");
        MenuItem patientTreatmentItem = new MenuItem("Patient Treatment Report");
        MenuItem medicationAdherenceItem = new MenuItem("Medication Adherence Report"); 
        conditionSummaryItem.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
        
        reportsMenu.getItems().addAll(conditionSummaryItem, patientTreatmentItem, medicationAdherenceItem);
        
        menuBarForMainWindow.getMenus().addAll( patientsMenu, conditionsMenuForMainWindw, treatmentsMenu, reportsMenu);
        
        
        Label titleLabelForMainWindw = new Label("Medical Patient Monitoring System");
        titleLabelForMainWindw.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; " +"-fx-text-fill: white; -fx-padding: 20px;");
        HBox titleBoxForMainWindw = new HBox(titleLabelForMainWindw);
        titleBoxForMainWindw.setAlignment(Pos.CENTER);
        
        //---------------------------------------------------buttons--------------------------------------------------
        
        Button SaveBTTNForMainWndw = new Button("Save");
        SaveBTTNForMainWndw.setPrefSize(200, 60);
        SaveBTTNForMainWndw.setStyle("-fx-background-color: #3CB371; -fx-text-fill: white; -fx-font-weight: bold;");

        
        Button ReadPatientsButton = new Button("Read Patients");
        ReadPatientsButton.setPrefSize(200, 60);
        ReadPatientsButton.setStyle("-fx-background-color: #5DADE2; -fx-text-fill: white; -fx-font-weight: bold;");
        ReadPatientsButton.setOnAction(e -> ReadPatientsFromChooseFile());
        
        
        Button ReadConditionsButton = new Button("Read Conditions");
        ReadConditionsButton.setPrefSize(200, 60);
        ReadConditionsButton.setStyle("-fx-background-color: #FFD700; -fx-text-fill: white; -fx-font-weight: bold;");
        ReadConditionsButton.setOnAction(e -> ReadConditionsChoosFromFile());
        
        HBox HBOXWndwButtons = new HBox(30, ReadPatientsButton, ReadConditionsButton,SaveBTTNForMainWndw);
        HBOXWndwButtons.setAlignment(Pos.CENTER);
        HBOXWndwButtons.setPadding(new Insets(10));
        
        Button ReadTreatmentsButton = new Button(" Read Treatments");
        ReadTreatmentsButton.setPrefSize(200, 60);
        ReadTreatmentsButton.setStyle("-fx-background-color: #90EE90; -fx-text-fill: white; -fx-font-weight: bold;");
        ReadTreatmentsButton.setOnAction(e -> ReadTreatmentsFromchooseeFile());
        
        Button ReadMedicationsButton = new Button(" Read Medications");
        ReadMedicationsButton.setPrefSize(200, 60);
        ReadMedicationsButton.setStyle("-fx-background-color: #EC7063; -fx-text-fill: white; -fx-font-weight: bold;");
        ReadMedicationsButton.setOnAction(e -> ReadMedicationsFromFile());
        
        HBox HBOXWndw2Buttons = new HBox(30, ReadTreatmentsButton, ReadMedicationsButton);
        HBOXWndw2Buttons.setAlignment(Pos.CENTER);
        HBOXWndw2Buttons.setPadding(new Insets(10));
        
        VBox centerVBX = new VBox(20);
        centerVBX.getChildren().addAll(titleBoxForMainWindw, HBOXWndwButtons, HBOXWndw2Buttons);
        centerVBX.setAlignment(Pos.CENTER);
        centerVBX.setPadding(new Insets(50));
        
        BorderPane BorderPaneForMainWndw = new BorderPane();
        BorderPaneForMainWndw.setTop(menuBarForMainWindow);
        BorderPaneForMainWndw.setCenter(centerVBX);
        BorderPaneForMainWndw.setStyle("-fx-background-color: #2C3E50;");
        
        Scene scenForMainWndoow = new Scene(BorderPaneForMainWndw, 800, 600);
        steege.setScene(scenForMainWndoow);
        steege.setTitle("Medical Patient Monitoring System");
    }
    
//-------------------------------------------------------Methodss--------------------------------------------------------------------
    
    private void ReadPatientsFromChooseFile() {
        FileChooser filForPatient = new FileChooser();
        filForPatient.setTitle("Open Patients File");
        filForPatient.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File choceFromMyCompouer = filForPatient.showOpenDialog(steege);
        if (choceFromMyCompouer != null) {
            try {
                patientList.RemoveAll();

                Scanner scannarForChoseFile = new Scanner(choceFromMyCompouer);

                while (scannarForChoseFile.hasNextLine()) {
                    String lineForFileTXT = scannarForChoseFile.nextLine();
                    String[] partSForTXT = lineForFileTXT.split(",");
                    if (partSForTXT.length == 4) {
                        try {
                            int patientIdparseInt = Integer.parseInt(partSForTXT[0].trim());
                            int ageparseInt = Integer.parseInt(partSForTXT[2].trim());

                            if (patientIdparseInt <= 0 || ageparseInt <= 0) {
                                showAlert("Error", "Invalid Patient ID or Age in file!!!!!!!S");
                                continue;
                            }

                            patientList.addLast(new PatientNode( patientIdparseInt,partSForTXT[1].trim(), ageparseInt, partSForTXT[3].trim() 
                            ));
                        } catch (NumberFormatException eForChoseFile) {
                            showAlert("Error", "Invalid number in file!!!!!.");
                        }
                    }
                }

                scannarForChoseFile.close();

                ArrayList<PatientNode> patentDataToLinkdList = new ArrayList<>();
                PatientNode current = (PatientNode) patientList.Front;
                while (current != null) {
                	patentDataToLinkdList.add(current);
                    current = current.next;
                }

                // ابديت للتيبل
                TableViewPatient.patientList.setAll(patentDataToLinkdList);
                TableViewPatient viewPatientsWindow = new TableViewPatient(patentDataToLinkdList);
                viewPatientsWindow.show();

                showAlert("Success", "Patients loaded successfully!");
            } catch (IOException e) {
                showAlert("Error", "Cannot read file!");
            }
        }
    }
    
    
    private void ReadConditionsChoosFromFile() {
        FileChooser filForCondton = new FileChooser();
        filForCondton.setTitle("Open Conditions File");
        filForCondton.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File fileChooserInDesktop = filForCondton.showOpenDialog(steege);

        if (fileChooserInDesktop != null) {
            try {
            	ConditionManagement.conditionList.RemoveAll();

                Scanner scannarForfilForCondton = new Scanner(fileChooserInDesktop);

                while (scannarForfilForCondton.hasNextLine()) {
                    String lineForCondton = scannarForfilForCondton.nextLine();
                    String[] partsForCondton = lineForCondton.split("\\|"); // استخدم | كفاصل

                    if (partsForCondton.length == 4) { // صار عندنا 4 أعمدة
                        String conditionName = partsForCondton[0].trim();
                        String treatmentsStr = partsForCondton[1].trim();
                        String medicationsStr = partsForCondton[2].trim();
                        String symptomsStr = partsForCondton[3].trim();

                        ConditionNode conditonForFile = new ConditionNode(conditionName);

                        // بضيف العلااجات
                        if (!treatmentsStr.isEmpty()) {
                            String[] treatmentsForFil = treatmentsStr.split(",");
                            for (String treatmntSt : treatmentsForFil) {
                            	conditonForFile.recommendedTreatments.addLast(new TreatmentNode(treatmntSt.trim()));
                            }
                        }

                        // بضيف الادووية
                        if (!medicationsStr.isEmpty()) {
                            String[] medicationsString = medicationsStr.split(",");
                            for (String medicateonST : medicationsString) {
                            	conditonForFile.recommendedMedications.addLast(new MedicationNode(medicateonST.trim()));
                            }
                        }

                        // بضيف الاعراضض
                        if (!symptomsStr.isEmpty()) {
                            String[] symptmSt = symptomsStr.split(",");
                            for (String symptmS : symptmSt) {
                            	conditonForFile.symptoms.addLast(new SymptomNode(symptmS.trim()));
                            }
                        }

                        ConditionManagement.conditionList.addLast(conditonForFile);
                    }
                }


                scannarForfilForCondton.close();

                TableViewCondition viewConditionsWindow = new TableViewCondition(null);
                viewConditionsWindow.show();
                viewConditionsWindow.updateTable();


                showAlert("Success", "Conditions Reade succesfully!");

            } catch (IOException excpForChooseFile) {
                showAlert("Error", "Cannot read file!");
            }
        }
    }
    
    
    private void ReadTreatmentsFromchooseeFile() {
        FileChooser fileChooserForTRETMNT = new FileChooser();
        fileChooserForTRETMNT.setTitle("Open Treatments File");
        fileChooserForTRETMNT.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File fileChooserFromMyComoputr = fileChooserForTRETMNT.showOpenDialog(steege);

        if (fileChooserFromMyComoputr != null) {
            try {
                // بشيل العلاجات السابقةة
                TableViewTreatment.treatmentManagement.treatmentList.RemoveAll();

                Scanner scannarFoRtrtmntFile = new Scanner(fileChooserFromMyComoputr);

             // بتجاهل صف العنوان
             if (scannarFoRtrtmntFile.hasNextLine()) {
            	 scannarFoRtrtmntFile.nextLine(); // يتجاهل أول صف الفرونت 
             }

             while (scannarFoRtrtmntFile.hasNextLine()) {
                 String lineTreatmnt = scannarFoRtrtmntFile.nextLine();
                 String[] partsForTrtmnt = lineTreatmnt.split(",");
                 if (partsForTrtmnt.length == 5) {
                     try {
                         String treatmentIDStForFile = partsForTrtmnt[0].trim().replace("T","");
                         int treatmentIDparseInt = Integer.parseInt(treatmentIDStForFile);
                         int patientIDparseInt = Integer.parseInt(partsForTrtmnt[1].trim());
                         String type = partsForTrtmnt[2].trim();
                         String startDate = partsForTrtmnt[3].trim();
                         String endDate = partsForTrtmnt[4].trim();

                         TreatmentNode treatment = new TreatmentNode(type);
                         treatment.setTreatmentId(treatmentIDparseInt);
                         treatment.setPatientID(patientIDparseInt);
                         treatment.setStartDate(startDate);
                         treatment.setEndDate(endDate);

                         TableViewTreatment.treatmentManagement.addTreatment(treatment);
                     } catch (NumberFormatException expForFile) {
                         showAlert("Error", "Invalid number format in file!");
                     }
                 }
             }
             scannarFoRtrtmntFile.close();


                TableViewTreatment viewTRTMNT = new TableViewTreatment(null);
                viewTRTMNT.show();
                viewTRTMNT.updateTable();

            } catch (Exception expForFILE) {
            	expForFILE.printStackTrace();
                showAlert("Error", "Failed to read treatments from file!");
            }
        }
    }

    
    private void ReadMedicationsFromFile() {
        FileChooser fileChoserMedcatin = new FileChooser();
        fileChoserMedcatin.setTitle("Open Medications File");
        fileChoserMedcatin.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File fileChooserInDesktop = fileChoserMedcatin.showOpenDialog(steege);

        if (fileChooserInDesktop != null) {
            try {
                TableViewMedication.medicationManagement.medicationList.RemoveAll();

                Scanner scannarForMedcatin = new Scanner(fileChooserInDesktop);

                if (scannarForMedcatin.hasNextLine()) {
                	scannarForMedcatin.nextLine();   
                }

                while (scannarForMedcatin.hasNextLine()) {
                    String line = scannarForMedcatin.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        try {
                            int medicatonIDparseInt = Integer.parseInt(parts[0].trim());
                            int patintIDparseInt = Integer.parseInt(parts[1].trim());
                            String nameMedcatin = parts[2].trim();
                            String dosageMedcatin = parts[3].trim();
                            String frequencyMedcatin = parts[4].trim();

                            MedicationNode medication = new MedicationNode(nameMedcatin);
                            medication.setMedicationId(medicatonIDparseInt);
                            medication.setPatientID(patintIDparseInt);
                            medication.setDosage(dosageMedcatin);
                            medication.setFrequency(frequencyMedcatin);

                            TableViewMedication.medicationManagement.addMedication(medication);
                        } catch (NumberFormatException expForFile) {
                            showAlert("Error", "Invalid number format in file!");
                        }
                    }
                }

                scannarForMedcatin.close();

                TableViewMedication viewMedicatonsWndw = new TableViewMedication(null);
                viewMedicatonsWndw.show();
                viewMedicatonsWndw.updateTable();

                showAlert("Success", "Medications loaded successfully!");

            } catch (Exception expForFilee) {
            	expForFilee.printStackTrace();
                showAlert("Error", "Failed to read medications from file!");
            }
        }
    }


 
    private void showAlert(String titleOfManWndw, String messageOfManWndw) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleOfManWndw);
        alert.setHeaderText(null);
        alert.setContentText(messageOfManWndw);
        alert.showAndWait();
    }
    
    
    public void show() {
        steege.show();
    }
}