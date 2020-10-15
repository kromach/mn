<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
 -->

<!--Plugin CSS file with desired skin-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ion-rangeslider/2.3.1/css/ion.rangeSlider.min.css"/>  
<!--Plugin JavaScript file-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/ion-rangeslider/2.3.1/js/ion.rangeSlider.min.js"></script>

<script src="/resources/js/jquery.selectric.js"></script>
<link rel="stylesheet" href="/resources/css/selectric.css">
<div class="search-area">
	<form action="index" name="schForm" id="schForm">
		<input type="hidden" name="isSearch" value="true"/>
		<div class="ssec pad-top15">
			<p class="slabel fl">주류 구분</p>
			<select id="schDkBkind" name="schDkBkind" class="sel short" >
				<option value="">대분류 선택</option>
				<c:forEach items="${bigCategoryList}" var="bigCategory">
					<option value="${bigCategory.code}">${bigCategory.value}</option>
				</c:forEach>
			</select>
			<select id="schDkSkind" name="schDkSkind" class="sel short" >
				<option value="">소분류 선택</option>
			</select>
		</div>
		<div class="ssec pad-top15">
			<p class="slabel fl">검색어</p>
			<input type="text" name="schDkVal" class="input"/>
		</div>
		<div class="clfix ssec pad-top15" style="pading:10px 0;">
			<p class="slabel fl">알콜도수</p>
		    <div class="fl" style="width:400px"><input type="text" class="js-range-slider" name="my_range"/></div>
		</div>
		<div class="ssec pad-top15">
			<p class="slabel fl">국가 선택</p>
			<label><input type="radio" name="schDkCountry" value="" /> 전체</label>
			<label><input type="radio" name="schDkCountry" value="K" /> 국내</label>
			<label><input type="radio" name="schDkCountry" value="O" /> 해외</label>
		</div>
		<div class="ssec pad-top15"><input type="submit" value="검색" class="btn btn-sm btn-blue" /></div>
	</form>
</div>
<div class="grid-Wrapper">
	<div class="grid"> 
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		
		<div class="grid-item"><a onclick="setDkcode('DK00001')"><img src="/resources/img/main/4.jpg" /></a></div>
		<div class="grid-item"><img src="/resources/img/main/3.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/8.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/2.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/9.jpg" /></div>		
		<div class="grid-item grid-item--width2"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item grid-item--width2"><img src="/resources/img/main/8.jpg" /></div>
	</div>
</div>
<script>
	$(function() {
		
		// select 형태 바꿔주는 JS 실행
		$(".sel").selectric();
		
		// 대분류의 값이 바뀌었을 경우 소분류 값과 항목명 가져오는 ajax 실행
	    $('#schDkBkind').change(function() {
	    	console.log("B");
	    	getSmallCategory(this.value);
	    });
		
	    $(".js-range-slider").ionRangeSlider({
	    	type: "double",
	    	min : 0,
	    	max : 100,
	    	step: 5,
	    	postfix: "도"
	    });

		
		/*
	    $( "#slider-range" ).slider({
			range: true,
			min: 0,
			max: 99,
			
			slide: function( event, ui ) {
				$( "#amount" ).val( ui.values[ 0 ]  + "도" + ui.values[ 1 ] + "도"  );
			}
	    });
	    
	    $( "#amount" ).val(  $( "#slider-range" ).slider( "values", 0 ) + "도 - " + $( "#slider-range" ).slider( "values", 1 )+"도" );
		*/
	});
	
	// 소분류 값 가져오는 ajax
	function getSmallCategory(bigCategory) {
		$.ajax({
			type : "POST",
			url : "selectSmallCategory",
			data : {bigCategory},  /*{bigCategory:bigCategory} 와 동일*/
			success : function(data){
				$("#schDkSkind").empty().append("<option value=''>소분류 선택</option>");
				addedSmallCategory(data);
			},
			error : function() {
				alert("error");
			}
		})
	}
	
	// ajax 으로 받아온 값을 parse 하여 option 등록
	function addedSmallCategory(data){
		var json = JSON.parse(data);
		
		json.forEach(function(item, index) {
			var optionStr = "<option value="+ item.code +">"+item.value+"</option>"
			
			$("#schDkSkind").append(optionStr);
		});
		
		$("#schDkSkind").selectric("refresh");
	};
	
	function setDkcode(dkCode) {
		var data = "&" + $("#schForm").serialize();
		
		var url = "detail?dkCode=" + dkCode + data;
		
		console.log(url)
		location.href = url;
	}

</script>

<script src="/resources/js/imageLoad.js"></script>