<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<script>
	$(function() {
		$("#header").removeClass("header_color").addClass("main_header");
		
		var img_random = Math.ceil(Math.random()*7);
		$(".main_header").css({"background":"url(/resources/img/main/main" + img_random + ".jpg) no-repeat center center"});
		
		$(".t" + (img_random % 2)).removeClass("display-none")
	});
</script>
<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width6"><div id = "editor"></div>
		<div class="grid-item grid-item--width2"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/8.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/7.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/3.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/9.jpg" /></div>
		<div class="grid-item grid-item--width3"><img src="/resources/img/main/5.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/5.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/2.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/7.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item grid-item--width5"><img src="/resources/img/main/7.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/5.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/2.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/7.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item grid-item--width4"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/8.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/7.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/3.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/9.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/7.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/2.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/5.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/2.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/7.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/8.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/3.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/8.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/3.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/8.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/3.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/3.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/3.jpg" /></div>
	</div>
</div>
<!-- <div class="chat">
	<a href="/chat"><img src="/resources/img/main/chat.png" ></a>
</div> -->
<script src="/resources/js/imageLoad.js"></script>
<!-- 데이터 스크롤해서 붙이는 스크립트  -->

<script type="text/javascript">
$(window).scroll(
	function() {
		// A(B+C) : document 높이 (고정)
		//console.log($(document).height());
		// B : browser 높이 (최상단 기본값)
		//console.log($(window).height());
		// C : 스크롤 위치
		//console.log('SCROLL_TOP' + $(window).scrollTop());
		if ($(window).scrollTop() >= $(document).height()
				- $(window).height() - 100) {
			//호출 메서드
		}
	});
</script>