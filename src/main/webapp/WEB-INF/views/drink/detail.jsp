 <%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://cdn.zingchart.com/zingchart.min.js"></script>
<script src="/resources/js/jqcloud.js"></script>
<link rel="stylesheet" href="/resources/js/jqcloud.css">

<div class="grid-Wrapper">
	<div class="grid2"> 
	
		<div class="detail-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="detail-item detail-width6">
			<h1 class="text-left pad-y10">주류 정보</h1>
		</div>
		<div class="detail-item detail-width2 detail-img-div" >
			<img id="mainImg" src="${drinkInfo.dkImg}" alt="${drinkInfo.dkName}" />
		</div>
		<div class="detail-item detail-width4 detail-info-div">
			<div class="detail-info text-left">
				<div class="navi margin-bottom5">
					<a href="index?schDkBkind=${drinkInfo.dkBkind}">${drinkInfo.dkBkindValue}</a> > <a href="index?schDkBkind=${drinkInfo.dkBkind}&schDkSkind=${drinkInfo.dkSkind}">${drinkInfo.dkSkindValue}</a>
				</div>
				<h3>${drinkInfo.dkName}</h3>
				
				<dl class="clear">
					<dt>양조장</dt>
					<dd>${drinkInfo.dkPlace}</dd>
					<dt>국가 / 지역</dt>
					<dd>${drinkInfo.dkCountry} > ${drinkInfo.dkCity} </dd>
					<dt>도수</dt>
					<dd>${drinkInfo.dkAlcohol} %</dd>
					<dt>어울리는 안주</dt>
					<dd>${drinkInfo.dkFood} </dd>
				</dl>
				<div>
					<input type="hidden" id="drinkLikeYN" value="${drinkLikeInfo}" />
					<input type="hidden" id="drinkLikeCount" value="${drinkInfo.dkLike}" />
					<span id="like-btn"></span><!-- 좋아요버튼 -->
					<a class="btn btn-lg btn-yellow" href="comment?dkCode=${drinkInfo.dkCode}">후기등록</a>
					<%-- <c:if test="${sessionScope.userKind eq 'admin' }"> --%>
					<a class="btn btn-lg btn-mint" href="modify?dkCode=${drinkInfo.dkCode}" >수정</a> <!-- 관리자만 노출 -->
					<%-- </c:if> --%>
				</div>
			</div>
		</div>
		<div class="detail-item detail-width3">
			<!-- 레이더 차트 -->
			<div id="dkChart" class="chart-container"></div>
		</div>
		<div class="detail-item detail-width3">
			<!-- 태그 클라우드 -->
			<div id="tagCloud" style="width:100%; height:450px; position: relative; background-color: #fff;"></div>
		</div>
		<div class="detail-item detail-width6">
			<h3 class="pad-top10 pad-bottom20 text-left">상세정보</h3>
			<div class="text-left">${drinkInfo.dkContent}</div>
		</div>
		<!-- 연관글 -->
		<div class="detail-item detail-width6">
			<h3 class="pad-top10 pad-bottom20 text-left">연관 게시글 보기</h3>
			<table id="boardTbl" class="detailTbl tbl-lg">
				<colgroup>
					<col width="*" />
					<col width="20%" />
					<col width="20%" />
				</colgroup>
				<tr>
					<th>제목</th>
					<th style="min-width:100px;">작성자</th>
					<th style="min-width:130px;">작성일</th>
				</tr>
				<!-- 주류코드별 작성된 글 개수 체크 메서드 & 글 불러오는 메서드 추가 필요 -->
				<c:if test="${articleCount < 1}">
				<tr>
					<td colspan="3"><p class="text-center font-bold" style="font-size: 15px;padding: 5px 0;color: #069;"><i class="fas fa-comment-slash"></i>아직 작성된 게시글이 없습니다.</p></td>
				</tr>
				</c:if>
				<c:forEach var="article" items="${articleList}">
				<tr>
					<td><a href="/article/detail?idx=${article.bnIdx}">${article.bnTitle}</a></td>
					<td>
						<span class="btn btn-twitter btn-xs default">${article.writerTitleCnt}</span>
						<span class="titleName">${article.writerTitleName}</span>
						${article.nickname}
					</td>
					<td><fmt:formatDate value="${article.insertDay}" pattern="yyyy.MM.dd"/></td>
				</tr>
				</c:forEach>
			</table>
			<!-- pager -->
			<div class="page_wrap">
			<div class="page_nation">
				<!-- 게시글이 있을때만 보여주기 -->
				<c:if test="${articleCount > 0}">
					<c:if test="${pageVO.startPage > pageVO.pageBlock}">
						<a class="arrow prev" href="?dkCode=${drinkInfo.dkCode}&pageNum=${pageVO.startPage-pageVO.pageBlock}#boardTbl">&lt;</a>
					</c:if>
					
					<c:forEach var="i" begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1"> 
						<c:if test="${i == pageNum}">
							<a class="active">${i}</a>		
						</c:if>
						<c:if test="${i != pageNum}">
							<a href="?dkCode=${drinkInfo.dkCode}&pageNum=${i}#boardTbl" class="pageNums">&nbsp;${i}&nbsp;</a>
						</c:if>
					</c:forEach>
					
					<c:if test="${pageVO.endPage < pageVO.pageCount}">
						<a class="arrow next" href="?dkCode=${drinkInfo.dkCode}&pageNum=${pageVO.startPage+pageVO.pageBlock}#boardTbl">&gt;</a>
					</c:if>
				</c:if>
			</div>
			</div>
			
		</div>
		<div class="detail-item detail-width6">
			<h3 class="pad-top10 pad-bottom20 text-left">후기 보기 (${commentStarInfo != null ? commentStarInfo.cmCount : '0'})</h3>
			<div class="commentDiv margin-bottom30" id="commentList" style="height:500px">
				<c:if test="${empty commentList}">  
					<div class="clfix pad-top10" style="border-top: 2px solid #333;">
						아직 등록된 후기가 없습니다.
					</div>
				</c:if>
				<table id="boardTbl" class="detailTbl tbl-lg">
					<colgroup>
						<col width="20%" />
						<col width="*" />
						<col width="15%" />
					</colgroup>
					<c:forEach items="${commentList}" var="comment">
					<tr>
						<td>
							<div class="nm_like">
								<!-- 작성자, 좋아요, 싫어요 -->
								<p class="likeArea">
									<c:if test="${comment.cmLike == 1}"><i class="far fa-smile"></i></c:if>
									<c:if test="${comment.cmUnLike == 1}"><i class="far fa-frown"></i></c:if>
								</p>
								<p><span class="btn btn-twitter btn-xs default">${comment.writerTitleCnt}</span><span class="titleName">${comment.writerTitleName}</span> <span class="nickname">${comment.nickname}</span></p>
							</div>
						</td>
						<td>
							<div class="report">
								<!-- 별점, 한줄평 -->
								<c:if test="${!empty comment.item1 and comment.item1 > 0}">
									<div class="star">
										${drinkInfo.item1Val} : <i class="fas fa-star"></i>${comment.item1} 
										${drinkInfo.item2Val} : <i class="fas fa-star"></i>${comment.item2} 
										${drinkInfo.item3Val} : <i class="fas fa-star"></i>${comment.item3} 
										${drinkInfo.item4Val} : <i class="fas fa-star"></i>${comment.item4} 
										${drinkInfo.item5Val} : <i class="fas fa-star"></i>${comment.item5} 
									</div>
								</c:if>
								<div>${comment.cmComment}</div>
							</div>
						</td>
						<td>
							<div class="commentDay">
								<fmt:formatDate value="${comment.insertDay}" pattern="yyyy-MM-dd"/><br />
								<fmt:formatDate value="${comment.insertDay}" type="time" dateStyle="medium" />
							</div>					
						</td>
					</tr>
					</c:forEach>
					<%-- 
					<c:forEach items="${commentList}" var="comment">
						<div class="item-list clfix pad-top10">
							<div class="nm_like">
								<!-- 작성자, 좋아요, 싫어요 -->
								<p class="likeArea">
									<c:if test="${comment.cmLike == 1}"><i class="far fa-smile"></i></c:if>
									<c:if test="${comment.cmUnLike == 1}"><i class="far fa-frown"></i></c:if>
								</p>
								<p><span class="btn btn-blue btn-xs default">${comment.writerTitleCnt}</span><span class="titleName">${comment.writerTitleName}</span> <span class="nickname">${comment.nickname}</span></p>
							</div>
							<div class="report">
								<!-- 별점, 한줄평 -->
								<c:if test="${!empty comment.item1 and comment.item1 > 0}">
									<div class="star">
										${drinkInfo.item1Val} : <i class="fas fa-star"></i>${comment.item1} 
										${drinkInfo.item2Val} : <i class="fas fa-star"></i>${comment.item2} 
										${drinkInfo.item3Val} : <i class="fas fa-star"></i>${comment.item3} 
										${drinkInfo.item4Val} : <i class="fas fa-star"></i>${comment.item4} 
										${drinkInfo.item5Val} : <i class="fas fa-star"></i>${comment.item5} 
									</div>
								</c:if>
								<div>${comment.cmComment}</div>
							</div>
							<div class="commentDay">
								<fmt:formatDate value="${comment.insertDay}" pattern="yyyy-MM-dd"/><br />
								<fmt:formatDate value="${comment.insertDay}" type="time" dateStyle="medium" />
							</div>
						</div>
					</c:forEach>
					 --%>
				</table>
			</div>
		</div>
	</div>
