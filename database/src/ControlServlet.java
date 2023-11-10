import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import quote.*;
import response.*;
import javax.servlet.http.Part;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;


@MultipartConfig
// file
public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private clientDAO clientDAO = new clientDAO();
	    private String currentClient;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	clientDAO = new clientDAO();
	    	currentClient= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	 System.out.println("doGet start: 111111111111111111111111111111111111");
	    	String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		clientDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	 case "/list": 
                 System.out.println("The action is: list");
                 listClient(request, response);           	
                 break;
        	 case "/qrequest":
        		 System.out.println("The action is: QuoteRequest");
        		 insertQuotes(request,response);
        		 break;
        	 case "/Negotiate":
        		 System.out.println("The action is: Negotiate by David");
        		 DavidResponseRedirect(request,response);
        		 DavidResponseToClient(request, response);
        		 break;
        	 case "/dresponse":
        		 System.out.println("The action is: Initial Response by David");
        		 DavidResponseToClient(request,response);
        		 break;
        	 case "/clistdresponse":
        		 System.out.println("The action is: Client Wanted to see Response from David");
        		 ListResponsefromDavid(request,response);
        	 case "/clistalldresponse":
        		 System.out.println("The action is: Client Wanted to see all Response from David");
        		 ListAllResponsefromDavid(request,response);
        	 case "/cNegotiate":
        		 System.out.println("The action is: Negotiate by Client");
        		 ClientResponseRedirect(request,response);
        		 ClientRespondToDavid(request, response);
        		 break;
        	 case "/cresponse":
        		 System.out.println("The action is: Client Response to David");
        		 ClientRespondToDavid(request,response);
        		 break;
        	 case "/drNegotiate":
        		 System.out.println("The action is: Negotiate by David");
        		 DavidResponseRedirect(request,response);
        		 DavidRResponseToClient(request, response);
        		 break;
        	 case "/drresponse":
        		 System.out.println("The action is: Initial Response by David");
        		 DavidResponseToClient(request,response);
        		 break;
        	 case "/dAccept":
        		 System.out.println("The action is:David Accept");
        		 DavidAccept(request,response);
        		 break;
        	 case "/cAccept":
        		 System.out.println("The action is:Client Accept");
        		 ClientAccept(request,response);
        		 break;
        	 case "/dReject":
        		 System.out.println("The action is:David Reject");
        		 DavidReject(request,response);
        		 break;
        	 case "cReject":
        		 System.out.println("The action is:Client Reject");
        		 ClientReject(request,response);
        		 break;

	    	}
        	
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void ListResponsefromDavid(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listresponse started: 00000000000000000000000000000000000"); 
	        String ClientID =  request.getParameter("ClientID");
	        //int ClientID = Integer.parseInt(ClientIDstring);
	        System.out.println("Client:"+ClientID);
	        request.setAttribute("ClientID", ClientID);
	        
	        request.getRequestDispatcher("ClientListQuote.jsp").forward(request, response);      
	        
	     
	        System.out.println("listresponse finished: 111111111111111111111111111111111111");
	    }
	    private void ListAllResponsefromDavid(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listresponse started: 00000000000000000000000000000000000"); 
	        String ClientID =  request.getParameter("ClientID");
	        //int ClientID = Integer.parseInt(ClientIDstring);
	        System.out.println("Client:"+ClientID);
	        request.setAttribute("ClientID", ClientID);
	        
	        request.getRequestDispatcher("cotherquotes.jsp").forward(request, response);      
	        
	     
	        System.out.println("listresponse finished: 111111111111111111111111111111111111");
	    }
	    private void listClient(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listclient started: 00000000000000000000000000000000000");

	     
	        List<client> listClient = clientDAO.listAllClients();
	        System.out.println("Number of clients: " + listClient.size());
	        request.setAttribute("listClient", listClient);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	            
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listClient", clientDAO.listAllClients());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String Email = request.getParameter("Email");
	    	 String Password = request.getParameter("Password");
	    	 
	    	 if (Email.equals("root") && Password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", Email);
				 rootPage(request, response, "");
	    	 }
	    	 else if(clientDAO.isValid(Email, Password)) 
	    	 {
			 	 
			 	 currentClient = Email;
			 	 int ClientID = clientDAO.getClientID(Email);
			 	request.setAttribute("ClientID", ClientID);
			 	System.out.println(ClientID);
				 System.out.println("Login Successful! Redirecting");
				 request.getRequestDispatcher("ClientDashboard.jsp").forward(request, response);
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String Email = request.getParameter("Email");
	   	 	String FirstName = request.getParameter("FirstName");
	   	 	String LastName = request.getParameter("LastName");
	   	 	String Password = request.getParameter("Password");
	   	 	String address_street_num = request.getParameter("address_street_num"); 
	   	 	String address_street = request.getParameter("address_street"); 
	   	 	String address_city = request.getParameter("address_city"); 
	   	 	String address_state = request.getParameter("address_state"); 
	   	 	String address_zip_code = request.getParameter("address_zip_code"); 
	   	    String Role = request.getParameter("Role");
	   	 	String PhoneNumber = request.getParameter("PhoneNumber");
	   	 	String CreditCardInfo = request.getParameter("CreditCardInfo");
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (Password.equals(confirm)) {
	   	 		if (!clientDAO.checkEmail(Email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            client clients = new client(Email,FirstName, LastName, Password, address_street_num,  address_street,  address_city,  address_state,  address_zip_code, Role,PhoneNumber,CreditCardInfo);
		   	 		clientDAO.insert(clients);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("clientname taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentClient = "";
        		response.sendRedirect("login.jsp");
        	}
	    private void insertQuotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        String Email = request.getParameter("Email");
	        String Size = request.getParameter("Size");
	        String Height = request.getParameter("Height");
	        String Location = request.getParameter("Location");
	        String DistanceToHouse = request.getParameter("DistanceToHouse");
	        Part picture1Part = request.getPart("Picture1");
	        Part picture2Part = request.getPart("Picture2");
	        Part picture3Part = request.getPart("Picture3");
	        String Note = request.getParameter("Note");
	        Integer LatestNegotiationID = 0;

	        int ClientID = clientDAO.getClientID(Email);

	        if (ClientID != 0) {
	        	byte[] Picture1 = getByteArrayFromPart(picture1Part);
	            byte[] Picture2 = getByteArrayFromPart(picture2Part);
	            byte[] Picture3 = getByteArrayFromPart(picture3Part);
	            quote quotes = new quote(ClientID, Note, Size, Height, Location, DistanceToHouse,Picture1,Picture2,Picture3,LatestNegotiationID);
	            clientDAO.insertQuotes(quotes, ClientID);
	            response.sendRedirect("activitypage.jsp");
	        } else {
	            System.out.println("Please enter a correct email");
	            request.setAttribute("errorOne", "Request failed: invalid email");
	        }
	    }
	    private byte[] getByteArrayFromPart(Part picture1Part) {
	        try {
	            InputStream input = picture1Part.getInputStream();
	            ByteArrayOutputStream output = new ByteArrayOutputStream();
	            byte[] buffer = new byte[1024];
	            int length;
	            while ((length = input.read(buffer)) != -1) {
	                output.write(buffer, 0, length);
	            }
	            return output.toByteArray();
	        } catch (IOException e) {
	            // Handle the exception as needed
	            e.printStackTrace();
	            return null;
	        }
	    }


		private void DavidResponseRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	
	    	System.out.println("Request ID stmt0");
	    	String RequestIDstring = request.getParameter("RequestID");
	    	//String NegotiationIDstring =  request.getParameter("NegotiationID");
	    	request.setAttribute("RequestID", RequestIDstring);
	    	//request.setAttribute("NegotiationID", NegotiationIDstring);
	    	System.out.println(RequestIDstring);
	    	//System.out.println(NegotiationIDstring);
	    	request.setAttribute("RequestID", RequestIDstring);
	    	//request.setAttribute("NegotiationID", NegotiationIDstring);
	    	request.getRequestDispatcher("DavidResponse.jsp").forward(request, response);
	    	
	    }
	    private void DavidResponseToClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        
	       
	        
	        String RequestIDstring =  request.getParameter("RequestID");
	        int RequestID = Integer.parseInt(RequestIDstring);
	            String priceSuggested = request.getParameter("PriceSuggested");
	            Double PriceSuggested = Double.parseDouble(priceSuggested);
	            String TimeWindowSuggested = request.getParameter("TimeWindowSuggested");
	            String Note = request.getParameter("Note");
	            //int NegotiationID = 1;
	            response responses = new response(RequestID, PriceSuggested, TimeWindowSuggested, Note);
	            clientDAO.DavidInitialResponse(responses);
	            response.sendRedirect("dactivitypage.jsp");
	        
	    }
private void DavidRResponseToClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        
	       
	        
	        String RequestIDstring =  request.getParameter("RequestID");
	        int RequestID = Integer.parseInt(RequestIDstring);
	        String NegotiationIDstring =  request.getParameter("NegotiationID");
	        int NegotiationID = Integer.parseInt(NegotiationIDstring);
	            String priceSuggested = request.getParameter("PriceSuggested");
	            Double PriceSuggested = Double.parseDouble(priceSuggested);
	            String TimeWindowSuggested = request.getParameter("TimeWindowSuggested");
	            String Note = request.getParameter("Note");
	            response responses = new response(RequestID, PriceSuggested, TimeWindowSuggested, Note);
	            clientDAO.DavidResponse(responses);
	            response.sendRedirect("dactivitypage.jsp");
	        
	    }
private void ClientResponseRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	
	    	System.out.println("Request ID stmt0");
	    	String RequestIDstring = request.getParameter("RequestID");
	    	
	    	request.setAttribute("RequestID", RequestIDstring);
	    	System.out.println(RequestIDstring);
	    	request.setAttribute("RequestID", RequestIDstring);
	    	request.getRequestDispatcher("ClientResponse.jsp").forward(request, response);
	    	
	    }
