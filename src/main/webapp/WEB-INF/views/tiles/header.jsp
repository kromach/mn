<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Header // -->
<script>
$(function() {
	$(".dropDownMenu").hide();
	$("#menuBtn").hover(function() {
		$(".dropDownMenu").show();
	}, function() {
		$(".dropDownMenu").hide();
	})
});
</script>
<div id="header">
	<div class="header_area clfix">
		<div class="h_logo fl">
			<h1 class="logo"><a href="/">마시는 녀석들</a></h1>
		</div> 
		<div class="fr h_menu"> 
			<ul>
				<%-- 일반회원 --%>
				<li><a href="/ready/product/pick.jsp">${sessionScope.memNickName}</a></li>
				<c:if test="${not empty sessionScope.memId}">
					<li><a href="/ready/member/logout.jsp" class="logout">LOGOUT</a></li>
				</c:if>
				<c:if test="${empty sessionScope.memId}">
					<li><a href="/ready/member/logout.jsp" class="logout">LOGIN</a></li>
				</c:if>
				<li class="menu-li"><i id="menuBtn" class="fas fa-bars fa-3x text-right"></i></li>
			</ul>
			<div class="dropDownMenu">
				<div class='content'>상품관리</div>
				<div class='content'>상품관리</div>
				<div class='content'>상품관리</div>
				<div class='content'>상품관리</div>
				<div class='content'>상품관리</div>
			</div>
		</div>
	</div>
</div>
<div class="margin-bottom20" style="height: 3px; width:100%; background:#2f4050; border-top:#23303d; border-bottom:#475665;"></div>
