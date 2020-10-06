<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="grid-Wrapper">
	<div class="grid2"> 
	
		<div class="detail-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="detail-item detail-width2 detail-img-div" >
			<img id="mainImg" src=" ${info.prImg}" alt=" ${info.prName}">
		</div>
		<div class="detail-item detail-width4 detail-info-div">
			<div class="detail-info text-left">
				<div class="navi margin-bottom5">
					<a href="productlist">${info.prBkindValue}</a> > <a href="productlist?schprSkind=${info.prSkind}">${info.prSkindValue}</a>
				</div>
				<h3>${info.prName}</h3>
				
				<dl class="clear">
					<dt>양조장</dt>
					<dd class="clfix">${info.prPlace}</dd>
					<dt>국가 / 지역</dt>
					<dd class="clfix">${info.prCountry} > ${info.prCity} </dd>
					<dt>도수</dt>
					<dd class="clfix">${info.prAlcohol} %</dd>
					<dt>어울리는 안주</dt>
					<dd class="clfix">${info.prFood} </dd>
				</dl>
				<div>
					<button class="btn btn-lg btn-blue" onclick="">좋아요</button>
					<a class="btn btn-lg btn-yellow" onclick="">후기등록</a>
				</div>
			</div>
		</div>
		
		<div class="detail-item detail-width4">
			<h3 class="pad-top10 pad-bottom20">상세정보</h3>
			${info.prContent}
		</div>
		<div class="detail-item detail-width2">
			<h3 class="pad-top10 pad-bottom20">구매하기</h3>
			
			가격 ${info.prPrice}
			<button class="btn btn-lg btn-blue" onclick="orderdetail">구매 페이지</button>
		</div>
		<div class="detail-item detail-width4">
			<h3 class="pad-top10 pad-bottom20">연관 게시글 보기</h3>
			<table class="detailTbl tbl-lg">
				<tr>
					<th>제목</th>
					<th style="min-width:100px;">작성자</th>
					<th style="min-width:130px;">작성일</th>
				</tr>
				<tr>
					<td>이 술 맛있음요</td>
					<td>김영성</td>
					<td>2020-09-28</td>
				</tr>
			</table>
		</div>
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

