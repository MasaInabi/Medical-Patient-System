package application;

 //Masa Ahmad 1231024

public class TreatmentManagement {
public DoublyLinkedList treatmentList;

public TreatmentManagement() {
  treatmentList = new DoublyLinkedList();
}

//بضيفف علاج
public void addTreatment(TreatmentNode treatment) {
  treatmentList.addLast(treatment);
}
public DoublyLinkedList getAllTreatments() {
	    return treatmentList;
	}

//حذف علاج
public boolean deleteTreatment(int treatmentId) {
  TreatmentNode curentdeleteTretment = (TreatmentNode) treatmentList.Front;
  while (curentdeleteTretment != null) {
      if (curentdeleteTretment.patientID == treatmentId) {
          if (curentdeleteTretment.prev != null)
         	 curentdeleteTretment.prev.next = curentdeleteTretment.next;
          else
              treatmentList.Front = curentdeleteTretment.next;

          if (curentdeleteTretment.next != null)
         	 curentdeleteTretment.next.prev = curentdeleteTretment.prev;
          else
              treatmentList.Back = curentdeleteTretment.prev;

          treatmentList.size--;
          return true;
      }
      curentdeleteTretment = curentdeleteTretment.next;
  }
  return false;
}

public boolean updateTreatment(int treatmentId, String newType, String newStart, String newEnd) {
	    TreatmentNode current = (TreatmentNode) treatmentList.Front;

	    while (current != null) {
	        if (current.getTreatmentId() == treatmentId) {
	            current.setType(newType);
	            current.setStartDate(newStart);
	            current.setEndDate(newEnd);
	            return true;
	        }
	        current = current.next;
	    }

	    return false;
	}

}
