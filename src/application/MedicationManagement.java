package application;

//Masa Ahmad 1231024

public class MedicationManagement {
public DoublyLinkedList medicationList;

public MedicationManagement() {
  medicationList = new DoublyLinkedList();
}

//-------------------------------------------------------------------METHODSS------------------------------------------------------------s
public DoublyLinkedList getAllMedications() {
	    return medicationList;
	}

public void addMedication(MedicationNode medicaton) {
  medicationList.addLast(medicaton);
}

public boolean updateMedication(int medicationId, String newName, String newDosage, String newFrequency) {
  MedicationNode currentMedicatonNod = (MedicationNode) medicationList.Front;
  while (currentMedicatonNod != null) {
      if (currentMedicatonNod.medicationId == medicationId) { 
     	 currentMedicatonNod.nameMedication = newName;
     	 currentMedicatonNod.dosage = newDosage;
     	 currentMedicatonNod.frequency = newFrequency;
          return true;
      }
      currentMedicatonNod = currentMedicatonNod.next;
  }
  return false;
}

public boolean deleteMedicationsByPatientID(int patientID) {
	    boolean deletedAny = false;
	    MedicationNode current = (MedicationNode) medicationList.Front;
	    while (current != null) {
	        MedicationNode next = current.next;
	        if (current.getPatientID() == patientID) {
	            if (current.prev != null)
	                current.prev.next = current.next;
	            else
	                medicationList.Front = current.next;

	            if (current.next != null)
	                current.next.prev = current.prev;
	            else
	                medicationList.Back = current.prev;

	            medicationList.size--;
	            deletedAny = true;
	        }
	        current = next;
	    }
	    return deletedAny;
	}



}
