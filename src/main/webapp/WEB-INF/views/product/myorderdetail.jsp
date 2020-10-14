<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 구매 목록 상세</title>
</head>

<div class="grid-Wrapper">
	<div class="grid">
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width3">
			<h3>주문 정보</h3>
			<table class="tableCss">
				<tr>
					<th>상품명</th>
					<td>${orderinfo.prName}</td>
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
		</div>
		<div class="grid-item grid-item--width3">
			<h3>주소 정보</h3>
			<table class="tableCss">
				<tr>
					<th>수령자명</th>
					<td><input type="text" value="${orderinfo.receiverName}" /></td>
				</tr>
				<tr>
					<th>수령자 연락처</th>
					<td><c:set var="tel" value="${orderinfo.receiverTel}" /> <c:set
							var="telArr" value="${fn:split(tel,',')}" /> <input type="text"
						name="receiverTel" value="${telArr[0]}">- <input
						type="text" name="receiverTel" value="${telArr[1]}">- <input
						type="text" name="receiverTel" value="${telArr[2]}"></td>
				</tr>
				<tr>
					<th>수령지</th>
					<td><input type="text" value="${orderinfo.receiverAddr}" /></td>

				</tr>
			</table>
			<h3>배송 정보</h3>
			<table class="tableCss">
				<tr>
					<th>배송 현황</th>
					<td>${orderinfo.orStatus}</td>
				</tr>
				<tr>
					<th>택배 회사</th>
					<td>${orderinfo.deliveryCompany}</td>
				</tr>
				<tr>
					<th>송장 번호</th>
					<td>${orderinfo.deliveryNum}</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</html>