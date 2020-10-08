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
    	// if (checkFormjquery()) {  // 모든 입력 항목 처리에 문제 없을때 주석 해제하기

    		//id가 smarteditor인 textarea에 에디터에서 대입
            editor_object.getById[editerId].exec("UPDATE_CONTENTS_FIELD", []);
    
    		// 이부분에 에디터 validation 검증
            //var el = document.createElement('html');
            //el.innerHTML = editor_object.getById["smarteditor"].elPlaceHolder.value;
            
            //폼 submit. 
            // 해당 입력 폼의 id나 name 등으로 select 한 후에 submit()
            $("#frm").submit();
    	//}   						// 모든 입력 항목 처리에 문제 없을때 주석 해제하기
    });
});