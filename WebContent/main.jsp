<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>::콩스</title>
<!-- 매 페이지 마다 defaultCss는 꼭 입력 -->
<link rel="stylesheet" type="text/css" href="css/defaultCss.css">
<link rel="stylesheet" type="text/css" href="css/indexCss.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- ID중복 체크  -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#id").keyup(function(){
		var id=	$('#id').val();
		$.ajax({
			type:"POST",
			url:"front?command=loginidcheck",
			dataType:"text",
			data:"id=" + id,
			success: function(data){
				$("#id_check").val(data);
			}
		});
	});
})
</script>
</head>
<body>
	<div id="mainMenu">
		<div class="container">
			<div id="logo">
				<a href="index.jsp"><img src="img/logo.png"></a>
			</div>
			<ul id="menu">
				<li><a href="<c:url value="/"/>front?command=contest">공모전 정보</a></li>
				<li><a href="<c:url value="/"/>front?command=free">커뮤니티</a></li>
				<li><a href="map.jsp">스터디룸</a></li>
				<c:choose>
					<c:when test="${empty sessionScope.user}">
						<li id="loginView"><a>Login</a></li>
					</c:when>
					<c:when test="${sessionScope.user!=null}">
						<li id="profileShow"><a>Profile</a></li>
					</c:when>
				</c:choose>
			</ul>
			<div id="profile">
				<div id="trangle-up"></div>
				<div id="profileView">
					<div id="profileImage">
						<img alt="유저 프로필 사진" src="img/main/team_profile1.jpg">
						<ul>
							<li class="profile_nick">${sessionScope.user.nick}님PROFILE</li>
						</ul>
					</div>
					<div id="profile_btn">
					<c:choose>
						<c:when test="${sessionScope.user.teamId != null}">
						<a>팀 게시판 이동</a> 
						</c:when>
						<c:when test="${sessionScope.user.teamId == null}">
						<a name="addTeam">팀 만들기</a> 
						</c:when>
					</c:choose>
						<a name="userModify">개인정보 수정</a> 
						<a href="logout.jsp">로그아웃</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<article id="imgBanner">
		<ul>
			<li><img src="img/main/banner1.jpg" alt="">
				<div class="container">
					<div class="bleft">
						공모전으로 우리들의 창의력을 발휘하자!<br> 첫번째 글<br> <a href="#">현재
							진행중인 공모전 보기</a>
					</div>
				</div></li>
			<li><img src="img/main/banner2.jpg" alt="">
				<div class="container">
					<div class="bcenter">
						함께하면 재미가 두배! 사람과의 인연은 덤으로!<br> 두번째 글<br> <a href="#">팀
							관련 페이지 보기</a>
					</div>
				</div></li>
			<li><img src="img/main/banner3.jpg" alt="">
				<div class="container">
					<div class="bright">
						여기에는 무엇을 채워야 할지 모르겠다!<br> 세번째 글<br> <a href="#">어디론가
							가는 페이지 보기</a>
					</div>
				</div></li>
		</ul>

		<div id="banner_btn_box"></div>

		<div id="banner_np_btn_box">
			<button data-type="prev">&lt;</button>
			<button data-type="next">&gt;</button>
		</div>

	</article>
	<div class="clear"></div>
	<div id="mainStati">
		<div class="stati_bg">
			<div>
				<span>현재 생성된 공모전수</span><br> <span>${size}</span>
			</div>
			<img src="img/main/stati1.jpg" alt="공모전수">
		</div>
		<div class="stati_bg">
			<div>
				<span>현재 구성된 팀수</span><br> <span>${teamcount}</span>
			</div>
			<img src="img/main/stati2.jpg" alt="팀수">
		</div>
		<div class="stati_bg">
			<div>
				<span>가입한 회원수</span><br> <span>${membercount}</span>
			</div>
			<img src="img/main/stati3.jpg" alt="회원수">
		</div>
	</div>
	<div class="clear"></div>
	<div id="photo">
		<div class="container">
			<h3 class="material-icons main_title">
				<div class="title_line"></div>
				<a href="#"> <span>Competition</span>
				</a>
			</h3>

			<!-- 공모전-->
			<div class="photo-view">
				<c:forEach items="${list}" var="contest">
					<div class="photo-image">
						<div class="photo-img">
							<a href="#"></a> <img src="img/contestImg/${contest.photoName}">
						</div>
						<h4>${contest.title}</h4>
					</div>
				</c:forEach>
				<p>
					<span></span>
				</p>
			</div>
		</div>
	</div>
	<div id="team">
		<div id="team-view">
			<div class="container">
				<h3 class="material-icons main_title">
					<div class="title_line"></div>
					<a href="#"> <span>Team</span>
					</a>
				</h3>
				<!-- 팀 소개-->
				<div id="team_img_view">
					<div class="team_content">
						<img src="img/main/team_profile1.jpg">
						<p class="competition_name">웹 프로젝트</p>
						<p class="team_name">엄청나게 좋은 팀</p>
						<p class="team_info">으으이으이ㅢ읭 ㅣ의의으이 ㅢ의의으 ㅣ의의으이ㅡ ㅣ의의 ㅡ이ㅡ이잉 ㅢ으이읭
							Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
							eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
							enim ad minim veniam, quis nostrud exercitation ullamco laboris
							nisi ut aliquip ex ea commodo.</p>
					</div>
					<div class="team_content">
						<img src="img/main/team_profile2.jpg">
						<p class="competition_name">자바 프로젝트</p>
						<p class="team_name">좋은 팀</p>
						<p class="team_info">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit, sed do eiusmod tempor incididunt ut labore et
							dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
							exercitation ullamco laboris nisi ut aliquip ex ea commodo
							consequat. Duis aute irure dolor in reprehenderit in voluptate
							velit esse.</p>
					</div>
					<div class="team_content">
						<img src="img/main/team_profile3.jpg">
						<p class="competition_name">무언가 프로젝트</p>
						<p class="team_name">팀 팀</p>
						<p class="team_info">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit, sed do eiusmod tempor incididunt ut labore et
							dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
							exercitation ullamco laboris nisi ut aliquip ex ea commodo
							consequat. Duis aute irure dolor in reprehenderit in voluptate
							velit esse.</p>
					</div>
				</div>
				<div id="team_np_btn_box">
					<button id="teamLeft">&lt;</button>
					<button id="teamRight">&gt;</button>
				</div>
				<h3 class="material-icons main_title">
					<div class="title_line"></div>
					<a href="#"> <span>moveing</span>
					</a>
				</h3>
			</div>
		</div>
	</div>
	<div id="loginPlace">
		<div class="login_box" id="loginForm">
			<div class="loginTitleBar">
				<span>로그인</span> <input type="button" name="cancle" value="X">
			</div>
			<!-- 로그인 폼-->
			<form action="front?command=login" method="post" onsubmit="">
				<input type="text" name="id" placeholder="ID" required> <input
					type="password" name="pass" placeholder="PW" required> <input
					type="submit" name="login" value="LOGIN"> <input
					type="button" name="sign" value="SIGN UP">
			</form>
		</div>
		<div class="login_box" id="signUpForm">
			<div class="loginTitleBar">
				<span>회원가입</span> <input type="button" name="cancle" value="X">
			</div>
			<!-- 회원가입 폼-->
			<form action="front?command=signup" method="post" onsubmit="">
				<input type="text" name="id" placeholder="아이디" required id="id"> 
				<input type="button" name="id_overlap" value="중복 검사" required id="id_check"> <div id=idcheck></div>
				<input type="password" name="pass" placeholder="비밀번호" required> 
				<input type="password" name="pass_re" placeholder="비밀번호 재입력" required>
				<input type="text" name="name" placeholder="이름" required> 
				<input type="text" name="nick" placeholder="닉네임" required> 
				<input type="submit" name="sign" value="회원가입"> 
				<input type="button" name="login" value="로그인 화면으로">
			</form>
		</div>
		<div class="login_box" id="userModify">
			<div class="loginTitleBar">
				<span>정보 수정</span> <input type="button" name="cancle" value="X">
			</div>
			<!-- 수정 폼 -->
			<form action="front?command=loginupdate" method="post" onsubmit="" name="modify">
				<input type="text" name="id" placeholder="아이디" value="${sessionScope.user.id}" required readonly> 
				<input type="text" name="uname" placeholder="이름" value="${sessionScope.user.name}" required readonly>
				<input type="text" name="nick" placeholder="닉네임" value="${sessionScope.user.nick}" required> 
				<input type="password" name="pass" placeholder="현재비밀번호" value="password" required> 
				<input type="password" name=modipass placeholder="변경할 비밀번호" required>
				<input type="password" name="modipass_re" placeholder="비밀번호 재입력" required>
				<input type="submit" name="sign"value="정보수정"> 
				<input type="button" name="secession" value="탈퇴하기">
			</form>
		</div>
		<div class="login_box" id="userSecession">
			<div class="loginTitleBar">
				<span>회원 탈퇴</span>
			</div>
			<p>정말로 탈퇴하시겠습니까?</p>
			<form action="front?command=logindelete" method="post" name="userdelete">
			<input type="text" name="id" placeholder="아이디" value="${sessionScope.user.id}" required readonly hidden="">
			<input type="password" name="pass" required	> 
			<div class="btns">
				<input type="submit" name="secessionYes" value="네">
				<input type="button" name="secessionNo" value="아니요">
			</div>
			</form>
		</div>
	</div>
		<div class="login_box" id="addTeam">
			<div class="loginTitleBar">
				<span>팀 만들기</span> <input type="button" name="cancle" value="X">
			</div>
			<!-- 팀 만드는 부분 -->
			<form action="" method="get" name="addTeam" id="addTeamSubmit">
				<input type="text" name="teamName" placeholder="팀 이름" required>
				<input type="text" name="competitionName" placeholder="공모전 타이틀" required>
				<input type="text" id = "teamMember" placeholder="등록할 팀원">
				<input type="button" name="findMember" value="조회하기">
				<textarea readonly="readonly"></textarea>
				<input type="submit" value="팀 등록">
			</form>
		</div>
		<div class="login_box" id="showTeamUserProfile">
			<div class="loginTitleBar">
				<span>유저정보</span>
			</div>
			<div class="showUserFalse">
				<p>유저를 찾을 수 없거나<br>이미 추가된 팀원입니다.</p>
				<form>
					<input type="button" name="userFalse" value="돌아가기">
				</form>
			</div>
			<div class="showUserTrue">
				<!-- js에서 텍스트 설정. -->
				<p></p>
				<form>
					<input type="button" name="userTrue" value="등록">
					<input type="button" name="userCancle" value="돌아가기">
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="js/smoothscroll-for-websites-master/SmoothScroll.js"></script>
	<script type="text/javascript" src="js/mainBanner.js"></script>
	<script type="text/javascript" src="js/mainStati.js"></script>
	<script type="text/javascript" src="js/teamBanner.js"></script>
	<script type="text/javascript" src="js/loginPlace.js"></script>
</body>
</html>