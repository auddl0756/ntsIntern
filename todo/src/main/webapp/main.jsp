<%@page import="com.nts.intern.dto.TodoResponseDto"%>
<%@page import="com.nts.intern.dto.TodoDto"%>
<%@page import="java.util.*" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@page import="com.fasterxml.jackson.databind.type.TypeFactory" %>
<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String jsonString= (String)request.getAttribute("data");

	ObjectMapper mapper = new ObjectMapper();
		
	List<TodoResponseDto> list = mapper.readValue(jsonString, new TypeReference<List<TodoResponseDto>>() {});
	
%>
 
<%=jsonString %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!--  
<c:if test="${ number==10}">
	number는10과 같다
</c:if>

<br/>

<c:choose>
	<c:when test="${number2==0 }">
		number2는 0과 같다
	</c:when>
	<c:when test="${number2==1 }">
		number2는 1과 같다
	</c:when>
	<c:otherwise>
		number2는 0,1 이외의 수다
	</c:otherwise>
</c:choose>
-->

<!-- 
<c:forEach var="item" items="${data}">
	${item[type]}
</c:forEach>
 -->
 
 
</body>
</html>