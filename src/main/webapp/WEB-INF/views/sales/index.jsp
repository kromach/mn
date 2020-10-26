<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<style>
.linkBar {
	width: 25%;
	border: 0;
	float: left;
	text-align: center;
}
</style>
<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width6"></div>
		
		<h2> 판매중인 품목 </h2>
		<br/><br/><br/><br/>
			<table class="tableCss" style="width: 100%;">
						<tr>
							<td>번호</td>
							<td>상품명</td>
							<td>상품종류</td>
							<td>가격</td>
							<td>판매여부</td>
						</tr>
						<c:forEach var="salesList" items="${salesList}">
							<tr>
								<td>${salesList.prCode.substring(2,7)}</td>
								<td><a href="/sales/modifyForm?prcode=${salesList.prCode}">${salesList.prName}</a></td>
								<td>${salesList.prBkindValue}/${salesList.prSkindValue}</td>
								<td>${salesList.prPrice}</td>
								<td>${salesList.prUse}</td>
							</tr>
						</c:forEach>
					</table>
		<br/>
		<a class="btn btn-lg btn-mint" href="/sales/insert">상품등록</a>
		<br/><br/><br/>
		<!-- pager -->
				<div class="page_wrap">
					<div class="page_nation">	
							<c:if test="${pageVO.startPage > pageVO.pageBlock}">
								<a class="arrow prev" href="/sales/index?pageNum=${pageVO.startPage-pageVO.pageBlock}">&lt;</a>
							</c:if>
							<c:forEach var="i" begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1">
								<c:if test="${i == pageNum}">
									<a class="active">${i}</a>		
								</c:if>			
								<c:if test="${i != pageNum}">			
									<a href="/sales/index?pageNum=${i}" class="pageNums">&nbsp;${i}&nbsp;</a>			
								</c:if>		
							</c:forEach>	
							<c:if test="${pageVO.endPage < pageVO.pageCount}">
								<a class="arrow next" href="/sales/index?pageNum=${pageVO.startPage+pageVO.pageBlock}">&gt;</a>
							</c:if>
					</div>
				</div>		
	</div>
</div>

<script src="/resources/js/imageLoad.js"></script>
<script type="text/javascript">
$(window).scroll(
	function() {
		// A(B+C) : document 높이 (고정)
		//console.log($(document).height());
		// B : browser 높이 (최상단 기본값)
		//console.log($(window).height());
		// C : 스크롤 위치
		//console.log('SCROLL_TOP' + $(window).scrollTop());
		if ($(window).scrollTop() >= $(document).height()
				- $(window).height() - 100) {
			//호출 메서드
		}
	});
</script>
</html>