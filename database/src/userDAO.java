import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/treecutting?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=Vishnupriya2");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from Client where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM Client";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            String role = resultSet.getString("role");
            String phonenumber = resultSet.getString("phonenumber");
            String creditcardnumber = resultSet.getString("creditcardnumber");
            
            

             
            user Client = new user(email,firstName, lastName, password,  adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code,role, phonenumber, creditcardnumber);
            listUser.add(Client);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into Client(email, firstName, lastName, password, birthday,adress_street_num, adress_street,adress_city,adress_state,adress_zip_code,cash_bal,PPS_bal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(6, users.getAdress_street_num());		
			preparedStatement.setString(7, users.getAdress_street());		
			preparedStatement.setString(8, users.getAdress_city());		
			preparedStatement.setString(9, users.getAdress_state());		
			preparedStatement.setString(10, users.getAdress_zip_code());
			preparedStatement.setString(5, users.getRole());
			preparedStatement.setString(11, users.getPhonenumber());		
			preparedStatement.setString(12, users.getCreditcardnumber());		

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM Client WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update Client set firstName=?, lastName =?,password = ?,adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?,role=?,phonenumber=?, creditcardnumber =? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		
		preparedStatement.setString(6, users.getAdress_street_num());		
		preparedStatement.setString(7, users.getAdress_street());		
		preparedStatement.setString(8, users.getAdress_city());		
		preparedStatement.setString(9, users.getAdress_state());		
		preparedStatement.setString(10, users.getAdress_zip_code());
		preparedStatement.setString(10, users.getRole());
		preparedStatement.setString(11, users.getPhonenumber());		
		preparedStatement.setString(12, users.getCreditcardnumber());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM Client WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            String role = resultSet.getString("role");
            String phonenumber = resultSet.getString("phonenumber");
            String creditcardnumber = resultSet.getString("creditcardnumber");
            user = new user(email, firstName, lastName, password, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code,role,phonenumber,creditcardnumber);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM Client WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM Client WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM Client";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists treecutting; ",
					        "create database treecutting; ",
					        "use treecutting; ",
					        "drop table if exists Client; ",
					        ("CREATE TABLE if not exists User( " +
					            "ClientID INT AUTO_INCREMENT," +
					            "Email VARCHAR(255) UNIQUE NOT NULL, " + 
					            "FirstName VARCHAR(255) NOT NULL, " +
					            "LastName VARCHAR(255) NOT NULL, " +
					            "Password VARCHAR(25), " +
					            "adress_street_num VARCHAR(4) , "+ 
					            "adress_street VARCHAR(30) , "+ 
					            "adress_city VARCHAR(20)," + 
					            "adress_state VARCHAR(2),"+ 
					            "adress_zip_code VARCHAR(5),"+ 
					            "Role VARCHAR(20)," +
					            "PhoneNumber VARCHAR(20) NOT NULL,"+ 
					            "CreditCardInfo VARCHAR(12),,"+
					            "PRIMARY KEY (ClientID) "+"); ")
        					};
        String[] TUPLES = {("INSERT INTO Client (FirstName, LastName, address_street_num, address_street, address_city, address_state, address_zip_code, CreditCardInfo, PhoneNumber, Email, Role, Password)"+
        			"values  ('root@gmail.com', 'Admin', 'User', 'pass1234', '1234', 'Admin St', 'Admin City', 'CA', '12345', 'Admin', '555-123-4567', '123456789012'),"+
			    		 	"('david@gmail.com', 'David', 'Smith', 'davidpassword', '5678', 'David St', 'David City', 'NY', '54321', 'client', '555-987-6543', '987654321012')," +
			    	 	 	"('john@gmail.com', 'John', 'Doe', 'password1', '1234', 'Main St', 'City1', 'CA', '12345', 'client', '555-123-4567', '123456789012'),"+
			    		 	"('jane@gmail.com', 'Jane', 'Smith', 'password2', '5678', 'Elm St', 'City2', 'NY', '54321', 'client', '555-987-6543', '987654321012'),"+
			    		 	"('bob@gmail.com', 'Bob', 'Johnson', 'password3', '4321', 'Oak St', 'City3', 'TX', '98765', 'client', '555-789-4567', '789456123012'),"+
			    		 	"('alice@gmail.com', 'Alice', 'Williams', 'password4', '8765', 'Cedar St', 'City4', 'CA', '65432', 'client', '555-321-7890', '321789654012'),"+
			    			"('mike@gmail.com', 'Mike', 'Brown', 'password5', '9876', 'Birch St', 'City5', 'NY', '76543', 'client', '555-654-1239', '654123987012'),"+
			    			"('sarah@gmail.com', 'Sarah', 'Davis', 'password6', '6543', 'Maple St', 'City6', 'TX', '87654', 'client', '555-987-3216', '987321654012'),"+
			    			" ('chris@gmail.com', 'Chris', 'Wilson', 'password7', '3456', 'Pine St', 'City7', 'CA', '56789', 'client', '555-456-9873', '456987321012'),"+
			    			"('emily@gmail.com', 'Emily', 'Jones', 'password8', '7890', 'Fir St', 'City8', 'NY', '43210', 'client', '555-123-4569', '123456987012'),"+
			    			"('lisa@gmail.com', 'Lisa', 'Miller', 'password9', '2109', 'Spruce St', 'City9', 'TX', '98701', 'client', '555-987-4563', '987456321012');")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
