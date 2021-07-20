<%@page import="com.nts.intern.dto.TodoResponseDto"%>
<%@page import="java.util.*" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@page import="com.fasterxml.jackson.databind.type.TypeFactory" %>
<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%!
	private static final ObjectMapper mapper = new ObjectMapper();
%>

<%
	String jsonString= (String)request.getAttribute("response");
	List<TodoResponseDto> list = mapper.readValue(jsonString, new TypeReference<List<TodoResponseDto>>() {});
%>
 
<%
	for(int i=0;i<list.size();i++){
%>
	<%=list.get(i).getId() %>
	<%=list.get(i).getTitle() %>
	<%=list.get(i).getName() %>
	<%=list.get(i).getSequence() %>
	<%=list.get(i).getType() %>
	<%=list.get(i).getRegDate() %>
	 <br/>
<%} %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/todoList.css">
</head>
<body>
 
 <nav class="top">
        <h1>나의 해야할 일들</h1>
		<ul>
			<li><a href="enroll.html">새로운 TODO 등록</a></li>
		</ul>
	</nav>

	<nav class="center-nav">
        <ul class="todo">
			TODO
		</ul>

        <ul class="doing">
            DOING
        </ul>

        <ul class="done">
            DONE
        </ul>
	</nav>

    <article class="center">
        <ul>
            <li>1</li>
            <li>2</li>
            <li>3</li>
            <li>4</li>
            <li>5</li>
            <li>6</li>
            
        </ul>
    </article>
 
 
 
</body>
</html>