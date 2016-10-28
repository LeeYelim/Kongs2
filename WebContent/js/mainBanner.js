var list = $("#imgBanner li");
var count = 0;
var max = list.length - 1;

var duration = 1000;
var textduration = 1000;
var gap = 5000;

var ratio = 0;

var sliding = true;

var timer = null;

$(function(){

	setTimer();
	showBannerText();
	setBannerButtons();

	setBanner();
	$(window).resize(function()	{
		setBanner();
	});

});

function banner(num) {
	var $now = list.eq(count);

	clearTimeout(timer);

	if(num == count || sliding)	{
		return;
	}

	sliding = true;

	if(typeof num == "number")	{
		count = num;
	} else if(typeof num == "string")	{
		(num == "next")? 
			((++count > max)? count = 0:0)
			: ((--count < 0)? count = max:0);
	}

	var $next = list.eq(count);

	$now.fadeOut(duration);
	$next.fadeIn(duration);

	$("#banner_btn_box button").eq(count).addClass("now").siblings("button").removeClass("now");

	showBannerText();
}

function showBannerText()	{
	var $box = list.eq(count).find(".container");

	$box.hide(0).css("bottom", "40%");

	setTimeout(function()	{
		$box.fadeIn(textduration)
			.animate({"bottom":"50%"}, {queue:false, duration:textduration, complete:function()	{
				sliding = false;
				setTimer();
			}});
	}, duration);
	
}

function setBanner()	{
	var $banner = $("#imgBanner");

	$banner.height($(window).height());

	ratio = $banner.width() / $banner.height();

	setImageSize();
}


function setImageSize(asdf, asdf)	{
	var $list = list.eq(count);

	list.each(function(i, u)	{
		var $li = $(this);

		var $img = $li.find("img");

		$li.show(0);
		var iratio = $img.width() / $img.height();
		$li.hide(0);

		if(ratio < iratio)
			$img.addClass("h100");
		else
			$img.addClass("w100");
	});

	$list.show(0);

}

function setBannerButtons()	{
	for(var i = 0; i <= max; i++)	{
		$("#banner_btn_box").append("<button>");
	}
	$("#banner_btn_box button").first().addClass("now");


	$("#banner_btn_box button").click(function()	{
		var i = $(this).index();
		banner(i);
	});

	$("#banner_np_btn_box button").click(function()	{
		banner($(this).data("type"))
	});
}


function setTimer()	{
	timer = setTimeout(function() {
		banner("next");
	}, gap);
}