<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 구매 목록</title>
</head>
<c:if test="${myordercount==0}">
	<h1>구매한 상품이 없습니다</h1>
	<a href="productlist"><h1>구매하러가기</h1></a>
</c:if>
<c:if test="${myordercount >0}">
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
				<div class="detail-item detail-width6" >
					<table class="tableCss table" id="more">
							<tr>
								<td>주문번호</td>
								<td>상품명</td>
								<td>수량</td>
								<td>배송지</td>
							</tr>
						
						<c:forEach var="my" items="${myorderlist}">
							<div class="detail-item detail-width6">
								<tr>
									<td><a href="myorderdetail?orcode=${my.orCode}">${my.orCode}</a></td>
									<td><a href="myorderdetail?orcode=${my.orCode}">${my.prName}</a></td>
									<td>${my.prCount}</td>
									<td>${my.receiverAddr}</td>
								</tr>
							</div>
						</c:forEach>
				</table>
			</div>
			<div class="detail-item detail-width6" id="add">
				<input id="addBtn" type="button" class="btn btn-md btn-grey" value="더보기" onclick="more()">
				<input type ="hidden" value="0" id="moreVal">
			</div>
	</div>
</div>
</c:if>

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
</html>