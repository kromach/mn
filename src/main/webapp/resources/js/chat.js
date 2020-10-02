window.socket = null;
function connect(){
	var ws = new WebSocket("ws://localhost:8080/replyEcho");
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
	    		$('.well').append('<div class = "detail" id = "status">'+strArr[i+1]+'</div>');
	    	}
	    	//참여인원 카운트 셋팅완료
	    	if(strArr[i] == 'CountMember'){
	    		console.log("카운트"+strArr[i+1]);
	    		$('.detail_count').html('<div class = "detailC" >'+strArr[i+1]+'</div>');
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
	    			$('.detail_member').append('<div class = "detailM">'+joinMembers[i]+'</div>');
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
	socket.onclose = function(event) {
		console.log('Info: connection closed.');
		//setTimeout( function(){ connect(); }, 1000); // retry connection!!
	};
	socket.onerror = function(err) {
		console.log('ERR: connection closed.', err);
	};
}
