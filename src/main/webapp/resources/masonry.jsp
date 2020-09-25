<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Ajax -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- JQuery  -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.js"
	integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
	crossorigin="anonymous"></script>
<!-- JQuery masonry CDN -->
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
<link href="/resources/css/base.css" rel="stylesheet">
<link href="/resources/css/button.css" rel="stylesheet">
</head>
<body>
	<div class="grid">
		<div class="grid-sizer"></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item grid-item--width2"><img src="/resources/img/main/8.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/7.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/3.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/9.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/5.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/5.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/2.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/7.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/7.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/2.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/5.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/2.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/7.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
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
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
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
</body>
<script>
	var msnry = new Masonry('.grid', {
		itemSelector : '.grid-item',
		// columnWidth: 200
		columnWidth : '.grid-sizer',
		percentPosition : true,
		gutter : 10,
	});
	imagesLoaded('.grid').on('progress', function() {
		msnry.layout();
	});
</script>
</html>