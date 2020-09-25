<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head><script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
<body>
<div class="grid">
<div class="grid-sizer"></div>
<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
<div class="grid-item"><img src="/resources/img/main/8.jpg" /></div>
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
</div>
</body>
<script>
	var msnry = new Masonry('.grid', {
		itemSelector : '.grid-item',
		// columnWidth: 200
		columnWidth : '.grid-sizer',
		percentPosition : true,
		gutter : 20,
	});
	imagesLoaded('.grid').on('progress', function() {
		msnry.layout();
	});
</script>
</html>