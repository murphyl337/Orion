$(document).ready(function() {
	$(".box").bind("click", function() {

		$.get("game/update", {
			move : "1",
			player : "X"
		}).done(function(data) {
			alert("Data Loaded: " + data);
		});
	});
});