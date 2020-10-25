<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="/resources/js/formCheck.js"></script>

	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<div class="mem-find margin-top20">
					<h3 class="pad-y10">아이디 찾기</h3>
					<div class="searchPanel">
					<form method="post" action="/member/findIdPro" name="inputForm">
						<div class="ssec pad-top15">
							<p class="s_label fl">이름</p>
							<input type="text" name="name" class="input required" msg="이름을" style="width:273px">
						</div>
						<div class="ssec pad-top15">
							<p class="s_label fl">생년월일 (YYMMDD + 주민등록 뒷번호 1자리)</p>
							<span class="input-line">
								<input type="text" name="birth" class="input required" onkeydown="return onlyNumber(event)" chknum msg="생년월일 6자리를" style="width:160px" maxlength="6">
								-
								<input type="text" name="birth" class="input required" onkeydown="return onlyNumber(event)" chknum msg="주민등록 뒷번호 1자리를" style="width:52px" maxlength="1">
							</span>
						</div>
						<div class="ssec pad-top15">
							<p class="s_label fl">전화번호</p>
							<span class="input-line">
								<input type="text" name="tel" class="input required" onkeydown="return onlyNumber(event)" chknum msg="전화번호를" style="width:50px" maxlength="3">
								-
								<input type="text" name="tel" class="input required" onkeydown="return onlyNumber(event)" chknum msg="전화번호를" style="width:51px" maxlength="4">
								-
								<input type="text" name="tel" class="input required" onkeydown="return onlyNumber(event)" chknum msg="전화번호를" style="width:51px" maxlength="4">
							</span>
						</div>
						<div class="ssec pad-top15">
							<input type="button" class="searchBtn btn btn-lg btn-blue" onclick="insert()" value="아이디 찾기">
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function insert(){
		if (checkFormjquery()) {
			$("form[name='inputForm']").submit();
		}
	}
	
	function onlyNumber(event){  // 141230-최재훈 기존 온리넘버의 기능한계로 신규 넘버 체크 추가
		event = event || window.event;
		var keyID = (event.which) ? event.which : event.keyCode;
		if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 || keyID == 35 || keyID == 36 || keyID == 16 || keyID == 9 ) 
			/* 48~57:일반 숫자키 코드, 96~105:숫자키패드 숫자키 코드,  8=Backspace, 46=Delete, 37=왼쪽 화살표, 39=오른쪽 화살표, 35=home, 36=end, 16=shift, 9 = Tab*/
			return;
		else
			return false;
	}

	</script>
	<script src="/resources/js/imageLoad.js"></script>