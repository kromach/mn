<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/png"  href="resources/images/svnicon.png"/> <!-- favicon fix -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- jQuery, bootstrap CDN -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
	<!-- SocketJS CDN -->
	<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
</head>
<!--chat setting-->
<script type="text/javascript">
$(document).ready(function(){
	//chat 연결
	//default 메세지 출력
	connect();
	getLog();
	$('#btnSend').on('click', function(evt) {
		evt.preventDefault();
		if (socket.readyState !== 1) return;
		let msg = $('input#msg').val();
		socket.send(msg);
	});
});
</script>
<style>
.chatWrapper{
	width:1000px;
}
</style>
<body>
	<div class="chatWrapper">
	<h1>${nickName}</h1>
	<br>
	<div class = "member">채팅 참여자 리스트
		<div class = "detail_member" ></div>
	</div>
	<br>
	<div class = "count">인원
		<div class = "detail_count" ></div>
	</div>
	<br>
	<div class="well" id="chatdata">
   		<!-- User Session Info Hidden -->
   		<input type="hidden" value='${userid}' id="sessionuserid">
   	</div>
    <br>
	<div>
		<input type="text" id="msg" value="1212"/>
   		<input type="button" id="btnSend" value="전송"/>
   	</div>
   	</div>
</body>
</html>