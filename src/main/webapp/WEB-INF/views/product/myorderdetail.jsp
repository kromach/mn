<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<div class="grid-item grid-item--width6">
					<table class="tableCss table">
							<tr>
								<td>주문번호</td>
								<td>상품명</td>
								<td>수량</td>
								<td>배송지</td>
								<td>주문상태</td>
							</tr>
						
							<div class="grid-item grid-item--width6">
								<tr>
									<td><a href="myorderdetail?orcode=${my.orCode}">${my.orCode}</a></td>
									<td>${my.prName}</td>
									<td>${my.prCount}</td>
									<td>${my.receiverAddr}</td>
									
								</tr>
							</div>
				</table>
			</div>
	</div>
</div>
</html>