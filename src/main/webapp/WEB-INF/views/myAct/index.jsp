<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<style>
.linkBar {
	width: 25%;
	border: 0;
	float: left;
	text-align: center;
}
</style>

<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<br/><br/>
			<h1 align="center">마이 페이지</h1>
			<br/><br/><br/><br/>
			<div class="linkBar">
				<button class="btn btn-md btn-blue" onclick="window.location='/myAct'">내 글 보기</button>
			</div>
			<div class="linkBar" id="likeArticle">
				<button class="btn btn-md btn-blue">글 좋아요</button>
			</div>
			<div class="linkBar" id="likeDrink">
				<button class="btn btn-md btn-blue">술 좋아요</button>
			</div>
			<div class="linkBar" id="getAllTitle">
				<button class="btn btn-md btn-blue">칭호</button>
			</div>
			<br/>
			<br/>
			<div class="grid-sizer"></div>
			<div id="tmp" class="grid-item grid-item--width6">
				<div id="index">
					<table class="tableCss" style="width: 100%;">
						<tr>
							<td>번호</td>
							<td>제목</td>
							<td>작성일</td>
							<td>조회</td>
							<td>추천</td>
						</tr>
						<c:forEach var="myArticle" items="${myArticle}">
							<tr>
								<td>${myArticle.bnIdx}</td>
								<td><a href="/article/detail?idx=${myArticle.bnIdx}">${myArticle.bnTitle}</a></td>
								<td><fmt:formatDate value="${myArticle.insertDay}" pattern="yyyy.MM.dd"/></td>
								<td>${myArticle.readcount}</td>
								<td>${myArticle.heart}</td>
							</tr>
						</c:forEach>
					</table>
					<!-- pager -->
					<div align="center" class="pageNums">
						<!-- 게시글이 있을때만 보여주기 -->
					<c:if test="${count>0}">
					<!-------------------------------------------------------------------------->
						<c:if test="${pageVO.startPage > pageVO.pageBlock}">
							<a href="/myAct/index?pageNum=${pageVO.startPage-pageVO.pageBlock}">&lt;</a>
						</c:if>
						<c:forEach var="i" begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1"> 
									<a href="/myAct/index?pageNum=${i}" class="pageNums">&nbsp;${i}&nbsp;</a>
						</c:forEach>
						<c:if test="${pageVO.endPage < pageVO.pageCount}">
							<a href="/myAct/index?pageNum=${pageVO.startPage+pageVO.pageBlock}">&gt;</a>
						</c:if>
						<!-------------------------------------------------------------------------->
					</c:if>
				</div>
				</div>
				<div id="likeArticle_result">
					
				</div>
				<div id="likeDrink_result">
					
				</div>
				<div id="title_result">
					
				</div>
			</div>
		</div>
	</div>
	<input type = "hidden" value="${sessionScope.memId}" id="memId">
