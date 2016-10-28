<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--
	공모전 글보기 페이지 
 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 매 페이지 마다 defaultCss는 꼭 입력 -->
<link rel="stylesheet" type="text/css" href="../css/defaultCss.css">
<link rel="stylesheet" type="text/css" href="../css/indexCss.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- 게시판, 글작성 페이지에는 BoardDefault.css도 꼭 입력 -->
<link rel="stylesheet" type="text/css"
	href="../css/Board/BoardDefault.css">
<link rel="stylesheet" type="text/css"
	href="../css/CompetitionBoardCss/CompetitionViewCss.css">
<title>공모전 글보기</title>
</head>
<body>
	<div id="background"></div>
	<!-- include로 가져올 지점 시작 -->
	<div id="titleBar"></div>
	<div id="competition_front_content">
		<div class="container">
			<p class="title">
				<img src="../img/CompetitionBoard/con_title_logo.gif"> 공모전 이름
			</p>
			<div class="front_content_view">
				<div class="front_content_img">
					<img src="../img/CompetitionBoard/com_img1.jpg">
				</div>
				<div class="front_content_info">
					<ul>
						<li>
							<div class="front_content_info_title">공모전 명</div>
							<div class="front_content_info_sentence">공모전 이름입니다.</div>
						</li>
						<li>
							<div class="front_content_info_title">주 회사</div>
							<div class="front_content_info_sentence">공모전 회사</div>
						</li>
						<li>
							<div class="front_content_info_title">응모분야</div>
							<div class="front_content_info_sentence">공모전 분야</div>
						</li>
						<li>
							<div class="front_content_info_title">공모전 명</div>
							<div class="front_content_info_sentence">공모전을 공모하기 위해서 만들어진
								공모전을 공모하는 공모전</div>
						</li>
						<li>
							<div class="front_content_info_title">접수기간</div>
							<div class="front_content_info_sentence">2016.11.21 ~
								2016.12.24</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div id="competition_end_content">
		<div class="container">
			<!-- 게시판 제목 -->
			<p class="title">
				<img src="../img/CompetitionBoard/con_title_logo.gif"> 상세내용
			</p>
			<!-- 상세 내용 출력 -->
			<div class="end_content_view">
				<pre class="end_content_sentence">
					안녕하세오 감사헤오 잘잇어오 고마워오 잘가오 PPick PPickfhkjshjksdhfkjshdskhsdkhfsdkhdkshdkjfhsjhf
					sdfjfhjkdhajkshfkjdahskljfhkjsdhfkjdhfjdhfjhdjfhdjhfjdhfjdhjfhdjhfjhdjhdjhdjdhjdhjhjfhdjhfdjfhjsdfghjkjhgfdsdfghjkasdasdasd
					
					안녕하세요
					
					
					감ㅅ합ㄴㅁ엄나ㅣ

					Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
					tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
					quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
					cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
					proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

					Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
					tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
					quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
					cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
					proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
					
					Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
					tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
					quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
					cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
					proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
					
					Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
					tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
					quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
					cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
					proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

				</pre>
			</div>
			<div class="end_content_btns">
				<div class="end_content_update">수정하기</div>
				<div class="end_content_delete">삭제하기</div>
			</div>
		</div>
		<div class="board_background"></div>
	</div>

	<!-- 이미지 크게 띄우기 -->
	<div class="img_background">
		<div class="img_close_btn">X</div>
		<div class="img_place">
			<img src="../img/CompetitionBoard/com_img1.jpg">
		</div>
	</div>

	<!-- 삭제 경고창 -->
	<div class="delete_background">
		<div class="login_box" id="competitionDelete">
			<div class="loginTitleBar">
				<span>공모전 삭제</span>
			</div>
			<p>정말로 삭제하시겠습니까?</p>
			<div class="btns">
				<input type="button" name="deleteYes" value="네"> <input
					type="button" name="deleteNo" value="아니요">
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript"
		src="../js/smoothscroll-for-websites-master/SmoothScroll.js"></script>
	<script type="text/javascript"
		src="../js/CompetitionBoardJs/CompetitionViewJs.js"></script>
</body>
</html>