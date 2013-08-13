$("#request").click(function(){
	$.get("js/observer.js", {move: "1", player: "X"}, function(data,status){
		alert("Data: " + data + "\nStatus: " + status);
	});
});