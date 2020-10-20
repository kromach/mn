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
					</tr>	
					<tr> 
						<td>
						<span class="btn btn-blue btn-xs default">${article.writerTitleCnt}</span>
						<span class="titleName">${article.writerTitleName}</span>
						${article.insertId}
						</td>
						<td><fmt:formatDate value="${article.insertDay}" pattern="yyyy.MM.dd"/></td>
						<td>${article.readcount}</td>
					</tr>
					<tr>
						<td colspan="4" style="min-height: 400px;" id="contentBlock">${article.content }</td>
					</tr>
					<tr>
						<td colspan="4" style="height: 100px; border-bottom: 1px solid;">
						<!--login은 실행후 검사 -->
						<div>
							<a class="btn btn-sm btn-dark" onclick="back()">뒤로가기</a>
							<c:if test="${(article.insertId eq sessionScope.memId) or sessionScope.memId eq 'admin' }">
								<input type="button" value="삭제" class="btn btn-sm btn-dark" onclick="deleteRe('${article.bnIdx}','${article.dkCode}')" />
							</c:if>
						</div>												 
						</td>
					</tr>
					
				</table>
			</div>
		</div>
	</div>
</body>
<script>
function deleteRe(bnIdx,dkCoed){
	var uri = "deletereview?bnIdx="+bnIdx+"&dkCoed="+dkCoed;
	var confirmMsg = confirm("정말 삭제하시겠습니까");
	if(confirmMsg){
				
				alert('해당 리뷰가 삭제되었습니다.');
				window.location=uri;
	}
}

function back(){
	history.go(-1)
}

</script>
</html>
