package com.inquiry.management;

import model.inquiry;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/inquiry")
public class inquiryService {
	inquiry inquiryObj = new inquiry();
	@GET
	@Path("/AddInquiry")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertNewInquiry(
			@FormParam("name")String name,
			@FormParam("address")String address,
			@FormParam("phoneNb")String phoneNb,
			@FormParam("date")String date,
			@FormParam("comment")String comment) {
	   String output= inquiryObj.insertInquiry(name, address, phoneNb, date, comment);
			   return output;
	} 
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getInquiry() {
		return inquiryObj.readInquiry();
	}
	
	@PUT
	@Path("/UpdateInquiry")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateInquiry(String InquiryData){
		
		JsonObject InquiryObject = new JsonParser().parse(InquiryData).getAsJsonObject();
		
		String InquiryID = InquiryObject.get("InquiryID").getAsString();
		String Inquiryname = InquiryObject.get("Inquiryname").getAsString();
		String InquiryAddress = InquiryObject.get("InquiryAddress").getAsString();
		String InquiryPhoneNb = InquiryObject.get("InquiryPhoneNb").getAsString();
		String InquiryDate = InquiryObject.get("InquiryDate").getAsString();
		String InquiryComment = InquiryObject.get("InquiryComment").getAsString();
		
		String output= InquiryObj.updateInquiry(InquiryID,Inquiryname,InquiryAddress,InquiryPhoneNb,InquiryDate,InquiryComment);
				
		return output;
		
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiry(String InquiryData)
	{
	//Convert the input string to an XML document
		Document doc = Jsoup.parse(InquiryData, "", Parser.xmlParser());
		//Read the value from the element <InquiryID>
		String InquiryID = doc.select("InquiryID").text();
		
		String output = InquiryObj.deleteInquiry(InquiryID);
				
		return output;
	}
	
}