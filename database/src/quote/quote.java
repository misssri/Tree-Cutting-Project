package quote;
public class quote {
	private int ClientID;
	protected String Note;
    protected String Size;
    protected String Height;
    protected String Location;
    protected String DistanceToHouse;
		    
		 
		    //constructors used
		    public quote() {
		    }
		 
		    public quote(String Note) 
		    {
		        this.Note = Note;
		    }

		    public quote(int ClientID,String Note,String Size,String Height,String Location,String DistanceToHouse) 
		    {
		    	this.ClientID = ClientID;
		    	 this.Note = Note;
			        this.Size = Size;
			        this.Height = Height;
			        this.Location = Location;
			        this.DistanceToHouse = DistanceToHouse;
		    }
		    
		    
		    public Integer getClientID() {
		        return ClientID;
		    }
		    public void setClientID(Integer ClientID) {
		        this.ClientID = ClientID;
		    }
		
		    

			   //getter and setter methods
			    public String getNote() {
			        return Note;
			    }
			    public void setNote(String Note) {
			        this.Note = Note;
			    }
			    public String getSize() {
			        return Size;
			    }
			    public void setSize(String Size) {
			        this.Size = Size;
			    }
			    public String getHeight() {
			        return Height;
			    }
			    public void setHeight(String Height) {
			        this.Height = Height;
			    }
			    public String getLocation() {
			        return Location;
			    }
			    public void setLocation(String Location) {
			        this.Location = Location;
			    }
			    public String getDistanceToHouse() {
			        return DistanceToHouse;
			    }
			    public void setDistanceToHouse(String DistanceToHouse) {
			        this.DistanceToHouse = DistanceToHouse;
			    }
		    
		   
}
