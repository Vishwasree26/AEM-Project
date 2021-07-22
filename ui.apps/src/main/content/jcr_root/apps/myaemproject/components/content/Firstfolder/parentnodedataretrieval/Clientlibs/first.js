$(document).ready(function(){
 alert("Iam from jquery");
	$("#submit").click(function(){
	alert("Onclick called");

		$.ajax({
			type: 'GET',

			url:'/bin/nodedataretrievalservlet',
			success: function(result){
				alert(result);	
				},
			error: function(err){
				alert("Unable to retrieve data"+err);
			}
		}); 
	});
});