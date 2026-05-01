package application;

//Masa Ahmad 1231024

public class DoublyLinkedList {
	public Object Front;
	public Object Back;
	int size;

	public DoublyLinkedList() {
		Front = Back = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;

	}

	public int size() {
		return size;

	}

// -----------------------------------------------------Methods-----------------------------------------------------------------------------

	public void addFirst(Object element) {
		if (isEmpty()) {
			Front = Back = element;
		} else {

			if (element instanceof PatientNode) {
				PatientNode newNodePatient = (PatientNode) element;
				PatientNode first = (PatientNode) Front;
				newNodePatient.next = first;
				first.prev = newNodePatient;
				Front = newNodePatient;
			} else if (element instanceof TreatmentNode) {
				TreatmentNode newNodeTreatmentForDoblList = (TreatmentNode) element;
				TreatmentNode first = (TreatmentNode) Front;
				newNodeTreatmentForDoblList.next = first;
				first.prev = newNodeTreatmentForDoblList;
				Front = newNodeTreatmentForDoblList;
			} else if (element instanceof MedicationNode) {
				MedicationNode newNodeMedeicationForDoblList = (MedicationNode) element;
				MedicationNode first = (MedicationNode) Front;
				newNodeMedeicationForDoblList.next = first;
				first.prev = newNodeMedeicationForDoblList;
				Front = newNodeMedeicationForDoblList;
			} else if (element instanceof ConditionNode) {
				ConditionNode newNodeConditonForDoblList = (ConditionNode) element;
				ConditionNode first = (ConditionNode) Front;
				newNodeConditonForDoblList.next = first;
				first.prev = newNodeConditonForDoblList;
				Front = newNodeConditonForDoblList;
			} else if (element instanceof SymptomNode) {
				SymptomNode newNodeSymptmForDoblList = (SymptomNode) element;
				SymptomNode first = (SymptomNode) Front;
				newNodeSymptmForDoblList.next = first;
				first.prev = newNodeSymptmForDoblList;
				Front = newNodeSymptmForDoblList;
			}
		}
		size++;
	}

	public void addLast(Object element) {
		if (isEmpty()) {
			Front = Back = element;
		} else {
			if (element instanceof PatientNode) {
				PatientNode newNodePatentForDoblList = (PatientNode) element;
				PatientNode last = (PatientNode) Back;
				last.next = newNodePatentForDoblList;
				newNodePatentForDoblList.prev = last;
				Back = newNodePatentForDoblList;
			} else if (element instanceof TreatmentNode) {
				TreatmentNode newNodeTreatmntForDoblLis = (TreatmentNode) element;
				TreatmentNode last = (TreatmentNode) Back;
				last.next = newNodeTreatmntForDoblLis;
				newNodeTreatmntForDoblLis.prev = last;
				Back = newNodeTreatmntForDoblLis;
			} else if (element instanceof MedicationNode) {
				MedicationNode newNodeMedicationForDoblLis = (MedicationNode) element;
				MedicationNode last = (MedicationNode) Back;
				last.next = newNodeMedicationForDoblLis;
				newNodeMedicationForDoblLis.prev = last;
				Back = newNodeMedicationForDoblLis;
			} else if (element instanceof ConditionNode) {
				ConditionNode newNodeConditonForDoblLis = (ConditionNode) element;
				ConditionNode last = (ConditionNode) Back;
				last.next = newNodeConditonForDoblLis;
				newNodeConditonForDoblLis.prev = last;
				Back = newNodeConditonForDoblLis;
			} else if (element instanceof SymptomNode) {
				SymptomNode newNodeSymptmForDoblLis = (SymptomNode) element;
				SymptomNode last = (SymptomNode) Back;
				last.next = newNodeSymptmForDoblLis;
				newNodeSymptmForDoblLis.prev = last;
				Back = newNodeSymptmForDoblLis;
			}
		}
		size++;
	}

