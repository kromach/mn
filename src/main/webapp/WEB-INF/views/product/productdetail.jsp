<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

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
					<a class="btn btn-lg btn-yellow" onclick="">싫어요</a>
					(${info.prLike})
				</div>
			</div>
		</div>
		
		<div class="detail-item detail-width4">
			<h3 class="pad-top10 pad-bottom20">상세정보</h3>
			${info.prContent}
		</div>
		<form action="productorder">
			<input type="hidden" name="prcode" value="${info.prCode}">
			<div class="detail-item detail-width2">
				<div class="detail-info text-left">
					<h3 class="pad-top10 pad-bottom20">구매하기</h3>
					
					<dl class="clear">
						<dt>수량</dt>
						<dd>												
							<span>											
								<i class="fas fa-minus-square btnMinus" onclick="SetAmount('M');"></i>										
								<select name="amount" id="amount" onchange="SetAmount('')" style="width: 40px; height: 26px; border: 1px solid rgb(204, 204, 204);">										
									<option value="0">0</option>									
									<option value="1">1</option>									
									<option value="2">2</option>									
									<option value="3">3</option>									
									<option value="4">4</option>									
									<option value="5">5</option>									
									<option value="6">6</option>									
									<option value="7">7</option>									
									<option value="8">8</option>									
									<option value="9">9</option>									
									<option value="10">10</option>									
								</select>										
								<i class="fas fa-plus-square btnPlus" onclick="SetAmount('P');"></i>										
							</span>											
						</dd>
						<dt>가격</dt>
						<span id="price" class="display-none">${info.prPrice}</span>	
						<dd id ="totalprice"></dd>			
					</dl>
					<input type="submit" class="btn btn-lg btn-yellow" value="구매하기">
				</div>
			</form>
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


<script>							
							
	function SetAmount(upDown){						
		var sel = document.getElementById("amount");					
		var price = document.getElementById("price").innerHTML;					
							
		//console.log(tk_price);					
							
		if(upDown == 'M') {					
			if(sel.selectedIndex > 0) {				
				sel.selectedIndex--;			
			}				
		} else if(upDown == 'P') {					
			if(sel.selectedIndex < 10) {				
				sel.selectedIndex++;			
			}				
		}					
		document.getElementById("totalprice").innerHTML = Number(price) * sel.selectedIndex;					
	}						
</script>							

