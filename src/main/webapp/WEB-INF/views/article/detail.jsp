<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
#contentBlock img{
	min-width: 0;
	display: inline-block;
}
</style>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="detail-item detail-width6">
				<h2 class="pad-top10 pad-bottom10">${articleDTO.bnTitle}</h2>
				<table class="detailTbl tbl-lg" style="margin: auto;text-align: center;">
					<tr>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
						<td>좋아요</td>
					</tr>	
					<tr> 
						<td>
						<span class="btn btn-blue btn-xs default">${articleDTO.writerTitleCnt}</span>
						<span class="titleName">writerTitleName</span>
						${articleDTO.nickname}
						</td>
						<td><fmt:formatDate value="${articleDTO.insertDay}" pattern="yyyy.MM.dd"/></td>
						<td>${articleDTO.readcount}</td>
						<td>${articleDTO.heart}</td>
					</tr>
					<tr>
						<td colspan="4" style="min-height: 400px;" id="contentBlock">${articleDTO.content }</td>
					</tr>
					<tr>
						<td colspan="4" style="height: 100px; border-bottom: 1px solid;">
						<!--login은 실행후 검사 -->
						<div>
							<button class="btn btn-lg btn-blue" onclick="like('${articleDTO.bnIdx}','${articleDTO.insertId }')">좋아요</button>
							<a class="btn btn-lg btn-blue" onclick="report('${articleDTO.bnIdx}','${articleDTO.insertId }','${memId}')">신고</a>
						</div>												 
						</td>
					</tr>
					
					<tr>
						<td colspan="4" style="height: 50px;" id="replyArea">
						<h3 class="pad-top10">댓글</h3>
						</td>
					<tr>
						<!-- 게시글이 있을때만 보여주기 -->
					<c:if test="${count<=0}">
						<tr>
							<td colspan="4" style="height: 50px;">등록된 댓글이 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${count>0}">
					<c:forEach var="articleReplyDTO" items="${reply}">
					<tr class="reply">
						<td colspan="2">${articleReplyDTO.bnComment}</td>
						<td>${articleReplyDTO.nickname}</td>
						<td><fmt:formatDate value="${articleReplyDTO.insertDay}" pattern="yyyy.MM.dd"/>
							<c:if test="${articleReplyDTO.insertId eq sessionScope.memId}">
								<button onclick="deleteReply('${articleReplyDTO.coIdx}','${sessionScope.memId}')">글 삭제</button>
							</c:if>
						</td>
					</tr>
					</c:forEach>
					</c:if>
					<tr>
						<td colspan="4">
						<c:if test="${pageVO.startPage > pageVO.pageBlock}">
							<a onclick="replyReload()" <%-- href="/Spring/board/list.git?pageNum=${pageVO.startPage-pageVO.pageBlock}" --%>>&lt;</a>
						</c:if>
						<c:forEach var="i" begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1">
							<a onclick="replyReload('${i}','${articleDTO.bnIdx}','${sessionScope.memId}')" class="pageNums" id="pageSel${i}">&nbsp;${i}&nbsp;</a>
						</c:forEach>
						<c:if test="${pageVO.endPage < pageVO.pageCount}">
							<a onclick="replyReload()" <%-- href="/Spring/board/list.git?pageNum=${pageVO.startPage+pageVO.pageBlock}" --%>>&gt;</a>
						</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="height: 100px; border-bottom: 1px solid;">
						<textarea name="content" type="textarea" class="required" msg="내용을" placeholder="댓글을 입력해주세요" 
					    id="content_textArea" style="width: 90%; display: block; border: 1px solid; height: 109px; margin:auto; resize: none;" ></textarea>
						<div style="margin-top: 5px">
							<input id="addBtn" type="button" class="btn btn-md btn-yellow" value="등록" onclick="reply('${articleDTO.bnIdx}')">
							<input id="addBtn" type="button" class="btn btn-md btn-blue" value="취소" onclick="replyCancle()">
						</div>
						</td>
					<tr>
				</table>
			</div>
			<div class="detail-item detail-width6">
				<div class="text-center pad-top10 pad-bottom20">
					<c:if test="${memId eq 'admin' }">	
						<input id="addBtn" type="button" class="btn btn-md btn-blue" value="글이동" onclick="move('${articleDTO.bnIdx}')">
					</c:if>
					<c:if test="${memId eq articleDTO.insertId}">
						<input id="addBtn" type="button" class="btn btn-md btn-blue" value="수정" onclick="window.location.href='/article/update?bnIdx=${articleDTO.bnIdx}'">
					</c:if>
					<c:if test="${memId eq 'admin' || memId eq articleDTO.insertId }">
						<input id="addBtn" type="button" class="btn btn-md btn-blue" value="삭제" onclick="deleteArticle()">
					</c:if>	
					<input type="button" class="btn btn-md btn-grey" value="목록으로" onclick="window.location='/article'" />
				</div>
			</div>
			<div class="detail-item detail-width6">
				<table class="detailTbl tbl-lg" id="more">
					<tbody>
					<tr>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
						<td>좋아요</td>
					</tr>
					<c:forEach var="articleDTO" items="${list}">
						<tr >
							<td><a href="/article/detail?idx=${articleDTO.bnIdx}">${articleDTO.bnTitle}</a></td>
							<td><a href="/article/detail?idx=${articleDTO.bnIdx}">${articleDTO.nickname}</a></td>
							<td><a href="/article/detail?idx=${articleDTO.bnIdx}"><fmt:formatDate value="${articleDTO.insertDay}" pattern="yyyy.MM.dd"/></a></td>
							<td><a href="/article/detail?idx=${articleDTO.bnIdx}">${articleDTO.readcount}</a></td>
							<td><a href="/article/detail?idx=${articleDTO.bnIdx}">${articleDTO.heart}</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="detail-item detail-width6" id="add">
			<input id="addBtn" type="button" class="btn btn-md btn-grey" value="더보기" onclick="more()">
			<input type ="hidden" value="0" id="moreVal">
		</div>
	</div>
