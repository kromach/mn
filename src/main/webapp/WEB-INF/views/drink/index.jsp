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
	<form name="schForm" id="schForm">
		<input type="hidden" name="isSearch" value="true"/>
		<div class="ssec pad-top15">
			<p class="s_label fl">주류 구분</p>
			<select id="schDkBkind" name="schDkBkind" class="sel short" value="${schDkBkind}" >
				<option value="">대분류 선택</option>
				<c:forEach items="${bigCategoryList}" var="bigCategory">
					<option value="${bigCategory.code}">${bigCategory.value}</option>
				</c:forEach>
			</select>
			<select id="schDkSkind" name="schDkSkind" class="sel short" value="${schDkSkind}" >
				<option value="">소분류 선택</option>
			</select>
		</div>
		<div class="ssec pad-top15">
			<p class="s_label fl">검색어</p>
			<input type="text" name="schDkVal" class="input" value="${schDkVal}" />
		</div>
		<div class="clfix ssec pad-top15" style="pading:10px 0;">
			<p class="s_label fl">알콜도수</p>
		    <div class="fl" style="width:400px"><input type="text" class="js-range-slider" name="schDkAlcohol" value="${schDkAlcohol}" /></div>
		</div>
		<div class="ssec pad-top15">
			<p class="s_label fl">국가 선택</p>
			<p style="line-height: 33px; font-size:13px">
				<label><input type="radio" name="schDkCountry" class="radio" value="" <c:if test="${empty schDkCountry}"> checked </c:if> /> 전체</label>
				<label><input type="radio" name="schDkCountry" class="radio" value="K" <c:if test="${schDkCountry == 'K'}"> checked </c:if> /> 국내</label>
				<label><input type="radio" name="schDkCountry" class="radio" value="O" <c:if test="${schDkCountry == 'O'}"> checked </c:if> /> 해외</label>
			</p>
		</div>
		<div class="ssec pad-top15"><input type="button" id="searchBtn" value="검색" class="btn btn-sm btn-blue" /></div>
	</form>
</div>
<div class="grid-Wrapper">
	<div class="grid"> 
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		
	</div>
</div>
<script>
	$(function() {
		
		getDrinkList(1);
			
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
	});

	var frm;
	
	$("#searchBtn").click(function() {
		//var frm = JSON.stringify($("#schForm").serializeObject());
		frm = $("#schForm").serialize();
		
		//console.log(frm)
		
		getDrinkList(1);
	});
	
	var $grid = $('.grid').masonry({
		itemSelector : '.grid-item',
		columnWidth : '.grid-sizer',
		percentPosition : true,
		gutter: '.gutter-sizer'
	});
	
	$grid.masonry();

	// list 가져오기
	function getDrinkList(pageNum) {
		//console.log(frm);
		$.ajax({
			type : "POST",
			url : "drinkList",
			data : frm + "&pageNum=" + pageNum,  /*{bigCategory:bigCategory} 와 동일*/
			success : function(data) {

				if(pageNum == 1) { 
					
					$(document).find(".grid-item").each(function() { 
						$grid.masonry('remove', this).masonry('layout');
					});
					// masonry 재 실행 
			    	$grid.masonry();
				}
				setTimeout(function() { 
					drinkGridView(data);
				}, 500);
			},
			error : function() {
				alert("error");
			}
		})
	}
	
	// ajax 으로 받아온 값을 parse 하여 option 등록
    function drinkGridView(data){
		
    	var json = JSON.parse(data);

    	json.forEach(function(item, index) {
    		
    		var el = '<div class="grid-item"><a href="detail?dkCode='+item.dkCode+'"><img src="'+ item.dkImg +'" /></a></div>';
 			
    		// 그리드 추가, 아이템 배치 
    		$grid.append( el ).masonry('appended', el).masonry('reloadItems');	
    	}); 
		
    	// masonry 재 실행 
    	$grid.masonry();
    };
    
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

<script type="text/javascript">
	$(window).scroll(function() {
		// A(B+C) : document 높이 (고정)
		//console.log($(document).height());
		// B : browser 높이 (최상단 기본값)
		//console.log($(window).height());
		// C : 스크롤 위치
		//console.log('SCROLL_TOP' + $(window).scrollTop());
		if ($(window).scrollTop() >= $(document).height() - $(window).height() - 100) {
			//호출 메서드
		}
	});
</script>

<!-- 
<script src="/resources/js/imageLoad.js"></script> -->