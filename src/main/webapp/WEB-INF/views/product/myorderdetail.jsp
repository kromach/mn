<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 구매 목록 상세</title>
</head>

<c:set var="dis" value=""></c:set>
<c:set var="del" value=""></c:set>
<c:if test="${orderinfo.orStatus > 2}">
	<c:set var="dis" value="disabled"></c:set>
</c:if>
<c:if test="${orderinfo.orStatus == 3}">
	<c:set var="del" value="delivery"></c:set>
</c:if>

<div class="grid-Wrapper">
	<div class="grid">
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<form action="modifyorder">
			<input type="hidden" name="orcode" value="${orderinfo.orCode}">
			<div class="grid-item grid-item--width3">
				<h3>주문 정보</h3>
				<table class="tableCss">
					<tr>
						<th>상품명</th>
						<td><a href="productdetail?prcode=${orderinfo.prCode}">${orderinfo.prName}</a></td>
					</tr>
					<tr>
						<th>수량</th>
						<td>${orderinfo.prCount}</td>
					</tr>
					<tr>
						<th>가격</th>
						<td>${orderinfo.prPrice}</td>
					</tr>
				</table>

				<h3>주소 정보</h3>
				<table class="tableCss">
					<tr>
						<th>수령자명</th>
						<td align="left"><input type="text"
							value="${orderinfo.receiverName}" name="receiverName" ${dis} /></td>
					</tr>
					<tr>
						<th>수령자 연락처</th>
						<td align="left"><c:set var="tel"
								value="${orderinfo.receiverTel}" /> <c:set var="telArr"
								value="${fn:split(tel,',')}" /> <input type="text"
							name="receiverTel" value="${telArr[0]}" ${dis}>- <input
							type="text" name="receiverTel" value="${telArr[1]}" ${dis}>-
							<input type="text" name="receiverTel" value="${telArr[2]}" ${dis}></td>
					</tr>
					<tr>
						<th>수령지</th>
						<td align="left"><input type="text"
							value="${orderinfo.receiverAddr}" name="receiverAddr" ${dis} /></td>

					</tr>
				</table>
				<h3>배송 정보</h3>
				<table class="tableCss">
					<tr>
						<th>배송 현황</th>
						<td>${orderinfo.orStatusValue}</td>
					</tr>
					<c:if test="${del.equals('delivery') }">
					<tr>
						<th>택배 회사</th>
						<td>${orderinfo.deliveryCompany}</td>
					</tr>
					<tr>
						<th>송장 번호</th>
						<td>${orderinfo.deliveryNum}</td>
					</tr>
					</c:if>
				</table>
				<c:if test="${!dis.equals('disabled')}">
					<input type="button" class="btn btn-lg btn-blue" value="주문 취소" 
					onclick="window.location.href='/product/deleteorder?orcode=${orderinfo.orCode}'">
					<input type="submit" class="btn btn-lg btn-blue" value="수정하기">
					
				</c:if>
					<input type="button" class="btn btn-lg btn-blue" value="구매목록" 
					onclick="window.location.href='/product/myorderlist'">
			</div>
		</form>
	</div>
</div>
</html>