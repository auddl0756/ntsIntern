<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1 class="logo">
	<a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span
		class="spr_bi ico_n_logo">네이버</span>
	</a> <a href="/" class="lnk_logo" title="예약"> <span
		class="spr_bi ico_bk_logo">예약</span>
	</a>
</h1>

<c:if test="${empty email}">
	<a href="/login" class="btn_my"> <span class="viewReservation"
		title="예약확인"> 예약확인</span>
	</a>
</c:if>

<c:if test="${!empty email}">
	<a href="/myreservation/${email}" class="btn_my"> <span
		class="viewReservation" title="예약확인"> ${email} </span>
	</a>
</c:if>

