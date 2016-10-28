<%@page import="java.util.List"%>
<%@page import="kongs.model.dto.Contest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kongs.util.PageAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 매 페이지 마다 defaultCss는 꼭 입력 -->
<link rel="stylesheet" type="text/css" href="../css/defaultCss.css">
<link rel="stylesheet" type="text/css" href="../css/indexCss.css">

<!-- 게시판, 글작성 페이지에는 BoardDefault.css도 꼭 입력 -->
<link rel="stylesheet" type="text/css" href="css/Board/BoardDefault.css">
<link rel="stylesheet" type="text/css" href="../js/jquery-ui/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="../css/CompetitionBoardCss/CompetitionWriteCss.css">

<title>글쓰기</title>

</head>
<body>
	<div id="background"></div>
	
	<!-- include로 가져올 지점 시작 -->
	<div id="titleBar"></div>
	<div id="CompetitionWrite_contentView">
		<div class="container">
			<p class="title">
				<img src="../img/CompetitionBoard/con_title_logo.gif"> 공모전 작성
			</p>
			<form action="<c:url value="/" />front?command=insertcontest" method="post" name="compertitionWrite" enctype="multipart/form-data">
				<ul>
					<li><div class="com_write_subTitle">공모전명:</div> <input type="text" name="name" placeholder="공모전명" required /></li>
					<li><div class="com_write_subTitle">응모분야:</div>
					<select name="category">
							<option value="design">디자인</option>
							<option value="adv">광고/마케팅</option>
							<option value="id">IT/소프트웨어</option>
							<option value="media">영상/사진</option>
							<option value="idea">기획/아이디어</option>
							<option value="venture">취업/창업</option>
					</select></li>
					<li><div class="com_write_subTitle">주최:</div> <input type="text" name="company" placeholder="주최" required /></li>
					<li><div class="com_write_subTitle">시작일:</div> <input type="text" id="startDatepicker" placeholder="공모전 시작일" name="startDate" required></li>
					<li><div class="com_write_subTitle">종료일:</div> <input type="text" id="endDatepicker" placeholder="공모전 종료일" name="endDate" required></li>
					<li><div class="com_write_subTitle">이미지:</div> <input type="file" name="fileName"></li>
					<!-- 만약에 종료일이 시작일 보다 작을때 페이지 이동 제어 -->
					<li><input type="submit" value="등록하기"></li>
				</ul>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/CompetitionBoardJs/CompetitionBoardJs.js"></script>
<script type="text/javascript" src="../js/smoothscroll-for-websites-master/SmoothScroll.js"></script>
</html>