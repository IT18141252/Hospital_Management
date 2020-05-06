$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	}  
	$("#alertError").hide(); }); 
 
// SUBMIT ============================================ 
$(document).on("click", "#btnSubmit", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#Id").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( 
	{  
		url : "Hospital",  
		type : type,  
		data : $("#form").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			SaveComplete(response.responseText, status);  
		} 
	}); 
}); 

function SaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divPaymentsGrid").html(resultSet.data);   
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

	$("#Id").val("");  
	$("#formPayment")[0].reset(); 
} 
 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#Id").val($(this).closest("tr").find('#hidPayIDUpdate').val());     
	$("#HName").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#address").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#email").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#Tphone").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 

//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "Hospital",   
		type : "DELETE",   
		data : "Id" + $(this).data("Id"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			DeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function DeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#hospitalGrid").html(resultSet.data);   
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
}
 
// CLIENT-MODEL========================================================================= 
function validateHospitalForm() 
{  
	
 
	// NAME  
	if ($("#HName").val().trim() == "")  
	{   
		return "Insert Name.";  
	} 
	//ADDRESS-------------------------------  
	if ($("#address").val().trim() == "")  
	{   
		return "Input Address";  
	} 

	// is numerical value  
	var tmpPrice = $("#PaymentPrice").val().trim();  
	if (!$.isNumeric(tmpPrice))  
	{   
		return "Insert a numerical value for Payment Price.";  
	} 

	// convert to decimal price  
	$("#PaymentPrice").val(parseFloat(tmpPrice).toFixed(2)); 

	// DESCRIPTION------------------------  
	if ($("#pDate").val().trim() == "")  
	{   
		return "Insert Date.";  
	} 

	return true; 
}