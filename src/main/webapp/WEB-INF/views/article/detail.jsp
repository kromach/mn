<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
#contentBlock img{
	min-width: 0;
	display: inline-block;
}
</style>
	<div class="grid-Wrapper">
		<div class="grid2">
			<div class="detail-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="detail-item detail-width6">
				<h2 class="pad-y10 text-left">${articleDTO.bnTitle}</h2>
				<div class="clfix">
					<div class="fl text-left writer-info">
						<span style="font-size:15px">
						<span class="btn btn-twitter btn-xs default" style="height:15px">${articleDTO.writerTitleCnt}</span>
							<span class="titleName">${articleDTO.writerTitleName}</span>
							${articleDTO.nickname}
						</span>
						<span style="padding: 0 10px;">|</span>
						<fmt:formatDate value="${articleDTO.insertDay}" pattern="yyyy.MM.dd HH:mm:SS"/>
					</div>
					
					<div class="fr text-right read-info">
						<span>조회수 ${articleDTO.readcount}</span> <span >|</span> <span>좋아요 ${articleDTO.heart}</span>
					</div>
				</div>
				<div style="width:100%; border-top:2px solid black;">
					<div class="article-content">
						${articleDTO.content}
					</div>
					<div class="report-area">
						<button class="btn btn-lg btn-blue" onclick="like('${articleDTO.bnIdx}','${articleDTO.insertId }')">좋아요</button>
						<a class="btn btn-lg btn-orange" onclick="report('${articleDTO.bnIdx}','${articleDTO.insertId }','${memId}')">신고</a>
					</div>		
				</div>
				<div class="text-right pad-top10 pad-bottom20" style="border-top: 1px solid #aaa;">
					<c:if test="${articleDTO.kind eq 'C' && memId eq 'admin'}">
						<input id="addBtn" type="button" class="btn btn-md btn-orange" value="되돌리기" onclick="move('${articleDTO.bnIdx}','back')">
					</c:if>
					<c:if test="${articleDTO.kind eq 'F' && memId eq 'admin' }">
						<input id="addBtn" type="button" class="btn btn-md btn-orange" value="글이동" onclick="move('${articleDTO.bnIdx}','move')">
					</c:if>
					<c:if test="${memId eq articleDTO.insertId}">
						<input id="addBtn" type="button" class="btn btn-md btn-blue" value="수정" onclick="window.location.href='/article/update?bnIdx=${articleDTO.bnIdx}'">
					</c:if>
					<c:if test="${memId eq 'admin' || memId eq articleDTO.insertId }">
						<input id="addBtn" type="button" class="btn btn-md btn-red" value="삭제" onclick="deleteArticle()">
					</c:if>	
					<input type="button" class="btn btn-md btn-grey" value="목록" onclick="window.location='/article'" />
				</div>
				<h3 class="pad-y10 text-left">댓글 (${count})</h3>
				<c:if test="${count <= 0}">
					<div class="pad-top10" style="border-top: 2px solid #333;">
						<p class="text-center font-bold" style="font-size: 15px;padding: 5px 0 15px 0;color: #069;"><i class="fas fa-comment-slash"></i> 등록 된 댓글이 없습니다.</p>
					</div>
				</c:if>
				<c:if test="${count > 0}">
					<table id="boardTbl" class="detailTbl tbl-lg replyTbl">
						<colgroup>
							<col width="20%" />
							<col width="*" />
							<col width="16%" />
						</colgroup>
						<c:forEach var="articleReplyDTO" items="${reply}">
						<tr class="reply">
							<td class="text-left">
								<span class="btn btn-twitter btn-xs default">${articleReplyDTO.writerTitleCnt}</span>
								<span class="titleName">${articleReplyDTO.writerTitleName}</span>
								${articleReplyDTO.nickname}
							</td>
							<td class="text-left">${articleReplyDTO.bnComment}</td>
							<td>
								<div class="fl">
									<fmt:formatDate value="${articleReplyDTO.insertDay}" pattern="yyyy-MM-dd"/><br />
									<fmt:formatDate value="${articleReplyDTO.insertDay}" type="time" dateStyle="medium" />
								</div>
								<c:if test="${articleReplyDTO.insertId eq sessionScope.memId}">
									<button class="btn btn-rouge delReplyBtn" onclick="deleteReply('${articleReplyDTO.coIdx}','${memId}')">X</button>
								</c:if>
							</td>
						</tr>
						</c:forEach>
					</table>
					<!-- pager -->
					<div class="page_wrap">
					<div class="page_nation">
	
						<c:if test="${pageVO.startPage > pageVO.pageBlock}">
							<a class="arrow prev" onclick="replyReload('${pageVO.startPage-pageVO.pageBlock}')">&lt;</a>
						</c:if>
						
						<c:forEach var="i" begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1"> 
							<c:if test="${i != pageNum}">
								<a onclick="replyReload('${i}')" class="pageNums" id="pageSel${i}">&nbsp;${i}&nbsp;</a>
							</c:if>
						</c:forEach>
						
						<c:if test="${pageVO.endPage < pageVO.pageCount}">
							<a class="arrow next" onclick="replyReload('${pageVO.startPage+pageVO.pageBlock}')" href="?dkCode=${drinkInfo.dkCode}&pageNum=${pageVO.startPage+pageVO.pageBlock}#boardTbl">&gt;</a>
						</c:if>
					</div>
					</div>
					<%-- 
						<tr>
							<td colspan="4">
							<c:if test="${pageVO.startPage > pageVO.pageBlock}">
								<a onclick="replyReload()" href="/Spring/board/list.git?pageNum=${pageVO.startPage-pageVO.pageBlock}">&lt;</a>
							</c:if>
							<c:forEach var="i" begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1">
								<a onclick="replyReload('${i}','${articleDTO.bnIdx}','${sessionScope.memId}')" class="pageNums" id="pageSel${i}">&nbsp;${i}&nbsp;</a>
							</c:forEach>
							<c:if test="${pageVO.endPage < pageVO.pageCount}">
								<a onclick="replyReload()" href="/Spring/board/list.git?pageNum=${pageVO.startPage+pageVO.pageBlock}">&gt;</a>
							</c:if>
							</td>
						</tr>
					 --%>
				</c:if>
				<div>
					<textarea name="content" type="textarea" class="required" msg="내용을" placeholder="댓글을 입력해주세요" 
				    id="content_textArea" style="width: 90%; border: 1px solid; height: 80px; margin:auto; resize: none; padding: 5px;" ></textarea>
					<div class="margin-top5 margin-bottom15">
						<input id="addBtn" type="button" class="btn btn-md btn-mint" value="등록" onclick="reply('${articleDTO.bnIdx}')">
						<input id="addBtn" type="button" class="btn btn-md btn-grey" value="취소" onclick="replyCancle()">
					</div>
				</div>
			</div>

			<div class="detail-item detail-width6">
				<table class="detailTbl tbl-lg" id="more">
					<colgroup>
						<col width="*" />
						<col width="20%" />
						<col width="10%" />
						<col width="8%" />
						<col width="8%" />
					</colgroup>
					<tbody>
					<tr>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>좋아요</th>
					</tr>
					<c:forEach var="articleDTO" items="${list}">
						<tr >
							<td class="text-left"><a href="/article/detail?idx=${articleDTO.bnIdx}">${articleDTO.bnTitle}</a></td>
							<td>
								<%-- <a href="/article/detail?idx=${articleDTO.bnIdx}"> --%>
									<span class="btn btn-twitter btn-xs default">${articleDTO.writerTitleCnt}</span>
									<span class="titleName">${articleDTO.writerTitleName}</span>
									${articleDTO.nickname}
									</td>
								<!-- </a> -->
							</td>
							<td><a href="/article/detail?idx=${articleDTO.bnIdx}"><fmt:formatDate value="${articleDTO.insertDay}" pattern="yyyy.MM.dd"/></a></td>
							<td><a href="/article/detail?idx=${articleDTO.bnIdx}">${articleDTO.readcount}</a></td>
							<td><a href="/article/detail?idx=${articleDTO.bnIdx}">${articleDTO.heart}</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div id="add" class="margin-y20">
					<input id="addBtn" type="button" class="btn btn-md btn-mint" value="더보기" onclick="more()">
					<input type ="hidden" value="0" id="moreVal">
				</div>
			</div>
		</div>
	</div>
