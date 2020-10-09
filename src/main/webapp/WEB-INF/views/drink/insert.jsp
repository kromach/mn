<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/resources/js/formCheck.js"></script>
<script src="/resources/js/jquery.selectric.js"></script>
<link rel="stylesheet" href="/resources/css/selectric.css">
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/resources/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript">

	// ckeditor 설정
	CKEDITOR.on('dialogDefinition', function (ev) {
		
		var dialogName = ev.data.name;
		var dialog = ev.data.definition.dialog;
		var dialogDefinition = ev.data.definition;

		if (dialogName == 'image') {
			dialog.on('show', function (obj) {
				this.selectPage('Upload'); //사진 추가 버튼 클릭시 업로드탭으로 시작
			});	
			dialogDefinition.removeContents('advanced'); // 자세히탭 제거
			dialogDefinition.removeContents('Link'); // 링크탭 제거
		}
	});

	//이미지 업로드 url 설정
	var ckedit_config = {
		filebrowserUploadUrl : 'ckuploader' ,  // 통신할 컨트롤러 매핑 주소 
		toolbar : ''
	}
	// ckeditor 설정 종료
	
</script>

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
			<h3 class="text-left pad-y10">새 정보 등록</h3>
			<form action="insertPro" name="dkForm" method="post" enctype="multipart/form-data">
				<table class="tableCss tbl-lg text-left">
					<tr>
						<th>주류 종류</th>
						<td>
							<select id="dkBkind" name="dkBkind" class="sel short required" type="select-one" msg="대분류를" >
								<option value="">대분류 선택</option>
								<c:forEach items="${bigCategoryList}" var="bigCategory">
									<option value="${bigCategory.code}">${bigCategory.value}</option>
								</c:forEach>
							</select>
							<select id="dkSkind" name="dkSkind" class="sel short required" type="select-one" msg="소분류를" >
								<option value="">소분류 선택</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>주류명</th>
						<td>
							<input type="text" name="dkName" class="input-lg required" msg="주류명을" />
						</td>
					</tr>
					<tr>
						<th>대표 이미지</th>
						<td>
							<input type="file" name="dkImg" class="required" />
						</td>
					</tr>
					<tr>
						<th>양조장</th>
						<td>
							<input type="text" name="dkPlace" class="input-lg required" />
						</td>
					</tr>
					<tr>
						<th>국가/도시</th>
						<td>
							<input type="text" name="dkCountry" class="input-sm required" placeholder="국가"/>
							&nbsp;<input type="text" name="dkCity" class="input-sm required" placeholder="도시"/>
						</td>
					</tr>
					<tr>
						<th>알콜 도수</th>
						<td>
							<input type="number" name="dkPlace" class="input-xs required" /> 도
						</td>
					</tr>
					<tr>
						<th>어울리는 안주</th>
						<td>
							<input type="text" name="dkPlace" class="input-lg required" />
						</td>
					</tr>
					<tr>
						<th>평가</th>
						<td id="star_grade">
							대분류를 선택하면 평가항목이 노출됩니다.	
						</td>
					</tr>
					<tr>
						<th>태그</th>
						<td>
							<input type="text" name="dkTag" class="input-lg required" />
							<br/>쉼표로 태그를 구분하여 입력해주세요 ex)마시는,녀석들,맥주,beer
						</td>
					</tr>
					<tr>
						<th>주류 정보</th>
						<td>
							<textarea name="dkContent" id="dkContent" type="textarea" class="required" style="width:98%" ></textarea>
							<script type="text/javascript">CKEDITOR.replace( 'dkContent' , ckedit_config);</script>
						</td>
					</tr>
					
				</table>
				<div class="text-center pad-top10">
					<input type="button" class="btn btn-lg btn-blue" value="저장" onclick="insertDrink()" />
					<input type="button" class="btn btn-lg btn-grey" value="취소" onclick="window.location='index'" />
				</div>
			</form>
		</div>
	</div>
</div>
<script>
	$(function() {
		
		// select 형태 바꿔주는 JS 실행
		$(".sel").selectric();
		
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
		
		// 대분류의 값이 바뀌었을 경우 소분류 값과 항목명 가져오는 ajax 실행
	    $('#dkBkind').change(function() {
	    	getSmallCategory(this.value);
	    	getItemValues(this.value);
	    });

	});
    
	// 소분류 값 가져오는 ajax
    function getSmallCategory(bigCategory) {
    	$.ajax({
			type : "POST",
    		url : "selectSmallCategory",
			data : {bigCategory},  /*{bigCategory:bigCategory} 와 동일*/
			success : function(data){
				$("#dkSkind").empty().append("<option value=''>소분류 선택</option>");
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
    		
    		$("#dkSkind").append(optionStr);
    	});
    	
    	$("#dkSkind").selectric("refresh");
    };
    
    // 평가 항목 명 가져오는 ajax
    function getItemValues(bigCategory) {
    	$.ajax({
			type : "POST",
    		url : "selectItemValues",
			data : {bigCategory},  /*{bigCategory:bigCategory} 와 동일*/
			success : function(data){
				var json = JSON.parse(data);
				//console.log(json)
				$('#star_grade').empty();
				
				json.forEach(function(item, index) {
		    		var grade_str = "<p>";
		    		grade_str += "<span class='item" + index +"'>" + item + "</span>";
		    		for(i = 1; i < 6; i++) {
		    			grade_str += "<a class='dkItem"+index+"' href='#' value='"+i+"'><i class='fas fa-star'></i></a>";
		    		}
		    		grade_str += "</p>";
		    		
		    		$("#star_grade").append(grade_str);
		    	});
				
				// 평점 별점 조절 
			    $('#star_grade a').click(function() {
			    	console.log("a");
			    	$(this).parent().children("a").removeClass("on");  /* 별점의 on 클래스 전부 제거 */ 
			    	$(this).addClass("on").prevAll("a").addClass("on"); /* 클릭한 별과, 그 앞 까지 별점에 on 클래스 추가 */
			    });
			},
			error : function() {
				alert("error");
			}
    	})
    }

    function insertDrink(){
    	if (checkFormjquery()) {
    		
    		$("form[name='dkForm']").submit();
    	}
    }
    
</script>