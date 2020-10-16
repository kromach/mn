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
									<td>
										<form action="orderdetail">
											<input type="hidden" name="orcode" value="${orderinfo.orCode}">
											<input type="hidden" name="ischang" value="true" />
											<select name="orstatus">
												<option value="1" <c:if test="${orderinfo.orStatus==1}">selected</c:if>>접수</option>
												<option value="2" <c:if test="${orderinfo.orStatus==2}">selected</c:if>>배송준비중</option>
												<option value="3" <c:if test="${orderinfo.orStatus==3}">selected</c:if>>배송완료</option>
												<option value="4" <c:if test="${orderinfo.orStatus==4}">selected</c:if>>취소접수</option>
												<option value="5" <c:if test="${orderinfo.orStatus==5}">selected</c:if>>취소완료</option>
												<option value="6" <c:if test="${orderinfo.orStatus==6}">selected</c:if>>주문취소</option>
											</select>
											<input type="submit" value="적용"/>
										</form>
									</td>
								</tr>
							</div>
				</table>
			</div>
	</div>
</div>
</html>