<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width6">
			<h1 align="center">회원가입</h1>
		</div>
		<div class="grid-item detail-width3">
			<div class="infoBox">
				<h1 class="text-center">일반 회원 가입</h1>
				<ul>
					<li>전통주 구매 등의 서비스를 이용할 수 있는 개인회원으로 가입합니다.</li>
				</ul>
				
				<p class="text-center margin-y20">
					<a href="/member/signupUser" class="btn btn-xl btn-mint" style="width:300px;height:60px;line-height:60px;">일반 회원 가입</a>
				</p>
			</div>
		</div>				
		<div class="grid-item detail-width3">
			<div class="infoBox">
				<h1 class="text-center">판매자 기업 회원 가입</h1>
				<ul>
					<li>상품을 등록하여 판매할 수 있는 기업회원으로 가입합니다.</li>
					<li>기업회원으로 가입하기 위해서는 사업자 등록번호가 반드시 필요합니다.</li>
					<li>가입시 사업자 등록증을 제출해야 가입이 가능합니다.</li>
				</ul>
				<p class="text-center margin-y20">
					<a href="/member/signupSales" class="btn btn-xl btn-orange" style="width:300px;height:60px;line-height:60px;">판매자 회원 가입</a>
				</p>
			</div>
		</div>
		<!-- 
		<div class="grid-item detail-width3">				
			<button class="statusBtnUser" onclick="window.location.href='/member/signupUser'">일반사용자</button>	
		</div>
		<div class="grid-item detail-width3">
			<button class="statusBtnSales" onclick="window.location.href='/member/signupSales'">판매자</button>
		</div> -->
	</div>
</div>
<script src="/resources/js/imageLoad.js"></script>
<script type="text/javascript">
	function kakaoLogin() {
		var client_id = "${restApikey}";
		var redirect_uri = "${callback_URL}";
		var location ='https://kauth.kakao.com/oauth/authorize?response_type=code&client_id='+client_id+'&redirect_uri='+redirect_uri;
		window.location.href=location;
	}
</script>
