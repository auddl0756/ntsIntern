<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
		<ul class="todo">TODO
		</ul>

		<ul class="doing">DOING
		</ul>

		<ul class="done">DONE
		</ul>
	</nav>
	
	<article class="center">
		<div class="todo">
			<ul>
				<c:forEach var="item" items="${response}">
					<c:choose>
							<c:when test="${item.type eq'TODO'}">
								<li>
									${item.title}<br/>
									등록 날짜 : ${item.regDate } 
									${item.name }
									${item.type }
									<button> =></button>
								</li>
							</c:when>	
						
						<c:otherwise>
							never!
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
	</article>

</body>
</html>