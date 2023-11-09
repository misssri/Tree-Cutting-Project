import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import quote.*;
import response.*;

/**
 * Servlet implementation class Connect
 */

@WebServlet("/clientDAO")
public class clientDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private PreparedStatement preparedStatement1 = null;
	private PreparedStatement preparedStatement2 = null;
	private ResultSet resultSet = null;
	
	public clientDAO(){}
	
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
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/treecutting?allowPublicKeyRetrieval=true&useSSL=false&user=root&Password=Vishnupriya2");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String Email, String Password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from Client where Email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, Email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String Password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/treecutting?allowPublicKeyRetrieval=true&useSSL=false&user=" + username + "&Password=" + Password);
            System.out.println(connect);
        }
    }
    
    public List<client> listAllClients() throws SQLException {
        List<client> listClient = new ArrayList<client>(); 
        
        String sql = "SELECT * FROM Client";      
        connect_func(); 
        try {
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String Email = resultSet.getString("Email");
            String FirstName = resultSet.getString("FirstName");
            String LastName = resultSet.getString("LastName");
            String Password = resultSet.getString("Password");
            String address_street_num = resultSet.getString("address_street_num"); 
            String address_street = resultSet.getString("address_street"); 
            String address_city = resultSet.getString("address_city"); 
            String address_state = resultSet.getString("address_state"); 
            String address_zip_code = resultSet.getString("address_zip_code"); 
            String Role = resultSet.getString("Role");
            String PhoneNumber = resultSet.getString("PhoneNumber");
            String CreditCardInfo = resultSet.getString("CreditCardInfo");
            
            

             
            client Clients = new client(Email,FirstName, LastName, Password,  address_street_num,  address_street,  address_city,  address_state,  address_zip_code,Role, PhoneNumber, CreditCardInfo);
            listClient.add(Clients);
        }       
        
        resultSet.close();
        }
        catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        } finally {
        disconnect();}        
        return listClient;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(client clients) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into Client(Email, FirstName, LastName, Password, address_street_num, address_street,address_city,address_state,address_zip_code,Role,PhoneNumber,CreditCardInfo) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, clients.getEmail());
			preparedStatement.setString(2, clients.getFirstName());
			preparedStatement.setString(3, clients.getLastName());
			preparedStatement.setString(4, clients.getPassword());
			preparedStatement.setString(5, clients.getAddress_street_num());		
			preparedStatement.setString(6, clients.getAddress_street());		
			preparedStatement.setString(7, clients.getAddress_city());		
			preparedStatement.setString(8, clients.getAddress_state());		
			preparedStatement.setString(9, clients.getAddress_zip_code());
			preparedStatement.setString(10, clients.getRole());
			preparedStatement.setString(11, clients.getPhoneNumber());		
			preparedStatement.setString(12, clients.getCreditCardInfo());		

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void insertQuotes(quote quotes, int clientID) throws SQLException {
        connect_func("root", "pass1234");

        // Insert a record into the QuoteRequest table
        String insertQuoteRequestSQL = "INSERT INTO QuoteRequest (ClientID, Note,LatestNegotiationID) VALUES (?, ?,?)";
        preparedStatement1 = (PreparedStatement) connect.prepareStatement(insertQuoteRequestSQL);
        preparedStatement1.setInt(1, clientID);
        preparedStatement1.setString(2, quotes.getNote());
        preparedStatement1.setInt(3, quotes.getLatestNegotiationID());
        preparedStatement1.executeUpdate();

        // After this insert, the RequestID in QuoteRequest is auto-generated
        int lastInsertedRequestID = getLastInsertedRequestID();

        // Now, insert a record into the Tree table with the corresponding RequestID
        String insertTreeSQL = "INSERT INTO Tree (RequestID, Size, Height, Location, DistanceToHouse,Picture1, Picture2, Picture3) VALUES (?, ?, ?, ?, ?,?,?,?)";
        preparedStatement2 = (PreparedStatement) connect.prepareStatement(insertTreeSQL);
        preparedStatement2.setInt(1, lastInsertedRequestID);
        preparedStatement2.setString(2, quotes.getSize());
        preparedStatement2.setString(3, quotes.getHeight());
        preparedStatement2.setString(4, quotes.getLocation());
        preparedStatement2.setString(5, quotes.getDistanceToHouse());
        preparedStatement2.setBytes(6, quotes.getPicture1());
        preparedStatement2.setBytes(7, quotes.getPicture2());
        preparedStatement2.setBytes(8, quotes.getPicture3());
        preparedStatement2.executeUpdate();
        
        preparedStatement1.close();
        preparedStatement2.close();
    }

    public int getLastInsertedRequestID() throws SQLException {
        String sql = "SELECT LAST_INSERT_ID()";
        PreparedStatement preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int lastInsertID = resultSet.getInt(1);
        preparedStatement.close();
        return lastInsertID;
    }
    public int getClientID(String Email) throws SQLException {
        int ClientID = 0; // Initialize with a default value or error code

        String sql = "SELECT ClientID FROM Client WHERE Email = ?";
        connect_func(); // Your connection setup method
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, Email);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            ClientID = resultSet.getInt("ClientID");
        }

        return ClientID;
    }
    public void DavidInitialResponse(response responses) throws SQLException {
        connect_func("root", "Vishnupriya2");

        // Insert a record into the QuoteRequest table
        String insertResponseSQL = "INSERT INTO QuoteNegotiation (RequestID,PriceSuggested,TimeWindowSuggested, Note, NegotiationDate,NegotiatedBy) VALUES (?,?, ?, ?,NOW(),'David')";
        preparedStatement1 = (PreparedStatement) connect.prepareStatement(insertResponseSQL);
        preparedStatement1.setInt(1, responses.getRequestID());
        preparedStatement1.setDouble(2, responses.getPriceSuggested());
        preparedStatement1.setString(3, responses.getTimeWindowSuggested());
        preparedStatement1.setString(4, responses.getNote());
        preparedStatement1.executeUpdate();

        

        preparedStatement1.close();
        
    }
    public void ClientResponse(response responses) throws SQLException {
        connect_func("root", "Vishnupriya2");

        // Insert a record into the QuoteRequest table
        String insertResponseSQL = "INSERT INTO QuoteNegotiation (RequestID,PriceSuggested,TimeWindowSuggested, Note, NegotiationDate,NegotiatedBy) VALUES (?,?, ?, ?,NOW(),'Client')";
        preparedStatement1 = (PreparedStatement) connect.prepareStatement(insertResponseSQL);
        preparedStatement1.setInt(1, responses.getRequestID());
        preparedStatement1.setDouble(2, responses.getPriceSuggested());
        preparedStatement1.setString(3, responses.getTimeWindowSuggested());
        preparedStatement1.setString(4, responses.getNote());
        preparedStatement1.executeUpdate();

        

        preparedStatement1.close();
        
    }
    public boolean DavidAResponse(int RequestID) throws SQLException {
    	String sql = "update QuoteRequest set Status='Accepted' where RequestID = ?";
        connect_func();

        preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;   
    }
    public boolean DavidRResponse(int RequestID) throws SQLException {
    	String sql = "update QuoteRequest set Status='Rejected' where RequestID = ?";
        connect_func();

        preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;   
    }
    public boolean ClientAResponse(int RequestID) throws SQLException {
    	String sql = "update QuoteRequest set Status='Accepted' where RequestID = ?";
        connect_func();

        preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;   
    }
    public boolean ClientRResponse(int RequestID) throws SQLException {
    	String sql = "update QuoteRequest set Status='Rejected' where RequestID = ?";
        connect_func();

        preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;   
    }
   
    

    public boolean delete(String Email) throws SQLException {
        String sql = "DELETE FROM Client WHERE Email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, Email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(client clients) throws SQLException {
        String sql = "update Client set FirstName=?, LastName =?,Password = ?,address_street_num =?, address_street=?,address_city=?,address_state=?,address_zip_code=?,role=?,PhoneNumber=?, CreditcardInfo =? where Email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, clients.getEmail());
		preparedStatement.setString(2, clients.getFirstName());
		preparedStatement.setString(3, clients.getLastName());
		preparedStatement.setString(4, clients.getPassword());
		
		preparedStatement.setString(5, clients.getAddress_street_num());		
		preparedStatement.setString(6, clients.getAddress_street());		
		preparedStatement.setString(7, clients.getAddress_city());		
		preparedStatement.setString(8, clients.getAddress_state());		
		preparedStatement.setString(9, clients.getAddress_zip_code());
		preparedStatement.setString(10, clients.getRole());
		preparedStatement.setString(11, clients.getPhoneNumber());		
		preparedStatement.setString(12, clients.getCreditCardInfo());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    
    public client getClient(String Email) throws SQLException {
    	client client = null;
        String sql = "SELECT * FROM Client WHERE Email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, Email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String FirstName = resultSet.getString("FirstName");
            String LastName = resultSet.getString("LastName");
            String Password = resultSet.getString("Password");
            String address_street_num = resultSet.getString("address_street_num"); 
            String address_street = resultSet.getString("address_street"); 
            String address_city = resultSet.getString("address_city"); 
            String address_state = resultSet.getString("address_state"); 
            String address_zip_code = resultSet.getString("address_zip_code"); 
            String Role = resultSet.getString("Role");
            String PhoneNumber = resultSet.getString("PhoneNumber");
            String CreditcardInfo = resultSet.getString("CreditcardInfo");
            client = new client(Email, FirstName, LastName, Password, address_street_num,  address_street,  address_city,  address_state,  address_zip_code,Role,PhoneNumber,CreditcardInfo);
        }
         
        resultSet.close();
        statement.close();
         
        return client;
    }
    
    public boolean checkEmail(String Email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM Client WHERE Email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, Email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    
    public boolean checkPassword(String Password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM Client WHERE Password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, Password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String Email, String Password) throws SQLException
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
    		if(resultSet.getString("Email").equals(Email) && resultSet.getString("Password").equals(Password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	try {connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists treecutting; ",
					        "create database treecutting; ",
					        "use treecutting; ",
					        "drop table if exists Client; ",
					        ("CREATE TABLE if not exists Client( " +
					            "Email VARCHAR(255) UNIQUE NOT NULL, " + 
					            "FirstName VARCHAR(255) NOT NULL, " +
					            "LastName VARCHAR(255) NOT NULL, " +
					            "Password VARCHAR(25), " +
					            "address_street_num VARCHAR(4) , "+ 
					            "address_street VARCHAR(30) , "+ 
					            "address_city VARCHAR(20)," + 
					            "address_state VARCHAR(2),"+ 
					            "address_zip_code VARCHAR(5),"+ 
					            "Role VARCHAR(20)," +
					            "PhoneNumber VARCHAR(20) ,"+ 
					            "CreditCardInfo VARCHAR(12),"+
					            "ClientID INT AUTO_INCREMENT," +
					            "PRIMARY KEY (ClientID) "+"); "),
					        ("CREATE TABLE QuoteRequest ("+
					        		 "    ClientID INT,"+
					        		 "    Note TEXT,"+
					        		 "    Status ENUM('open', 'rejected', 'accepted') NOT NULL,"+
					        		 "    LatestNegotiationID INT," +
					        		"RequestID INT PRIMARY KEY AUTO_INCREMENT,"+
					        		 "    FOREIGN KEY (ClientID) REFERENCES Client(ClientID)"+");"),
					        ("CREATE TABLE QuoteNegotiation (" +
					        		"    NegotiationID INT PRIMARY KEY AUTO_INCREMENT," +
					        		"    RequestID INT NOT NULL," +
					        		"    PriceSuggested DECIMAL(10, 2)," +
					        		"    TimeWindowSuggested TEXT," +
					        		"    Note TEXT," +
					        		"    NegotiationDate DATE NOT NULL," +
					        		"    NegotiatedBy ENUM('David', 'Client') NOT NULL," +
					        		"    FOREIGN KEY (RequestID) REFERENCES QuoteRequest(RequestID)" +
					        		");"),
					        		("CREATE TABLE Tree ("+
					        		 
					        		 "    RequestID INT NOT NULL,"+
					        		 "    Picture1 TEXT,"+
					        		 "    Picture2 TEXT,"+
					        		 "    Picture3 TEXT,"+
					        		 "    Size DECIMAL(5,2),"+
					        		 "    Height DECIMAL(5,2),"+
					        		 "    Location TEXT,"+
					        		 "    DistanceToHouse DECIMAL(5,2),"+
					        		 "    TreeID INT PRIMARY KEY AUTO_INCREMENT,"+
					        		 "    FOREIGN KEY (RequestID) REFERENCES QuoteRequest(RequestID)"+");"),
					        ("CREATE TABLE WorkOrder ("+
					        		
					        		 "    RequestID INT NOT NULL,"+
					        		 "    FinalPrice DECIMAL(10, 2) NOT NULL,"+
					        		 "    TimeWindow TEXT NOT NULL,"+
					        		 "    OrderID INT PRIMARY KEY AUTO_INCREMENT,"+
					        		 "    FOREIGN KEY (RequestID) REFERENCES QuoteRequest(RequestID)"+
					        		 ");"),
					        ("CREATE TABLE Bill (" +
					        		"    BillID INT PRIMARY KEY AUTO_INCREMENT," +
					        		"    OrderID INT NOT NULL," +
					        		"    Amount DECIMAL(10, 2) NOT NULL," +
					        		"    Status ENUM('in progress', 'rejected', 'accepted') NOT NULL," +
					        		"    LatestNegotiationID INT," +
					        		"    FOREIGN KEY (OrderID) REFERENCES WorkOrder(OrderID)" +
					        		");"),
					        ("CREATE TABLE BillNegotiation ("+
					        		 "    NegotiationID INT PRIMARY KEY AUTO_INCREMENT,"+
					        		 "    BillID INT NOT NULL,"+
					        		 "    AmountSuggested DECIMAL(10, 2),"+
					        		 "    Note TEXT,"+
					        		 "    NegotiationDate DATE NOT NULL,"+
					        		 "    NegotiatedBy ENUM('David', 'Client') NOT NULL,"+
					        		 "    FOREIGN KEY (BillID) REFERENCES Bill(BillID)"+
					        		 ");")
					        
        					};
        String[] TUPLES = {("INSERT INTO Client (Email, FirstName, LastName, Password, address_street_num, address_street, address_city, address_state, address_zip_code, Role, PhoneNumber, CreditCardInfo)"+
        			"values  ('root@gmail.com', 'Admin', 'User', 'pass1234', '1234', 'Admin St', 'Admin City', 'CA', '12345', 'Admin', '555-123-4567', '123456789012'),"+
			    		 	"('david@gmail.com', 'David', 'Smith', 'davidPassword', '5678', 'David St', 'David City', 'NY', '54321', 'client', '555-987-6543', '987654321012')," +
			    	 	 	"('john@gmail.com', 'John', 'Doe', 'Password1', '1234', 'Main St', 'City1', 'CA', '12345', 'client', '555-123-4567', '123456789012'),"+
			    		 	"('jane@gmail.com', 'Jane', 'Smith', 'Password2', '5678', 'Elm St', 'City2', 'NY', '54321', 'client', '555-987-6543', '987654321012'),"+
			    		 	"('bob@gmail.com', 'Bob', 'Johnson', 'Password3', '4321', 'Oak St', 'City3', 'TX', '98765', 'client', '555-789-4567', '789456123012'),"+
			    		 	"('alice@gmail.com', 'Alice', 'Williams', 'Password4', '8765', 'Cedar St', 'City4', 'CA', '65432', 'client', '555-321-7890', '321789654012'),"+
			    			"('mike@gmail.com', 'Mike', 'Brown', 'Password5', '9876', 'Birch St', 'City5', 'NY', '76543', 'client', '555-654-1239', '654123987012'),"+
			    			"('sarah@gmail.com', 'Sarah', 'Davis', 'Password6', '6543', 'Maple St', 'City6', 'TX', '87654', 'client', '555-987-3216', '987321654012'),"+
			    			" ('chris@gmail.com', 'Chris', 'Wilson', 'Password7', '3456', 'Pine St', 'City7', 'CA', '56789', 'client', '555-456-9873', '456987321012'),"+
			    			"('emily@gmail.com', 'Emily', 'Jones', 'Password8', '7890', 'Fir St', 'City8', 'NY', '43210', 'client', '555-123-4569', '123456987012'),"+
			    			"('lisa@gmail.com', 'Lisa', 'Miller', 'Password9', '2109', 'Spruce St', 'City9', 'TX', '98701', 'client', '555-987-4563', '987456321012');"),
        					("INSERT INTO QuoteRequest (ClientID, Note, Status, LatestNegotiationID)"+ 
			    			"VALUES   (1, 'Request 1', 'open', 1),"
        							+ "  (2, 'Request 2', 'rejected', 2),"
        							+ "  (3, 'Request 3', 'accepted', 3),"
        							+ "  (4, 'Request 4', 'open', 4),"
        							+ "  (5, 'Request 5', 'rejected', 5),"
        							+ "  (6, 'Request 6', 'accepted', 6),"
        							+ "  (7, 'Request 7', 'open', 7),"
        							+ "  (8, 'Request 8', 'rejected', 8),"
        							+ "  (9, 'Request 9', 'accepted', 9),"
        							+ "  (10, 'Request 10', 'open', 10);"),
        					("INSERT INTO QuoteNegotiation (RequestID, PriceSuggested, TimeWindowSuggested, Note, NegotiationDate, NegotiatedBy)"
        							+ "VALUES (1, 1000.00, '10 AM - 2 PM', 'Negotiation for Request 1', '2023-10-17', 'David'),"
        							+ "  (2, 800.00, '9 AM - 1 PM', 'Negotiation for Request 2', '2023-10-18', 'Client'),"
        							+ "  (3, 1100.00, '11 AM - 3 PM', 'Negotiation for Request 3', '2023-10-19', 'David'),"
        							+ "  (4, 750.00, '1 PM - 5 PM', 'Negotiation for Request 4', '2023-10-20', 'Client'),"
        							+ "  (5, 1200.00, '12 PM - 4 PM', 'Negotiation for Request 5', '2023-10-21', 'David'),"
        							+ "  (6, 850.00, '10 AM - 2 PM', 'Negotiation for Request 6', '2023-10-22', 'Client'),"
        							+ "  (7, 950.00, '11 AM - 3 PM', 'Negotiation for Request 7', '2023-10-23', 'David'),"
        							+ "  (8, 900.00, '2 PM - 6 PM', 'Negotiation for Request 8', '2023-10-24', 'Client'),"
        							+ "  (9, 1050.00, '9 AM - 1 PM', 'Negotiation for Request 9', '2023-10-25', 'David'),"
        							+ "  (10, 950.00, '12 PM - 4 PM', 'Negotiation for Request 10', '2023-10-26', 'Client');"),
        					("INSERT INTO Tree (RequestID, Picture1, Picture2, Picture3, Size, Height, Location, DistanceToHouse)"
        							+ "VALUES(1, 'tree1.jpg', 'tree2.jpg', 'tree3.jpg', 5.5, 12.3, 'Backyard', 10.0),"
        							+ "  (2, 'tree4.jpg', 'tree5.jpg', 'tree6.jpg', 6.0, 14.2, 'Frontyard', 8.5),"
        							+ "  (3, 'tree7.jpg', 'tree8.jpg', 'tree9.jpg', 4.2, 10.1, 'Garden', 12.7),"
        							+ "  (4, 'tree10.jpg', 'tree11.jpg', 'tree12.jpg', 3.7, 9.8, 'Courtyard', 15.3),"
        							+ "  (5, 'tree13.jpg', 'tree14.jpg', 'tree15.jpg', 7.1, 16.5, 'Park', 7.2),"
        							+ "  (6, 'tree16.jpg', 'tree17.jpg', 'tree18.jpg', 5.8, 13.7, 'Backyard', 9.8),"
        							+ "  (7, 'tree19.jpg', 'tree20.jpg', 'tree21.jpg', 6.4, 15.6, 'Garden', 11.0),"
        							+ "  (8, 'tree22.jpg', 'tree23.jpg', 'tree24.jpg', 4.5, 11.9, 'Frontyard', 8.0),"
        							+ "  (9, 'tree25.jpg', 'tree26.jpg', 'tree27.jpg', 4.8, 11.2, 'Park', 14.5),"
        							+ "  (10, 'tree28.jpg', 'tree29.jpg', 'tree30.jpg', 5.2, 13.8, 'Courtyard', 10.2);"),
        					("INSERT INTO WorkOrder (RequestID, FinalPrice, TimeWindow)"
        							+ "VALUES (1, 950.00, '12 PM - 4 PM'),"
        							+ "  (2, 800.00, '10 AM - 2 PM'),"
        							+ "  (3, 1100.00, '9 AM - 1 PM'),"
        							+ "  (4, 750.00, '2 PM - 6 PM'),"
        							+ "  (5, 1200.00, '11 AM - 3 PM'),"
        							+ "  (6, 850.00, '1 PM - 5 PM'),"
        							+ "  (7, 950.00, '3 PM - 7 PM'),"
        							+ "  (8, 900.00, '10 AM - 2 PM'),"
        							+ "  (9, 1050.00, '8 AM - 12 PM'),"
        							+ "  (10, 950.00, '11 AM - 3 PM');"),
        					("INSERT INTO Bill (OrderID, Amount, Status, LatestNegotiationID)"
        							+ "VALUES (1, 950.00, 'accepted', 1),"
        							+ "  (2, 800.00, 'accepted', 2),"
        							+ "  (3, 1100.00, 'accepted', 3),"
        							+ "  (4, 750.00, 'accepted', 4),"
        							+ "  (5, 1200.00, 'accepted', 5),"
        							+ "  (6, 850.00, 'accepted', 6),"
        							+ "  (7, 950.00, 'accepted', 7),"
        							+ "  (8, 900.00, 'accepted', 8),"
        							+ "  (9, 1050.00, 'accepted', 9),"
        							+ "  (10, 950.00, 'accepted', 10);"),
        					("INSERT INTO BillNegotiation (BillID, AmountSuggested, Note, NegotiationDate, NegotiatedBy)"
        							+ "VALUES (1, 950.00, 'Negotiation for Bill 1', '2023-10-17', 'David'),"
        							+ "  (2, 800.00, 'Negotiation for Bill 2', '2023-10-18', 'Client'),"
        							+ "  (3, 1100.00, 'Negotiation for Bill 3', '2023-10-19', 'David'),"
        							+ "  (4, 750.00, 'Negotiation for Bill 4', '2023-10-20', 'Client'),"
        							+ "  (5, 1200.00, 'Negotiation for Bill 5', '2023-10-21', 'David'),"
        							+ "  (6, 850.00, 'Negotiation for Bill 6', '2023-10-22', 'Client'),"
        							+ "  (7, 950.00, 'Negotiation for Bill 7', '2023-10-23', 'David'),"
        							+ "  (8, 900.00, 'Negotiation for Bill 8', '2023-10-24', 'Client'),"
        							+ "  (9, 1050.00, 'Negotiation for Bill 9', '2023-10-25', 'David'),"
        							+ "  (10, 950.00, 'Negotiation for Bill 10', '2023-10-26', 'Client');")
			    			};
        
        //for loop to put these in database
        for (String sql : INITIAL) {
            statement.execute(sql);
        }

        for (String sql : TUPLES) {
            statement.execute(sql);
        }

        disconnect();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    
   
    
    
    
    
    
	
	

}
