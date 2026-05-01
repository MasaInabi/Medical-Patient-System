package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Comparator;

//Masa Ahmad 1231024 

public class TableViewPatient {
	private PatientManagement patient;
	public static PatientManagement patientManagement = new PatientManagement(); // الكلاس المسؤول
	Stage stage;
	static ObservableList<PatientNode> patientList = FXCollections.observableArrayList();
	public static TableView<PatientNode> tableView;

	public TableViewPatient() {
		patient=new PatientManagement();
	}

	public TableViewPatient(ArrayList<PatientNode> patients) {
		stage = new Stage();
		Label titleLabel = new Label("Patient Management");
		titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
		Label searchPatientbyNameLabel = new Label("Search by Name:");
		searchPatientbyNameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
		TextField searchPatientbyNameTXTField = new TextField();
		searchPatientbyNameTXTField.setPromptText("Enter patient name");
		Label deletePatentbyIDLabel = new Label("Delete by ID:");
		deletePatentbyIDLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
		TextField deletePatentbyIDTXTField = new TextField();
		deletePatentbyIDTXTField.setPromptText("Patient ID");
		Button addPatientButton = new Button("Add Patient");
		Button deletePatientButton = new Button("Delete");
		Button SortedascendingnamesButton = new Button("Sorted ascending name");
		Button backButton = new Button("Back");
		Button searchPatentButton = new Button("Search");
		Button editPatentButton = new Button("Edit");
		HBox buttonBox = new HBox(10, addPatientButton, searchPatientbyNameLabel, searchPatientbyNameTXTField,
				searchPatentButton, deletePatentbyIDLabel, deletePatentbyIDTXTField, deletePatientButton,
				SortedascendingnamesButton, backButton, editPatentButton);
		buttonBox.setPadding(new Insets(10));
		buttonBox.setStyle("-fx-background-color: #2C3E50; -fx-border-color: white; -fx-border-width: 1;");
		tableView = new TableView<>();
		TableColumn<PatientNode, String> patientIDColumn = new TableColumn<>("Patient ID");
		patientIDColumn.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().patientId)));
		TableColumn<PatientNode, String> nameOfPatentColumn = new TableColumn<>("Name");
		nameOfPatentColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().name));
		TableColumn<PatientNode, String> ageOfPatentColumn = new TableColumn<>("Age");
		ageOfPatentColumn.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().age)));
		TableColumn<PatientNode, String> conditionColumn = new TableColumn<>("Condition");
		conditionColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().condition));
		tableView.getColumns().addAll(patientIDColumn, nameOfPatentColumn, ageOfPatentColumn, conditionColumn);
		tableView.setItems(patientList);
		if (patients != null) {
			patientList.addAll(patients);
		}
		// ********************************************************* Action of Buton********************************************************************
		addPatientButton.setOnAction(e -> {
			AddPatient addPatient = new AddPatient(this);
			addPatient.show();
		});
		searchPatentButton.setOnAction(e -> {
			SearchPatient searchWindow = new SearchPatient(this);
			searchWindow.showWindow();
		});
		deletePatientButton.setOnAction(e -> {
			DeletePatient deletePatient = new DeletePatient(this);
			deletePatient.showWindowForPatint();
		});
		SortedascendingnamesButton.setOnAction(e -> {
			FXCollections.sort(patientList, new Comparator<PatientNode>() {
				@Override
				public int compare(PatientNode PatientNode1, PatientNode PatientNode2) {
					return ReportComparatorClassToSort.SortPatientByName(PatientNode1, PatientNode2);
				}
			});
		});
		backButton.setOnAction(e -> stage.close());
		
		editPatentButton.setOnAction(e -> {
			UpdatePatient editPatient = new UpdatePatient(this);
		});
	
		VBox layout = new VBox(10, titleLabel, buttonBox, tableView);
		layout.setPadding(new Insets(10));
		layout.setStyle("-fx-background-color: #2C3E50;");
		Scene scene = new Scene(layout, 1000, 600);
		stage.setScene(scene);
		stage.setTitle("Patient Library");
		stage.show();
	}

	private void showAlert(String string, String string2) {
	}

	// بحدث التيبل بعد مضيف واحدف
	public static void updateTable() {
		ObservableList<PatientNode> data = tableView.getItems();
		// بضيف بس المريضين الجداد الموجودين باللنكدليست وما نضافو قبللل
		PatientNode currentupdateTable = (PatientNode) patientManagement.patientList.Front;
		boolean PatentIsalreadyInTable;
		while (currentupdateTable != null) {
			PatentIsalreadyInTable = false;
			for (PatientNode p : data) {
				if (p.getPatientId() == currentupdateTable.getPatientId()) {
					PatentIsalreadyInTable = true;
					break;
				}
			}
			if (!PatentIsalreadyInTable) {
				data.add(currentupdateTable);
			}
			currentupdateTable = currentupdateTable.next;
		}
	   }

	public void show() {
		stage.show();
 }
}
