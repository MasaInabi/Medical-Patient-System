package application; 
import java.util.ArrayList; 

//Masa Ahmad 1231024
public class PatientManagement { 
    public DoublyLinkedList patientList; 

    public PatientManagement() { 
        patientList = new DoublyLinkedList(); 
    } 

    // ----------------------------------------------------------METhoD----------------------------------------------------------------- 
    public void addPatient(PatientNode newPatent) { 
        patientList.addLast(newPatent); 
    } 

    public boolean updatePatient(int patientId, String newName, int newAge, String newCondition) { 
        PatientNode currentupdtePatent = (PatientNode) patientList.Front; 
        while (currentupdtePatent != null) { 
            if (currentupdtePatent.getPatientId() == patientId) { 
                currentupdtePatent.setName(newName); 
                currentupdtePatent.setAge(newAge); 
                currentupdtePatent.setCondition(newCondition); 
                return true; 
            } 
            currentupdtePatent = currentupdtePatent.next; 
        } 
        return false; 
    } 

    public boolean deletePatientt(int patientId) { 
        if (patientList.isEmpty()) { 
            System.out.println("Treatment of this Patient ID is deleted from table view Treatment"); 
            System.out.println(" Medication of this Patient ID is deleted from table view Medication"); 
            System.out.println("Condition of this Patient ID is deleted from table view Condition"); 

            return false; 
          } 
        PatientNode currentdeltePatentt = (PatientNode) patientList.Front; 
        while (currentdeltePatentt != null) { 
            System.out.println(" ID: " + currentdeltePatentt.getPatientId() + ", Name: " + currentdeltePatentt.getName()); 
            currentdeltePatentt = currentdeltePatentt.next; 
      } 
        currentdeltePatentt = (PatientNode) patientList.Front; 
        while (currentdeltePatentt != null) { 
            System.out.println(" ID=" + currentdeltePatentt.getPatientId()); 
            if (currentdeltePatentt.getPatientId() == patientId) { 
                System.out.println("patint" + patientId ); 
                if (currentdeltePatentt == patientList.Front) { 
                    patientList.removeFirst(); 
                } else if (currentdeltePatentt == patientList.Back) { 
                    patientList.removeLast(); 
                } else { 
                    currentdeltePatentt.prev.next = currentdeltePatentt.next; 
                    currentdeltePatentt.next.prev = currentdeltePatentt.prev; 
                    patientList.size--; 
                } 
                System.out.println("delete sucsess!!!!!!!!!!!"); 
                return true; 
            } 
            currentdeltePatentt = currentdeltePatentt.next; 
        } 
        System.out.println("not found patient of this id = " + patientId); 
        return false; 
    } 

    public PatientNode searchPatient(int patientId) { 
        PatientNode currentserchPatent = (PatientNode) patientList.Front; 
        while (currentserchPatent != null) { 
            if (currentserchPatent.getPatientId() == patientId) { 
                return currentserchPatent; 
            } 
            currentserchPatent = currentserchPatent.next; 
        } 
        return null; 
    } 

   

    public void printAllPatients() { 
        PatientNode currentprntAlPatents = (PatientNode) patientList.Front; 
        while (currentprntAlPatents != null) { 
            System.out.println("Patient:" + currentprntAlPatents.getName() + " , Condition:" + currentprntAlPatents.getCondition()); 
            currentprntAlPatents = currentprntAlPatents.next; 
        } 
    } 
}



