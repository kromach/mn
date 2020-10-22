<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="del" value=""/>
<c:if test="${orderinfo.orStatus ==3}">
	<c:set var="del" value="delivery"/>
</c:if>

<div class="grid-Wrapper">
	<div class="grid">
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width6">
			<table class="tableCss tbl-lg margin-bottom10">
				<tr>
					<th>주문번호</th>
					<th>상품명</th>
					<th>수량</th>
					<th>가격</th>
					<th>수령인</th>
					<th>수령인 전화번호</th>
					<th>배송지</th>
					<c:if test="${del.equals('delivery')}">
						<td>송장번호</td>
					</c:if>
					<th>주문상태</th>
				</tr>
				<tr>
					<td>${orderinfo.orCode}</td>
					<td>${orderinfo.prName}</td>
					<td>${orderinfo.prCount}</td>
					<td>${orderinfo.prPrice}</td>
					<td>${orderinfo.receiverName}</td>
					<td><c:set var="tel" value="${orderinfo.receiverTel}" /> <c:set
							var="telArr" value="${fn:split(tel,',')}" />
						${telArr[0]}-${telArr[1]}-${telArr[2]}</td>
					<td>${orderinfo.receiverAddr}</td>
					<c:if test="${del.equals('delivery')}">
						<td>${orderinfo.deliveryNum}</td>
					</c:if>
					<td>
						<form action="orderdetail" name="chor">
							<input type="hidden" name="orCode" value="${orderinfo.orCode}">
							<input type="hidden" name="ischang" value="true" />
							<select name="orstatus" class="sel short required">
								<option value="1"
									<c:if test="${orderinfo.orStatus==1}">selected</c:if>>접수</option>
								<option value="2"
									<c:if test="${orderinfo.orStatus==2}">selected</c:if>>배송준비중</option>
								<option value="3"
									<c:if test="${orderinfo.orStatus==3}">selected</c:if>>배송완료</option>
								<option value="4"
									<c:if test="${orderinfo.orStatus==4}">selected</c:if>>취소접수</option>
								<option value="5"
									<c:if test="${orderinfo.orStatus==5}">selected</c:if>>취소완료</option>
								<option value="6"
									<c:if test="${orderinfo.orStatus==6}">selected</c:if>>주문취소</option>
							</select>
							<input type="button" value="적용" class="btn btn-xs btn-grey" onclick="test()" >
						</form>
					</td>
				</tr>
			</table>
			<c:if test="${del.equals('delivery')}">
				<form action="orderdetail">
				<input type="hidden" name="orCode" value="${orderinfo.orCode}">
				<input type="hidden" name="insertdel" value="true" />
					<table class="tableCss table">
						<tr>
							<td>택배회사명</td>
							<td>송장번호</td>
						</tr>
						<tr>
							<td><input type="text" name="deliveryCompany" /></td>
							<td><input type="text" name="deliveryNum" /></td>
							<td><input type="submit" value="입력" /></td>
					</table>
				</form>
			</c:if>
			<input type="button" class="btn btn-lg btn-blue" value="판매목록"
				onclick="window.location.href='/product/orderlist'">
		</div>
	</div>
</div>


<script>
	function test(){
		if (confirm("상태값을 변경 하시겠습니까?")) {
			$("form[name='chor']").submit();
		}
	}
	
	$(function() {
		

		// select 형태 바꿔주는 JS 실행
		$(".sel").selectric();
		
		// masonry 실행
		var msnry = new Masonry('.grid2', {
			itemSelector : '.detail-item',
			columnWidth : '.detail-sizer',
			percentPosition : true,
			gutter: '.gutter-sizer'
		});
		imagesLoaded('.grid2').on('progress', function() {
			msnry.layout();
		});
		
		// 대분류의 값이 바뀌었을 경우 소분류 값과 항목명 가져오는 ajax 실행
	    $('#dkBkind').change(function() {
	    	getSmallCategory(this.value);
	    	getItemValues(this.value);
	    });

	});
</script>