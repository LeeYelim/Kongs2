<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
</style>


<title>Insert title here</title>
 	<link rel="stylesheet" type="text/css" href="css/defaultCss.css">
	<link rel="stylesheet" type="text/css" href="css/indexCss.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=0420f8faff992e976974551ab41996c0&libraries=services"></script>
	<script>
	// 검색 keyword
	var keyword = new Array();
	keyword[0] = "카페";
	keyword[1] = "스터디룸";
	keyword[2] = "도서관";
	
	// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
	var infowindow = new daum.maps.InfoWindow({zIndex:1});
	// 장소 검색 객체를 생성합니다
	var ps = new daum.maps.services.Places(); 
	
	var size=3;
	/**
		현재 위치 받아오기
		- HTML5의 geolocation으로 사용할 수 있는지 확인
		- 현 위치 중심으로 지도 생성 
		- 햔 위치 기준으로 한 검색 메소드 호출
	*/
	if (navigator.geolocation) {
	    // GeoLocation을 이용한 접속 위치 얻어오기
	    navigator.geolocation.getCurrentPosition(function(position) {
	    	lat = position.coords.latitude; // 위도
	       	lon = position.coords.longitude; // 경도
	       	locPosition = new daum.maps.LatLng(lat, lon),    
	       	mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	        mapOption = {
	            center: locPosition, // 지도 중심좌표
	            level: size // 지도 확대 레벨
	        }; 
	       	
	       	makeMap(locPosition, mapOption);
	       	
	    });
	   
	} else { // HTML5의 GeoLocation 사용X => 마커 표시 위치 & 인포윈도우 내용 설정
		mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        locPosition = new daum.maps.LatLng(33.450701, 126.570667);
		mapOption = {
            center: locPosition, // 지도 중심좌표
            level: size // 지도 확대 레벨
        }; 
		makeMap(locPosition, mapOption);
	}
	
	function makeMap(locPosition, mapOption) {
		console.log(mapOption);
		// 지도 생성 
		map = new daum.maps.Map(mapContainer, mapOption);
		// 검색
	 	for(var i=0; i<keyword.length; i++) {
			ps.keywordSearch(keyword[i], placesSearchCB, new Object({
		 		location:locPosition,
		 		radius:500
		 	})); 
	 	}
	 	message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다
        // 마커와 인포윈도우를 표시합니다
        mark(locPosition, message, map);
	}
		
	// 키워드 검색 완료 시 호출되는 콜백함수 입니다
	function placesSearchCB (status, data, pagination) {
	    if (status === daum.maps.services.Status.OK) {
	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        var bounds = new daum.maps.LatLngBounds();
	
	        for (var i=0; i<data.places.length; i++) {
	            displayMarker(data.places[i]);    
	            bounds.extend(new daum.maps.LatLng(data.places[i].latitude, data.places[i].longitude));
	        }       
	
	    } 
	}
	
	// 지도에 마커와 인포윈도우를 표시하는 함수입니다
	function mark(locPosition, message, map) {

	    // 마커를 생성합니다
	    var marker = new daum.maps.Marker({  
	        map: map, 
	        position: locPosition
	    }); 
	    
	    var iwContent = message, // 인포윈도우에 표시할 내용
	        iwRemoveable = true;

	    // 인포윈도우를 생성합니다
	    var infowindow = new daum.maps.InfoWindow({
	        content : iwContent,
	        removable : iwRemoveable
	    });
	    
	    // 인포윈도우를 마커위에 표시합니다 
	    infowindow.open(map, marker);
	    
	    // 지도 중심좌표를 접속위치로 변경합니다
	    map.setCenter(locPosition);      
	}    

	// 지도에 마커를 표시하는 함수입니다
	function displayMarker(place) {
	    
	   // 마커를 생성하고 지도에 표시합니다
	    var marker = new daum.maps.Marker({
	        map: map,
	        position: new daum.maps.LatLng(place.latitude, place.longitude) 
	    }); 
	   
	// 마커에 클릭이벤트를 등록합니다
	    daum.maps.event.addListener(marker, 'click', function() {
	        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
	        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.title + '</div>');
	        infowindow.open(map, marker);
	    });
	}
</script>
</head>
<body>
	<div id="mainMenu">
		<div class="container">
			<div id="logo">
				<a href="index.jsp"><img src="img/logo.png"></a>
			</div>
			<ul id="menu">				
				<li><a href="#">공모전 정보</a></li>
				<li><a href="#">게시판</a></li>
				<li><a href="#">팀 게시판</a></li>
				<li><a href="map.jsp">스터디룸</a></li>
				<li id="loginView">
					<a>Login</a>
				</li>
			</ul>
			<div id="profile">
				<div id="trangle-up"></div>
				<div id="profileView">
					<div id="profileImage">
						<img alt="유저 프로필 사진" src="img/main/team_profile1.jpg">
						<ul>
							<li class="profile_id">jhlee1201ashdsajkhasjksahdjk</li>
							<li class="profile_nick">HwaYak 님 로그인</li>
						</ul>
					</div>
					<div id="profile_btn">
						<a href="">팀 만들기</a>
						<a href="">팀 게시판 이동</a>
						<a href="">개인정보 수정</a>
					</div>
				</div>
			</div>
		</div>
	</div>
<div id="map" style="width:600px;height:600px;"></div>
	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="js/smoothscroll-for-websites-master/SmoothScroll.js"></script>
	<script type="text/javascript" src="js/mainBanner.js"></script>
	<script type="text/javascript" src="js/mainStati.js"></script>
	<script type="text/javascript" src="js/teamBanner.js"></script>
	<script type="text/javascript" src="js/loginPlace.js"></script>
</body>
</html>