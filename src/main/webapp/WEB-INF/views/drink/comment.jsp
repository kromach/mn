<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/resources/js/formCheck.js"></script>
<style>
     #star_grade a{
        text-decoration: none;
        color: gray;
    }
    #star_grade a.on{
        color: red;
    }
</style>

<div class="grid-Wrapper">
	<div class="grid2"> 	
		<div class="detail-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="detail-item detail-width6">
			<h3 class="text-left pad-y10">주류 후기 등록</h3>
			<form action="commentPro" name="cmForm" method="post">
			
				<div>
					<h3>${drinkInfo.dkName}</h3>
					<div>
						<input type="hidden" name="like" id="like" class="required" msg="좋아요/싫어요를" />
						<a class='cmLike' id="like" value='like'><i class="far fa-smile"></i></a>
						<a class='cmLike' id="unlike" value='unlike'><i class="far fa-frown"></i></a>
					</div>
					<hr />
					<div>
						<label>한줄평
						<textarea name="cmComment" id="cmComment" style="width:98%; height:60px"></textarea></label>
					</div>
					<hr />
					<div>
						<input type="hidden" name="dkItem1" id="dkItem1" class="required" msg="항목 점수를" />
						<input type="hidden" name="dkItem2" id="dkItem2" class="required" msg="항목 점수를" />
						<input type="hidden" name="dkItem3" id="dkItem3" class="required" msg="항목 점수를" />
						<input type="hidden" name="dkItem4" id="dkItem4" class="required" msg="항목 점수를" />
						<input type="hidden" name="dkItem5" id="dkItem5" class="required" msg="항목 점수를" />
						<div id="star_grade">
						    <p>
						    	<span class="item-val">쓴맛</span>
						    	<a class="item1" index="1" value="1"><i class="fas fa-star" aria-hidden="true"></i></a>
						    	<a class="item1" index="1" value="2"><i class="fas fa-star" aria-hidden="true"></i></a>
						    	<a class="item1" index="1" value="3"><i class="fas fa-star" aria-hidden="true"></i></a>
						    	<a class="item1" index="1" value="4"><i class="fas fa-star" aria-hidden="true"></i></a>
						    	<a class="item1" index="1" value="5"><i class="fas fa-star" aria-hidden="true"></i></a>
						    </p>
						    <p><span class="item-val">단맛</span><a class="item2" index="2" value="1"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item2" index="2" value="2"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item2" index="2" value="3"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item2" index="2" value="4"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item2" index="2" value="5"><i class="fas fa-star" aria-hidden="true"></i></a></p>
						    <p><span class="item-val">바디감</span><a class="item3" index="3" value="1"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item3" index="3" value="2"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item3" index="3" value="3"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item3" index="3" value="4"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item3" index="3" value="5"><i class="fas fa-star" aria-hidden="true"></i></a></p>
						    <p><span class="item-val">탄산</span><a class="item4" index="4" value="1"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item4" index="4" value="2"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item4" index="4" value="3"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item4" index="4" value="4"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item4" index="4" value="5"><i class="fas fa-star" aria-hidden="true"></i></a></p>
						    <p><span class="item-val">후르티</span><a class="item5" index="5" value="1"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item5" index="5" value="2"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item5" index="5" value="3"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item5" index="5" value="4"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item5" index="5" value="5"><i class="fas fa-star" aria-hidden="true"></i></a></p>
						</div>
					</div>
					<hr />
					<div>
						<label>태그추가
							<input type="text" name="dkTags" class="input-lg" />
							<br/>쉼표로 태그를 구분하여 입력해주세요 ex)마시는,녀석들,맥주,beer
						</label>
					</div>
				</div>
				<div class="text-center pad-top10">
					<input type="button" class="btn btn-lg btn-blue" value="등록" onclick="insertComment()" />
					<input type="button" class="btn btn-lg btn-grey" value="취소" onclick="history.back()" />
				</div>
			</form>
		</div>
	</div>
</div>
<script>
	$(function() {
		
		/* 테스트 정보 */
		//$("input[name='dkAlcohol']").val("4.5");
		//$("input[name='dkTags']").val("말표, 흑맥주,말표맥주,MALPYO,CU,콜라보,에스프레소,다크초콜릿,밤");
		
		// masonry 실행
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

	// 평점 별점 조절 
    $('#star_grade a').click(function() {
    	//console.log("a");
    	$(this).parent().children("a").removeClass("on");  /* 별점의 on 클래스 전부 제거 */ 
    	$(this).addClass("on").prevAll("a").addClass("on"); /* 클릭한 별과, 그 앞 까지 별점에 on 클래스 추가 */
    	var index = $(this).attr("index");
    	var value = $(this).attr("value");
    	//console.log("i : " + index + "v : " + value);
    	$("#dkItem"+index).val(value);
    });
	
    function insertComment(){
    	if (checkFormjquery()) {
    		
    		if (confirm("정보를 입력하시겠습니까?")) {
	    		$("form[name='cmForm']").submit();
			}
    	}
    }
    
</script>