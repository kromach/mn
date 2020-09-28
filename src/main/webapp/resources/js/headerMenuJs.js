$(document).ready(function(){
	$(".dropDownMenu").hide();
	$(".menuWrapper").hover(function() {
		$(".dropDownMenu").show();
	}, function() {
		$(".dropDownMenu").hide();
	})
});
