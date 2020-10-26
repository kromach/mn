<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/resources/js/formCheck.js"></script>
<!DOCTYPE html>
<html>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<c:if test="${status eq 'sales' }">
					<form method="post" action="/member/signupSalesPro"
						enctype="multipart/form-data" name="inputForm"
						accept-charset="utf-8">
				</c:if>
				<c:if test="${status eq 'user' || status eq 'admin' }">
					<form method="post" action="/member/signupUserPro"
						enctype="multipart/form-data" name="inputForm"
						accept-charset="utf-8">
				</c:if>
				<div class="mem-find margin-top20">
				<h3 class="pad-y10">회원가입</h3>
				<div class="searchPanel">
					<div class="ssec pad-top15">
						<p class="s_label fl">아이디</p>
						<input class="input required" type="text" name="id" id="id" msg="아이디를" style="width:273px">
						<div class="loginLabel" id="id_label"></div>
					<div class="ssec pad-top15">
						<p class="s_label fl">비밀번호</p>
						<input type="password" name="pw" msg="비밀번호를" class="input required" style="width:273px" id="pw">
					</div>
					<div class="ssec pad-top15">
						<p class="s_label fl">비밀번호 확인</p>
						<input type="password" name="pwCh" msg="비밀번호 확인을" class="input required" style="width:273px" id="pwCh">
					</div>
					<div class="ssec pad-top15">
						<p class="s_label fl">이름</p>
						<input class="input required" type="text" name="name" msg="이름을" style="width:273px">
					</div>
					<div class="ssec pad-top15">
						<p class="s_label fl">닉네임</p>
						<input	class="input required" type="text" name="nickName" id="nickName" msg="닉네임을" style="width:273px">
						<div class="loginLabel" id="nickName_label"></div>
					</div>
					<div class="loginLabel" id="nickName_label"></div>
					<!--  -->
					<div class="ssec pad-top15">
						<p class="s_label fl">생년월일 (YYMMDD + 주민등록 뒷번호 1자리)</p>
						<span class="input-line">
							<input type="text" name="birth" class="input required" value="${birthfrist}" ${birthReadOnly} onkeydown="return onlyNumber(event)" chknum msg="생년월일 6자리를" style="width:160px" maxlength="6">
							-
							<input type="text" name="birth" class="input required" value="${birthsecond}" ${birthReadOnly} onkeydown="return onlyNumber(event)" chknum msg="주민등록 뒷번호 1자리를" style="width:52px" maxlength="1">
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
					<c:if test="${status eq 'sales'}">
						<div class="ssec pad-top15">
							<p class="s_label fl">사업자 번호</p>
							<input type="text" name="licenseNum" class="input required" value="${memberDTO.licenseNum}" msg="사업자 번호를" style="width:273px">
						</div>		
						<div class="ssec pad-top15">
							<p class="s_label fl">사업자 등록증</p>
							<input type="file" name="file" class="input" style="width:273px">
						</div>		
					</c:if>
					<c:if test="${status eq 'sales' }">
						<c:set var="addrName" value = "사업장 " />
					</c:if>
					<div class="ssec pad-top15">
						<p class="s_label fl">${addrName}주소 (Optional) <button type="button" class="btn btn-sm btn-dark margin-bottom5" id="find_address">주소찾기</button></p>
						<input type="text" name="address" class="input margin-bottom5" value="${addressfrist}" style="width:273px" id="address">
						<input type="text" name="address" class="input" value="${addresssecond}" style="width:273px">
					</div>	
					<div class="ssec pad-top15">
						<input type="button" class="searchBtn btn btn-lg btn-blue" onclick="insert()" value="회원가입">
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function insert(){
		console.log();
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
</body>
</html>