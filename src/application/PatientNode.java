package application;

	//Masa Ahmad 1231024


	public class PatientNode {
	public int patientId;
	public String name;
	public int age;
	public String condition;
	public PatientNode next; // pointer next node
	public PatientNode prev; // pointer prev

	public DoublyLinkedList treatments;  
	public DoublyLinkedList medications; 
	public DoublyLinkedList symptoms; 

	public PatientNode(int patientId, String name, int age, String condition) {
		this.patientId = patientId;
		this.name = name;
		this.age = age;
		this.condition = condition;
		this.next = null;
		this.prev = null;
		 this.treatments = new DoublyLinkedList();
	   this.medications = new DoublyLinkedList();
	   this.symptoms = new DoublyLinkedList(); 

		
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}



	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public DoublyLinkedList getTreatments() {
		return treatments;
	}

	public void setTreatments(DoublyLinkedList treatments) {
		this.treatments = treatments;
	}

	public DoublyLinkedList getMedications() {
		return medications;
	}

	public void setMedications(DoublyLinkedList medications) {
		this.medications = medications;
	}

	public DoublyLinkedList getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(DoublyLinkedList symptoms) {
		this.symptoms = symptoms;
	}







	}
	 

