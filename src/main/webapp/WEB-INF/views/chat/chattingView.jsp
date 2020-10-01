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
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script> <!-- msie 문제 해결 -->
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
	<!-- SocketJS CDN -->
	<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
</head>
<body>
	<h1>Chatting Page (id: ${userId})</h1>
	<div>
		<input type="button" id="chattinglistbtn" value="채팅 참여자 리스트">
	</div>
	<br>
	<div>
		<div>
			<input type="text" id="msg" value="1212"/>
    		<input type="button" id="btnSend" value="전송"/>
    	</div>
    	<br>
    	<div class="well" id="chatdata">
    		<!-- User Session Info Hidden -->
    		<input type="hidden" value='${userid}' id="sessionuserid">
    	</div>
	</div>
</body>
<script type="text/javascript">
var socket = null;
function connect(){
	var ws = new WebSocket("ws://localhost:8080/replyEcho");
	socket = ws;
	ws.onopen = function () {
	    console.log('Info: connection opened.');
	    
	};
	ws.onmessage = function (event) {
	    console.log('Receive MSG : ', event.data+'\n');
	};

	ws.onclose = function(event) {
		console.log('Info: connection closed.');
		//setTimeout( function(){ connect(); }, 1000); // retry connection!!
	};
	
	ws.onerror = function(err) {
		console.log('ERR: connection closed.', err);
	};
}
$(document).ready(function(){
	$('#btnSend').on('click', function(evt) {
		evt.preventDefault();
		if (socket.readyState !== 1) return;
		let msg = $('input#msg').val();
		alert(msg);
		socket.send(msg);
	});
	connect();
});
</script>
</html>