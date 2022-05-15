//Hide the alters on page load
$(document).ready(function()
{

	$("#alertSuccess").hide();

 	$("#alertError").hide();

}); 

$(document).on("click", "#btnSave", function(event)
{
	console.log($("#hidInquiryIDSave").val());
	
	// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();

// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 var type = ($("#hidInquiryIDSave").val() == "") ? "POST" : "PUT";
console.log(type); 
 $.ajax(
 {
 url : "InquiriesAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onInquirySaveComplete(response.responseText, status);
 }
 });
});

// CLIENT-MODEL================================================================
function validateInquiryForm()
{
	
// Name
if ($("#name").val().trim() == "")
 {
 return "Insert Name.";
 }
 
 // Address
if ($("#address").val().trim() == "")
 {
 return "Insert Address.";
 }
 
// Phone number
if ($("#phoneNb").val().trim() == "")
 {
 return "Insert Phone number.";
 } 
// is numerical value
var tmpContact = $("#phoneNb").val().trim();
if (!$.isNumeric(tmpContact))
 {
 return "Insert a numerical value for Phone number.";
 }

// Date
if ($("#date").val().trim() == "")
 {
 return "Insert Date.";
 }
 
// Comment
if ($("#comment").val().trim() == "")
 {
 return "Insert your Comment.";
 }
return true;
}

$(document).on("click", ".btnUpdate", function()
{
 $("#hidInquiryIDSave").val($(this).data("inquiryid"));
 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#address").val($(this).closest("tr").find('td:eq(1)').text());
 $("#phoneNb").val($(this).closest("tr").find('td:eq(2)').text());
 $("#date").val($(this).closest("tr").find('td:eq(3)').text());
 $("#comment").val($(this).closest("tr").find('td:eq(4)').text());
});

function onInquirySaveComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully saved.");
 			$("#alertSuccess").show();
			
 			$("#divInquiryGrid").html(resultSet.data);
			
 		} else if (resultSet.status.trim() == "error")
 			{
			 	$("#alertError").text(resultSet.data);
 			 	$("#alertError").show();
 			}	
 	} else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} else
 			{
 				$("#alertError").text("Unknown error while saving..");
 				$("#alertError").show();
 			} 
		
 $("#hidInquiryIDSave").val("");
 $("#formItem")[0].reset();
}


$(document).on("click", ".btnRemove", function()
{
	var id = $(this).data("inquiryid");
	console.log("id is :"+id)
 $.ajax(
 {
 url : "InquiryAPI",
 type : "DELETE",
 data : "inquiryID=" + id,
 dataType : "text",
 complete : function(response, status)
 {
	console.log(id)
 onInquiryDeleteComplete(response.responseText, status);
 }
 });
});

function onInquiryDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
	$("#hidInquiryIDSave").val(""); 
	$("#formItem")[0].reset(); 
}









