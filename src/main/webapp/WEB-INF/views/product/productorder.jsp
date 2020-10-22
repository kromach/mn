<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="/resources/js/formCheck.js"></script>
<script src="/resources/js/jquery.selectric.js"></script>
<div class="grid-Wrapper">
	<div class="grid2">

		<div class="detail-sizer"></div>
		<div class="gutter-sizer"></div>
		<form action="insertOrder" name="orForm" method="post">
			<input type="hidden" name="prCode" value="${info.prCode}"> <input
				type="hidden" name="prName" value="${info.prName}"> <input
				type="hidden" name="prPrice" value="${info.prPrice *amount}">
			<input type="hidden" name="prCount" value="${amount}"> <input
				type="hidden" name="userId" value="${meminfo.id}">
			<div class="detail-item detail-width3 detail-info-div">
				<div class="detail-info text-left">
					<h3>배송지 정보</h3>
					<table class="tableCss">

						<tr>
							<th>구매자 이름</th>
							<td><input type="text" value="${meminfo.name}"class="sel short required" type="select-one" msg="구매자 이름을"></td>
						</tr>
						<tr>
							<th>받는사람 이름</th>
							<td><input type="text" name="receiverName" class="sel short required" type="select-one" msg="수신자 이름을"
								value="${meminfo.name}"></td>
						</tr>
						<tr>
							<th>받는사람 연락처</th>
							<td><c:set var="tel" value="${meminfo.tel}"  /> <c:set
									var="telArr" value="${fn:split(tel,',')}" /> <input type="text"
								name="receiverTel" value="${telArr[0]}" class="sel short required" msg="연락처를">- <input
								type="text" name="receiverTel" value="${telArr[1]}" class="sel short required" msg="연락처를">- <input
								type="text" name="receiverTel" value="${telArr[2]}" class="sel short required" msg="연락처를"></td>
						</tr>
						<tr>
							<th>받는사람 주소</th>
							<td><input type="text" name="receiverAddr"
								value="${meminfo.address}" class="input-lg required" msg="수령지를" ></td>
						</tr>

					</table>
				</div>
			</div>
			<div class="detail-item detail-width3 detail-info-div">
				<div class="detail-info text-left">
					<h3>결제 정보</h3>
					<dl class="clear">
						<dt>상품명</dt>
						<dd class="clfix">${info.prName}</dd>
						<dt>수량</dt>
						<dd class="clfix required" >${amount}개</dd>
						
						<dt>가격</dt>
						<dd class="clfix ">${info.prPrice*amount}원</dd>
					</dl>
				</div>
				<input type="button" class="btn btn-lg btn-rouge" value="결제하기" onclick="insertOrder()">
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
	
	  function insertOrder(amount,prAmount){
	    	if (checkFormjquery()) {
	    		if(confirm("구매를 하시겠습니까?")){
	    			$("form[name='orForm']").submit();	
	    		}
	    	}
	    }
</script>


