<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Header // -->
<div id="header" class="header_color">
	<div class="header_area clfix">
		<div class="h_logo fl">
			<h1 class="logo">
				<a href="/">마시는 녀석들</a>
			</h1>
		</div>
		<div id="menu-wrapper" class="fr">
			<div id="hamburger-menu">
				<span></span><span></span><span></span>
			</div>
		</div>
		<div class="fr h_menu">
			<ul>
			<li><a href="/member/modify">${sessionScope.memNickName}</a></li>
			<c:if test="${not empty sessionScope.memId}">
				<li><a href="/member/logout" class="logout">LOGOUT</a></li>
			</c:if>
			<c:if test="${empty sessionScope.memId}">	
				<li><a href="/member/login" class="logout">LOGIN</a></li>
			</c:if>	
			</ul>
		</div>
	</div>
	<!-- 
	<div style="width: 100%; text-align: center;position: relative;font-size: 40px;color: white;z-index: 100;top: -280px;height: 22px">
		술마시는 시간을 낭비하는 시간이라고 생각하지 말라.<br/>
		그 시간에 당신의 마음은 쉬고 있으니까.<br/>
		- 탈무드
	</div> -->
	<div class="center-text t0 display-none">
		<span>&nbsp;술은 지금까지 인간이 만들어 낸 것 중 가장 큰 행복을 만들어내는 것이다.&nbsp;<br /></span>
		<span>&nbsp;-새뮤얼 존슨&nbsp;</span>
	</div>
	<div class="center-text t1 display-none">
		<span>&nbsp;음주는 Fun 하고 Cool 하고 Sexy 해야죠.&nbsp;<br /></span>
		<span>&nbsp;그것이 약속이니까.&nbsp;</span>
	</div>
</div>
<div class="margin-bottom20" style="height: 3px; width: 100%; background: #2f4050; border-top: #23303d; border-bottom: #475665;"></div>
<nav class="rightMenu" style="display: none;">
	<ul>
		<li>
			<a href="/drink/index"><div class="fa fa-wine-glass-alt"></div> 주류 저장소</a>
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
				<li><a href="/product/productlist"> 우주마켓 </a></li>
				<li><a href="/product/myorderlist"> 구매내역 </a></li>
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
		
		// 마우스클릭시 메뉴 레이어가 열려 있으면 닫아준다. 
		$('html').click(function() {
			if ($(".rightMenu").hasClass('active')) { 
				$("#menu-wrapper").click();
			}
		});
		
		// 마우스 클릭 위치가 메뉴 레이어라면 닫히지 않게 막는다.
		$('.rightMenu').click(function(event){
		     event.stopPropagation();
		});
		
		// 햄버거 버튼 영역을 클릭하면 애니메이션 효과와 메뉴 레이어를 토글(toggle) 처리한다.
		$("#menu-wrapper").click(function(event) {
			event.stopPropagation();
			$("#hamburger-menu").toggleClass("open");
			$(".rightMenu").fadeToggle(400).toggleClass("active");
		});

	}); 

	// 메뉴 중에 세부 항목이 있는 메뉴를 클릭하면 세부 항목을 열고 닫는다.
	$(".sub-menu a").click(function() {
		$(this).parent(".sub-menu").children("ul").slideToggle("100");
		$(this).find(".fr").toggleClass("fa-caret-up fa-caret-down");
	});
</script>