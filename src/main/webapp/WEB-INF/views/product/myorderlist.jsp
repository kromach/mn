<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
				<div class="detail-item detail-width6" >
					<h3 class="text-left pad-y10">구매내역</h3>
					<table class="tableCss tbl-lg margin-bottom10" id="more">
						<colgroup>
							<col width="15%" />
							<col width="*" />
							<col width="10%" />
							<col width="25%" />
							<col width="10%" />
						</colgroup>
						<tr>
							<th>주문번호</th>
							<th>상품명</th>
							<th>수량</th>
							<th>배송지</th>
							<th>주문상태</th>
						</tr>
					
						<c:if test="${myordercount == 0}">
						<tr><td colspan="5">구매한 상품이 없습니다.</td></tr>
						</c:if>
					
						<c:if test="${myordercount > 0}">
						
						<c:forEach var="my" items="${myorderlist}">
							<!-- <div class="detail-item detail-width6"> -->
							<tr>
								<td><a href="myorderdetail?orcode=${my.orCode}">${my.orCode}</a></td>
								<td><a href="myorderdetail?orcode=${my.orCode}">${my.prName}</a></td>
								<td>${my.prCount}</td>
								<td>${my.receiverAddr}</td>
								<td>${my.orStatusValue}</td>
							</tr>
							<!-- </div> -->
						</c:forEach>
						</c:if>
				</table>
			</div>
			
			<c:if test="${myordercount > 0}">
				<div class="detail-item detail-width6" id="add">
					<input id="addBtn" type="button" class="btn btn-md btn-mint" value="더보기" onclick="more()">
					<input type ="hidden" value="0" id="moreVal">
				</div>
			</c:if>
	</div>
</div>


<script>
function more(){
	var moreVal = Number($('#moreVal').val())+1;
	$('#moreVal').val(moreVal);
	var context = window.location.pathname.substring(0,window.location.pathname.indexOf("/", 2));
	$.ajax({
		url : context + '/more?num='+moreVal,
		type : "post",
		success : function(data) {
			console.log("Object.keys Length : ",Object.keys(data).length);
			var endlen = Object.keys(data).length;
			if(endlen != 0){			
				for(var i in data){
					$("#more > tbody:last").append('<tr><td><a href="myorderdetail?orcode='+data[i].orCode+'">'+data[i].orCode
										+'</a></td><td><a href="myorderdetail?orcode='+data[i].orCode+'">'+data[i].prName
										+'<a></td><td>'+data[i].prCount
										+'</td><td>'+data[i].receiverAddr
										+'</td><td>'+data[i].orStatusValue
										+'</td><tr/>'
					);
				}
			}else if(endlen ==0 ){
				$('#add').remove();
			}
		}
	});
}
</script>