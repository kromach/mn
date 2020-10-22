<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/resources/js/formCheck.js"></script>
<style>
    #star_grade a, #like_div a{
        text-decoration: none;
        color: gray;
    }
    #star_grade a.on, #like_div a.on{
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
				<input type="hidden" name="dkCode" value="${drinkInfo.dkCode}"/>
				<input type="hidden" name="insertId" value="${memId}"/>
				<div>
					<h3>${drinkInfo.dkName}</h3>
					<div id="like_div" style="font-size:20px">
						<input type="hidden" name="cmLike" id="cmLike" class="like_input required" msg="좋아요/싫어요를" />
						<input type="hidden" name="cmUnLlike" id="cmUnLlike" class="like_input required" msg="좋아요/싫어요를" />
						<a value='cmLike'><i class="far fa-smile"></i></a>
						<a value='cmUnLlike'><i class="far fa-frown"></i></a>
					</div>
					<hr />
					<div>
						<label>한줄평
						<textarea name="cmComment" id="cmComment" type="textarea" class="required" msg="한줄평을" style="width:98%; height:60px"></textarea></label>
					</div>
					<hr />
					<div>
						<input type="hidden" name="item1" id="dkItem1" />
						<input type="hidden" name="item2" id="dkItem2" />
						<input type="hidden" name="item3" id="dkItem3" />
						<input type="hidden" name="item4" id="dkItem4" />
						<input type="hidden" name="item5" id="dkItem5" />
						<div id="star_grade">
							<c:forEach var="item" items="${elementList}" varStatus="status">
								<p>
									<span class="item-val">${item}</span>
									<c:forEach var="i" begin="1" end="5">
								    	<a class="item${status.count}" index="${status.count}" value="${i}"><i class="fas fa-star"></i></a>
							    	</c:forEach>
							    </p>
							</c:forEach>
<!-- 
						    <p><span class="item-val">단맛</span><a class="item2" index="2" value="1"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item2" index="2" value="2"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item2" index="2" value="3"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item2" index="2" value="4"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item2" index="2" value="5"><i class="fas fa-star" aria-hidden="true"></i></a></p>
						    <p><span class="item-val">바디감</span><a class="item3" index="3" value="1"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item3" index="3" value="2"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item3" index="3" value="3"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item3" index="3" value="4"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item3" index="3" value="5"><i class="fas fa-star" aria-hidden="true"></i></a></p>
						    <p><span class="item-val">탄산</span><a class="item4" index="4" value="1"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item4" index="4" value="2"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item4" index="4" value="3"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item4" index="4" value="4"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item4" index="4" value="5"><i class="fas fa-star" aria-hidden="true"></i></a></p>
						    <p><span class="item-val">후르티</span><a class="item5" index="5" value="1"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item5" index="5" value="2"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item5" index="5" value="3"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item5" index="5" value="4"><i class="fas fa-star" aria-hidden="true"></i></a><a class="item5" index="5" value="5"><i class="fas fa-star" aria-hidden="true"></i></a></p>
						     -->
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

	// 좋아요 / 싫어요 토글
    $('#like_div a').click(function() {
    	//console.log("a");
    	$(this).parent().children("a").removeClass("on");  /* 좋아요 아이콘 on 클래스 전부 제거 */ 
    	$(this).addClass("on"); /* 클릭한 아이콘에 on 클래스 추가 */
    	$('.like_input').val("0");
    	
    	var value = $(this).attr("value");
    	//console.log("i : " + index + "v : " + value);
    	$("#"+value).val(1);
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