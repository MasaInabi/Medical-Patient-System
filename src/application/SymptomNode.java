package application;

 //Masa Ahmad 1231024

public class SymptomNode {
	
	  public String description;  
	    public String dateRecord;
	    public SymptomNode next;
	    public SymptomNode prev;
		public SymptomNode(String description) {
			this.description = description;
			this.dateRecord = dateRecord;
			this.next = null;
			this.prev = null;
}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getDateRecord() {
			return dateRecord;
		}
		public void setDateRecord(String dateRecord) {
			this.dateRecord = dateRecord;
		}
		
}