<script>
$(function() {
	
	var msnry = new Masonry('.grid2', {
		itemSelector : '.detail-item',
		columnWidth : '.detail-sizer',
		percentPosition : true,
		gutter: '.gutter-sizer'
	});
	imagesLoaded('.grid2').on('progress', function() {
		msnry.layout();
	});
	
});

// 이미지 못 불러올 시 오류 처리 
$("img").each(function(){
	$(this).on("error", function () {
	    $(this).attr("src", "/resources/img/broken.png");
	    $(this).css({"width": "350px", "height": "276px"});
	});
});

function deleteArticle(){
	var confirmdelete = confirm('정말 삭제하시겠습니까?');
	if(confirmdelete){
		window.location.href='/article/delete?bnIdx=${articleDTO.bnIdx}';
	}
}

function like(bnIdx,insertId){
	
	var context = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	var session = '<c:out value="${memId}"/>';
	
	if(session!=''){
		console.log(session);
		$.ajax({
			url : context + '/like?num='+bnIdx+'&id='+session+'&insertId='+insertId,
			type : "post",
			success : function(data) {
				console.log(data);
				if(data == -1){
					console.log("cancleSession"+session);
					$.ajax({
						url : context + '/unlike?num='+bnIdx+'&id='+session+'&insertId='+insertId,
						type : "post",
						success : function(data_) {
							console.log(data_);
							alert("좋아요가 취소되었습니다.")	
						}
					});
				}else{
					console.log(data);
					alert("좋아한 글로 등록되었습니다.")
				}
			}
		});
	}
	else{
		alert("로그인후 이용 가능한 서비스 입니다");
	}
}

