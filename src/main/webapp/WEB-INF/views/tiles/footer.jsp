<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- SocketJS CDN -->
<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
<script src="/resources/js/chat.js"></script>

<!-- 채팅방 영역 -->
<div id="totChatArea"></div>

<!-- 채팅, Top 아이콘 -->
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
#chatArea {
	position: fixed;
	right: 80px;
	bottom: 11px;
	background-color: white;
	width: 300px;
	height: 450px;
	z-index: 15;
	border-radius: 10px;
}

.totChatDIV>.memberWrapper {
	border-bottom: 1px solid black;
	height:80px;
	overflow: auto;
	overflow-x: hidden;
}
.totChatDIV>.memberWrapper>.member {
	text-overflow: clip;
	display: inline-block;
	font-size: 12px;
	font-weight: bold;
	padding-left: 8px;
}

.totChatDIV > .memberWrapper .detailM {padding-bottom: 2px;}


.totChatDIV>.countWrapper {
	border-bottom: 1px solid black;
	height:50px;
	overflow: auto;
	overflow-x: hidden;
}
.totChatDIV>.countWrapper>.count {
	text-overflow: clip;
	display: inline-block;
	font-size: 12px;
	font-weight: bold;
	padding-left: 8px;
}

.totChatDIV>.chatWrapper {
	border-bottom: 1px solid black;
	height: 280px;
	padding: 5px 0;
	overflow-y: auto;
	_overflow-x: hidden;
}

.totChatDIV > .chatWrapper > .well {
	_text-overflow: clip;
	_display: inline-block;
	font-weight: bold;
	padding-left: 8px;
	word-break:break-all;
}
.totChatDIV > .chatWrapper > .well div {padding-bottom: 3px;}
.totChatDIV > .chatWrapper > #chatdata {}

.totChatDIV>.msgWrapper {
	background-color: black;
}

#chatdata .status {color:#069;}

#msg {
	width: 100%;
   	height: 21px;
    display: inline-block;
    font-size: 14px;
    font-weight: bold;
    vertical-align: middle;
    border: none;
}
#btnSend {
	width: 100%;
    /* height: 21px; */
    display: inline-block;
    font-size: 14px;
    font-weight: bold;
    vertical-align: middle;
    border: none;
}

#msgWrapper {
	position: fixed;
	width: 300px;
	bottom: 11px;
	overflow: hidden;
}
</style>
<script>
function chatLoad(){
	var isLoad = $('#isLoad').val();
	if(isLoad == 0){
		//chatZoneCreate
		$('#totChatArea').append('<div id="chatArea"></div>');
		
		//chat load in Zone
		$("#chatArea").load("/chattingView",function(){
			$('#isLoad').val(1);
		});
	}else if(isLoad ==1){
		console.log(socket);
		socket.close();
		$('#isLoad').val(0);
		$("#totChatArea").empty();
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