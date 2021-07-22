
$(document).ready(function(){
	
	$("#submit").click(function(){

		var ID=$('#ID').val();
		var Name=$('#Name').val();

		$.ajax({
			type: 'GET',
		 	data: {ID:ID,Name:Name},
			url:'./bin/nodedataretrievalservlet',
			success: function(result){
				alert(result);	
				},
			error: function(err){
				alert("Unable to retrieve data"+err);
			}
		}); 
	});
});