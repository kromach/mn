<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 주문 내역 상세</title>
</head>

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
								<td>가격</td>
								<td>수령인</td>
								<td>수령인 전화번호</td>
								<td>배송지</td>
								<td>주문상태</td>
							</tr>
						
							<div class="grid-item grid-item--width6">
								<tr>
									<td>${orderinfo.orCode}</td>
									<td>${orderinfo.prName}</td>
									<td>${orderinfo.prCount}</td>
									<td>${orderinfo.prPrice}</td>
									<td>${orderinfo.receiverName}</td>
									<td>
										<c:set var="tel" value="${orderinfo.receiverTel}"/>
										<c:set var="telArr" value="${fn:split(tel,',')}"/>
										${telArr[0]}-${telArr[1]}-${telArr[2]}
									</td>
									<td>${orderinfo.receiverAddr}</td>
									<c:choose>
										<c:when test="${orderinfo.orStatus ==1}"><td>주문 미확인
										<c:if test="${orderinfo.orStatus == 1 ||orderinfo.orStatus == 2||orderinfo.orStatus == 3  }">
											<a href="deleteorder">상태바꾸기</a>
										</c:if></td></c:when>
										<c:when test="${orderinfo.orStatus ==2}"><td>주문 확인
										<c:if test="${orderinfo.orStatus == 1 ||orderinfo.orStatus == 2||orderinfo.orStatus == 3  }">
											<a href="deleteorder">상태바꾸기</a>
										</c:if></td></c:when>
										<c:when test="${orderinfo.orStatus ==3}"><td>배송 준비
										<c:if test="${orderinfo.orStatus == 1 ||orderinfo.orStatus == 2||orderinfo.orStatus == 3  }">
											<a href="deleteorder">상태바꾸기</a>
										</c:if></td></c:when>
										<c:when test="${orderinfo.orStatus ==4}"><td>배송 완료</td></c:when>
										<c:when test="${orderinfo.orStatus ==5}"><td>택배로 이동중</td></c:when>
										<c:otherwise><td>확인 요망</td></c:otherwise>
									</c:choose>
									
										
								</tr>
							</div>
				</table>
			</div>
	</div>
</div>
</html>