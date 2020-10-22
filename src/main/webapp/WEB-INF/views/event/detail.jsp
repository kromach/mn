<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- css 확인하기 가운데로 안와요ㅜㅜㅜㅜ -->
<div class="grid-Wrapper">
	<div class="grid2"> 
	
		<div class="detail-sizer"></div> 
		<div class="gutter-sizer"></div>
		<div class="detail-item detail-width6">
			<h1 class="text-left pad-y10">이벤트 보기</h1>
		</div>
		<div class="detail-item detail-width2 detail-img-div">
			<img id="mainImg" src=" ${vo.thumImg}" alt=" ${vo.thumImg}">
		</div>
		<div class="detail-item detail-width4 detail-info-div">
			<div class="detail-info text-left">
				<div class="navi margin-bottom5">
					<a href="/product/productlist">${vo.prBkindValue}</a> > <a href="/product/productlist?isSearch=true&Skind=${vo.prSkind}">${vo.prSkindValue}</a>
				</div>
				
				<h3>${vo.eventName}</h3>
					
				<dl class="clear">
					<dt>양조장</dt>
					<dd class="clfix">${vo.prPlace}</dd>
					<dt>국가 / 지역</dt>
					<dd class="clfix">${vo.prCountry} > ${vo.prCity} </dd>
					<dt>도수</dt>
					<dd class="clfix">${vo.prAlcohol} %</dd>
					<dt>어울리는 안주</dt>
					<dd class="clfix">${vo.prFood} </dd>
				</dl>
				<div id="joinEvent">
					<c:if test="${memId != null && idx == 0}">
						<button class="btn btn-sm btn-dark" onclick="joinEvent('${vo.eventCode}')">이벤트 신청하기</button>
					</c:if>
						<button class="btn btn-sm btn-blue" onclick="window.location='/event/index'">목록으로</button>
					<c:if test="${memId != null && idx != 0}">
						<p>신청완료</p>
					</c:if>
					<c:if test="${memId == null }">
						<p></p>
					</c:if>
				</div>
			</div>
		</div>

		<div class="detail-item detail-width6">
			<h3 class="pad-top10 pad-bottom20">상세정보</h3>
			${vo.content}
		</div>
	</div>
</div>
<script>
function joinEvent(eventCode){
	
	var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	$.ajax({
		url: context + '/joinEvent?eventCode='+eventCode,
		type:"get",
		success : function(data){
			$('#joinEvent').empty();
			$('#joinEvent').append('<p>신청완료</p>')
			
			console.log(data);
			

		}
	})
}

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
