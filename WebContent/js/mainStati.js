var statiList = $("#mainStati .stati_bg");
var statiHeight = statiList.width()/ 1.25;


$(function(){
	setStatiHeight();
	$(window).resize(function()	{
		setStatiHeight();
	});
});

function setStatiHeight(){
	statiList.each(function(index, item){
		$(this).height(statiHeight);
	});
}