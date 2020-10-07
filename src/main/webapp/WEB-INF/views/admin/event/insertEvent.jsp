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
  <!-- 에디터 -->
<script src="https://uicdn.toast.com/tui-editor/latest/tui-editor-Editor-full.js"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/tui-editor/latest/tui-editor.css"></link>
<link rel="stylesheet" href="https://uicdn.toast.com/tui-editor/latest/tui-editor-contents.css"></link>

<script>
$( function() {
    var dateFormat = "mm/dd/yy",
      from = $( "#from" )
        .datepicker({
          defaultDate: "+1w",
          changeMonth: true,
          numberOfMonths: 3
        })
        .on( "change", function() {
          to.datepicker( "option", "minDate", getDate( this ) );
        }),
      to = $( "#to" ).datepicker({
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






<!-- Scripts -->

<!-- 상단에 넣기 -->

<script>
	document.addEventListener('DOMContentLoaded',()=>{
		var editor = new tui.Editor({
			el : document.querySelector('#editor'),
			initialEditType : 'wysiwyg',
			previewStyle : 'vertical',
			height : '500px'
		});	
	},false);
</script>

</head>
<body>

<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		
		<form action="/admin/event/insertEventPro" method="post">
			<table class="tableCss table">
				<tr>
					<th>제목</th>
					<td colspan="2"><input type="text" name="eventName" size="150"/></td>
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
					<td colspan="3">
							<div class="grid-item grid-item--width6" id="editor"></div>
					</td>
				</tr>
			</table>
			<div>
				<input type="submit" class="btn-md" value="등록" />
				<input type="button" class="btn-md" value="취소" onclick="window.location='/admin/event/eventList'"/>
			</div>
		</form>

	</div>
</div>


</body>


</html>