function report(bnIdx,insertId,reportId){
	var context = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	var session = '<c:out value="${memId}"/>';
	if(session!=''){
		$.ajax({
			url : context + '/report?num='+bnIdx+'&insertId='+insertId+'&reportId='+reportId,
			type : "post",
			success : function(data) {
				if(data==1){
					alert("신고 되었습니다");
				}else if(data == -1){
					alert("이미 신고하신 글입니다.");
				}
			}
		});
	}
	else{
		alert("로그인후 이용 가능한 서비스 입니다");
	}
}
function reply(bnIdx){
	var session = '<c:out value="${memId}"/>';
	var text = $('textarea#content_textArea').val();
	var context = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	console.log(bnIdx);
	if(session!=''){
		if(text==''){
			alert('댓글 입력후 등록해주세요');
		}else if(text!=''){
			//댓글 등록 ajax
			console.log(bnIdx);
			$.ajax({
				url : context + '/reply',
				data : 'bnIdx='+bnIdx+'&session='+session+'&text='+text,
				type : "post",
				success : function(data) {
					console.log(data);
				}
			});
			alert('댓글이 등록되었습니다.');
			window.location.reload();
		}
	}else{
		alert("로그인후 이용 가능한 서비스 입니다");
	}
	$('textarea#content_textArea').val('');
}

function replyCancle(bnIdx){
	var session = '<c:out value="${memId}"/>';
	$('textarea#content_textArea').val('');
}

function move(bnIdx,code){
	var session = '<c:out value="${memId}"/>';
	var context = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	if(session != '' && session == 'admin') {
		$.ajax({
			url : context + '/move?num='+bnIdx+'&code='+code,
			type : "post",
			success : function(data) {
				console.log(data);
				if(data==1) {
					alert("게시글이 읽을거리 글로 이동되었습니다.");
				}
			}
		});
	}else if(session==''){
		alert("로그인후 이용 가능한 서비스 입니다");
	}else if(session!='admin'){
		alert("관리자만 사용 가능한 메뉴 입니다.");
	}
}

