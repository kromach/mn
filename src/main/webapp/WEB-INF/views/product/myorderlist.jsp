<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 구매 목록</title>
</head>
<c:if test="${myordercount==0}">
	<h1>구매한 상품이 없습니다</h1>
	<a href="productlist"><h1>구매하러가기</h1></a>
</c:if>
<c:if test="${myordercount >0}">
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
				<div class="grid-item grid-item--width6">
					<table class="tableCss table">
							<tr>
								<td>주문번호</td>
								<td>상품명</td>
								<td>수량</td>
								<td>배송지</td>
								<td>주문상태</td>
							</tr>
						
						<c:forEach var="my" items="${myorderlist}">
							<div class="grid-item grid-item--width6">
								<tr>
									<td><a href="myorderdetail?orcode=${my.orCode}">${my.orCode}</a></td>
									<td>${my.prName}</td>
									<td>${my.prCount}</td>
									<td>${my.receiverAddr}</td>
									
									<c:choose>
										<c:when test="${my.orStatus ==1}"><td>주문 미확인</td></c:when>
										<c:when test="${my.orStatus ==2}"><td>주문 확인</td></c:when>
										<c:when test="${my.orStatus ==3}"><td>배송 준비</td></c:when>
										<c:when test="${my.orStatus ==4}"><td>배송 완료</td></c:when>
										<c:when test="${my.orStatus ==5}"><td>택배로 이동중</td></c:when>
										<c:otherwise><td>확인 요망</td></c:otherwise>
									</c:choose>
	
								</tr>
							</div>
						</c:forEach>
				</table>
			</div>
	</div>
</div>
</c:if>
</html>