<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.zingchart.com/zingchart.min.js"></script>
<script src="/resources/js/jqcloud.js"></script>
<link rel="stylesheet" href="/resources/js/jqcloud.css">

<div class="grid-Wrapper">
	<div class="grid2"> 
	
		<div class="detail-sizer"></div>
		<div class="gutter-sizer"></div>
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
					<dd class="clfix">${drinkInfo.dkPlace}</dd>
					<dt>국가 / 지역</dt>
					<dd class="clfix">${drinkInfo.dkCountry} > ${drinkInfo.dkCity} </dd>
					<dt>도수</dt>
					<dd class="clfix">${drinkInfo.dkAlcohol} %</dd>
					<dt>어울리는 안주</dt>
					<dd class="clfix">${drinkInfo.dkFood} </dd>
				</dl>
				<div>
					<button class="btn btn-lg btn-blue" onclick="">좋아요</button>
					<a class="btn btn-lg btn-yellow" onclick="">후기등록</a>
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
			<h3 class="pad-top10 pad-bottom20">상세정보</h3>
			${drinkInfo.dkContent}
		</div>
		<div class="detail-item detail-width6">
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
		
	    var words = [
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
	  	  {text: "벨지안 효모", weight: 7}
	  	];

	  	$('#tagCloud').jQCloud(words, {
	  		autoResize: true,
	  		fontSize: {
	  			from: 0.15,
	  			to: 0.04
	  		}
	  	});
	});

	    
</script>

<!-- 
<script src="/resources/js/imageLoad.js"></script> -->