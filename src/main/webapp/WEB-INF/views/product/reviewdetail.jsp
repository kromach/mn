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
				<h2 class="pad-top10 pad-bottom10">${article.bnTitle}</h2>
				<table class="detailTbl tbl-lg" style="margin: auto;text-align: center;">
					<tr>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
						<td>좋아요</td>
					</tr>	
					<tr> 
						<td>
						<span class="btn btn-blue btn-xs default">${article.writerTitleCnt}</span>
						<span class="titleName">${article.writerTitleName}</span>
						${article.nickname}
						</td>
						<td><fmt:formatDate value="${article.insertDay}" pattern="yyyy.MM.dd"/></td>
						<td>${article.readcount}</td>
						<td>${article.heart}</td>
					</tr>
					<tr>
						<td colspan="4" style="min-height: 400px;" id="contentBlock">${article.content }</td>
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
					<c:if test="${re.insertId eq memId}">
						<input type="button" value="삭제" class="btn btn-sm btn-dark" onclick="deleteRe('${re.bnIdx}')" />
					</c:if>
				</table>
			</div>
		</div>
	</div>
</body>
<script>
function deleteRe(bnIdx){
	var context = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	var confirmMsg = confirm("정말 삭제하시겠습니까");
	if(confirmMsg){
		$.ajax({
			url : context + '/deleteRe',
			data : 'bnIdx='+bnIdx,
			type : "post",
			success : function(data) {
				console.log(data);
				alert('해당 리뷰가 삭제되었습니다.');
				window.location.reload();
			}
		});
	}
}
</script>
</html>
