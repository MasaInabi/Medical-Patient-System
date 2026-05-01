package application;


//Masa Ahmad 1231024


 public class ConditionNode {
public int 	PatientID;
public String nameCondition;
public ConditionNode next; // pointer next node
public ConditionNode prev; // pointer prev


public DoublyLinkedList recommendedTreatments;
public DoublyLinkedList recommendedMedications;
public DoublyLinkedList symptoms;

public ConditionNode(String nameCondition) {
	this.PatientID = PatientID;
this.nameCondition = nameCondition;
	this.next = null;
	this.prev = null;
	 this.recommendedTreatments = new DoublyLinkedList();
	 this.recommendedMedications = new DoublyLinkedList();
	 this.symptoms = new DoublyLinkedList();

}

public String getNameCondition() {
	return nameCondition;
}

public void setNameCondition(String nameCondition) {
	this.nameCondition = nameCondition;
}

public int getPatientID() {
	return PatientID;
}

public void setPatientID(int patientID) {
	PatientID = patientID;
}





}
