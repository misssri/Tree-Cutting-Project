public class user 
{
		protected String Password;
	 	protected String Email;
	    protected String FirstName;
	    protected String LastName;
	    protected String address_street_num;
	    protected String address_street;
	    protected String address_city;
	    protected String address_state;
	    protected String address_zip_code;
	    protected String Role;
	    protected String PhoneNumber;
	    protected String CreditCardInfo;
	 
	    //constructors
	    public user() {
	    }
	 
	    public user(String Email) 
	    {
	        this.Email = Email;
	    }
	    
	    public user(String Email,String FirstName, String LastName, String Password, String address_street_num, String address_street, String address_city, String address_state,String address_zip_code, String Role,String PhoneNumber,  String CreditCardInfo) 
	    {
	    	this(FirstName,LastName,Password, address_street_num,  address_street,  address_city,  address_state,  address_zip_code,Role,PhoneNumber,CreditCardInfo);
	    	this.Email = Email;
	    }
	 
	
	    public user(String FirstName, String LastName, String Password, String address_street_num, String address_street, String address_city, String address_state,String address_zip_code,String Role, String PhoneNumber,  String CreditCardInfo) 
	    {
	    	
	    	this.FirstName = FirstName;
	    	this.LastName = LastName;
	    	this.Password = Password;
	        
	        this.address_street_num = address_street_num;
	        this.address_street = address_street;
	        this.address_city= address_city;
	        this.address_state = address_state;
	        this.address_zip_code = address_zip_code;
	        this.Role = Role;
	        this.PhoneNumber = PhoneNumber;
	        this.CreditCardInfo = CreditCardInfo;
	    }
	    
	   //getter and setter methods
	    public String getEmail() {
	        return Email;
	    }
	    public void setEmail(String Email) {
	        this.Email = Email;
	    }
	    
	    public String getFirstName() {
	        return FirstName;
	    }
	    public void setFirstName(String FirstName) {
	        this.FirstName = FirstName;
	    }
	    
	    public String getLastName() {
	        return LastName;
	    }
	    public void setLastName(String LastName) {
	        this.LastName = LastName;
	    }
	    
	    public String getPassword() {
	        return Password;
	    }
	    public void setPassword(String Password) {
	        this.Password = Password;
	    }
	  
	    
	    
	    public String getAddress_street_num() {
	        return address_street_num;
	    }
	    public void setAddress_street_num(String address_street_num) {
	        this.address_street_num = address_street_num;
	    }
	    public String getAddress_street() {
	        return address_street;
	    }
	    public void setAddress_street(String address_street) {
	        this.address_street = address_street;
	    }
	    public String getAddress_city() {
	        return address_city;
	    }
	    public void setAddress_city(String address_city) {
	        this.address_city = address_city;
	    }
	    public String getAddress_state() {
	        return address_state;
	    }
	    public void setAddress_state(String address_state) {
	        this.address_state = address_state;
	    }
	    public String getAddress_zip_code() {
	        return address_zip_code;
	    }
	    public void setAddress_zip_code(String address_zip_code) {
	        this.address_zip_code = address_zip_code;
	    }
	    public String getPhoneNumber() {
	    	return PhoneNumber;
	    }
	    public void setPhoneNumber(String PhoneNumber) {
	    	this.PhoneNumber = PhoneNumber;
	    }
	    public String getCreditCardInfo() {
	        return CreditCardInfo;
	    }
	    public void setCreditCardInfo(String CreditCardInfo) {
	        this.CreditCardInfo = CreditCardInfo;
	    }
	    public String getRole() {
	        return Role;
	    }
	    public void setRole(String Role) {
	        this.Role = Role;
	    }
	}