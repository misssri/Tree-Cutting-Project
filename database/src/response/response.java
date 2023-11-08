package response;

public class response {
	protected int RequestID;
	protected String Note;
    protected String TimeWindowSuggested;
    protected Double PriceSuggested;
    public response() {
    }
 
    public response(Integer RequestID) 
    {
        this.RequestID = RequestID;
    }
	public response(Integer RequestID,Double PriceSuggested,String TimeWindowSuggested,String Note) 
    {
		this.RequestID = RequestID;
	        this.Note = Note;
	        this.PriceSuggested = PriceSuggested;
	        this.TimeWindowSuggested = TimeWindowSuggested;
	       
    }
	public Integer getRequestID() {
        return RequestID;
    }
    public void setRequestID(Integer RequestID) {
        this.RequestID = RequestID;
    }
	public String getNote() {
        return Note;
    }
    public void setNote(String Note) {
        this.Note = Note;
    }
    public Double getPriceSuggested() {
        return PriceSuggested;
    }
    public void setPriceSuggested(Double PriceSuggested) {
        this.PriceSuggested = PriceSuggested;
    }
    public String getTimeWindowSuggested() {
        return TimeWindowSuggested;
    }
    public void setTimeWindowSuggested(String TimeWindowSuggested) {
        this.TimeWindowSuggested = TimeWindowSuggested;
    }

}
