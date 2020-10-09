<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<div class="grid-Wrapper">
	<div class="grid"> 
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width6 text-left">
			${dkName}
		</div>
		<div class="grid-item"><a onclick="setDkcode('DK00001')"><img src="/resources/img/main/4.jpg" /></a></div>
		<div class="grid-item"><img src="/resources/img/main/3.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/8.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/2.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/9.jpg" /></div>		
		<div class="grid-item grid-item--width2"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item grid-item--width2"><img src="/resources/img/main/8.jpg" /></div>
	</div>
</div>
<script>
	function setDkcode(dkCode) {
		var data = "&" + $("#schForm").serialize();
		
		var url = "detail?dkCode=" + dkCode + data;
		
		console.log(url)
		location.href = url;
	}

</script>

<script src="/resources/js/imageLoad.js"></script>