	public boolean removeFirst() {
		if (isEmpty())
			return false;
		if (size == 1) {
			Front = Back = null;
		} else {
			if (Front instanceof PatientNode) {
				PatientNode PatientElementFirstForDoblLis = (PatientNode) Front;
				Front = PatientElementFirstForDoblLis.next;
				((PatientNode) Front).prev = null;
			} else if (Front instanceof TreatmentNode) {
				TreatmentNode TreatmentElementFirstForDoblLis = (TreatmentNode) Front;
				Front = TreatmentElementFirstForDoblLis.next;
				((TreatmentNode) Front).prev = null;
			} else if (Front instanceof MedicationNode) {
				MedicationNode MedicationElementFirstForDoblLis = (MedicationNode) Front;
				Front = MedicationElementFirstForDoblLis.next;
				((MedicationNode) Front).prev = null;
			} else if (Front instanceof ConditionNode) {
				ConditionNode ConditionElementFirstForDoblLis = (ConditionNode) Front;
				Front = ConditionElementFirstForDoblLis.next;
				((ConditionNode) Front).prev = null;
			} else if (Front instanceof SymptomNode) {
				SymptomNode SymptomElementFirstForDoblLis = (SymptomNode) Front;
				Front = SymptomElementFirstForDoblLis.next;
				((SymptomNode) Front).prev = null;
			}
		}
		size--;
		return true;
	}

	public boolean removeLast() {
		if (isEmpty())
			return false;
		if (size == 1) {
			Front = Back = null;
		} else {
			if (Back instanceof PatientNode) {
				PatientNode PatientElementLastForDoblLis = (PatientNode) Back;
				Back = PatientElementLastForDoblLis.prev;
				((PatientNode) Back).next = null;
			} else if (Back instanceof TreatmentNode) {
				TreatmentNode TreatmentElementLastForDoblLis = (TreatmentNode) Back;
				Back = TreatmentElementLastForDoblLis.prev;
				((TreatmentNode) Back).next = null;
			} else if (Back instanceof MedicationNode) {
				MedicationNode MedicationElementLastForDoblLis = (MedicationNode) Back;
				Back = MedicationElementLastForDoblLis.prev;
				((MedicationNode) Back).next = null;
			} else if (Back instanceof ConditionNode) {
				ConditionNode ConditionElementLastForDoblLis = (ConditionNode) Back;
				Back = ConditionElementLastForDoblLis.prev;
				((ConditionNode) Back).next = null;
			} else if (Back instanceof SymptomNode) {
				SymptomNode SymptomElementLastForDoblLis = (SymptomNode) Back;
				Back = SymptomElementLastForDoblLis.prev;
				((SymptomNode) Back).next = null;
			}
		}
		size--;
		return true;
	}

	public boolean removeById(int patientId) {
		if (isEmpty())
			return false;

		PatientNode currentPatientNoderemoveById = (PatientNode) Front;

		while (currentPatientNoderemoveById != null) {
			if (currentPatientNoderemoveById.getPatientId() == patientId) {

				// ازا كان اول عنصر
				if (currentPatientNoderemoveById == Front) {
					return removeFirst();
				}
				// ازا كان اخر عنصرر
				else if (currentPatientNoderemoveById == Back) {
					return removeLast();
				}
				// ازا كان بالنص
				else {
					currentPatientNoderemoveById.prev.next = currentPatientNoderemoveById.next;
					currentPatientNoderemoveById.next.prev = currentPatientNoderemoveById.prev;
					size--;
					return true;
				}
			}
			currentPatientNoderemoveById = currentPatientNoderemoveById.next;
		}
		return false;
	}

	public boolean remove(int index) {

		if (size == 0)
			return false;
		else if (index == 0)
			return removeFirst();
		else if (index == size - 1)
			return removeLast();
		else if (index > 0 && index < size - 1) {
			PatientNode currentPatientNode;
			currentPatientNode = (PatientNode) Front;
			MedicationNode currentMedicationNode;
			currentMedicationNode = (MedicationNode) Front;
			SymptomNode currentSymptomNode;
			currentSymptomNode = (SymptomNode) Front;
			TreatmentNode currentTreatmentNode;
			currentTreatmentNode = (TreatmentNode) Front;

			for (int i = 0; i < index - 1; i++)
				currentPatientNode = currentPatientNode.next;
			currentPatientNode.next = currentPatientNode.next.next;
			size--;
			return true;
		} else
			return false;
	}

	public void RemoveAll() {
		Front = Back = null;
		size = 0;
	}
}








  
