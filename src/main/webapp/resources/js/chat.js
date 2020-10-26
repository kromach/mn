window.socket = null;
function connect(){
//	var ws = new WebSocket("ws://localhost:8080/replyEcho");
	var ws = new WebSocket("ws://192.168.0.103:8080/replyEcho");
//  시연체크
	socket = ws;
	socket.onopen = function (event) {
		console.log('Info: connection opened.');
		console.log(event.data);
		console.log('------------');
	};
}
function getLog(){
	socket.onmessage = function (event) {
		console.log('log');
		console.log('============');
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
	    		$('#chatdata').append('<div class="detail status">*** '+strArr[i+1]+' ***</div>');
	    		$("#chatdata").scrollTop($("#chatdata")[0].scrollHeight);
	    	}
	    	//참여인원 카운트 셋팅완료
	    	if(strArr[i] == 'CountMember'){
	    		console.log("카운트"+strArr[i+1]);
	    		$('#detail_count').html(strArr[i+1]);
	    	}
	    	//참여자 리스트 셋팅완료
	    	if(strArr[i] == 'JoinMember'){
	    		//전에것 지우고
	    		//$('div').remove('.detail_member');
	    		//다시만들고
	    		//$('.member').append('<div class = "detail_member" />');
	    		$('.detail_member').empty();
	    		
	    		//잘라서 붙이기
	    		var joinMembers = strArr[i+1].split(',');
	    		for(var i=0;i<joinMembers.length;i++){
	    			console.log(joinMembers[i]);
	    			$('.detail_member').append('<div class = "detailM">'+joinMembers[i]+'</div>');
	    		}
	    	}
	    	
	    	if(strArr[i] == 'Message'){
	    		var message = strArr[i+1].split(':');
	    		sessionNick = message[0];
	    		message = message[1];
		    	$('#chatdata').append('<div class = "detail message">'+sessionNick+' : '+message+'</div>');
	    		$(".chatWrapper").scrollTop($("#chatdata")[0].scrollHeight);
	    		console.log($("#chatdata")[0].scrollHeight);
	    	}
	    }
	};
	socket.onclose = function(event) {
		console.log('Info: connection closed.');
		//setTimeout( function(){ connect(); }, 1000); // retry connection!!
	};
	socket.onerror = function(err) {
		console.log('ERR: connection closed.', err);
	};
}
