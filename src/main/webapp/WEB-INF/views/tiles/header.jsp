<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<li><a href="/ready/product/pick.jsp">admin</a></li>
				<li><a href="/ready/member/logout.jsp" class="logout">LOGOUT</a></li>
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
