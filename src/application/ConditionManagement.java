package application;

//Masa Ahmad 1231024

public class ConditionManagement {
public static DoublyLinkedList conditionList = new DoublyLinkedList(); // ⚡ هنا

public ConditionManagement() {

}
//---------------------------------------------------METHDS---------------------------------------------------------------
 //  دضيف مريض جديد بالاخر
 public void addCondition(ConditionNode newConditionAdedByMe) {
    conditionList.addLast(newConditionAdedByMe);
}

// ميثود عشان احذف مرض
 public boolean deleteConditionsByPatientID(int patientID) {
	    boolean deleteCONDByPatientID;
	    deleteCONDByPatientID   = false;
	    ConditionNode currentCNDT = (ConditionNode) 
	    		conditionList.Front;
	    while (currentCNDT != null) {
	        ConditionNode next = currentCNDT.next;
	        if (currentCNDT.getPatientID() == patientID) {
	            if (currentCNDT.prev != null)
	            	currentCNDT.prev.next = currentCNDT.next;
	            else
	                conditionList.Front = currentCNDT.next;

	            if (currentCNDT.next != null)
	            	currentCNDT.next.prev = currentCNDT.prev;
	            else
	                conditionList.Back = currentCNDT.prev;

	            conditionList.size--;
	            deleteCONDByPatientID = true;
	        }
	        currentCNDT = next;
	    }
	    return deleteCONDByPatientID;
	}
 
 
 public boolean deleteConditionByName(String conditionName) {
	    boolean deletCNDNM ;
	    deletCNDNM  = false;
	    ConditionNode currentCNDNAM = (ConditionNode) conditionList.Front;
	    while (currentCNDNAM != null) {
	        ConditionNode next = currentCNDNAM.next;
	        if (currentCNDNAM.nameCondition.equalsIgnoreCase(conditionName)) {
	            if (currentCNDNAM.prev != null)
	            	currentCNDNAM.prev.next = currentCNDNAM.next;
	            else
	                conditionList.Front = currentCNDNAM.next;

	            if (currentCNDNAM.next != null)
	            	currentCNDNAM.next.prev = currentCNDNAM.prev;
	            else
	                conditionList.Back = currentCNDNAM.prev;

	            conditionList.size--;
	            deletCNDNM = true;
	        }
	        currentCNDNAM = next;
	    }
	    return deletCNDNM;
	}
 public boolean updateCondition(String oldName, String newName) {
	    ConditionNode current = (ConditionNode) conditionList.Front;

	    while (current != null) {
	        if (current.getNameCondition().equalsIgnoreCase(oldName)) {
	            current.setNameCondition(newName);
	            return true;
	        }
	        current = current.next;
	    }

	    return false;
	}

}