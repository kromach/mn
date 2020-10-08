/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';


    config.toolbar = 'MyToolbar';

    config.toolbar_MyToolbar =
    [
		{ name: 'styles', items: [ 'Font', 'FontSize' ] },
		{ name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike', '-', 'RemoveFormat' ] },
		{ name: 'colors', items: [ 'TextColor', 'BGColor' ] },
		{ name: 'clipboard', items: [ 'Cut', 'Copy', 'Paste', 'PasteText', '-', 'Undo', 'Redo' ] },
		'/',
		{ name: 'editing', items: [ 'Find', 'Replace' ] },
		{ name: 'paragraph', items: [ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'] },
		{ name: 'links', items: [ 'Link', 'Unlink'] },
		{ name: 'insert', items: [ 'Image', 'Table', 'HorizontalRule', 'SpecialChar'] },
		{ name: 'document', items: [ 'Source'] }
    ];
	
	config.toolbarGroups = [
		{ name: 'styles', groups: [ 'styles' ] },
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'colors', groups: [ 'colors' ] },
		{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
		{ name: 'forms', groups: [ 'forms' ] },
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
		{ name: 'links', groups: [ 'links' ] },
		{ name: 'insert', groups: [ 'insert' ] },
//		'/',
		{ name: 'tools', groups: [ 'tools' ] },
		{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'others', groups: [ 'others' ] },
		{ name: 'about', groups: [ 'about' ] }
	];


	//안쓰는 버튼 제거
	config.removeButtons = 'Save,NewPage,Print,Preview,Templates,PasteFromWord,SelectAll,Scayt,Form,Checkbox,Radio,TextField,Textarea,Button,Select,ImageButton,HiddenField,Subscript,Superscript,NumberedList,Outdent,Blockquote,Indent,BulletedList,CreateDiv,Language,BidiLtr,BidiRtl,Anchor,Flash,Iframe,PageBreak,Styles,Format,Maximize,ShowBlocks,About,Smiley';

	//기본 폰트
	config.font_defaultLabel = '나눔고딕';	
	//폰트 리스트
	config.font_names='나눔고딕/Nanum Gothic;굴림/Gulim;돋움/Dotum;바탕/Batang;궁서/Gungsuh;Arial/Arial;Tahoma/Tahoma;Verdana/Verdana';

	//기본 폰트 사이즈
	config.fontSize_defaultLabel = '12px';

	//이미지 미리보기 내 필요 없는 텍스트 제거. 
	config.image_previewText = '';

	config.height = 250;

	/*
	//입력창 사이즈 자동조절 추가
	config.extraPlugins = 'autogrow';
	config.autoGrow_minHeight = 250;
	config.autoGrow_maxHeight = 600;
	config.autoGrow_bottomSpace = 50; 
	*/
	//필요 없는 플러그인 미사용
	config.removePlugins = 'elementspath, resize'; // 입력창 하단에 태그 선택 안나오게

};
