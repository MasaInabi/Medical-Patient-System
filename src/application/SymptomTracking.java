package application;
 //  SymptomNode هون دمجت معو كلاس //////////////////////////////
//Masa Ahmad 1231024

public class SymptomTracking {

 public void addSymptom(PatientNode patient, SymptomNode symptom) {
     patient.symptoms.addLast(symptom);
 }

 public boolean removeSymptom(PatientNode patient, String description) {
     SymptomNode currentRemovSyptom = (SymptomNode) patient.symptoms.Front;
     while (currentRemovSyptom != null) {
         if (currentRemovSyptom.description.equalsIgnoreCase(description)) {
             if (currentRemovSyptom.prev != null)
            	 currentRemovSyptom.prev.next = currentRemovSyptom.next;
             else
                 patient.symptoms.Front = currentRemovSyptom.next;

             if (currentRemovSyptom.next != null)
            	 currentRemovSyptom.next.prev = currentRemovSyptom.prev;
             else
                 patient.symptoms.Back = currentRemovSyptom.prev;

             patient.symptoms.size--;
             return true;
         }
         currentRemovSyptom = currentRemovSyptom.next;
     }
     return false;
 }

 public void viewSymptomsOfPatent(PatientNode patient) {
     SymptomNode currentviewSymptomsOfPatent = (SymptomNode) patient.symptoms.Front;
     System.out.println("Symptoms for " + patient.getName() + ":");
     while (currentviewSymptomsOfPatent != null) {
         System.out.println( currentviewSymptomsOfPatent.description + " (" + currentviewSymptomsOfPatent.dateRecord + ")");
         currentviewSymptomsOfPatent = currentviewSymptomsOfPatent.next;
     }
 }
}



