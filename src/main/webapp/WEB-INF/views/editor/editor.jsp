<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script type="text/javascript" src="<c:url value="/resources/smarteditor/js/HuskyEZCreator.js"/>" charset="utf-8"></script>
<!-- <script type="text/javascript" charset="utf-8" src="/resources/js/writeForm.js"></script>
 -->

<form action="write" method="post" id="frm">
	<table style="width:70%">
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" id="title" placeholder="제목"></td>
		</tr>
		<tr>
			<td style="margin:0; padding:0;" colspan="2">
				<textarea name="tarea" id="smarteditor" rows="10" cols="100" style="width:100%; height:412px;"></textarea>
			</td>
		</tr>
	</table>
	<input id="addBtn" type="submit" value="전송">
</form>

<script>
$(function() {
    var editor_object = [];
    var ctx = getContextPath();
    
    var editerId = "smarteditor"; //에디터를 붙일 textarea의 id 값

    nhn.husky.EZCreator.createInIFrame({
        oAppRef: editor_object,
        elPlaceHolder: editerId,
        sSkinURI: ctx + "/resources/smarteditor/SmartEditor2Skin.html",
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
    
  //전송버튼 클릭이벤트
    $("#addBtn").click(function(){
        //id가 smarteditor인 textarea에 에디터에서 대입
        editor_object.getById[editerId].exec("UPDATE_CONTENTS_FIELD", []);
         
        // 이부분에 에디터 validation 검증
        var el = document.createElement('html');
        el.innerHTML = editor_object.getById["smarteditor"].elPlaceHolder.value;
        //폼 submit
        $("#frm").submit();
    });
    
    function getContextPath() {
    	return sessionStorage.getItem("contextpath");
    }
});
</script>
<script type="text/javascript" charset="utf-8">
	sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
</script>
