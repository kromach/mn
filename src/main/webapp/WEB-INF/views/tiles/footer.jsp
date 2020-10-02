<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- SocketJS CDN -->
<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
<script src="/resources/js/chat.js"></script>
<div id="chatArea"></div>
<div class="fixIcon">
	<input type="hidden" value="0" id="isLoad">
	<c:if test="${not empty sessionScope.memId}">
		<div id="cahtIcon" class="chatIcon icon"
			onclick="chatLoad()">
			<i class="fas fa-comments" style="font-size: 36px; margin-top: 5px;"></i>
		</div>
	</c:if>
	<div id="topIcon" class="topIcon icon display-none margin-top5"
		onclick="$('html').animate({scrollTop : 0}, 300)">
		<i class="fas fa-angle-up" style="font-size: 50px"></i>
	</div>
</div>
<style>
#chatArea{
	position:fixed;
	right:80px;
	bottom:10px;
	background-color: white;
	width: 250px;
	z-index: 20;
}
</style>
<script>
function chatLoad(){
	var isLoad = $('#isLoad').val();
	if(isLoad == 0){
		//chat load
		$("#chatArea").load("/chattingView",function(){
			$('#isLoad').val(1);
		});
	}else if(isLoad ==1){
		console.log(socket);
		socket.close();
		$('#isLoad').val(0);
		$("#chatArea").empty();
	}
}
</script>
<script>
	//top 버튼 제어
	//스크롤 위치 확인
	window.__scrollPosition = document.documentElement.scrollTop || 0;

	document.addEventListener('scroll', function() {
		let documentY = document.documentElement.scrollTop; //스크롤 휠 위치 
		// console.log(documentY); 
		let topIcon = document.getElementById('topIcon');

		if (documentY > 10) { //스크롤 휠 시작시 버튼 노출
			if (topIcon.classList.contains('display-none'))
				topIcon.classList.remove('display-none')
		} else {
			topIcon.classList.add('display-none')
		}
	});
</script>