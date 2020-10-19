<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>

<!--Plugin CSS file with desired skin-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ion-rangeslider/2.3.1/css/ion.rangeSlider.min.css"/>  
<!--Plugin JavaScript file-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/ion-rangeslider/2.3.1/js/ion.rangeSlider.min.js"></script>

<script src="/resources/js/jquery.selectric.js"></script>
<link rel="stylesheet" href="/resources/css/selectric.css">


<div class="search-area">
	<form action="productlist" name="schForm" id="schForm" method="get">
		<input type="hidden" name="isSearch" value="true" />
		<div class="ssec pad-top15">
			<p class="s_label fl">주류구분</p>
			<select name="Skind" class="sel short">
				<option selected="selected">소분류 선택</option>
				<option value="S006">탁주</option>
				<option value="S007">청주</option>
				<option value="S008">약주</option>
				<option value="S009">증류주</option>
				<option value="S010">리큐르</option>
				<option value="S011">기타</option>
				<option value="S012">과실주</option>
			</select>
		</div>
		<div class="ssec pad-top15">
			<p class="s_label fl">검색어 </p>
			<input type="text" name="name" class="input" value="${name}">
		</div>
		<div class="clfix ssec pad-top15" style="pading:10px 0;">
			<p class="s_label fl">알콜도수</p>
		    <div class="fl" style="width:400px"><input type="text" class="js-range-slider" name="prAlcohol"/></div>
		</div>
		<div class="clfix ssec pad-top15" style="pading:10px 0;">
			<p class="s_label fl">금액</p>
		    <div class="fl" style="width:400px"><input type="text" class="js-range-slider1" name="prPrice"/></div>
		</div>
		<div class="ssec pad-top15"><input type="submit" value="검색" class="btn btn-sm btn-blue" /></div>
	</form>
</div>



<div class="grid-Wrapper">
	<div class="grid">
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<c:if test="${count ==0}">
			<div>
				<h1>검색한 상품은 없습니다</h1>
			</div>
		</c:if>
		<c:forEach var="pr" items="${productlist}">
			<div class="grid-item">
				<a href="productdetail?prcode=${pr.prCode}"><img
					src="${pr.prImg}" /></a>
			</div>
		</c:forEach>
	</div>
</div>

<script>
	$(function() {
		
		// select 형태 바꿔주는 JS 실행
		$(".sel").selectric();
		
	
	    $(".js-range-slider").ionRangeSlider({
	    	type: "double",
	    	min : 0,
	    	max : 100,
	    	step: 5,
	    	postfix: "도"
	    });
	    
	    $(".js-range-slider1").ionRangeSlider({
	    	type: "double",
	    	min : 0,
	    	max : 100000,
	    	step: 5000,
	    	postfix: "원"
	    });
	});   
</script>

<script src="/resources/js/imageLoad.js"></script>
	<!-- 데이터 스크롤해서 붙이는 스크립트  -->

	



