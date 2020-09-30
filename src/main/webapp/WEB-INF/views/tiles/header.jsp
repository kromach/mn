<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Header // -->
<div id="header" class="header_color">
	<div class="header_area clfix">
		<div class="h_logo fl">
			<h1 class="logo"><a href="/">마시는 녀석들</a></h1>
		</div>
		<div id="menu-wrapper" class="fr">
	    	<div id="hamburger-menu"><span></span><span></span><span></span></div>
		</div>
		<div class="fr h_menu">
			<ul>
				<li><a href="/ready/product/pick.jsp">admin</a></li>
				<li><a href="/ready/member/logout.jsp" class="logout">LOGOUT</a></li>
 			</ul>
		</div>
	</div>
	<!-- 
	<div style="width: 100%; text-align: center;position: relative;font-size: 40px;color: white;z-index: 100;top: -280px;height: 22px">
		술마시는 시간을 낭비하는 시간이라고 생각하지 말라.<br/>
		그 시간에 당신의 마음은 쉬고 있으니까.<br/>
		- 탈무드
	</div> -->
	<div class="center-text display-none">
		<span>&nbsp;술은 지금까지 인간이 만들어 낸 것 중 가장 큰 행복을 만들어내는 것이다.&nbsp;<br/></span>
		<span>&nbsp;-새뮤얼 존슨&nbsp;</span>
	</div>
</div>
<div class="margin-bottom20" style="height: 3px; width: 100%; background: #2f4050; border-top: #23303d; border-bottom: #475665;"></div>
<nav class="rightMenu" style="display: none;">
	<ul>
		<li>
			<a href="#profile"><div class="fa fa-wine-glass-alt"></div> 주류 저장소</a>
		</li>
		<li class="sub-menu">
			<a href="#board"><div class="fa fa-list-alt"></div> 게시판 <div class="fa fa-caret-up fr"></div></a>
			<ul style="display: block;">
				<li><a href="#board"> 자유게시판 </a></li>
				<li><a href="#board"> 읽을거리 </a></li>
			</ul>
		</li>
		<li>
			<a href="#my"><div class="fa fa-award"></div> 나의 활동내역</a>
		</li>
		<li class="sub-menu">
			<a href="#product"><div class="fa fa-wine-bottle"></div> 전통주 주문 <div class="fa fa-caret-up fr"></div></a>
			<ul style="display: block;">
				<li><a href="#product"> 우주마켓 </a></li>
				<li><a href="#product"> 구매내역 </a></li>
			</ul>
		</li>
		<li>
			<a href="#event"><div class="fa fa-bullhorn"></div> 이벤트</a>
		</li>
	</ul>
	<div class="company-info">
		<p>(주)마녀</p>
		<p class="margin-top10">대표자명 : 김영성 | 전화번호 : 02-6020-0055</p>
		<p class="margin-top5">사업자등록번호 : 119-81-85582</p>
		<p class="margin-top5">서울 관악구 남부순환로 1820 14층</p>
	</div>

</nav>
<script type="text/javascript">
	$(function() {
		function slideMenu() {
			var activeState = $("#menu-container .menu-list")
					.hasClass("active");
			$("#menu-container .menu-list").fadeToggle(400);
		}
		$("#menu-wrapper").click(function(event) {
			event.stopPropagation();
			$("#hamburger-menu").toggleClass("open");
			$(".rightMenu").fadeToggle(400);
		});

	}); // jQuery load

	$(".sub-menu a").click(function() {
		$(this).parent(".sub-menu").children("ul").slideToggle("100");
		$(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
	});
</script>