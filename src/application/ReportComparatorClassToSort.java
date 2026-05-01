package application;

 //Masa Ahmad 1231024

public class ReportComparatorClassToSort {

	// ميثود عشان اعمل سورت لاسم المريض  يرتب من A to z  SPBN
	 public static int SortPatientByName(PatientNode PatentNode1, PatientNode PatentNode2) {
	        return PatentNode1.getName().compareToIgnoreCase(PatentNode2.getName());
	    }
	 
	 
		// ميثود عشان اعمل سورت لاسم اكندشنن  يرتب من A to z  SCBM

		    public static int SortConditionByName(ConditionNode SortConditonByName1, ConditionNode SortConditonByName2) {
		        return SortConditonByName1.getNameCondition().compareToIgnoreCase(SortConditonByName2.getNameCondition());
		    }
		
			// ميثود عشان اعمل سورت لاسم الدوا  يرتب من A to z  SMBN

   public int SortMedicationByName(MedicationNode SortMedcatonByName1, MedicationNode SortMedcatonByName2) {
	       return SortMedcatonByName1.getNameMedication().compareToIgnoreCase(SortMedcatonByName2.getNameMedication());
		        }

	// ميثود عشان اعمل سورت لاسم التريتمنت  يرتب من A to z   STBNMB

	    public int sortTreatmentByNameButton(TreatmentNode sortTratmentByNameBoton1, TreatmentNode sortTratmentByNameBoton2) {
		        return sortTratmentByNameBoton1.getType().compareTo(sortTratmentByNameBoton2.getType());
	    }
	    
		// ميثود عشان اعمل سورت لاند ديت  يرتب من SBED

	    public int sortByEndDate(TreatmentNode sortByEndDat1, TreatmentNode sortByEndDat2) {
		     		        return sortByEndDat1.getEndDate().compareTo(sortByEndDat2.getEndDate());
	    }
	    
	    
		// ميثود عشان اعمل سورت لستارت ديت  يرتب من  SBSD
 
	    public int sortByStartDate(TreatmentNode sortByStrtDat1, TreatmentNode sortByStrtDat2) {
		        return sortByStrtDat1.getStartDate().compareTo(sortByStrtDat2.getStartDate());
		    }
		     			
		// ميثود عشان اعمل سورت لتايب التريتمنت  يرتب من A to z  TCBT

	    	 public int TreatmentComparatorByType(TreatmentNode t1, TreatmentNode t2) {
	    	     return t1.type.compareToIgnoreCase(t2.type);
	    	 }
	    	}
