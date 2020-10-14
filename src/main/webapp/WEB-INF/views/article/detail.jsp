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
						<td>${articleDTO.insertId}</td>
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
							<a class="btn btn-lg btn-yellow" onclick="reply('${articleDTO.bnIdx}')">댓글등록</a>
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="height: 50px;">Comment</td>
					</tr>
				</table>
			</div>
			<div class="detail-item detail-width6">
				<div class="text-center pad-top10 pad-bottom20">
					<c:if test="${memId eq 'admin' }">	
						<input id="addBtn" type="button" class="btn btn-md btn-blue" value="글이동" onclick="move('${articleDTO.bnIdx}')">
					</c:if>
						<input type="button" class="btn btn-md btn-grey" value="목록으로" onclick="window.location='/article'" />
					<c:if test="${memNickName eq articleDTO.insertId}">
						<input id="addBtn" type="button" class="btn btn-md btn-blue" value="수정">
						<input id="addBtn" type="button" class="btn btn-md btn-blue" value="삭제">
					</c:if>
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
							<td>${articleDTO.bnTitle}</td>
							<td>${articleDTO.insertId}</td>
							<td><fmt:formatDate value="${articleDTO.insertDay}" pattern="yyyy.MM.dd"/></td>
							<td>${articleDTO.readcount}</td>
							<td>${articleDTO.heart}</td>
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
function like(bnIdx,insertId){
var context = window.location.pathname.substring(0,
		window.location.pathname.indexOf("/", 2));
var session = '<c:out value="${memNickName}"/>';
	if(session!=''){
	$.ajax({
		url : context + '/like?num='+bnIdx+'&nick=${memNickName}&insertId='+insertId,
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
	var session = '<c:out value="${memNickName}"/>';
	if(session!=''){
		$.ajax({
			url : context + '/report?num='+bnIdx+'&insertId='+insertId+'&reportId='+reportId,
			type : "post",
			success : function(data) {
			}
		});
	}
	else{
		alert("로그인후 이용 가능한 서비스 입니다");
	}
}
function reply(bnIdx){
	//window.open();
	var context = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	$.ajax({
		url : context + '/reply?num='+bnIdx,
		type : "post",
		success : function(data) {
	}
});}
function move(bnIdx){
	var context = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	$.ajax({
		url : context + '/move?num='+moreVal,
		type : "post",
		success : function(data) {
		}
	});
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
