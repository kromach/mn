<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!-- 상단에 넣기 -->
<link rel="stylesheet" href="https://uicdn.toast.com/tui-editor/latest/tui-editor.css"></link>
<link rel="stylesheet" href="https://uicdn.toast.com/tui-editor/latest/tui-editor-contents.css"></link>
<!-- Scripts -->
<script src="https://uicdn.toast.com/tui-editor/latest/tui-editor-Editor-full.js"></script>
<!-- 상단에 넣기 -->
<script>
document.addEventListener('DOMContentLoaded',()=>{
	window.editor = new tui.Editor({
		el : document.querySelector('#editor'),
		initialEditType : 'wysiwyg',
		previewStyle : 'vertical',
		height : '500px'
	});	
},false);
</script>
<script>
function data(){
	
	console.log(data);
}
function getData(){
	var form = document.createElement('form');
	form.setAttribute('method', 'post');
	form.setAttribute('action', '/editorData');
	document.charset = "utf-8";
	var hiddenField = document.createElement('input');
	hiddenField.setAttribute('type', 'hidden');
	
	var data = editor.getHtml().split('<img');
	var len = data.size();
	hiddenField.setAttribute('name', 'data');
	hiddenField.setAttribute('value', editor.getHtml());
	
	form.appendChild(hiddenField);
	document.body.appendChild(form);
	form.submit();
}
</script>
<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width6" id="editor"></div>
		<div class="grid-item grid-item--width6">
			<button class="btn btn-sm btn-dark" id="submit" onclick='data()'>등록</button>
		</div>
	</div>	
</div>