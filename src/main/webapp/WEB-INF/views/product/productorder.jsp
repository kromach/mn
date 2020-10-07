<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="grid-Wrapper">
	<div class="grid2"> 
	
		<div class="detail-sizer"></div> 
		<div class="gutter-sizer"></div>
		<form action="orderPro">
			<div class="detail-item detail-width3 detail-info-div" >
				<div class="detail-info text-left">
					<h3>배송지 정보</h3>
					<table class="tableCss">
				
						<tr>
							<th>구매자 아이디</th>
							<td>
								<input type="text" value="o202010071">
							</td>
						</tr>
						<tr>
							<th>받는사람 이름</th>
							<td>
								<input type="text" value="o202010071">
							</td>
						</tr>
						<tr>
							<th>받는사람 연락처</th>
							<td>
								<input type="text" value="o202010071">
							</td>
						</tr>
						<tr>
							<th>받는사람 주소</th>
							<td>
								<input type="text" value="o202010071">
							</td>
						</tr>
					
					</table>
				</div>
			</div>
			<div class="detail-item detail-width3 detail-info-div">
				<div class="detail-info text-left">
					<h3>결제 정보</h3>
					
					<dl class="clear">
						<dt>상품명</dt>
						<dd class="clfix">${info.prPlace}</dd>
						<dt>수량</dt>
						<dd class="clfix">${info.prCountry} 개 </dd>
						<dt>가격</dt>
						<dd class="clfix">${info.prAlcohol} 원</dd>
					</dl>
				</div>
				<input type="button" class="btn btn-lg btn-rouge" value="결제하기">
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
			gutter: '.gutter-sizer'
		});
		imagesLoaded('.grid2').on('progress', function() {
			msnry.layout();
		});
	});
</script>


