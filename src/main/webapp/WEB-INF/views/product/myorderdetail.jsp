<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="/resources/js/formCheck.js"></script>
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
		<form action="modifyorder" method="post" name="modifyForm">
			<input type="hidden" name="orcode" value="${orderinfo.orCode}">
			<div class="grid-item grid-item--width6">
				<h1 class="text-left pad-y10">주문 정보</h1>
				<table class="tableCss tbl-lg cell-h20 margin-bottom10">
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
					<tr>
						<th>상품명</th>
						<td><a href="productdetail?prcode=${orderinfo.prCode}">${orderinfo.prName}</a></td>
					</tr>
					<tr>
						<th>수량</th>
						<td>${orderinfo.prCount} 개</td>
					</tr>
					<tr>
						<th>가격</th>
						<td>
							<fmt:formatNumber value="${orderinfo.prPrice}" type="currency" currencySymbol="\\" />
						</td>
					</tr>
				</table>

				<h3 class="text-left pad-y10">배송지 정보</h3>
				<table class="tableCss tbl-lg cell-h20 margin-bottom10">
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
					<tr>
						<th>수령자명</th>
						<td align="left"><input type="text" class="input-xs required" msg="수령자명을" value="${orderinfo.receiverName}" name="receiverName" ${dis} /></td>
						<td align="left">
						<input type="text"value=" ${orderinfo.receiverName}" name="receiverName" class="sel short required" type="select-one" msg="구매자 이름을"  ${dis} /></td>
					</tr>
					<tr>
						<th>수령자 연락처</th>
						<td align="left">
							<c:set var="tel" value="${orderinfo.receiverTel}" /> 
							<c:set var="telArr" value="${fn:split(tel,',')}" /> 
							<input type="text" class="input-xs required" msg="전화번호를" name="receiverTel" value="${telArr[0]}" ${dis}> - 
							<input type="text" class="input-xs required" msg="전화번호를" name="receiverTel" value="${telArr[1]}" ${dis}> -
							<input type="text" class="input-xs required" msg="전화번호를" name="receiverTel" value="${telArr[2]}" ${dis}>
						</td>
					</tr>
					<tr>
						<th>수령지 주소</th>
						<td align="left">
							<input type="text" value="${orderinfo.receiverAddr}" class="input-lg required" msg="수령지 주소를" name="receiverAddr" ${dis} />
						</td>
					</tr>
				</table>
				
				<h3 class="text-left pad-y10">배송 상태</h3>
				<table class="tableCss tbl-lg cell-h20 margin-bottom10">
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
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
				<div>
					<c:if test="${!dis.equals('disabled')}">
					<input type="button" class="btn btn-lg btn-rouge" value="주문취소" onclick="cancelOrder()'">
					<input type="button" class="btn btn-lg btn-blue" value="수정하기" onclick="modifyOrder()">	
					</c:if>
					<input type="button" class="btn btn-lg btn-grey" value="구매목록" onclick="location.href='/product/myorderlist'">
				</div>
			</div>
		</form>
	</div>
</div>

<script>
$(function() {
	var msnry = new Masonry('.grid2', {
		itemSelector : '.detail-item',
		columnWidth : '.detail-sizer',
		percentPosition : true,
		gutter : '.gutter-sizer'
	});
	imagesLoaded('.grid2').on('progress', function() {
		msnry.layout();
	});
});
	
function modifyOrder(){
	if (checkFormjquery()) {
   		if(confirm("수정하시겠습니까?")){
   			$("form[name='modifyForm']").submit();	
   		}
   	}
}
 

 function cancelOrder(){
	   	if (checkFormjquery()) {
	   		if(confirm("주문 취소 하시겠습니까?")){
	   			window.location.href='/product/deleteorder?orcode=${orderinfo.orCode}'
	   		}
	   	}
	   }

	
</script>


