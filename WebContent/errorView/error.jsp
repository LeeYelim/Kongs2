<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<c:url value="/" var="url"/>
<link rel="stylesheet" href="${url}css/style.css">
</head>
<body>
	죄송합니다. 문제가 발생하였습니다.<br>
	발생된 문제 : ${requestScope.errorMsg}<br>

	<a href="front"> 처음으로 돌아가기</a>
	<a href="javascript:history.back()">뒤로가기</a>
</body>
</html>