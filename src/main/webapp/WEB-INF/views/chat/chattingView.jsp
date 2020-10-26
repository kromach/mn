<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<link rel="icon" type="image/png"  href="resources/images/svnicon.png"/> <!-- favicon fix -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- jQuery, bootstrap CDN -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
	<!-- SocketJS CDN -->
	<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>

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
		let msg = $('#msg').val();
		socket.send(msg);
		$('#msg').val('');
	});
	
    $('textarea').keypress(function(event) { // enter로 전송
        if (event.keyCode == 13) {
            event.preventDefault();
            $('#btnSend').click();
        }
    });	
	
});
</script>

<div class="totChatDIV">
	<div class="memberWrapper" style="border-bottom: 1px solid black ;">
		<div class="member">
			<p style="font-size:14px; color:#069; padding-bottom: 3px;">채팅 참여자 (<span class="detail_count" id="detail_count"></span>)</p>
			<div class="detail_member"></div>
		</div>
	</div>
	<!-- 
	<div class="countWrapper" style="border-bottom: 1px solid black ;">
		<div class="count">
			&nbsp;인원
			
		</div>
	</div>
	 -->
	<div class="chatWrapper">
		<div class="well" id="chatdata">
			<!-- User Session Info Hidden -->
		</div>
	</div>
	<div id="msgWrapper">
		<div>
			<!-- <input type="text"  id="msg" value="1212" /> -->
			<textarea name="msg" id="msg" style="width:99%; height: 52px; padding: 0 3px; resize:none" _onKeydown="javascript:if(event.keyCode == 13) #btnSend.click();"></textarea>
			<input type="button" class="btn btn-sm btn-blue" id="btnSend" value="전송" />
		</div>
	</div>
</div>
