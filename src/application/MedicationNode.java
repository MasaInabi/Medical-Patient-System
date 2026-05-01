package application;

//Masa Ahmad 1231024

public class MedicationNode {
	   public int medicationId;    
	    public int patientID;       
	    public String nameMedication;       
	    public String dosage;      
	    public String frequency;
	    public MedicationNode next; 
	    public MedicationNode prev; 
	    
		public MedicationNode(String nameMedication) {
			this.medicationId = medicationId;
	        this.patientID = patientID;
			this.nameMedication = nameMedication;
			this.dosage = dosage;
			this.frequency = frequency;
			this.next = null;
			this.prev = null;
		}

		public int getMedicationId() {
			return medicationId;
		}

		public void setMedicationId(int medicationId) {
			this.medicationId = medicationId;
		}

		public int getPatientID() {
			return patientID;
		}

		public void setPatientID(int patientID) {
			this.patientID = patientID;
		}

		public String getNameMedication() {
			return nameMedication;
		}

		public void setNameMedication(String nameMedication) {
			this.nameMedication = nameMedication;
		}

		public String getDosage() {
			return dosage;
		}

		public void setDosage(String dosage) {
			this.dosage = dosage;
		}

		public String getFrequency() {
			return frequency;
		}

		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}

		@Override
		public String toString() {
			return "MedicationNode [medicationId=" + medicationId + ", patientID=" + patientID + ", nameMedication="
					+ nameMedication + ", dosage=" + dosage + ", frequency=" + frequency + ", next=" + next + ", prev="
					+ prev + "]";
		}

	    
	    
}