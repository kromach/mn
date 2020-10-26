<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/resources/js/formCheck.js"></script>
<div class="grid-Wrapper">
	<div class="grid2"> 	
		<div class="detail-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="detail-item detail-width6">
			<h1 class="text-left pad-y10">주류 후기 등록</h1>
			<form action="commentPro" name="cmForm" method="post">
				<input type="hidden" name="dkCode" value="${drinkInfo.dkCode}"/>
				<input type="hidden" name="insertId" value="${memId}"/>
				<div class="mem-find">
					<h3 class="pad-y10">${drinkInfo.dkName}</h3>
					<div class="searchPanel">
						<div class="ssec pad-top15 clfix">
							<p class="s_label">주류 평가</p>
							<div id="like_div">
								<input type="hidden" name="cmLike" id="cmLike" class="like_input required" msg="좋아요/싫어요를" />
								<input type="hidden" name="cmUnLlike" id="cmUnLlike" class="like_input required" msg="좋아요/싫어요를" />
								<div class="fl" style="width: 40%; text-align: right; padding-right: 20px;">
									<a value="cmLike" class="likeIcon"><i class="far fa-smile" style="font-size:40px; padding-bottom:5px"></i><br><span>좋아요</span></a>
								</div>
								<div class="fr" style="width: 40%; text-align: left;  padding-left: 20px;">
									<a value="cmUnLlike" class="likeIcon"><i class="far fa-frown" style="font-size:40px; padding-bottom:5px"></i><br><span>별로에요</span></a>
								</div>
							</div>
						</div>
						<div class="ssec pad-top15">
							<p class="s_label">한줄평</p>
							<textarea name="cmComment" id="cmComment" type="textarea" class="required" msg="한줄평을" style="width:98%; padding: 2px; height:65px; resize:none;"></textarea>
						</div>
						<div class="ssec pad-top15">
							<p class="s_label fl">세부평가 (Optional)</p>
							<input type="hidden" name="item1" id="dkItem1" value="0" />
							<input type="hidden" name="item2" id="dkItem2" value="0"/>
							<input type="hidden" name="item3" id="dkItem3" value="0"/>
							<input type="hidden" name="item4" id="dkItem4" value="0"/>
							<input type="hidden" name="item5" id="dkItem5" value="0"/>
							<div id="star_grade">
								<c:forEach var="item" items="${elementList}" varStatus="status">
									<p>
										<span class="item-val">${item}</span>
										<c:forEach var="i" begin="1" end="5">
									    	<a class="item${status.count}" index="${status.count}" value="${i}"><i class="fas fa-star"></i></a>
								    	</c:forEach>
								    </p>
								</c:forEach>
							</div>
						</div>
						<div class="ssec pad-top15">
							<p class="s_label fl">태그 등록 (Optional)</p>
							<input type="text" name="dkTags" style="width:99%; height: 20px;" />
							<p class="pad-top5">쉼표로 태그를 구분하여 입력해주세요.<br /> ex)마시는,녀석들,맥주,beer</p>
						</div>
						<div class="ssec pad-top15 text-center">
							<input type="button" class="btn btn-lg btn-blue" value="등록" onclick="insertComment()" />
							<input type="button" class="btn btn-lg btn-grey" value="취소" onclick="history.back()" />
						</div>
					</div>
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
    $('a.likeIcon').click(function() {
    	//console.log("a");
    	$("a.likeIcon").removeClass("on");  /* 좋아요 아이콘 on 클래스 전부 제거 */ 
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