</body>
<script>
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
	$.ajax({
		url : context + '/like?num='+bnIdx+'&id=${memId}&insertId='+insertId,
		type : "post",
		success : function(data) {
			if(data == -1){
				$.ajax({
					url : context + '/unlike?num='+bnIdx+'&nick=${memNickName}&insertId='+insertId,
					type : "post",
					success : function(data_) {
						console.log(data_);
						alert("좋아요가 취소되었습니다.")	
					}
				});
			}else{
				console.log(data);
				alert("좋아요에 추가되셨습니다.")
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
					alert("신고가 완료 되었습니다");
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
function move(bnIdx){
	var session = '<c:out value="${memId}"/>';
	var context = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	if(session!=''&&session=='admin'){
		$.ajax({
			url : context + '/move?num='+bnIdx,
			type : "post",
			success : function(data) {
				console.log(data);
				if(data==1){
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
					$("<tr class='reply'><td colspan='2'>"+data[i].bnComment+"</td><td>"+data[i].nickname+"</td><td>"+
							moment(new Date(data[i].insertDay)).format('YYYY.MM.DD')+"&nbsp;<button type='button' onclick=\"deleteReply("+"\'"+data[i].coIdx+"\',\'"+session+"\')\">글삭제</button></td></tr>").insertAfter('table tr:eq(5)');					
				}else{			
					$("<tr class='reply'><td colspan='2'>"+data[i].bnComment+"</td><td>"+data[i].nickname+"</td><td>"+
						moment(new Date(data[i].insertDay)).format('YYYY.MM.DD')+"</td></tr>").insertAfter('table tr:eq(5)');
					}
			}
		}
	});
}
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
	alert(moreVal);
	var context = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	$.ajax({
		url : context + '/more?num='+moreVal,
		type : "post",
		success : function(data) {
			console.log("Object.keys Length : ",Object.keys(data).length);
			var endlen = Object.keys(data).length;
			if(endlen != 0){			
				for(var i in data){
					$("#more > tbody:last").append('<tr><td>'+data[i].bnTitle
										+'</td><td>'+data[i].insertId
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
</html>
