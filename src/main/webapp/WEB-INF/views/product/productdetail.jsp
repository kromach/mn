<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<div class="grid-Wrapper">
	<div class="grid2"> 
	
		<div class="detail-sizer"></div> 
		<div class="gutter-sizer"></div>
		<div class="detail-item detail-width6">
			<h1 class="text-left pad-y10">상품 정보</h1>
		</div>
		<div class="detail-item detail-width2 detail-img-div" >
			<img id="mainImg" src=" ${info.prImg}" alt=" ${info.prName}">
		</div>
		<div class="detail-item detail-width4 detail-info-div">
			<div class="detail-info text-left">
				<div class="navi margin-bottom5">
					<a href="productlist">${info.prBkindValue}</a> > <a href="productlist?isSearch=true&Skind=${info.prSkind}">${info.prSkindValue}</a>
				</div>
				<h3>${info.prName} </h3>
				
				<dl class="clear">
					<dt>양조장</dt>
					<dd class="clfix">${info.prPlace}</dd>
					<dt>국가 / 지역</dt>
					<dd class="clfix">${info.prCountry} > ${info.prCity} </dd>
					<dt>도수</dt>
					<dd class="clfix">${info.prAlcohol} 도</dd>
					<dt>어울리는 안주</dt>
					<dd class="clfix">${info.prFood} </dd>
					<dt>판매된 수량</dt>
					<dd class="clfix">${info.prTotalcount} 개</dd>
				</dl>
				<div>
					<input type="hidden" id="productLikeYN" value="${productLikeInfo}" />
					<input type="hidden" id="productLikeCount" value="${info.prLike}" />
					<span id="like-btn"></span><!-- 좋아요버튼 -->
					<a class="btn btn-lg btn-yellow" href="/product/writeform?kind=R&prcode=${info.prCode}&prname=${info.prName}">후기등록</a>
				</div>
			</div>
		</div>
		<div class="detail-item detail-width6"></div> 
		<div class="detail-item detail-width4">
			<h3 class="text-left pad-top10 pad-bottom20" style="border-bottom:1px solid #333">상세정보</h3>
			<div class="text-left info-area pad-top10" style="font-size:15px;">${info.prContent}</div>
		</div>
		<form action="productorder" name="count">
			<input type="hidden" name="prcode" value="${info.prCode}">
			<input type="hidden" id="prcount" name="prcount" value="${info.prAmount}">
			<div class="detail-item detail-width2">
				<h3 class="pad-top10 pad-bottom20 text-left" style="border-bottom:1px solid #333">구매하기</h3>
				<div class="buy-area">
					<dl class="clear">
						<dt>수량</dt>
						<dd>
							<span>											
								<i class="fas fa-minus-square btnMinus" onclick="SetAmount('M');"></i>										
								<select name="amount" id="amount" onchange="SetAmount('')" style="width: 40px; height: 24px; border: 1px solid rgb(204, 204, 204);">
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
						<dd class="font-bold margin-right5"><span>￦ <span id="totalPrice">0</span></span></dd>	
					</dl>
					<input type="button" class="btn btn-lg btn-blue pad-bottom10" style="width:100%" value="구매하기" onclick="countNum()" />
				</div>
			</div>
		</form>
		<div class="detail-item detail-width6">
			<h3 class="pad-top10 pad-bottom20 text-left">상품 후기</h3>
			<table class="detailTbl tbl-lg">
				<tr>
					<th style="min-width:150px;">작성자</th>
					<th>제목</th>
					<th style="min-width:50px;">조회수</th>
					<th style="min-width:100px;">작성일</th>
				</tr>
				
				<c:forEach var="re" items="${articlelist}">
				<tr>
					<td>${re.insertId}</td>
					<td><a href="reviewdetail?bnIdx=${re.bnIdx}">${re.bnTitle}</a></td>
					<td>${re.readcount}</td>
					<td><fmt:formatDate value="${re.insertDay}" pattern="yyyy.MM.dd"/></td>
				</tr>
				</c:forEach>
			</table>
			<div class="pad-y20" >
				<input id="addBtn" type="button" class="btn btn-lg btn-mint" value="더보기" onclick="more2('${info.prCode}')">	
				<input type="hidden" value="0" id="moreVal">
			</div>
		</div>
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
		
		likeBtnToggle();
	});

	function likeBtnToggle(){
		console.log($('#productLikeYN').val());
		var likeCount = $('#productLikeCount').val();
		var btn = '<button class="btn btn-lg btn-blue" onclick="like(\'${info.prCode}\',\'${info.insertId}\')">[heart] '+ likeCount +'</button>';
		var heart = '<i class="far fa-heart"></i>';
		
		if($('#productLikeYN').val() == 'Y') { // 좋아요 상태면
			heart = '<i class="fas fa-heart c_orange"></i>';
		}
		
		$("#like-btn").empty().html(btn.replace('[heart]', heart));
	}
	
	//숫자 유효성 체크
	function  checkNum( str ) {
		if ( isNaN( str ) ) {
			return false;
		}
		return true;
	}

	function countNum() {
	    var buy = 0;
	    var amount = $("#amount").val();
	    var remain = $("#prcount").val();
		//alert("매수" + amount);
		//alert("남은수량"+remain)
		buy = amount;
		
		if (buy == 0) {
			alert("최소 구매수량은 1개입니다.");
			return false;
		}else{
			if(Number(buy) > Number(remain)){
				alert("재고가 부족합니다.");
				return false;
			}else{
				$("form[name='count']").submit();
			}
		}
	}

	function SetAmount(upDown) {
		var sel = document.getElementById("amount");
		var price = document.getElementById("price").innerHTML;

		//console.log(tk_price);					

		if (upDown == 'M') {
			if (sel.selectedIndex > 0) {
				sel.selectedIndex--;
			}
		} else if (upDown == 'P') {
			if (sel.selectedIndex < 10) {
				sel.selectedIndex++;
			}else if(sel.selectedIndex == 10){
				alert("경고:지나친 음주는 간경화나 간암을 일으키며 운전이나 작업 중 사고 발생률을 높입니다");
				sel.selectedIndex = 10;
			}
		}
		document.getElementById("totalPrice").innerHTML = numberWithCommas(Number(price) * sel.selectedIndex);
	}

	// 숫자 3자리 마다 , 삽입
	function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	
	// 좋아요
	function like(prCode, insertId) {
		var context = window.location.pathname.substring(0,
				window.location.pathname.indexOf("/", 2));
		var session = '<c:out value="${memId}"/>';
		if (session != '') {
			$.ajax({
				url : context + '/like?prCode=' + prCode
						+ '&memId=${memId}&insertId=' + insertId,
				type : "post",
				success : function(data) {
					if (data == -1) {
						$.ajax({
							url : context + '/unlike?prCode=' + prCode
									+ '&memId=${memId}&insertId='
									+ insertId,
							type : "post",
							success : function(data_) {
								console.log(data_);
								alert("좋아요가 취소되었습니다.")
								
								$("#productLikeYN").val('N');
								$("#productLikeCount").val(data_);
								likeBtnToggle();

								//window.location.reload();
							}
						});
					} else {
						console.log(data);
						alert("좋아요에 추가되셨습니다.")
						//window.location.reload();
						$("#productLikeYN").val('Y');
						$("#productLikeCount").val(data);
						likeBtnToggle();
					}
				}
			});
		} else {
			alert("로그인후 이용 가능한 서비스 입니다");
		}
	}
	

</script>							