function replyReload(pageNum){

	var articleIdx = "${articleDTO.bnIdx}";
	var memId = "${memId}";

	$.ajax({
		url : '/article/replyReload',
		data : 'index='+pageNum+'&idx='+articleIdx,
		type : "post",
		success : function(data) {
			
			//console.log(data);
			$('.reply').remove();
			var len = Object.keys(data).length;
			var replyStr = "";
			
			for(var i = 0; i < len; i++){
				replyStr = replyStr + '<tr class="reply">';
				replyStr = replyStr + '		<td class="text-left">';
				replyStr = replyStr + '			<span class="btn btn-twitter btn-xs default">'+data[i].writerTitleCnt+'</span>';
				replyStr = replyStr + '			<span class="titleName">'+ (data[i].writerTitleName ? data[i].writerTitleName : '') +'</span>';
				replyStr = replyStr + '			'+data[i].nickname;
				replyStr = replyStr + '		</td>';
				replyStr = replyStr + '		<td class="text-left">'+data[i].bnComment+'</td>';
				replyStr = replyStr + '		<td>';
				replyStr = replyStr + '			<div class="fl">';
				replyStr = replyStr + 				moment(new Date(data[i].insertDay)).format('YYYY.MM.DD') + '<br />';
				replyStr = replyStr +				moment(new Date(data[i].insertDay)).format('a h:mm:ss');
				replyStr = replyStr + '			</div>';
				if(memId == data[i].insertId) { // 삭제버튼 표시
				replyStr = replyStr + '			<button class="btn btn-rouge delReplyBtn" onclick="deleteReply(\''+ data[i].coIdx +'\',\'${memId}\')">X</button>';
				}
				replyStr = replyStr + '		</td>';
				replyStr = replyStr + '</tr>';
			}
			
			$(".replyTbl").append(replyStr);
		}
	});
}

/* 
function replyReload(index,idx,session){
	console.log("댓글 인덱스"+index);
	console.log("글번호 "+idx);
	var context = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	$.ajax({
		url : context + '/replyReload',
		data : 'index='+index+'&idx='+idx,
		type : "post",
		success : function(data) {
			$('.reply').remove();
			var len = Object.keys(data).length;
			console.log(data);
			for(var i=len-1;i>=0;i--){
				
				
				if(session == data[i].insertId){
					$("<tr class='reply'><td colspan='2'>"+data[i].bnComment+"</td><td><span class='btn btn-blue btn-xs default'>"+data[i].writerTitleCnt+"</span><span class='titleName'>"+data[i].writerTitleName+"</span>"+data[i].nickname+"</td><td>"+
							moment(new Date(data[i].insertDay)).format('YYYY.MM.DD')+"&nbsp;<button type='button' class='btn btn-sm btn-dark' onclick=\"deleteReply("+"\'"+data[i].coIdx+"\',\'"+session+"\')\">글삭제</button></td></tr>").insertAfter('table tr:eq(5)');					
				}else{			
					$("<tr class='reply'><td colspan='2'>"+data[i].bnComment+"</td><td><span class='btn btn-blue btn-xs default'>"+data[i].writerTitleCnt+"</span><span class='titleName'>"+data[i].writerTitleName+"</span>"+data[i].nickname+"</td><td>"+
						moment(new Date(data[i].insertDay)).format('YYYY.MM.DD')+"</td></tr>").insertAfter('table tr:eq(5)');
					}
			}
		}
	});
}
*/
function deleteReply(coIdx,session){
	var context = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	var confirmMsg = confirm("정말 삭제하시겠습니까");
	if(confirmMsg){
		$.ajax({
			url : context + '/deleteReply',
			data : 'coIdx='+coIdx+'&session='+session,
			type : "post",
			success : function(data) {
				console.log(data);
				alert('해당 댓글이 삭제되었습니다.');
				window.location.reload();
			}
		});
	}
}
function more(){
	var moreVal = Number($('#moreVal').val())+1;
	$('#moreVal').val(moreVal);
	var context = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	$.ajax({
		url : context + '/more?num='+moreVal,
		type : "post",
		success : function(data) {
			console.log(data);
			var endlen = Object.keys(data).length;
			if(endlen != 0){			
				for(var i in data){
					var writerTitle = data[i].writerTitleName;
					if(writerTitle == null )writerTitle = '';
					$("#more > tbody:last").append('<tr><td class="text-left">'+data[i].bnTitle
										+'</td><td><span class="btn btn-twitter btn-xs default">'+data[i].writerTitleCnt+'</span>'
										+'<span class="titleName">&nbsp;'+writerTitle+'</span>'
										+ data[i].nickname
										+'</td><td>'+moment(new Date(data[i].insertDay)).format('YYYY.MM.DD') 
										+'</td><td>'+data[i].readcount
										+'</td><td>'+data[i].heart
										+'</td><tr/>'
					);
				}
			}else if(endlen ==0 ){
				$('#add').remove();
			}
		}
	});
}
</script>