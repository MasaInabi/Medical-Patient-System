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

public class TableViewCondition {
	private PatientManagement patient;

	public static ConditionManagement conditionManagement = new ConditionManagement();

	Stage stagee;
	static ObservableList<ConditionNode> conditionList = FXCollections.observableArrayList();
	public static TableView<ConditionNode> tableView;

	public TableViewCondition() {
		patient = new PatientManagement();

	}

	public TableViewCondition(ConditionNode firstCondition) {
		stagee = new Stage();

		Label titleConditionManagementLabel = new Label("Condition Management");
		titleConditionManagementLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

		Label DeltebyNameLabel = new Label("Delete by Name:");
		DeltebyNameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

		TextField DeletebyNameTXTField = new TextField();
		DeletebyNameTXTField.setPromptText("Condition Name");

		Button addConditonBotton = new Button("Add Condition");
		Button deletConditonBotton = new Button("Delete");
		Button SortByConditionName = new Button("Sort By Condition Name");
		Button backBoton = new Button("Back");
		Button editConditionButton = new Button("Edit");

		HBox buttonBox = new HBox(10, addConditonBotton, DeltebyNameLabel, DeletebyNameTXTField, deletConditonBotton,
				SortByConditionName, backBoton, editConditionButton);
		buttonBox.setPadding(new Insets(10));
		buttonBox.setStyle("-fx-background-color: #2C3E50; -fx-border-color: white; -fx-border-width: 1;");

		tableView = new TableView<>();

		TableColumn<ConditionNode, String> conditionNameColumn = new TableColumn<>("Condition Name");
		conditionNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().nameCondition));

		TableColumn<ConditionNode, String> treatmentsColumn = new TableColumn<>("Recommended Treatments");
		treatmentsColumn.setCellValueFactory(c -> new SimpleStringProperty(getTreatmntsStrng(c.getValue())));

		TableColumn<ConditionNode, String> medicationsColumn = new TableColumn<>("Recommended Medications");
		medicationsColumn.setCellValueFactory(c -> new SimpleStringProperty(getMedicationsString(c.getValue())));

		TableColumn<ConditionNode, String> symptomsColumn = new TableColumn<>("Symptoms");
		symptomsColumn.setCellValueFactory(c -> new SimpleStringProperty(getSymptomsStrng(c.getValue())));

		tableView.getColumns().addAll(conditionNameColumn, treatmentsColumn, medicationsColumn, symptomsColumn);

		tableView.setItems(conditionList);

		// ========================================= Action of Buttons
		// =======================================

		addConditonBotton.setOnAction(e -> {
			Stage addStage = new Stage();
			addStage.setTitle("Add Condition");

			Label ConditionNameToaddStageLabl = new Label("Condition Name:");
			ConditionNameToaddStageLabl.setStyle("-fx-text-fill: white;");

			TextField ConditionNameToaddStageTXTField = new TextField();

			Label RecomendedTratmentsToaddStageLabl = new Label("Recommended Treatments:");
			RecomendedTratmentsToaddStageLabl.setStyle("-fx-text-fill: white;");

			TextField RecomendedTratmentsToaddStageTXTFeld = new TextField();

			Label RecomendedMedcatonsToaddStageLabl = new Label("Recommended Medications :");
			RecomendedMedcatonsToaddStageLabl.setStyle("-fx-text-fill: white;");

			TextField RecomendedMedcatonsToaddStageTXTFeld = new TextField();

			Button addBotonToaddStage = new Button("Add");
			addBotonToaddStage.setStyle("-fx-background-color: #2C3E50; -fx-text-fill: white;");

			addBotonToaddStage.setOnAction(ee -> {
				String condtionNameToaddStage = ConditionNameToaddStageTXTField.getText().trim();
				String treatmentsTXTToaddStage = RecomendedTratmentsToaddStageTXTFeld.getText().trim();
				String medictiontreatmentsTXTToaddStage = RecomendedMedcatonsToaddStageTXTFeld.getText().trim();

				if (condtionNameToaddStage.isEmpty() || treatmentsTXTToaddStage.isEmpty()
						|| medictiontreatmentsTXTToaddStage.isEmpty()) {
					showAlert("Error", "Plz fill in all felds!!!");
					return;
				}

				// بتحقق اذا الاسم مكرر
				ConditionNode currentCondtionNde = (ConditionNode) ConditionManagement.conditionList.Front;
				while (currentCondtionNde != null) {
					if (currentCondtionNde.nameCondition.equalsIgnoreCase(condtionNameToaddStage)) {
						showAlert("Error", "Condition " + condtionNameToaddStage + " already exists!");
						return;
					}
					currentCondtionNde = currentCondtionNde.next;
				}

//بعمل كندشن جديد
				ConditionNode newCondtion = new ConditionNode(condtionNameToaddStage);

				for (String t : treatmentsTXTToaddStage.split(",")) {
					newCondtion.recommendedTreatments.addLast(new TreatmentNode(t.trim()));
				}
				for (String m : medictiontreatmentsTXTToaddStage.split(",")) {
					newCondtion.recommendedMedications.addLast(new MedicationNode(m.trim()));
				}

				// ببضيف الى اللنكدليست ـ
				ConditionManagement.conditionList.addLast(newCondtion);

				// بحدث الجدول
				updateTable();

				showAlert("Success", "Condition added success!!!");
				addStage.close();
			});

			VBox VBxToaddStage = new VBox(10, ConditionNameToaddStageLabl, ConditionNameToaddStageTXTField,
					RecomendedTratmentsToaddStageLabl, RecomendedTratmentsToaddStageTXTFeld,
					RecomendedMedcatonsToaddStageLabl, RecomendedMedcatonsToaddStageTXTFeld, addBotonToaddStage);
			VBxToaddStage.setPadding(new Insets(10));
			VBxToaddStage.setStyle("-fx-background-color: #2C3E50;");

			Scene sceneToaddStage = new Scene(VBxToaddStage, 400, 300);
			addStage.setScene(sceneToaddStage);
			addStage.show();
		});

		deletConditonBotton.setOnAction(e -> {
			String conditionNameOfdeletConditonBotton = DeletebyNameTXTField.getText().trim();

			if (conditionNameOfdeletConditonBotton.isEmpty()) {
				showAlert("Error!!", "Plz enter condition name!!!!");
				return;
			}

			// بحذف من اللنكدليست
			boolean removFromLinkedLst = TableViewCondition.conditionManagement
					.deleteConditionByName(conditionNameOfdeletConditonBotton);

			// بحذف من التيبل فيو
			for (int i = 0; i < conditionList.size(); i++) {
				if (conditionList.get(i).nameCondition.equalsIgnoreCase(conditionNameOfdeletConditonBotton)) {
					conditionList.remove(i);
					break;
				}
			}

			// بطلع النتيجة
			if (removFromLinkedLst) {
				showAlert("Success!!", "Condtion deleted succesfully!");
			} else {
				showAlert("Error!!!", "No conditon found with name: " + conditionNameOfdeletConditonBotton);
			}
		});

		SortByConditionName.setOnAction(e -> {
			FXCollections.sort(conditionList, new Comparator<ConditionNode>() {
				@Override
				public int compare(ConditionNode SortByConditionName1, ConditionNode SortByConditionName2) {
					return ReportComparatorClassToSort.SortConditionByName(SortByConditionName1, SortByConditionName2);
				}
			});
		});

		backBoton.setOnAction(e -> stagee.close());

		editConditionButton.setOnAction(e -> {
			new UpdateCondition(this);
		});

		VBox VBxTableViewCondition = new VBox(10, titleConditionManagementLabel, buttonBox, tableView);
		VBxTableViewCondition.setPadding(new Insets(10));
		VBxTableViewCondition.setStyle("-fx-background-color: #2C3E50;");

		Scene sceneTableViewCondition = new Scene(VBxTableViewCondition, 1000, 600);
		stagee.setScene(sceneTableViewCondition);
		stagee.setTitle("Condition Library");
		stagee.show();

