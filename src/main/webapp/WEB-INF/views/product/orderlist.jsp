<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 </title>
</head>
<c:if test="${ordercount==0}">
	<h1>구매한 상품이 없습니다</h1>
	<a href="productlist"><h1>구매하러가기</h1></a>
</c:if>
<c:if test="${ordercount >0}">
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
								<td>주문자아이디/수령자명</td>
								<td>주문상태</td>
							</tr>
						
						<c:forEach var="order" items="${orderlist}">
							<div class="grid-item grid-item--width6">
								<tr>
									<td>${order.orCode}</td>
									<td><a href="orderdetail?orcode=${order.orCode}">${order.prName}</a></td>
									<td>${order.prCount}</td>
									<td>${order.userId}/${order.receiverName}</td>
									
									
									<c:choose>
										<c:when test="${order.orStatus ==1}"><td>주문 미확인</td></c:when>
										<c:when test="${order.orStatus ==2}"><td>주문 확인</td></c:when>
										<c:when test="${order.orStatus ==3}"><td>배송 준비</td></c:when>
										<c:when test="${order.orStatus ==4}"><td>배송 완료</td></c:when>
										<c:when test="${order.orStatus ==5}"><td>택배로 이동중</td></c:when>
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