</div>
<script>
	$(function() {
		
		likeBtnToggle();
		
		var msnry = new Masonry('.grid2', {
			itemSelector : '.detail-item',
			columnWidth : '.detail-sizer',
			percentPosition : true,
			gutter: '.gutter-sizer'
		});
		imagesLoaded('.grid2').on('progress', function() {
			msnry.layout();
		});

	    var labelArr = ["${drinkInfo.item1Val}", "${drinkInfo.item2Val}", "${drinkInfo.item3Val}", "${drinkInfo.item4Val}", "${drinkInfo.item5Val}"];
    	
	    var myConfig = {
			type: 'radar',
			legend: {
				align: "center",
				'vertical-align': "bottom",
				marginBottom: "30px"
			},
	      	plot: {
	        	aspect: 'area',
	        	animation: {
	          		effect: 3,
	          		sequence: 1,
	          		speed: 700
	        	},
	        	tooltip: {
	        		text: "%t: %v"
	        	}
	    
	      	},
		   	scaleV: { // 세로축 값 표시 
		    	//visible: false
		   		values: "0:5:1" // 최소값, 최대값, 증가값
/* 				   		guide: { //Guides (both lines and background colors)
		        	'line-color': "#607D8B",
		        	'line-width':1,
		        	'line-style': "dotted",
		    	}
*/				    },
		    scaleK: { // 꼭지점별 범주
		    	labels: labelArr, //Scale Labels
				item: { // To style your scale labels.
			    	fontColor: '#607D8B',
			    	fontWeight: "700",
			    	fontSize:"15px",
					backgroundColor: "white",
					borderColor: "#aeaeae",
					borderWidth: 1,
					padding: '5 7',
					borderRadius: 10
		        },
				refLine: {
					lineColor: '#c10000'
				},
				tick: { //Tick Marks 눈금표시
					lineColor: '#59869c',
					lineWidth: 2,
					lineStyle: 'dotted',
					size: 15
				},
				guide: {  // Guides (both lines and background colors)
					lineColor: "#607D8B",
					lineStyle: 'solid',
					alpha: 0.3,
					backgroundColor: "#c5c5c5 #718eb4"
				}
			},
			series: [ //표시할 값들
				{
		          values: [${drinkInfo.dkItem1}, ${drinkInfo.dkItem2}, ${drinkInfo.dkItem3}, ${drinkInfo.dkItem4}, ${drinkInfo.dkItem5}],
		          text: '작성자 평가점수'
				},
				{
		          values: [${commentStarInfo.cmItem1}, ${commentStarInfo.cmItem2}, ${commentStarInfo.cmItem3}, ${commentStarInfo.cmItem4}, ${commentStarInfo.cmItem5}],
		          lineColor: '#53a534',
		          backgroundColor: '#689F38',
		          text: '유저평가 평균점수'
				}
			]
		};
	
	    zingchart.render({
			id: 'dkChart',
			data: myConfig,
			height: 'auto',
			width: 'auto'
	    });
		
	    var words = [];
	    
    	<c:forEach items="${tagCloudInfo}" var="tagInfo" varStatus="status" >
    		words.push({text: "${tagInfo.tagValue}", weight: "${tagInfo.tagCount}"});
	    </c:forEach>
/* 
	  	  {text: "페일에일", weight: 13},
	  	  {text: "에일", weight: 10.5},
	  	  {text: "한국", weight: 9.4},
	  	  {text: "Sit", weight: 8},
	  	  {text: "Amet", weight: 6.2},
	  	  {text: "Consectetur", weight: 5},
	  	  {text: "밥", weight: 5},
	  	  {text: "맥주", weight: 11},
	  	  {text: "비아", weight: 9},
	  	  {text: "ビア", weight: 9},
	  	  {text: "달달", weight: 1},
	  	  {text: "플레이그라운드", weight: 5},
	  	  {text: "스칼라 벨지안 블론드 에일", weight: 3},
	  	  {text: "스칼라", weight: 9},
	  	  {text: "경기도", weight: 9},
	  	  {text: "고양시", weight: 1},
	  	  {text: "국산맥주", weight: 5},
	  	  {text: "피자", weight: 3},
	  	  {text: "치킨", weight: 5},
	  	  {text: "부드러운", weight: 3},
	  	  {text: "벨지안 효모", weight: 7} */
	  	//];

		//태그 클라우드 폰트 사이즈 조절 
	  	var fromSize = 0.12;
	  	var toSize = 0.04;
	  	
	  	if (words.length < 10) {
	  		// tag 개수가 적을때 기본 태그 추가
	  		words.push({text: "마시는 녀석들", weight: 5}, {text: "마녀", weight: 4}, {text: "주류정보", weight: 3});
		  	
		  	toSize = 0.05;
	  	} else {
		  	fromSize = 0.1;
		  	toSize = 0.02;	  		
	  	}
	  	
	  	$('#tagCloud').jQCloud(words, {
	  		colors: ["#5944aa", "#d52896", "#7396e5", "#ddbd15", "#9197D3", "#75cb74", "#6187C8", "#6bc3f6", "#49654C", "#e88770"],
	  		autoResize: true,
	  		fontSize: {
	  			from: fromSize,
	  			to: toSize
	  		}
	  	});
	});
	
	function likeBtnToggle(){
		console.log($('#drinkLikeYN').val());
		var likeCount = $('#drinkLikeCount').val();
		var btn = '<button class="btn btn-lg btn-blue" onclick="like()">[heart] '+ likeCount +'</button>';
		var heart = '<i class="far fa-heart"></i>';
		
		if($('#drinkLikeYN').val() == 'Y') { // 좋아요 상태면
			heart = '<i class="fas fa-heart c_orange"></i>';
		}
		
		$("#like-btn").empty().html(btn.replace('[heart]', heart));
	}
	
	function like() {
		if (!"${memId}") {
			alert("로그인이 필요합니다.");
		} else {
			$.ajax({
				url : 'like?dkCode=${drinkInfo.dkCode}&memId=${memId}',
				type : "POST",
				success : function(data) {
					//console.log(data);
					// 1. input hedden 업데이트 
					$("#drinkLikeYN").val(data);
	
					// 2. 좋아요 개수 업데이트
					var likeCount = $('#drinkLikeCount').val();
					$("#drinkLikeCount").val((data == 'Y' ? Number(likeCount) + 1 : Number(likeCount) - 1));
					
					// 3. 버튼 새로고침
					likeBtnToggle();
				},
				error : function() {
					alert("error");
				}
			});
		}
	}
</script>

<script type="text/javascript">
$(window).scroll(
	function() {
		// A(B+C) : document 높이 (고정)
		//console.log($(document).height());
		// B : browser 높이 (최상단 기본값)
		//console.log($(window).height());
		// C : 스크롤 위치
		//console.log('SCROLL_TOP' + $(window).scrollTop());
		if ($(window).scrollTop() >= $(document).height()
				- $(window).height() - 100) {
			//호출 메서드
		}
	});
</script>
<%-- 	<c:forEach items="${tagCloudInfo}" varStatus="status" >
	var tagNum = "${status.index}";
	var 	
	{text: "${tagInfo.tagValue}", weight: "${tagInfo.tagCount}"}
</c:forEach> --%>
<!-- 
<script src="/resources/js/imageLoad.js"></script> -->