// بحدث التيبل مباشرة من لنكدليست
		updateTable();
	}

	public static void updateTable() {
		if (tableView == null)
			return; // لو ما تم تهيئة الجدول بعد
		ObservableList<ConditionNode> conditionNodes = tableView.getItems();
		conditionNodes.clear();

		ConditionNode current = (ConditionNode) conditionManagement.conditionList.Front;
		while (current != null) {
			conditionNodes.add(current);
			current = current.next;
		}
	}

	private String getTreatmntsStrng(ConditionNode node) {
		StringBuilder getTreatmntsStrngOfCondition = new StringBuilder();
		TreatmentNode curentTreatmntNode = (TreatmentNode) node.recommendedTreatments.Front;

		while (curentTreatmntNode != null) {
			getTreatmntsStrngOfCondition.append(curentTreatmntNode.type);
			if (curentTreatmntNode.next != null)
				getTreatmntsStrngOfCondition.append(", ");
			curentTreatmntNode = curentTreatmntNode.next;
		}
		return getTreatmntsStrngOfCondition.toString();
	}

	private String getMedicationsString(ConditionNode node) {
		StringBuilder getMedcatonStrngOfCondition = new StringBuilder();
		MedicationNode curentMedicatonNode = (MedicationNode) node.recommendedMedications.Front;
		while (curentMedicatonNode != null) {
			getMedcatonStrngOfCondition.append(curentMedicatonNode.nameMedication);
			if (curentMedicatonNode.next != null)
				getMedcatonStrngOfCondition.append(", ");
			curentMedicatonNode = curentMedicatonNode.next;
		}
		return getMedcatonStrngOfCondition.toString();
	}

	private String getSymptomsStrng(ConditionNode node) {
		StringBuilder getSyptomsStrngOfConditon = new StringBuilder();
		SymptomNode currantSymptamNode = (SymptomNode) node.symptoms.Front;
		while (currantSymptamNode != null) {
			getSyptomsStrngOfConditon.append(currantSymptamNode.description);
			if (currantSymptamNode.next != null)
				getSyptomsStrngOfConditon.append(", ");
			currantSymptamNode = currantSymptamNode.next;
		}
		return getSyptomsStrngOfConditon.toString();
	}

	private void showAlert(String titleTableViewCondition, String messageTableViewCondition) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(titleTableViewCondition);
		alert.setHeaderText(null);
		alert.setContentText(messageTableViewCondition);
		alert.showAndWait();
	}

	public void show() {
		stagee.show();
	}
}