private void ClientRespondToDavid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        
	       
	        
	        String RequestIDstring =  request.getParameter("RequestID");
	        System.out.println(RequestIDstring);
	        int RequestID = Integer.parseInt(RequestIDstring);
	        //String NegotiationIDstring =  request.getParameter("NegotiationID");
	       // int NegotiationID = Integer.parseInt(NegotiationIDstring);
	            String priceSuggested = request.getParameter("PriceSuggested");
	            Double PriceSuggested = Double.parseDouble(priceSuggested);
	            String TimeWindowSuggested = request.getParameter("TimeWindowSuggested");
	            String Note = request.getParameter("Note");
	            response responses = new response(RequestID, PriceSuggested, TimeWindowSuggested, Note);
	            clientDAO.ClientResponse(responses);
	            response.sendRedirect("activitypage.jsp");
	        
	    }

private void DavidAccept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	
	System.out.println("Request ID stmt0");
    String requestIDString = request.getParameter("RequestID");
    
    int requestID = Integer.parseInt(requestIDString);
    
    if (clientDAO.DavidAResponse(requestID)) {
        System.out.println("Client Response Updated Successfully");
    } else {
        System.out.println("Failed to update Client Response");
    }
    request.getRequestDispatcher("dactivitypage.jsp").forward(request, response);
}
private void DavidReject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	
	System.out.println("Request ID stmt0");
    String requestIDString = request.getParameter("RequestID");
    
    int requestID = Integer.parseInt(requestIDString);
    
    if (clientDAO.DavidRResponse(requestID)) {
        System.out.println("Client Response Updated Successfully");
    } else {
        System.out.println("Failed to update Client Response");
    }
    request.getRequestDispatcher("dactivitypage.jsp").forward(request, response);
}
private void ClientAccept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	
	System.out.println("Request ID stmt0");
    String requestIDString = request.getParameter("RequestID");
    
    int requestID = Integer.parseInt(requestIDString);
    
    if (clientDAO.ClientAResponse(requestID)) {
        System.out.println("Client Response Updated Successfully");
    } else {
        System.out.println("Failed to update Client Response");
    }
    request.getRequestDispatcher("activitypage.jsp").forward(request, response);
}
private void ClientReject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	
	System.out.println("Request ID stmt0");
    String requestIDString = request.getParameter("RequestID");
    
    int requestID = Integer.parseInt(requestIDString);
    
    if (clientDAO.ClientRResponse(requestID)) {
        System.out.println("Client Response Updated Successfully");
    } else {
        System.out.println("Failed to update Client Response");
    }
    request.getRequestDispatcher("activitypage.jsp").forward(request, response);
}

	   
	
	   
	     
        
	    
	    
	    
	    
	    
}
	        
	        
	    
	        
	        
	        
	    


