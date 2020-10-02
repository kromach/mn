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
<body>
	<h1>Chatting Page (id: ${userId})</h1>
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
	    console.log(event.data+'\n');
	    var data = event.data;
	    var sessionNick = null;
	    var message = null;
	    var status = null;
	    
	    //splite Status
	    var strArr = data.split('|');
	    for(var i=0;i<strArr.length;i++){
	    	//참여 퇴장 완료
	    	if(strArr[i] == 'Status'){
	    		$('.well').append('<div class = "detail" id = "status">'+strArr[i+1]+'</div>');
	    	}
	    	//참여인원 카운트 셋팅완료
	    	if(strArr[i] == 'CountMember'){
	    		console.log("카운트"+strArr[i+1]);
	    		$('.detail_count').html('<div class = "detail" >'+strArr[i+1]+'</div>');
	    	}
	    	//참여자 리스트 셋팅완료
	    	if(strArr[i] == 'JoinMember'){
	    		//전에것 지우고
	    		$('div').remove('.detail_member');
	    		//다시만들고
	    		$('.member').append('<div class = "detail_member" />');
	    		//잘라서 붙이기
	    		var joinMembers = strArr[i+1].split(',');
	    		for(var i=0;i<joinMembers.length;i++){
	    			console.log(joinMembers[i]);
	    			$('.detail_member').append('<div class = "detail">'+joinMembers[i]+'</div>');
	    		}
	    	}
	    	
	    	if(strArr[i] == 'Message'){
	    		var message = strArr[i+1].split(':');
	    		sessionNick = message[0];
	    		message = message[1];
		    	$('.well').append('<div class = "detail" id = "message">'+sessionNick+':'+message+'</div>');
	    	}
	    }
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
		socket.send(msg);
		console.log($('.detail').length);
	});
	connect();
});
</script>
</html>