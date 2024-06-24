<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${path}/chatRoom" method="GET">
	    <input type="text" name="inputValue">
	    <button type="submit">채팅</button>
	</form>
		<a href="${path }/postMessage">쪽지=</a>
</body>
</html>