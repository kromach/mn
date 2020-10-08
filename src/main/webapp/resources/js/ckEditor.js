// ckeditor 설정
CKEDITOR.on('dialogDefinition', function(ev) {

	var dialogName = ev.data.name;
	var dialog = ev.data.definition.dialog;
	var dialogDefinition = ev.data.definition;

	if (dialogName == 'image') {
		dialog.on('show', function(obj) {
			this.selectPage('Upload'); //사진 추가 버튼 클릭시 업로드탭으로 시작
		});
		dialogDefinition.removeContents('advanced'); // 자세히탭 제거
		dialogDefinition.removeContents('Link'); // 링크탭 제거
	}
});

//이미지 업로드 url 설정
var ckedit_config = {
	filebrowserUploadUrl : 'ckuploader', // 통신할 컨트롤러 매핑 주소 
	toolbar : ''
}
// ckeditor 설정 종료

