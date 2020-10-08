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
<!-- 날짜 -->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
 <!-- 유효성검사 js -->
<script src="/resources/js/formCheck.js"></script> 
<!-- 에디터 js -->
<script type="text/javascript" src="<c:url value="/resources/smarteditor/js/HuskyEZCreator.js"/>" charset="utf-8"></script>
  
 
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
		
			<form action="/admin/event/insertEventPro" method="post" id="frm">
				<table class="tableCss table">
					<tr>
						<th>제목</th>
						<td colspan="2"><input type="text" name="eventName" id="tilte" class="required" msg="제목을" placeholder="제목" size="130"/></td>
					</tr>
					<tr>
						<th>술 검색</th>
						<td><input id="dkSch" name="dkSch" /></td>
						<td style=""><button>검색</button></td>
					</tr>
					<tr>
						<th>술 선택</th>
						<td colspan="2">
							<select>
								<option value="">선택</option>
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
						<td colspan="3" style="margin:0; padding:0">
							<textarea name="tarea" id="smart-editor" type="textarea" class="required" msg="내용을"  rows="10" cols="100" style="width:100%; height:412px;"></textarea>
						</td>
					</tr>
				</table>
				<div>
					<input type="button" id="addBtn" class="btn btn-md btn-blue" value="등록" />
					<input type="button" class="btn-md" value="취소" onclick="window.location='/admin/event/eventList'"/>
				</div>
			</form>
		</div>
	</div>
</div>
</body>

<script>
$(function() {
    var editor_object = [];
    var editerId = "smart-editor"; //에디터를 붙일 textarea의 id 값을 설정

    nhn.husky.EZCreator.createInIFrame({
        oAppRef: editor_object,
        elPlaceHolder: editerId,
        sSkinURI: "/resources/smarteditor/SmartEditor2Skin.html",
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,             
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,     
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true,
            fOnBeforeUnload : function(){
                
            }
        }
    });
    
	// 전송버튼 클릭이벤트 
	// 에디터의 처리가 필요하므로 클릭 이벤트가 필요.
    $("#addBtn").click(function(){
    	
    	// checkFormjquery() form 유효성 검사 JS 
    	// input 등의 class에 required 가 붙은 항목을 검사 후 입력 값이 없으면 msg의 값을 바탕으로
    	// 팝업을 보여주고 멈춤.
    	 if (checkFormjquery()) {  // 모든 입력 항목 처리에 문제 없을때 주석 해제하기

    		//id가 smarteditor인 textarea에 에디터에서 대입
            editor_object.getById[editerId].exec("UPDATE_CONTENTS_FIELD", []);
    
    		// 이부분에 에디터 validation 검증
            var el = document.createElement('html');
            el.innerHTML = editor_object.getById["smarteditor"].elPlaceHolder.value;
            
            //폼 submit. 
            // 해당 입력 폼의 id나 name 등으로 select 한 후에 submit()
            $("#frm").submit();
    	}   						// 모든 입력 항목 처리에 문제 없을때 주석 해제하기
    });
});
</script>


</html>