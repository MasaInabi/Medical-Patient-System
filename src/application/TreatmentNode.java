package application;

 //Masa Ahmad 1231024

public class TreatmentNode {
public int treatmentId;   
public int patientID;      
public String type;      
public String startDate; 
public String endDate;
public TreatmentNode next;
public TreatmentNode prev;
public TreatmentNode(String type) {
			this.treatmentId = treatmentId;
	        this.patientID = patientID;

			this.type = type;
			this.startDate = startDate;
			this.endDate = endDate;
			this.next = null;
			this.prev = null;
		}
		public int getTreatmentId() {
			return treatmentId;
		}
		public void setTreatmentId(int treatmentId) {
			this.treatmentId = treatmentId;
		}
		public int getPatientID() {
			return patientID;
		 }
		 public void setPatientID(int patientID) {
			this.patientID = patientID;
	     	}
		public String getType() {
	 		return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getStartDate() {
			return startDate;
		 }
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		      }
		public String getEndDate() {
			return endDate;
		 }
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		 }
		@Override
		public String toString() {
			return "TreatmentNode [treatmentId=" + treatmentId + ", patientID=" + patientID + ", type=" + type
					+ ", startDate=" + startDate + ", endDate=" + endDate + ", next=" + next + ", prev=" + prev + "]";
		}
		
}