var teamList = $("#team_img_view > .team_content");
var teamListMax = teamList.length - 1;
var teamCount = 0;
var $teamTmp = $();
var teamDuration = 500;

$(function(){
	$("button").click(function(){
		$teamTmp = $(this).get(0).id;
		if($teamTmp == "teamLeft"){
			moveLeft();
		}else if($teamTmp == "teamRight"){
			moveRight();
		}
	})
});

function moveLeft(){
	$(teamList[teamCount]).animate({
		"left":"-70%", 
		"opacity":"0"
	}, {duration:loginDuration, queue:false}, function(){
		$(this).css({"left":"70%"});
	});

	teamCount--;

	if(teamCount < 0) teamCount = teamListMax;

	$(teamList[teamCount]).css({
		"left":"70%"
	});

	$(teamList[teamCount]).animate({
		"left": "0px", 
		"opacity":"1"
	}, {duration:loginDuration, queue:false});
}


function moveRight(){
	$(teamList[teamCount]).animate({
		"left":"70%", 
		"opacity":"0"
	}, {duration:loginDuration, queue:false}, function(){
		$(this).css({"left":"-70%"});
	});

	teamCount++;

	if(teamCount > teamListMax) teamCount = 0;

	$(teamList[teamCount]).css({
		"left":"-70%"
	});

	$(teamList[teamCount]).animate({
		"left": "0px", 
		"opacity":"1"
	}, {duration:loginDuration, queue:false});
}



