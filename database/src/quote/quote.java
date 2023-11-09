package quote;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
public class quote {
	private int ClientID;
	protected String Note;
    protected String Size;
    protected String Height;
    protected String Location;
    protected String DistanceToHouse;
    protected int LatestNegotiationID;
    protected byte[] Picture1;
    protected byte[] Picture2;
    protected byte[] Picture3;
   
		    //constructors used
		    public quote() {
		    }
		 
		    public quote(String Note) 
		    {
		        this.Note = Note;
		    }

		    public quote(int ClientID,String Note,String Size,String Height,String Location,String DistanceToHouse,byte[] Picture1, byte[] Picture2, byte[] Picture3,int LatestNegotiationID) throws IOException 
		    {
		    	this.ClientID = ClientID;
		    	 this.Note = Note;
			        this.Size = Size;
			        this.Height = Height;
			        this.Location = Location;
			        this.DistanceToHouse = DistanceToHouse;
			        this.Picture1 = Picture1;
			        this.Picture2 = Picture2;
			        this.Picture3 = Picture3;
			        this.LatestNegotiationID = LatestNegotiationID;
			        
			       
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
			    public Integer getLatestNegotiationID() {
			        return LatestNegotiationID;
			    }
			    public void setLatestNegotiationID(Integer LatestNegotiationID) {
			        this.LatestNegotiationID = LatestNegotiationID;
			    }
			    public byte[] getPicture1() {
			        return Picture1;
			    }

			    public void setPicture1(byte[] Picture1) {
			        this.Picture1 = Picture1;
			    }

			    public byte[] getPicture2() {
			        return Picture2;
			    }

			    public void setPicture2(byte[] Picture2) {
			        this.Picture2 = Picture2;
			    }

			    public byte[] getPicture3() {
			        return Picture3;
			    }

			    public void setPicture3(byte[] Picture3) {
			        this.Picture3 = Picture3;
			    }
		    
		   
}
