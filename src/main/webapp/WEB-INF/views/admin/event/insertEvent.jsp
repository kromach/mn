<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<!-- 디자인 변경,   술 선택 셀렉트문 가져오기, 술 선택 아래 --- 변경
	에디터 변경
	파라미터 수정(id 등)
 -->

<title>insert Event</title>
<!-- 유효성검사 js -->
<script src="/resources/js/formCheck.js"></script> 
<!-- 에디터 js -->
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
		filebrowserUploadUrl : '/editor/ckuploader' ,  // 통신할 컨트롤러 매핑 주소 
		toolbar : ''
	}
	// ckeditor 설정 종료
	
</script>
 

<!-- 날짜 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
$( function() {
    var dateFormat = "mm/dd/yy",
      from = $( "#from" )
        .datepicker({
        	
          dateFormat: "yy-mm-dd",
          defaultDate: "+1w",
          changeMonth: true,
          numberOfMonths: 3
        })
        .on( "change", function() {
          to.datepicker( "option", "minDate", getDate( this ) );
        }),
      to = $( "#to" ).datepicker({
        dateFormat: "yy-mm-dd",
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 3
      })
      .on( "change", function() {
        from.datepicker( "option", "maxDate", getDate( this ) );
      });
 
    function getDate( element ) {
      var date;
      try {
        date = $.datepicker.parseDate( dateFormat, element.value );
      } catch( error ) {
        date = null;
      }
 
      return date;
    }
    
  } );

</script>


</head>
<body>

<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width6 ">
		
			<form action="insertEventPro" method="post" id="frm" enctype="multipart/form-data">
				<table class="tableCss table">
					<tr>
						<th>제목</th>
						<td colspan="2"><input type="text" name="eventName" id="tilte" class="required" msg="제목을" placeholder="제목" size="130"/></td>
					</tr>
					<tr>
						<th>술 검색</th>
							<td>
								<input id="dkSch" name="dkSch" class ="boardTitle_middle" />
								<button type="button" class="btn btn-sm btn-grey" onclick="searchDk()">검색</button>
							</td>
					</tr>
					<tr>
						<th>술 선택</th>
						<td colspan="2">
							<select id="option" name="productCode">
								<option value="option">선택</option>
							</select></td>
					</tr>
					<tr>
						<th>
							기간
						</th>
						<td>
							<div>
								<label for="from">시작일</label>
								<input type="text" name="evStart" id="from"/>
								<label for="to">종료일</label>
								<input type="text" name="evEnd" id="to"/>
							</div>
						</td>
					</tr>
					<tr>
						<th>대표사진</th>
						<td>
							<input type="file" name="eventImg" />
						</td>
					</tr>
				
					
					<tr>
						<td colspan="3" style="margin:0; padding:0">
							<textarea name="content" id="ckeditor" type="textarea" class="required" msg="내용을"  rows="10" cols="100" style="width:100%; height:412px;"></textarea>
							<script type="text/javascript">CKEDITOR.replace( 'ckeditor' , ckedit_config);</script>					
						</td>
					</tr>
				</table>
				<div class="text-center pad-top10">
					<input id="addBtn" type="button" class="btn btn-md btn-blue" value="바보">
					<input type="button" class="btn-md" value="취소" onclick="window.location='/admin/event/eventList'"/>
				</div>
			</form>
		</div>
	</div>
</div>
</body>


 <!-- 검색기능 -->
 
 <script>
function searchDk(){
	var input = $('#dkSch').val();
	console.log(input);
	var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	$.ajax({
		url: context +'/event/drinkSearch?input='+input,
		type: "get",
		success : function(data){
			console.log(data);
			
			$('#option').empty();
			$('#option').append('<select id="option" name="productCode"><option value="">선택</option>');
			
			var dataLog;
			for(var i in data){
				if(data[i].length>0){
					dataLog = data[i];
				}
			}	
			for(var j in dataLog){
				console.log(dataLog[j].DK_NAME);
				$('#option').append('<option value="'+dataLog[j].DK_CODE+'">'+dataLog[j].DK_NAME+'</option></select>');
			} 	
		}
	});
}


/* 	<tr>
						<th>술 선택</th>
						<td colspan="2">
							<select id="option" name="productCode">
								<option value="option">선택</option>
							</select></td>
					</tr>  */
</script>
<script>
$(function() {
	// 전송버튼 클릭이벤트 
	// 에디터의 처리가 필요하므로 클릭 이벤트가 필요.
    $('#addBtn').click(function(){
    	
    	/*
    	 checkFormjquery() form 유효성 검사 JS 
    	 input 등의 class에 required 가 붙은 항목을 검사 후 입력 값이 없으면 msg의 값을 바탕으로
    	 팝업을 보여주고 멈춤.
    	*/
    	//if (checkFormjquery()) {  // 모든 입력 항목 처리에 문제 없을때 주석 해제하기
    
            // 해당 입력 폼의 id나 name 등으로 select 한 후에 submit() 실행
            $content = CKEDITOR.instances.ckeditor.getData();
            $('#frm').submit();
    		
    	//}   						// 모든 입력 항목 처리에 문제 없을때 주석 해제하기
    });
});

</script>
</html>
