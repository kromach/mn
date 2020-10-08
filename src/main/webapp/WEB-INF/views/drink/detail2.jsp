<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/resources/js/chart.min.js"></script>
 <style>
   .chart--container {
     width: 100%;
     height: 100%;
   }
 </style>
<div class="grid-Wrapper">
	<div class="grid2"> 
	
		<div class="detail-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="detail-item detail-width2 detail-img-div" >
			<img id="mainImg" src="${selectDrinkInfo.dkImg}" alt="${selectDrinkInfo.dkName}" />
		</div>
		<div class="detail-item detail-width4 detail-info-div">
			<div class="detail-info text-left">
				<div class="navi margin-bottom5">
					<a href="index?schDkBkind=${selectDrinkInfo.dkBkind}">${selectDrinkInfo.dkBkindValue}</a> > <a href="index?schDkBkind=${selectDrinkInfo.dkBkind}&schDkSkind=${selectDrinkInfo.dkSkind}">${selectDrinkInfo.dkSkindValue}</a>
				</div>
				<h3>${selectDrinkInfo.dkName}</h3>
				
				<dl class="clear">
					<dt>양조장</dt>
					<dd class="clfix">${selectDrinkInfo.dkPlace}</dd>
					<dt>국가 / 지역</dt>
					<dd class="clfix">${selectDrinkInfo.dkCountry} > ${selectDrinkInfo.dkCity} </dd>
					<dt>도수</dt>
					<dd class="clfix">${selectDrinkInfo.dkAlcohol} %</dd>
					<dt>어울리는 안주</dt>
					<dd class="clfix">${selectDrinkInfo.dkFood} </dd>
				</dl>
				<div>
					<button class="btn btn-lg btn-blue" onclick="">좋아요</button>
					<a class="btn btn-lg btn-yellow" onclick="">후기등록</a>
				</div>
			</div>
		</div>
		<div class="detail-item detail-width3">
			<canvas id="dkChart" class="chart--container">
			    
			</canvas>
		</div>
		<div class="detail-item detail-width3"><p>태그클라우드</p><img src="/resources/img/main/8.jpg" /></div>
		<div class="detail-item detail-width6">
			<h3 class="pad-top10 pad-bottom20">상세정보</h3>
			${selectDrinkInfo.dkContent}
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
		
		var labelsArr 	= ['산미', '쓴맛', '단맛', '바디감', '탄닌'],
			dataArr1 	= [1, 5, 4, 3, 2];
			dataArr2 	= [2, 3, 4, 4, 1];
		
		var config = {
			type: 'radar',
			data: {
				labels: labelsArr,
				datasets: [{
					data: dataArr1,
					"fill": true,
		            "backgroundColor": "rgba(255, 99, 132, 0.2)",
		            "borderColor": "rgb(255, 99, 132)",
		            "pointBackgroundColor": "rgb(255, 99, 132)",
		            "pointBorderColor": "#fff",
		            "pointHoverBackgroundColor": "#fff",
		            "pointHoverBorderColor": "rgb(255, 99, 132)",
					label: '작성자 평가점수'
				},{
					data: dataArr2,
					"fill": true,
		            "backgroundColor": "rgba(54, 162, 235, 0.2)",
		            "borderColor": "rgb(54, 162, 235)",
		            "pointBackgroundColor": "rgb(54, 162, 235)",
		            "pointBorderColor": "#fff",
		            "pointHoverBackgroundColor": "#fff",
		            "pointHoverBorderColor": "rgb(54, 162, 235)",
					label: '유저평가 평균점수'
				}]
			},
		    "options": {
		        layout: {
		            padding: {
		                left: 0,
		                right: 0,
		                top: 20,
		                bottom: 20
		            }
		        },
		        "elements": {
		            "line": {
		                "tension": 0,
		                "borderWidth": 3
		            }
		        },
		        legend: {
		        	position: 'bottom',
		            labels: {
			        	boxWidth: 20,
		                fontColor: 'black',
		                fontFamily : "'Nanum Gothic','나눔고딕','돋움',Dotum,'Trebuchet MS',Helvetica,sans-serif",
		                fontSize: 14
		            }
		        }
		    }
		};
		
		var ctx = document.getElementById('dkChart').getContext('2d');
		var myPie = new Chart(ctx, config);
		// window.myPie = new Chart(ctx, config);  // window 객체에 myPie 라는 키를 넣고 값을 대입함
		
	});
</script>
<!-- 
<script src="/resources/js/imageLoad.js"></script> -->