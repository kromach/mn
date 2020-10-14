<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 </title>
</head>
<div class="grid-Wrapper">
	<div class="grid">
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width3">
			<form action="/product/orderlist" name="schMember" id="schMember">
				<input type="hidden" name="isSearch" value="true">
				<div>
					<table class="tableCss">
						<tr>
							<th>상품명</th>
							<td align="left"><input type="text" name="prName" /></td>
						</tr>
						<tr>
							<th>구매자명</th>
							<td align="left"><input type="text" name="prName" /></td>
						</tr>
						<tr>
							<th>구매일자</th>
						
							<td align="left">
								<div>
									<label for="from">시작일</label>
									<input type="text" name="evStart" id="from"/>
									<label for="to">종료일</label>
									<input type="text" name="evEnd" id="to"/>
								</div>
							</td>
						</tr>
						<tr>
							<th>주문상태</th>
							<td align="left">
								<input type="checkbox" name="orStatus" value="1" />결재완료
								<input type="checkbox" name="orStatus" value="2" />배송준비중
								<input type="checkbox" name="orStatus" value="1" />출고완료
								<input type="checkbox" name="orStatus" value="1" />취소접수
								<input type="checkbox" name="orStatus" value="1" />취소
							</td>
						</tr>
					</table>
				</div>
				
						
						<div><input type="submit" value="검색" class="btn btn-sm btn-blue" /></div>
			</form>
		</div>
		<c:if test="${ordercount==0}">
			<h1>주문받은 상품이 없습니다</h1>
		</c:if>
		<c:if test="${ordercount >0}">
			<div class="grid-item grid-item--width6">
				<table class="tableCss table">
						<tr>
							<td>주문번호</td>
							<td>상품명</td>
							<td>수량</td>
							<td>주문자아이디/수령자명</td>
							<td>주문상태</td>
						</tr>
						<c:forEach var="order" items="${orderlist}">
								<tr>
									<td>${order.orCode}</td>
									<td><a href="orderdetail?orcode=${order.orCode}">${order.prName}</a></td>
									<td>${order.prCount}</td>
									<td>${order.userId}/${order.receiverName}</td>
									<c:choose>
										<c:when test="${order.orStatus ==1}"><td>주문 미확인</td></c:when>
										<c:when test="${order.orStatus ==2}"><td>주문 확인</td></c:when>
										<c:when test="${order.orStatus ==3}"><td>배송 준비</td></c:when>
										<c:when test="${order.orStatus ==4}"><td>배송 완료</td></c:when>
										<c:when test="${order.orStatus ==5}"><td>택배로 이동중</td></c:when>
										<c:otherwise><td>확인 요망</td></c:otherwise>
									</c:choose>
								</tr>
						</c:forEach>
				</table>
				<!-- pager -->
				<div align="center" class="pageNums">
							<c:if test="${pageVO.startPage > pageVO.pageBlock}">
								<a href="/product/orderlist?pageNum=${pageVO.startPage-pageVO.pageBlock}">&lt;</a>
							</c:if>
							<c:forEach var="i" begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1"> 
										<a href="/product/orderlist?pageNum=${i}" class="pageNums">&nbsp;${i}&nbsp;</a>
							</c:forEach>
							<c:if test="${pageVO.endPage < pageVO.pageCount}">
								<a href="/product/orderlist?pageNum=${pageVO.startPage+pageVO.pageBlock}">&gt;</a>
							</c:if>
				</div>
			
			</div>	
		</c:if>	
	</div>
</div>

<!-- 날짜 -->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  

<script>
$( function() {
    var dateFormat = "mm/dd/yy",
      from = $( "#from" )
        .datepicker({
        	
          dateFormat: "yy-mm-dd",
          defaultDate: "+1w",
          changeMonth: true,
          numberOfMonths: 3
        })
        .on( "change", function() {
          to.datepicker( "option", "minDate", getDate( this ) );
        }),
      to = $( "#to" ).datepicker({
        dateFormat: "yy-mm-dd",
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 3
      })
      .on( "change", function() {
        from.datepicker( "option", "maxDate", getDate( this ) );
      });
 
    function getDate( element ) {
      var date;
      try {
        date = $.datepicker.parseDate( dateFormat, element.value );
      } catch( error ) {
        date = null;
      }
 
      return date;
    }
    
  } );

</script>

</html>