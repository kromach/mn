<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/resources/js/formCheck.js"></script>

	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			
			<div class="grid-item grid-item--width6">
				<div class="mem-find margin-top20">
					<h3 class="pad-y10">KAKAO 추가 정보 입력</h3>
					<div class="searchPanel">
					<form method="post" name="inputForm" action="/member/signUpKakaoPro" enctype="multipart/form-data" accept-charset="utf-8">
						<div class="ssec pad-top15">
							<p class="s_label">아이디</p>
							<p class="s_label">${kakaoMember.id}</p>
							<input type="hidden" name="id" value="${kakaoMember.id}">
						</div>
						<div class="ssec pad-top15">
							<p class="s_label">이름</p>
							<p class="s_label">${kakaoMember.name}</p>
							<input type="hidden" name="name" value="${kakaoMember.name}">
						</div>
						<div class="ssec pad-top15">
							<p class="s_label fl">비밀번호</p>
							<input type="password" name="pw" msg="비밀번호를" class="input required" style="width:273px" id="pw">
						</div>
						<div class="ssec pad-top15">
							<p class="s_label fl">비밀번호 확인</p>
							<input type="password" name="pwCh" msg="비밀번호 확인을" class="input required" style="width:273px" id="pwCh">
						</div>
						<div class="ssec pad-top15">
							<p class="s_label fl">닉네임</p>
							<input type="text" name="nickName" msg="닉네임을" class="input required" value="${kakaoMember.nickName}" id="nickName" style="width:273px">
							<div class="loginLabel" id="nickName_label"></div>
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
							<p class="s_label">주소 (Optional) <button type="button" class="btn btn-sm btn-dark margin-bottom5" id="find_address">주소찾기</button></p>
							<input type="text" name="address" class="input margin-bottom5" style="width:273px" id="address">
							<input type="text" name="address" class="input" style="width:273px">
						</div>		
						<div class="ssec pad-top15">
							<input type="button" class="searchBtn btn btn-lg btn-blue" onclick="insert()" value="회원가입">
						</div>
					</form>
					</div>
				</div>
			</div>
			<%-- 
			<div class="grid-item grid-item--width6">
				<h1 align="center">Kakao 추가정보 입력</h1>
				<form method="post" action="/member/signUpKakaoPro"
						enctype="multipart/form-data" name="inputForm" accept-charset="utf-8">
				<div class="loginWrapper">
					<div class="loginLabel">아이디</div>
					<input class="loginInput" type="text" name="id" value="${kakaoMember.id }" readonly="readonly" >
					<div class="loginLabel">
					비밀번호
					</div>
					<input class="loginInput required" type="password" name="pw" msg="비밀번호를">
					<div class="loginLabel">
					비밀번호 확인
					</div>
					<input class="loginInput required" type="password" name="pwCh" msg="비밀번호 확인란을"> 
					<div class="loginLabel">
					이름
					</div>
					<input class="loginInput" type="text" name="name" value="${kakaoMember.name }" readonly="readonly">
					<div class="loginLabel">
					닉네임
					</div>
					<input	class="loginInput required" type="text" name="nickName" id="nickName" value="${kakaoMember.nickName}" msg="닉네임을" >
					<div class="loginLabel" id="nickName_label"></div>
					<div class="loginLabel">
					생년월일
					</div>
					<div class="birthWrapper">
					<input class="birthfirstInput required" type="text" name="birth" msg="생년월일을"
						size="6" maxlength="6">
					-<input class="birthsecondInput required" type="text" name="birth" msg="생년월일을"
						size="1" maxlength="1">
					</div>
						<div class="loginLabel">
					전화번호
					</div>
					<div class = "birthWrapper">
					<input class="telInput required"
					type="text" name="tel" maxlength="3" msg="전화번호를">-<input class="telInput required"
					type="text" name="tel" maxlength="4" msg="전화번호를">-<input class="telInput required"
					type="text" name="tel" maxlength="4" msg="전화번호를">
					</div>
					<div class="loginLabel">
					주소
					</div>
					<input type="text" name="address" class="loginInput" id="address">
					<div class="loginLabel">
					상세 주소
					</div>
					<input type="text" name="address" class="loginInput"> 
					<button type="button" class="btn btn-sm btn-dark" id="find_address">주소찾기</button>
					<br><br>
					<button type="button" class="btn btn-sm btn-grey" onclick="insert()">추가입력</button>
					<button type="reset" class="btn btn-sm btn-grey">재입력</button>
					<button type="button" class="btn btn-sm btn-grey">취소</button>
				</div>
				</form>
			</div>
			 --%>
		</div>
	</div>
	<script type="text/javascript">
	function insert(){
		if(!$('#idCheck').text() == ''){
			alert("중복되는 아이디는 사용할 수 없습니다");
		}else if(!$('#nickCheck').text() == ''){
			alert("중복되는 닉네임은 중복될수 없습니다");
		}else if($('#pw').val()!=$('#pwCh').val()){
			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
		}else if (checkFormjquery()) {
			$("form[name='inputForm']").submit();
		} 
	}
	</script>
	<script src="/resources/js/memberForm.js"></script>
	<script src="/resources/js/imageLoad.js"></script>