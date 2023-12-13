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
	private PreparedStatement preparedStatement3 = null;
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
    public void ClientPayment(int BillID, int ClientID) throws SQLException {
        connect_func("root", "pass1234");

        // Insert a record into the QuoteRequest table
        String insertpay = "INSERT INTO Payment (BillID,ClientID,PaidDate) VALUES (?, ?,NOW())";
        preparedStatement1 = (PreparedStatement) connect.prepareStatement(insertpay);
        preparedStatement1.setInt(1, BillID);
        preparedStatement1.setInt(2, ClientID);
        
       
        preparedStatement1.executeUpdate();
        preparedStatement1.close();
        
        String sql = "update Bill set Status='accepted',PaidDate=NOW() where BillID = ?";
        connect_func();
        

        preparedStatement2 =(PreparedStatement) connect.prepareStatement(sql);
        preparedStatement2.setInt(1, BillID);

        boolean rowUpdated = preparedStatement2.executeUpdate() > 0;
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
    
    public void DavidResponse(response responses) throws SQLException {
    	connect_func("root", "Vishnupriya2");
        String selectNegotiationIDSQL = "SELECT LatestNegotiationID FROM QuoteRequest WHERE RequestID = ?";
        preparedStatement2 = (PreparedStatement) connect.prepareStatement(selectNegotiationIDSQL);
        preparedStatement2.setInt(1, responses.getRequestID());
        resultSet = preparedStatement2.executeQuery();

        int currentNegotiationID = 0;

        // Check if a record is found
        if (resultSet.next()) {
            currentNegotiationID = resultSet.getInt("LatestNegotiationID");
        }

        // Increment the current NegotiationID
        int newNegotiationID = currentNegotiationID + 1;
        
        // Insert a record into the QuoteRequest table
        String insertResponseSQL = "INSERT INTO QuoteNegotiation (RequestID,PriceSuggested,TimeWindowSuggested, Note, NegotiationID,NegotiationDate,NegotiatedBy) VALUES (?,?, ?, ?,?,NOW(),'David')";
        preparedStatement1 = (PreparedStatement) connect.prepareStatement(insertResponseSQL);
        preparedStatement1.setInt(1, responses.getRequestID());
        preparedStatement1.setDouble(2, responses.getPriceSuggested());
        preparedStatement1.setString(3, responses.getTimeWindowSuggested());
        preparedStatement1.setString(4, responses.getNote());
        preparedStatement1.setInt(5, newNegotiationID);
        preparedStatement1.executeUpdate();

        String updateLatestNegotiationSQL = "UPDATE QuoteRequest SET LatestNegotiationID = ? WHERE RequestID = ?";
        preparedStatement3 = (PreparedStatement) connect.prepareStatement(updateLatestNegotiationSQL);
        preparedStatement3.setInt(1, newNegotiationID);
        preparedStatement3.setInt(2, responses.getRequestID());
        preparedStatement3.executeUpdate();
        
        preparedStatement1.close();
        preparedStatement3.close();
        preparedStatement2.close();
        resultSet.close();
        
    }
    public void ClientResponse(response responses) throws SQLException {
        connect_func("root", "Vishnupriya2");
        String selectNegotiationIDSQL = "SELECT LatestNegotiationID FROM QuoteRequest WHERE RequestID = ?";
        preparedStatement2 = (PreparedStatement) connect.prepareStatement(selectNegotiationIDSQL);
        preparedStatement2.setInt(1, responses.getRequestID());
        resultSet = preparedStatement2.executeQuery();

        int currentNegotiationID = 0;

        // Check if a record is found
        if (resultSet.next()) {
            currentNegotiationID = resultSet.getInt("LatestNegotiationID");
        }

        // Increment the current NegotiationID
        int newNegotiationID = currentNegotiationID + 1;
        
        // Insert a record into the QuoteRequest table
        String insertResponseSQL = "INSERT INTO QuoteNegotiation (RequestID,PriceSuggested,TimeWindowSuggested, Note, NegotiationID,NegotiationDate,NegotiatedBy) VALUES (?,?, ?, ?,?,NOW(),'Client')";
        preparedStatement1 = (PreparedStatement) connect.prepareStatement(insertResponseSQL);
        preparedStatement1.setInt(1, responses.getRequestID());
        preparedStatement1.setDouble(2, responses.getPriceSuggested());
        preparedStatement1.setString(3, responses.getTimeWindowSuggested());
        preparedStatement1.setString(4, responses.getNote());
        preparedStatement1.setInt(5, newNegotiationID);
        preparedStatement1.executeUpdate();

        String updateLatestNegotiationSQL = "UPDATE QuoteRequest SET LatestNegotiationID = ? WHERE RequestID = ?";
        preparedStatement3 = (PreparedStatement) connect.prepareStatement(updateLatestNegotiationSQL);
        preparedStatement3.setInt(1, newNegotiationID);
        preparedStatement3.setInt(2, responses.getRequestID());
        preparedStatement3.executeUpdate();
        
        preparedStatement1.close();
        preparedStatement3.close();
        preparedStatement2.close();
        resultSet.close();
        
    }
    public boolean UpdateBillNegotiationID(int BillID,int NegID,Double Amount) throws SQLException {
    	String sql = "update Bill set LatestNegotiationID=?,Amount=? where BillID = ?";
        connect_func();

        preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, BillID);
        System.out.println("NEG:"+NegID);
        preparedStatement.setInt(2, NegID);
        preparedStatement.setDouble(3, Amount);
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;   
    }
    
    public void ClientBillNegotiateResponse(int BillID,Double AmountSuggested,String Note) throws SQLException {
    	connect_func("root", "Vishnupriya2");
        String selectNegotiationIDSQL = "SELECT LatestNegotiationID FROM Bill WHERE BillID = ?";
        preparedStatement2 = (PreparedStatement) connect.prepareStatement(selectNegotiationIDSQL);
        preparedStatement2.setInt(1, BillID);
        resultSet = preparedStatement2.executeQuery();

        int currentNegotiationID = 0;

        // Check if a record is found
        if (resultSet.next()) {
            currentNegotiationID = resultSet.getInt("LatestNegotiationID");
        }

        // Increment the current NegotiationID
        int newNegotiationID = currentNegotiationID + 1;
        
        // Insert a record into the QuoteRequest table
        String insertResponseSQL = "INSERT INTO BillNegotiation (BillID,AmountSuggested, Note,NegotiationDate,NegotiatedBy,NegotiationID) VALUES (?,?, ?,NOW(),'Client',?)";
        preparedStatement1 = (PreparedStatement) connect.prepareStatement(insertResponseSQL);
        preparedStatement1.setInt(1, BillID);
        preparedStatement1.setDouble(2, AmountSuggested);
        preparedStatement1.setString(3, Note);
       
        preparedStatement1.setInt(4, newNegotiationID);
        preparedStatement1.executeUpdate();

        String updateLatestNegotiationSQL = "UPDATE Bill SET LatestNegotiationID = ? WHERE BillID = ?";
        preparedStatement3 = (PreparedStatement) connect.prepareStatement(updateLatestNegotiationSQL);
        preparedStatement3.setInt(1, newNegotiationID);
        preparedStatement3.setInt(2, BillID);
        preparedStatement3.executeUpdate();
        
        preparedStatement1.close();
        preparedStatement3.close();
        preparedStatement2.close();
        resultSet.close();
    	
    	
        
    }
    
    public void DavidBillNegotiateResponse(int BillID,Double AmountSuggested,String Note) throws SQLException {
    	connect_func("root", "Vishnupriya2");
        String selectNegotiationIDSQL = "SELECT LatestNegotiationID FROM Bill WHERE BillID = ?";
        preparedStatement2 = (PreparedStatement) connect.prepareStatement(selectNegotiationIDSQL);
        preparedStatement2.setInt(1, BillID);
        resultSet = preparedStatement2.executeQuery();

        int currentNegotiationID = 0;

        // Check if a record is found
        if (resultSet.next()) {
            currentNegotiationID = resultSet.getInt("LatestNegotiationID");
        }

        // Increment the current NegotiationID
        int newNegotiationID = currentNegotiationID + 1;
        
        // Insert a record into the QuoteRequest table
        String insertResponseSQL = "INSERT INTO BillNegotiation (BillID,AmountSuggested, Note,NegotiationDate,NegotiatedBy,NegotiationID) VALUES (?,?, ?,NOW(),'David',?)";
        preparedStatement1 = (PreparedStatement) connect.prepareStatement(insertResponseSQL);
        preparedStatement1.setInt(1, BillID);
        preparedStatement1.setDouble(2, AmountSuggested);
        preparedStatement1.setString(3, Note);
       
        preparedStatement1.setInt(4, newNegotiationID);
        preparedStatement1.executeUpdate();

        String updateLatestNegotiationSQL = "UPDATE Bill SET LatestNegotiationID = ? WHERE BillID = ?";
        preparedStatement3 = (PreparedStatement) connect.prepareStatement(updateLatestNegotiationSQL);
        preparedStatement3.setInt(1, newNegotiationID);
        preparedStatement3.setInt(2, BillID);
        preparedStatement3.executeUpdate();
        
        preparedStatement1.close();
        preparedStatement3.close();
        preparedStatement2.close();
        resultSet.close();
        
    }
    public boolean DavidBillAcceptResponse(int BillID, Double AmountSuggested) throws SQLException {
    	String sql = "update Bill set Status='pending',Amount=? where BillID = ?";
    	connect_func("root", "Vishnupriya2");

        preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setDouble(1, AmountSuggested);
        preparedStatement.setInt(2, BillID);
        

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;   
    }
    
    public boolean DavidAResponse(int RequestID, Double FinalPrice, String TimeWindow) throws SQLException {
    	String sql = "update QuoteRequest set Status='accepted' where RequestID = ?";
    	connect_func("root", "Vishnupriya2");

        preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        
        String sql1 = "INSERT INTO WorkOrder (RequestID,FinalPrice,TimeWindow) VALUES (?,?, ?)";
        connect_func();
        preparedStatement1 =(PreparedStatement) connect.prepareStatement(sql1);
        preparedStatement1.setInt(1, RequestID);
        preparedStatement1.setDouble(2, FinalPrice);
        preparedStatement1.setString(3, TimeWindow);
        preparedStatement1.executeUpdate();
       
        
       
        int lastInsertedOrderID = getLastInsertedRequestID();
        Double Price=FinalPrice;
        String status= "in progress";
        int negid =0;
        String sql2 = "INSERT INTO Bill (OrderID, Amount, Status, LatestNegotiationID, BillDate, DueDate) VALUES (?,?,?,?,Now(),NOW() + INTERVAL 7 DAY)";
        preparedStatement2 = connect.prepareStatement(sql2);
        preparedStatement2.setInt(1, lastInsertedOrderID);
        preparedStatement2.setDouble(2, Price);
        preparedStatement2.setString(3, status);
        preparedStatement2.setInt(4, negid);
        preparedStatement2.executeUpdate();
        preparedStatement1.close();
        preparedStatement2.close();
        return rowUpdated;   
    }
    public boolean DavidRResponse(int RequestID) throws SQLException {
    	String sql = "update QuoteRequest set Status='rejected' where RequestID = ?";
    	connect_func("root", "Vishnupriya2");

        preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;   
    }
    public boolean ClientAResponse(int RequestID, Double FinalPrice, String TimeWindow) throws SQLException {
    	String sql = "update QuoteRequest set Status='accepted' where RequestID = ?";
    	connect_func("root", "Vishnupriya2");

        preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        String sql1 = "INSERT INTO WorkOrder (RequestID,FinalPrice,TimeWindow) VALUES (?,?, ?)";
        connect_func();
        preparedStatement1 =(PreparedStatement) connect.prepareStatement(sql1);
        preparedStatement1.setInt(1, RequestID);
        preparedStatement1.setDouble(2, FinalPrice);
        preparedStatement1.setString(3, TimeWindow);
        preparedStatement1.executeUpdate();
        int lastInsertedOrderID = getLastInsertedRequestID();
        Double Price=FinalPrice;
        String status= "in progress";
        int negid =0;
        String sql2 = "INSERT INTO Bill (OrderID, Amount, Status, LatestNegotiationID, BillDate, DueDate) VALUES (?,?,?,?,Now(),NOW() + INTERVAL 7 DAY)";
        preparedStatement2 = connect.prepareStatement(sql2);
        preparedStatement2.setInt(1, lastInsertedOrderID);
        preparedStatement2.setDouble(2, Price);
        preparedStatement2.setString(3, status);
        preparedStatement2.setInt(4, negid);
        preparedStatement2.executeUpdate();
        preparedStatement1.close();
        preparedStatement2.close();
        return rowUpdated;   
    }
    public boolean ClientRResponse(int RequestID) throws SQLException {
    	String sql = "update QuoteRequest set Status='rejected' where RequestID = ?";
    	connect_func("root", "Vishnupriya2");

        preparedStatement =(PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, RequestID);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;   
    }
   
    

    public boolean delete(String Email) throws SQLException {
        String sql = "DELETE FROM Client WHERE Email = ?";        
        connect_func("root", "Vishnupriya2");
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, Email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(client clients) throws SQLException {
        String sql = "update Client set FirstName=?, LastName =?,Password = ?,address_street_num =?, address_street=?,address_city=?,address_state=?,address_zip_code=?,role=?,PhoneNumber=?, CreditcardInfo =? where Email = ?";
        connect_func("root", "Vishnupriya2");
        
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
					        		 "    TreeCount INT,"+
					        		 "    Note TEXT,"+
					        		 "    Status ENUM('open', 'rejected', 'accepted') NOT NULL,"+
					        		 "    LatestNegotiationID INT," +
					        		 "    RequestRaiseDate DATETIME," +
					        		"RequestID INT PRIMARY KEY AUTO_INCREMENT,"+
					        		 "    FOREIGN KEY (ClientID) REFERENCES Client(ClientID)"+");"),
					        ("CREATE TABLE QuoteNegotiation (" +
					        		
					        		"    RequestID INT NOT NULL," +
					        		"    PriceSuggested DECIMAL(10, 2)," +
					        		"    TimeWindowSuggested TEXT," +
					        		"    Note TEXT," +
					        		"    NegotiationDate DATE NOT NULL," +
					        		"    NegotiatedBy ENUM('David', 'Client') NOT NULL," +
					        		"    NegotiationID INT ," +
					        		"    FOREIGN KEY (RequestID) REFERENCES QuoteRequest(RequestID)" +
					        		");"),
					        		("CREATE TABLE Tree ("+
					        		 
					        		 "    RequestID INT NOT NULL,"+
					        		 "    Picture1 LONGBLOB,"+
					        		 "    Picture2 LONGBLOB,"+
					        		 "    Picture3 LONGBLOB,"+
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
					        		
					        		"    OrderID INT NOT NULL," +
					        		"    Amount DECIMAL(10, 2) NOT NULL," +
					        		"    Status ENUM('in progress', 'pending', 'accepted') NOT NULL," +
					        		"    BillDate DATETIME," +
					        		"    DueDate DATETIME," +
					        		"    PaidDate DATETIME," +
					        		"    LatestNegotiationID INT," +
					        		"    BillID INT PRIMARY KEY AUTO_INCREMENT," +
					        		"    FOREIGN KEY (OrderID) REFERENCES WorkOrder(OrderID)" +
					        		");"),
					        ("CREATE TABLE BillNegotiation ("+
					        		 
					        		 "    BillID INT NOT NULL,"+
					        		 "    AmountSuggested DECIMAL(10, 2),"+
					        		 "    Note TEXT,"+
					        		 "    NegotiationDate DATE NOT NULL,"+
					        		 "    NegotiatedBy ENUM('David', 'Client') NOT NULL,"+
					        		 "    NegotiationID INT ,"+
					        		 "    FOREIGN KEY (BillID) REFERENCES Bill(BillID)"+
					        		 ");"),
					        ("CREATE TABLE Payment ("+
					        		 
					        		 "    BillID INT NOT NULL,"+
					        		 "    ClientID INT NOT NULL,"+
					        		 "    PaidDate DATETIME,"+
					        		 "    PaymentID INT PRIMARY KEY AUTO_INCREMENT,"+
					        		 "    FOREIGN KEY (BillID) REFERENCES Bill(BillID)"+
					        		 ");")
					        
        					};
        String[] TUPLES = {("INSERT INTO Client (Email, FirstName, LastName, Password, address_street_num, address_street, address_city, address_state, address_zip_code, Role, PhoneNumber, CreditCardInfo)"+
        			"values  ('root@gmail.com', 'Admin', 'User', 'pass1234', '1234', 'Admin St', 'Admin City', 'CA', '12345', 'Admin', '555-123-4567', '123456789012'),"+
			    		 	"('david@gmail.com', 'David', 'Smith', 'davidpassword', '5678', 'David St', 'David City', 'NY', '54321', 'Contractor', '555-987-6543', '987654321012')," +
			    	 	 	"('john@gmail.com', 'John', 'Doe', 'password1', '1234', 'Main St', 'City1', 'CA', '12345', 'client', '555-123-4567', '123456789012'),"+
			    		 	"('jane@gmail.com', 'Jane', 'Smith', 'password2', '5678', 'Elm St', 'City2', 'NY', '54321', 'client', '555-987-6543', '987654321012'),"+
			    		 	"('bob@gmail.com', 'Bob', 'Johnson', 'password3', '4321', 'Oak St', 'City3', 'TX', '98765', 'client', '555-789-4567', '789456123012'),"+
			    		 	"('alice@gmail.com', 'Alice', 'Williams', 'password4', '8765', 'Cedar St', 'City4', 'CA', '65432', 'client', '555-321-7890', '321789654012'),"+
			    			"('mike@gmail.com', 'Mike', 'Brown', 'password5', '9876', 'Birch St', 'City5', 'NY', '76543', 'client', '555-654-1239', '654123987012'),"+
			    			"('sarah@gmail.com', 'Sarah', 'Davis', 'password6', '6543', 'Maple St', 'City6', 'TX', '87654', 'client', '555-987-3216', '987321654012'),"+
			    			" ('chris@gmail.com', 'Chris', 'Wilson', 'password7', '3456', 'Pine St', 'City7', 'CA', '56789', 'client', '555-456-9873', '456987321012'),"+
			    			"('emily@gmail.com', 'Emily', 'Jones', 'password8', '7890', 'Fir St', 'City8', 'NY', '43210', 'client', '555-123-4569', '123456987012'),"+
			    			"('lisa@gmail.com', 'Lisa', 'Miller', 'password9', '2109', 'Spruce St', 'City9', 'TX', '98701', 'client', '555-987-4563', '987456321012');"),
        					("INSERT INTO QuoteRequest (ClientID,TreeCount, Note, Status, LatestNegotiationID,RequestRaiseDate)"+ 
			    			"VALUES   (3, 1,' Please Let me know if further details needed', 'open', 1, '2023-10-15'),"
        							+ "  (4, 3,'Please give me a quote', 'rejected', 0, '2023-10-15'),"
        							+ "  (5, 2,'I want to raise a request for 2 trees please let me know if it is possible', 'accepted', 2, '2023-10-18'),"
        							+ "  (6,2, 'Let me know when you are available ', 'open', 1, '2023-10-13'),"
        							+ "  (7,1, 'Let me know if you can work early in the morning', 'rejected', 1, '2023-10-15'),"
        							+ "  (8, 4,'Can you cut all the trees on the same day', 'accepted', 2, '2023-10-19'),"
        							+ "  (9, 1,'Please provide me a quote, Thanks', 'open', 1, '2023-10-19'),"
        							+ "  (10,5, 'Please cut all the trees before noon ', 'rejected', 2, '2023-10-20'),"
        							+ "  (7,1, 'Please give me the approximate estimate for cutting the tree', 'accepted', 3, '2023-10-20'),"
        							+ "  (5,3, 'I want the total cost to be $1000, Please let me know if it works', 'open', 1, '2023-10-21'),"
        							+ "  (6, 1,' Please Let me know if further details needed', 'accepted ', 1, '2023-11-15'),"
        							+ "  (8, 1,'Please give me a quote', 'accepted ', 1, '2023-11-15'),"
        							+ "  (10, 1,'Please let know the price estimate for this job', 'accepted', 1, '2023-11-18'),"
        							+ "  (9,1, 'Let me know when you are available ', 'accepted ', 1, '2023-11-13'),"
        							+ "  (3,1, 'Let me know if you can work early in the morning', 'accepted ', 1, '2023-11-15'),"
        							+ "  (7, 1,'Can you cut all the trees on the same day', 'accepted', 1, '2023-11-19'),"
        							+ "  (6, 1,'Please provide me a quote, Thanks', 'accepted ', 1, '2023-11-19');"),
        					("INSERT INTO QuoteNegotiation (RequestID, PriceSuggested, TimeWindowSuggested, Note, NegotiationDate, NegotiatedBy,NegotiationID)"
        							+ "VALUES (1, 1000.00, '10 AM - 2 PM', 'Please review the attached', '2023-10-17', 'David',1),"
        							+ "  (3, 1100.00, '11 AM - 3 PM', 'Let me know if you are okay with this', '2023-10-19', 'David',1),"
        							+ "  (3, 1100.00, '11 AM - 3 PM', 'This works for me', '2023-10-20', 'Client',2),"
        							+ "  (4, 950.00, '1 PM - 5 PM', 'Please see attached', '2023-10-18', 'David',1),"
        							+ "  (5, 1200.00, '12 PM - 4 PM', 'This is the initial quote', '2023-10-21', 'David',1),"
        							+ "  (6, 2150.00, '8 AM - 2 PM', 'Quote for 4 trees and we can cut it on same day', '2023-10-22', 'David',1),"
        							+ "  (6, 2000.00, '8 AM - 2 PM', 'Can you round off the deal for 1500', '2023-10-22', 'Client',2),"
        							+ "  (7, 950.00, '11 AM - 3 PM', 'Here is  the quote', '2023-10-23', 'David',1),"
        							+ "  (8, 3000.00, '8 AM - 3 PM', 'Sorry I need time till evening to cut', '2023-10-23', 'David',1),"
        							+ "  (8, 3000.00, '5 AM - 10 AM', 'Let me know if this time time works, or else it’s fine', '2023-10-24', 'Client',2),"
        							+ "  (9, 1250.00, '9 AM - 1 PM', 'Here is the quote', '2023-10-25', 'David',1),"
        							+ "  (9, 950.00, '10 AM - 2 PM', 'I want the tree to be cut in these time and can you also reduce price', '2023-10-26', 'Client',2),"
        							+ "  (9, 1050.00, '10 AM - 2 PM', 'The time works for me but this is the best price that I can give for you', '2023-10-27', 'David',3),"
        							+ "  (10, 1000.00, '10 AM - 2 PM', 'Please see the price and time details', '2023-11-22', 'David ',1),"
        							+ "  (11, 550.00, ' 3PM - 4 PM', 'This is the best I can do! Please review', '2023-11-16', 'David ',1),"
        							+ "  (12, 650.00, '1 PM - 2 PM', 'Please let me know if it works','2023-11-16', 'David ',1),"
        							+ "  (13, 750.00, '11AM - 1 PM', 'I have provided you the price and time details', '2023-11-17', 'David ',1),"
        							+ "  (14, 850.00, '10 AM - 11 AM', 'I This is the best I can do! Please let me know if it works for you ', '2023-11-14', 'David ',1),"
        							+ "  (15, 700.00, '9 AM - 11 AM', 'Yes! Please see the price and time details', '2023-11-16', 'David ',1),"
        							+ "  (16, 600.00, '6 PM - 8 PM', 'Yes, it is possible. I have provided the price and time details', '2023-11-20', 'David ',1),"
        							+ "  (17, 500.00, '7 PM - 9 PM', 'Please see the attached details details', '2023-11-20', 'David ',1);"),
        					("INSERT INTO Tree (RequestID, Picture1, Picture2, Picture3, Size, Height, Location, DistanceToHouse)"
        							+ "VALUES(1, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 5.5, 12.3, 'Backyard', 10.0),"
        							+ "  (2, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 6.0, 14.2, 'Frontyard', 8.5),"
        							+ "  (2, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree4.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree5.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree6.jpeg'), 3.0, 7.2, 'BackYard', 5.5),"
        							+ "  (2, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree7.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree8.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree9.jpeg'), 9.0, 23.2, 'Garden', 10.5),"
        							+ "  (3, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 4.2, 10.1, 'Garden', 12.7),"
        							+ "  (3, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree4.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree5.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree6.jpeg'), 5.2, 11.1, 'Lawn', 13.7),"
        							+ "  (4, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 3.7, 9.8, 'Courtyard', 15.3),"
        							+ "  (4, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree4.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree5.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree6.jpeg'), 2.7, 8.8, 'Courtyard', 13.3),"
        							+ "  (5, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 7.1, 16.5, 'Park', 7.2),"
        							+ "  (6, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 5.8, 13.7, 'Backyard', 9.8),"
        							+ "  (6, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree4.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree5.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree6.jpeg'),  4.8, 10.7, 'Frontyard', 8.8),"
        							+ "  (6, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree7.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree8.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree9.jpeg'), 8.8, 12.7, 'Lawn', 9.8),"
        							+ "  (6, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree10.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree11.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree12.jpeg'), 4.8, 12.7, 'Garden', 9.8),"
        							+ "  (7, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 6.4, 15.6, 'Garden', 11.0),"
        							+ "  (8, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 4.5, 11.9, 'Lawn', 8.0),"
        							+ "  (8, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree4.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree5.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree6.jpeg'), 7.5, 22.9, 'Garden', 5.0),"
        							+ "  (8, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree7.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree8.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree9.jpeg'), 9.5, 17.9, 'Backyard', 12.0),"
        							+ "  (8, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree10.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree11.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree12.jpeg'), 7.3, 21.9, 'Frontyard', 23.0),"
        							+ "  (8, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree13.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree14.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree15.jpeg'),  8.5, 17.9, 'Courtyard', 11.0),"
        							+ "  (9, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 4.8, 11.2, 'Park', 14.5),"
        							+ "  (10, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 5.2, 13.8, 'Courtyard', 10.2),"
        							+ "  (10, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree4.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree5.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree6.jpeg'),  7.4, 11.8, 'Courtyard', 3.2),"
        							+ "  (10, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree7.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree8.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree9.jpeg'), 3.2, 5.8, 'Courtyard', 1.2),"
        							+ "  (11, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'),  5.2, 6.8, 'Courtyard', 4.2),"
        							+ "  (12, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 8.2, 30.8, 'Courtyard', 5.2),"
        							+ "  (13, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 10.2, 11.8, 'Courtyard', 6.2),"
        							+ "  (14, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 9.2, 12.8, 'Courtyard', 7.2),"
        							+ "  (15, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 6.2, 13.8, 'Courtyard', 8.2),"
        							+ "  (16, LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'), 4.2, 15.8, 'Courtyard', 4.2),"
        							+ "  (17,LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree1.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree2.jpeg'), LOAD_FILE('C:/Users/svpv2/OneDrive/Documents/DB_Images/Tree3.jpeg'),  7.2, 10.8, 'Courtyard', 5.2);"),
        					("INSERT INTO WorkOrder (RequestID, FinalPrice, TimeWindow)"
        							+ "VALUES (3, 1100.00, '11 AM - 3 PM '),"
        							+ "  (6, 2000.00, '8 AM - 2 PM'),"
        							+ "  (9, 1050.00, '10 AM - 2 PM'),"
        							+ "  (11, 550.00, ' 3PM - 4 PM'),"
        							+ "  (12, 650.00, '1 PM - 2 PM'),"
        							+ "  (13, 750.00, '11AM - 1 PM'),"
        							+ "  (14, 950.00, '3 PM - 7 PM'),"
        							+ "  (15, 700.00, '9 AM - 11 AM'),"
        							+ "  (16, 600.00, '6 PM - 8 PM'),"
        							+ "  (17, 500.00, '7 PM - 9 PM');"),
        					("INSERT INTO Bill (OrderID, Amount, Status, LatestNegotiationID, BillDate, DueDate, PaidDate)"
        							+ "VALUES (1, 1050.00, 'accepted', 2, '2023-01-01', '2023-01-08', '2023-01-03'),"
        							+ "  (2, 2000.00, 'accepted', 0, '2023-02-05', '2023-02-12', '2023-02-06'),"
        							+ "  (3, 1050.00, 'accepted', 0, '2023-03-12', '2023-03-19', '2023-03-19'),"
        							+ "  (4, 550.00, 'accepted', 0, '2023-04-18', '2023-04-25', '2023-04-24'),"
        							+ "  (5, 650.00, 'accepted', 0, '2023-05-23', '2023-05-30', '2023-05-30'),"
        							+ "  (6, 750.00, 'accepted', 0, '2023-06-28', '2023-07-05', '2023-07-06'),"
        							+ "  (7, 950.00, 'pending', 2, '2023-07-30', '2023-08-06',NULL),"
        							+ "  (8, 600.00, 'in progress', 3, '2023-08-15', '2023-08-22', NULL),"
        							+ "  (9, 570.00, 'in progress', 2, '2023-09-20', '2023-09-27', NULL),"
        							+ "  (10, 500.00, 'pending', 1, '2023-10-25', '2023-11-01', NULL);"),
        					("INSERT INTO BillNegotiation (BillID, AmountSuggested, Note, NegotiationDate, NegotiatedBy,NegotiationID)"
        							+ "VALUES (1, 1000.00, 'You delayed the start of work by 1 hour so I wish to get some money deduction for it', '2023-10-02', 'Client',1),"
        							+ "  (1, 1050.00, 'I can just deduct $50 as penalty, Please consider this as final price', '2023-10-02', 'David',2),"
        							+ "  (7, 900.00, 'Can I expect some discount on this?', '2023-10-19', 'Client',1),"
        							+ "  (7, 950.00, 'Sorry this is the final price', '2023-10-20', 'David',2),"
        							+ "  (8, 500.00, 'How about this price since you didn’t clean the site', '2023-10-21', 'Client',1),"
        							+ "  (8, 650.00, 'May be $50 dollars ', '2023-10-22', 'David',2),"
        							+ "  (8, 600.00, 'This is the final bill bcz there was too much of the wood shaves', '2023-10-23', 'Client',3),"
        							+ "  (9, 500.00, 'I am expecting a discount', '2023-10-24', 'Client',1),"
        							+ "  (9, 570.00, 'I can give $30 but $100 is little much', '2023-10-25', 'David',2),"
        							+ "  (10, 500.00, 'Is this the final bill amount?', '2023-10-26', 'Client',1);"),
        					("INSERT INTO Payment (BillID, ClientID,PaidDate)"
        							+ "VALUES (1,5, '2023-01-03'),"
        							+ "  (2,8, '2023-02-06'),"
        							+ "  (3,7, '2023-03-19'),"
        							+ "  (4,6, '2023-04-24'),"
        							+ "  (5,8, '2023-05-30'),"
        							+ "  (6,10, '2023-07-06');")
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
