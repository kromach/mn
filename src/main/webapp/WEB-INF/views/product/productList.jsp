<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>

<link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
<script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
  <script>
  $(function() {
    $( "#slider-range" ).slider({
      range: true,
      min: 0,
      max: 99,
     
      slide: function( event, ui ) {
        $( "#amount" ).val( ui.values[ 0 ]  + "도" + ui.values[ 1 ] + "도"  );
      }
    });
    $( "#amount" ).val(  $( "#slider-range" ).slider( "values", 0 ) +
      "도 - " + $( "#slider-range" ).slider( "values", 1 )+"도" );
  });
  </script>

  <script>
  $(function() {
    $( "#slider-range1" ).slider({
      range: true,
      min: 0,
      max: 200000,
      slide: function( event, ui ) {
        $( "#price" ).val(  ui.values[ 0 ] + "원" + ui.values[ 1 ] + "원"  );
      }
    });
    $( "#price" ).val( $( "#slider-range1" ).slider( "values", 0 ) +
      "원 - " + $( "#slider-range1" ).slider( "values", 1 )+"원" );
  });
  </script>

</head>



<c:if test="${count == 0}">
		
	<body>
		<div class="grid-Wrapper">
			<div class="grid">
				<div class="grid-sizer"></div>
				<div class="gutter-sizer"></div>
				<div><h1>판매중인술이 없습니다</h1></div>
					<div class="grid-item grid-item--width6 text-left">
						<form action="productlist" name="schForm" id="schForm" method="get">
							<input type="hidden" name="isSearch" value="true"/>
							<div>
								<select name="Skind">
									<option selected="selected">전통주 종류</option>
									<option value="S006">탁주</option>
									<option value="S007">청주</option>
									<option value="S008">약주</option>
									<option value="S009">증류주</option>
									<option value="S010">리큐르</option>
									<option value="S011">기타</option>
									<option value="S012">과실주</option>
								</select>
								검색어 : <input type="text" name="name" >
							</div>
							<div>
								<p>
								  <label for="amount">도수：</label>
								  <input type="text" id="amount" name="prAlcohol" >
								</p>
								 
								<div id="slider-range"></div>
		 					</div>
							<div>
								<p>
								  <label for="price">가격：</label>
								  <input type="text" id="price" name="prPrice">
								</p>
								 
								<div id="slider-range1"></div>
		 					</div>
							<div><input type="submit" value="검색" class="btn btn-sm btn-blue"></div>
						</form>
					</div>
			</div>
		</div>
	</body>
</c:if>
<c:if test="${count > 0}">
	<body>
		<div class="grid-Wrapper">
			<div class="grid">
				<div class="grid-sizer"></div>
				<div class="gutter-sizer"></div>
					<div class="grid-item grid-item--width6 text-left">
						<form action="productlist" name="schForm" id="schForm" method="get">
							<input type="hidden" name="isSearch" value="true"/>
							<div>
								<select name="Skind">
									<option selected="selected">전통주 종류</option>
									<option value="S006">탁주</option>
									<option value="S007">청주</option>
									<option value="S008">약주</option>
									<option value="S009">증류주</option>
									<option value="S010">리큐르</option>
									<option value="S011">기타</option>
									<option value="S012">과실주</option>
								</select>
								검색어 : <input type="text" name="name" >
							</div>
							<div>
								<p>
								  <label for="amount">도수：</label>
								  <input type="text" id="amount" name="prAlcohol" >
								</p>
								 
								<div id="slider-range"></div>
		 					</div>
							<div>
								<p>
								  <label for="price">가격：</label>
								  <input type="text" id="price" name="prPrice">
								</p>
								 
								<div id="slider-range1"></div>
		 					</div>
							<div><input type="submit" value="검색" class="btn btn-sm btn-blue"></div>
						</form>
					</div>
				<c:forEach var="pr" items="${productlist}">
					<div class="grid-item">
						<a href="productdetail?prcode=${pr.prCode}"><img
							src="${pr.prImg}" /></a>
					</div>
				</c:forEach>
			</div>
		</div>
	<script src="/resources/js/imageLoad.js"></script>
	<!-- 데이터 스크롤해서 붙이는 스크립트  -->
	
	<script type="text/javascript">
	$(window).scroll(
		function() {
			// A(B+C) : document 높이 (고정)
			console.log($(document).height());
			// B : browser 높이 (최상단 기본값)
			console.log($(window).height());
			// C : 스크롤 위치
			console.log('SCROLL_TOP' + $(window).scrollTop());
			if ($(window).scrollTop() >= $(document).height()
					- $(window).height() - 100) {
				//호출 메서드
			}
		});
	</script>
	</body>
</c:if>
</html>