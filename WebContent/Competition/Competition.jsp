<%@page import="java.util.List"%>
<%@page import="kongs.model.dto.Contest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kongs.util.PageAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- 게시판 페이지 -->
<meta charset="UTF-8">
<title>게시판</title>
<!-- 매 페이지 마다 defaultCss는 꼭 입력 -->
<link rel="stylesheet" type="text/css" href="css/defaultCss.css">
<link rel="stylesheet" type="text/css" href="css/indexCss.css">
<!-- 게시판, 글작성 페이지에는 BoardDefault.css도 꼭 입력 -->
<link rel="stylesheet" type="text/css" href="css/Board/BoardDefault.css">
<link rel="stylesheet" type="text/css" href="css/CompetitionBoardCss/CompetitionBoardCss.css">
</head>
<body>
	<div id="background"></div>
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
 					<c:when test="${empty sessionScope.user}"><li id="loginView"><a>Login</a></li></c:when>
 					<c:when test="${sessionScope.user!=null}"><li id="profileShow"><a>Profile</a></li></c:when>
 				</c:choose>
			</ul>
			<div id="profile">
				<div id="trangle-up"></div>
				<div id="profileView">
					<div id="profileImage">
						<img alt="유저 프로필 사진" src="img/main/team_profile1.jpg">
						<ul>
							<li class="profile_nick">${sessionScope.user.nick}님 PROFILE</li>
						</ul>
					</div>
					<div id="profile_btn">
						<a href="">팀 만들기</a>
						<a href="">팀 게시판 이동</a>
						<a href="">개인정보 수정</a>
						<a href="<c:url value="/"/>logout.jsp">로그아웃</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<!-- include로 가져올 지점 시작 -->
	<div id="titleBar"></div>
	<div id="com_front_title">
		<div class="container">
			<p class="title">
				<img src="img/CompetitionBoard/con_title_logo.gif"> 분야별 공모전
			</p>
			<ul>
				<li><strong><a href="front?command=contest&keyField=design">디자인</a></strong></li>
				<li><strong><a href="front?command=contest&keyField=advertisement">광고/마케팅</a></strong></li>
				<li><strong><a href="front?command=contest&keyField=it">IT/소프트웨어</a></strong></li>
				<li><strong><a href="front?command=contest&keyField=media">영상/사진</a></strong></li>
				<li><strong><a href="front?command=contest&keyField=idea">기획/아이디어</a></strong></li>
			</ul>
		</div>
	</div>
	<div id="com_front_content">
		<div class="container">
			<ul>
				<c:forEach items="${list}" var="contest">
					<li>
						<div class="com_front_content_img">
							<a href="#">
							<img src="img/contestImg/${contest.photoName}"></a>
						</div>
						<article>
							<a href="#"><h3>${contest.title}</h3></a>
							<p class="com_front_content_date">${contest.startDate} ~ ${contest.endDate}</p>
							<p class="com_front_content_host">${contest.title}</p>
						</article>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div id="com_post_board">
		<div class="container">
			<!-- 게시판 제목 -->
			<p class="title">
				<img src="img/CompetitionBoard/con_title_logo.gif"> 공모전
			</p>
			<div id="tableView">
				<form action="" method="get" onsubmit="">
					<table>
						<tr>
							<th>공모명</th>
							<th>주최</th>
							<th>진행사항</th>
							<th>진행기간</th>
							<th>조회수</th>
						</tr>
						<!-- 게시판 내용 -->
						<c:forEach var="pageList" items="${requestScope.pageList}">
						        <tr>
						        <td><a href="#">${pageList.title}</a></td>
						        <td>${pageList.sponser}</a></td>
						        <td>D - ${pageList.dday}</td>
						        <td>${pageList.startDate}<br>~<br>${pageList.endDate}</td>
						        <td>${pageList.clickCount}</td>
						        </tr>
						</c:forEach>
					</table>
				</form>
				<!-- 공모전 쓰기 버튼 -->
				<div id="listBtn">
					<a href="Board/Board.jsp" class="writeBtn">공모전 쓰기</a>
				</div>
				<!-- 페이징 -->
				<div id="boardPage"><%=PageAction.pageNumber()%></div>
				<div id="boardSearchView">
					<form action="front?command=contest" method="post">
						<div id="boardSearchBar">
							<select name="keyField">
								<option value="title">공모전명</option>
								<option value="sponser">주최사</option>
							</select> 
							<input type="text" placeholder="검색 키워드" name="keyWord"> 
							<input type=submit value="검색">
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="board_background"></div>
	</div>
	<!-- include로 가져올 지점 끝 -->
	<div id="loginPlace">
		<div class="login_box" id="loginForm">
			<div class="loginTitleBar">
				<span>로그인</span>
				<input type="button" name="cancle" value="X">
			</div>
			<!-- 로그인 폼-->
			<form action="front?command=login" method="post" onsubmit="">
				<input type="text" name="id" placeholder="ID">
				<input type="password" name="pass" placeholder="PW">
				<input type="submit" name="login" value="LOGIN">
				<input type="button" name="sign" value="SIGN UP">
			</form>
		</div>
		<div class="login_box" id="signUpForm">
			<div class="loginTitleBar">
				<span>회원가입</span>
				<input type="button" name="cancle" value="X">
			</div>
			<!-- 회원가입 폼-->
            <form action="front?command=signup" method="post" onsubmit="">
            	<input type="text" name="id" placeholder="아이디">
            	<input type="button" name="id_overlap" value="중복 검사">
				<input type="password" name="pass" placeholder="비밀번호">
				<input type="password" name="pass_re" placeholder="비밀번호 재입력">
				<input type="text" name="name" placeholder="이름">
				<input type="text" name="nick" placeholder="닉네임">
                <input type="submit" name="sign" value="회원가입">
                <input type="button" name="login" value="로그인 화면으로">
            </form>
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