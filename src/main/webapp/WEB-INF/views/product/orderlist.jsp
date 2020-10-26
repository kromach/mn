<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<h1 class="text-left pad-y10">구매내역</h1>
			<form action="/product/orderlist" name="schMember" id="schMember">
				<input type="hidden" name="isSearch" value="true">
				<div>
					<table class="tableCss tbl-lg margin-bottom10" >
						<tr>
							<th>상품명</th>
							<td align="left"><input type="text" name="prName" /></td>
						</tr>
						<tr>
							<th>구매자명</th>
							<td align="left"><input type="text" name="userId" /></td>
						</tr>
						<tr>
							<th>구매일자</th>
						
							<td align="left">
								<div>
									<label for="from">시작일</label>
									<input type="text" name="orStart" id="from"/>
									<label for="to">종료일</label>
									<input type="text" name="orEnd" id="to"/>
								</div>
							</td>
						</tr>
						<tr>
							<th>주문상태</th>
							<td align="left">
								<input type="checkbox" name="orStatus" value="1" />접수
								<input type="checkbox" name="orStatus" value="2" />배송준비중
								<input type="checkbox" name="orStatus" value="3" />배송완료
								<input type="checkbox" name="orStatus" value="4" />취소접수
								<input type="checkbox" name="orStatus" value="5" />취소완료
								<input type="checkbox" name="orStatus" value="6" />주문취소
							</td>
						</tr>
					</table>
				</div>
				<div><input type="submit" value="검색" class="btn btn-lg btn-mint" /></div>
			</form>
		</div>

		
			<div class="grid-item grid-item--width6">
				<table class="tableCss tbl-lg margin-bottom20" >
						<tr>
							<th>주문번호</th>
							<th>상품명</th>
							<th>수량</th>
							<th>주문자아이디 / 수령자명</th>
							<th>주문일자</th>
							<th>주문상태</th>
						</tr>
						<c:if test="${ordercount==0}">
							<h1>주문받은 상품이 없습니다</h1>
						</c:if>
						<c:forEach var="order" items="${orderlist}">
								<tr>
									
									<td><a href="orderdetail?orCode=${order.orCode}">${order.orCode}</a></td>
									<td><a href="orderdetail?orCode=${order.orCode}">${order.prName}</a></td>
									<td>${order.prCount}</td>
									<td>${order.userId} / ${order.receiverName}</td>
									<td><fmt:formatDate value="${order.insertDay}" pattern="yyyy.MM.dd"/></td>
									<td>${order.orStatusValue}</td>
								</tr>
						</c:forEach>
				</table>
				<br/><br/>
				<!-- pager -->
				<div class="page_wrap">
					<div class="page_nation">	
							<c:if test="${pageVO.startPage > pageVO.pageBlock}">
								<a class="arrow prev" href="/product/orderlist?pageNum=${pageVO.startPage-pageVO.pageBlock}">&lt;</a>
							</c:if>
							<c:forEach var="i" begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1">
								<c:if test="${i == pageNum}">
									<a class="active">${i}</a>		
								</c:if>			
								<c:if test="${i != pageNum}">			
									<a href="/product/orderlist?pageNum=${i}" class="pageNums">&nbsp;${i}&nbsp;</a>			
								</c:if>		
							</c:forEach>	
							<c:if test="${pageVO.endPage < pageVO.pageCount}">
								<a class="arrow next" href="/product/orderlist?pageNum=${pageVO.startPage+pageVO.pageBlock}">&gt;</a>
							</c:if>
					</div>
				</div>
			
			</div>	
		
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