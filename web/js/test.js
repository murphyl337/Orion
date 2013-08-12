$("#request").click(function(){
	$.get("js/observer.js", function(data,status){
		alert("Data: " + data + "\nStatus: " + status);
	});
});