</body>
<script>
			$(document).ready(function(){
			$("#likeArticle").click(function(){
				$.ajax({
					type:"POST",
					url:"/myAct/likeArticle",
					data:{id:$("#memId").val()},
					success:function(data){
						var likeArticle = data.likeArticle;
						var count = data.count;
						var pageNum = data.pageNum;
						var number = data.number;
						var pageVO = data.pageVO;
						console.log(count);
						console.log("pageVO :" + pageVO.startPage);
						$('#index').empty();
						$('#likeDrink_result').empty();
						$('#title_result').empty();
						$('#likeArticle_result').empty();
						$('#tmp').attr('class','grid-item grid-item--width6');	
						
						$('#likeArticle_result').append('<table class="tableCss" style="width: 100%;">');
						$('#likeArticle_result > table').append('<tr>');
						$('#likeArticle_result > table > tr').append('<td>번호</td>');
						$('#likeArticle_result > table > tr').append('<td>제목</td>');
						$('#likeArticle_result > table > tr').append('<td>작성일</td>');
						$('#likeArticle_result > table > tr').append('<td>조회</td>');
						$('#likeArticle_result > table > tr').append('<td>추천</td>');
						$('#likeArticle_result > table').append('</tr>');		
						
						for(var i in likeArticle){
							console.log(likeArticle[i]);
							$('#likeArticle_result > table').append('<tr>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].bnIdx+'</td>');
							$('#likeArticle_result > table > tr:last').append('<td><a href="/article/detail?idx='+likeArticle[i].bnIdx+'">'+likeArticle[i].bnTitle+'</a></td>');
							$('#likeArticle_result > table > tr:last').append('<td>'+ moment(new Date(likeArticle[i].insertDay)).format('YYYY.MM.DD')+'</td>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].readcount+'</td>');
							$('#likeArticle_result > table > tr:last').append('<td>'+likeArticle[i].heart+'</td>');
							$('#likeArticle_result > table').append('</tr>');
						}
						
						
						
					}
				});
			});
		});
	</script>
	<script>
		$(document).ready(function(){
			$("#likeDrink").click(function(){
				$.ajax({
					type:"POST",
					url:"/myAct/likeDrink",
					data:{id:$("#memId").val()},
					success:function(data){
						var likeDrink = data.likeDrink;
						var likeProduct = data.likeProduct;
						console.log(data.likeProduct);
						$('#index').empty();
						$('#likeDrink_result').empty();
						$('#title_result').empty();
						$('#likeArticle_result').empty();
						$('#tmp').attr('class','grid-item');
						
						if(data.likeDrink == null && data.likeProduct == null){
							$('#likeDrink_result').append('<h2> 좋아요한 게시글이 없습니다. </h2>');
						}
						for(var i in likeProduct){
							console.log(likeProduct[i]);
							console.log(likeDrink[i]);
							$('#likeDrink_result').append('<div>');
							$('#likeDrink_result').append('<a href="/product/productdetail?prcode='+likeProduct[i].prCode+'"><img src="'+likeProduct[i].prImg+'" /></a>');
							$('#likeDrink_result').append('</div>'); 		
						}
					}
				});
			});
		});
	
	</script>
	
	<script>
			$(document).ready(function(){
			$("#getAllTitle").click(function(){
				
				$.ajax({
					type:"POST",
					url:"/myAct/getAllTitle",
					data:{id:$("#memId").val()},
					success:function(data){
						
						var getAllTitle = data.allTitle; // 타이틀 정보 
						var updateTitle = data.updateTitle; // 획득 여부 가져오기
						console.log(data);
						
						/* console.log(updateTitle[j]); */
						$('#index').empty();
						$('#likeDrink_result').empty();
						$('#title_result').empty();
						$('#likeArticle_result').empty();	
						$('#tmp').attr('class','grid-item grid-item--width6');						
						
						$('#title_result').append('<table class="tableCss" style="width: 100%;">');
						
						var getTitleIndex;
						
						for(var i in getAllTitle){
							
							// 획득한 칭호인지 - chooseTitle에 값이 있으면 획득
							// 선택한 칭호인지 - chooseTitle이 Y 면 선택한 칭호
							var myTitle = "";
							var chooseTitle = "";
							var value ="";
							console.log(getAllTitle[i].chooseTitle);
							
							if(getAllTitle[i].chooseTitle) {
								myTitle = "color:red";
								if(getAllTitle[i].chooseTitle == "Y") {
									value ="선택 완료";
									//alert(getAllTitle[i].titleName);
								}else{
									value ="칭호 선택";
								}
							} 
							
							//console.log(myTitle, chooseTitle)
							
							var str = '<tr>';
							str = str + '<td style="'+myTitle+'">'+getAllTitle[i].titleName+'</td>';
							str = str + '<td>'+getAllTitle[i].titleDetail+'</td>';
							if (myTitle) {
							str = str + '<td><button class="btn btn-sm btn-red chooseTitle '+chooseTitle+' btn'+getAllTitle[i].titleIdx+' " onclick="choosen('+getAllTitle[i].titleIdx+')">'+value+'</button></td>';
							}else{
							str = str + '<td></td>';
							}
							str = str + '</tr>';	
							
							$('#title_result > table').append(str);
						}
					}
				});
			});
		});
	</script>

	<script>
	function choosen(titleIdx){
		console.log(typeof(titleIdx));
		//alert(titleIdx);
		if(confirm("칭호를 변경하시겠습니까?")) {
			titleIdx_ = titleIdx+"";
			
			$.ajax({
				url: "/myAct/chooseTitle?titleIdx="+titleIdx,
				type:"POST",
				success : function(data){
					
					// 감춰져 있는 이전 칭호의 버튼 표시하기
					$("#title_result").find(".chooseTitle").text('칭호 선택');
					// 현재 변경한 칭호의 버튼 바꾸기
					$('.btn' + titleIdx).text('선택 완료');
				}
			})
		}
	}
	</script>
	
	<script type="text/javascript">
	function fetchList(context,index){
		if(isEnd == true){
			return;
		}
		$.ajax({
			url : context + '/reload',
			data : 'index='+index,
			type : "post",
			async: false,
			success : function(data) {
				if(data==""){
					isEnd = true;
					console.log('end');
				}else{
					console.log(data);
					$('#index').val(Number(index)+1);
					var $grid = $('.grid').masonry({
						itemSelector : '.grid-item',
						columnWidth : '.grid-sizer',
						percentPosition : true,
						gutter: '.gutter-sizer'
					});
					for(var i in data){
						var el = '<div class="grid-item"><a href="'+data[i].aLinkUri+'"><img src="'+data[i].imgUri+'" onerror="this.src=\'/resources/img/noImage.jpg\'" /></a></div>';
						$grid.append( el ).masonry( 'appended', el ,true);
					}
					// 재훈 테스트
					// masonry 재 실행 
					$grid.masonry( 'reloadItems' );
		   			$grid.masonry();
				}
			}
		});
	}
	</script>
	
	<script src="/resources/js/imageLoad.js"></script>
	<!-- 데이터 스크롤해서 붙이는 스크립트  -->
	<script type="text/javascript">
		$(window).s croll(
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
</html>