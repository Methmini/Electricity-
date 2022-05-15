package model;
import java.sql.*;

public class Inquiry{
	private Connection connect() {
		Connection con=null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//database connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/paf","root","root");
		}
		catch(Exception e )
		{e.printStackTrace();}
		
		return con;
		
	}
	
	public String insertInquiry(String name, String address, String phoneNb, String date, String comment) 
	{
		
		String output="";
		
		try 
		{
			Connection con=connect();
			
			if(con ==null)
			{return "Error while cooecting to the inserting.";}
			
			//create a prepared statement(SQL insert query)
			String query ="insert in to Inquiry('InquiryID'.'name','address','phoneNb', 'date','comment')"
					+"value(?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt= con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,name);
			preparedStmt.setString(3,address);
			preparedStmt.setString(4,phoneNb);
			preparedStmt.setString(5,date);
			preparedStmt.setString(5,comment);
			
			//execute the statement
			//excecute sql query
			preparedStmt.execute();
			
			output="inserted successfully";
				
		}
		catch(Exception e) {
			output="error while inserting the Inquiry request";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String readInquiry() 
	{
		String output="";
		
			try
			{
				Connection con=connect();
			
				if(con== null)
				{return "Error while connecting to the database for reading."; }
			
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Inquiry ID</th><th>Inquiry Name</th>" +
						"<th>name</th>" +
						"<th>Address</th>" +
						"<th>PhoneNb</th>" +
						"<th>Date</th>" +
						"<th>Comment</th>" +
						"<th>Update</th><th>Remove</th></tr>";
				String query = "select * from items";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
			
			
		
				while(rs.next()) {
					String InquiryID=Integer.toString(rs.getInt("InquiryID"));
					String name=rs.getString("name");
					String address=rs.getString("address");
					String phoneNb=rs.getString("phoneNb");
					Double date=rs.getString("date");
					String comment=rs.getString("comment");
					
		
				//buttons
				output = "<td><input name='btnpdate' type ='button' value='Update "
					+ "class='btn-secndary'></td>"
					+"<td><form method='post' action='items.jsp'>"
					+"<input name='InquiryID' type='hidden value'"+ InquiryID
					+"'>"+"</form></td></tr>";
							
				}
				con.close();
		//cmplete the html table
					output += "</table>";
	}
	catch(Exception e)
	{
		output = "Error while reading the Inquiry.";
		System.err.println(e.getMessage());
			
	}
	return output;
	}
	
	//update
	public String updateInquiry(String InquiryID, String name, String address, String phoneNb, String date, String comment) 
	{

		String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for updating."; }
				// create a prepared statement
				String query = "UPDATE Inquiry SET name=?, address=?,phoneNb=?,date=?, comment=? where InquiryID=? ";
						
						
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, address);
				preparedStmt.setString(3, phoneNb);
				preparedStmt.setString(3, date);
				preparedStmt.setString(4, comment);
				preparedStmt.setInt(5, Integer.parseInt(InquiryID));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			}
		catch (Exception e)
		{
		output = "Error while updating the Inquiry.";
		System.err.println(e.getMessage());
		}
		return output;
		}
	
	//Delete
	public String deleteInquiry(String InquiryID)
	{
		String output = "";
		try
		{
				Connection con = connect();
				if (con == null)
					{return "Error while connecting to the database for deleting."; }
					// create a prepared statement
				    //delete all the details related id
					String query = "delete from Inquiry where InquiryID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(InquiryID));
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the Inquiry.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
	

}