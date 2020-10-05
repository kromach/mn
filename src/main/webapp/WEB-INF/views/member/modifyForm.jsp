<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<h1 align="center">정보수정${status }</h1>
			</div>
			<div class="grid-item grid-item--width6">
				<c:if test="${status eq 'salse' }">
					<form method="post" action="/member/modifySalesPro"
						enctype="multipart/form-data" name="inputForm"
						onsubmit="return check()" accept-charset="utf-8">
				</c:if>
				<c:if test="${status eq 'user' }">
					<form method="post" action="/member/modifyUserPro"
						enctype="multipart/form-data" name="inputForm"
						onsubmit="return check()" accept-charset="utf-8">
				</c:if>
				<div class="loginWrapper">
					<div class="loginLabel">아이디</div>
					<input class="loginInput" type="text" name="id" value="${memberDTO.id}" readonly="readonly" style="cursor: default;">
					<div class="loginLabel">아이디중복체크</div>
					<button type="button">중복확인</button>
					<div class="loginLabel">
					비밀번호
					</div>
					<input class="loginInput" type="text" name="pw" value="${memberDTO.pw}" >
					<div class="loginLabel">
					비밀번호 확인
					</div>
					<input class="loginInput" type="password" name="pwCh"> 
					<div class="loginLabel">
					이름
					</div>
					<input class="loginInput" type="text" name="name" value="${memberDTO.name}" readonly="readonly">
					<div class="loginLabel">
					닉네임
					</div>
					<input	class="loginInput" type="text" name="nickName" value="${memberDTO.nickName}">
					닉네임 중복체크
					<button type="button">중복확인</button>
					<div class="loginLabel"> 
					생년월일
					</div>
					<div class="birthWrapper">
					<c:set var="len" value = "${fn:length(memberDTO.birth)}"/>
					<c:set var="birth" value="${memberDTO.birth}"/> 
					<c:set var="birthfrist" value="${fn:substring(birth,0,len-1)}"/>
					<c:set var="birthsecond" value="${fn:substring(birth,len-1,len)}"/>
					<input class="birthfirstInput" type="text" name="birth" value="${birthfrist}"
						size="7"> 
					-<input class="birthsecondInput" type="text" name="birth" value="${birthsecond}"
						size="1">
					</div>
					<div class="loginLabel">
					전화번호
					</div>
					<div class = "birthWrapper">
					<input class="telInput"
					type="text" name="tel" size="3">-<input class="telInput"
					type="text" name="tel" size="3">-<input class="telInput"
					type="text" name="tel" size="3">
					</div>
					<c:if test="${status eq 'user' }">
					<div class="loginLabel">
					주소
					</div>
					<input type="text" name="address" class="loginInput" value="${memberDTO.address }">
					<button type="button">주소찾기</button>
					</c:if>
					<c:if test="${status eq 'salse' }">
						<div class="loginLabel">
						사업장 주소
						</div>
						<input type="text" name="address" class="loginInput" value="${memberDTO.address }">
						<div class="loginLabel">
						사업자 등록증 
						</div>
						<input type="file" name="file" class="loginInput">
						<div class="loginLabel">
						사업자 번호
						</div>
						<input type="text" name="licenseNum" class="loginInput" value="${memberDTO.licenseNum}">
					</c:if>
					<br><br>
					<button type="submit">가입</button>
					<button type="reset">재입력</button>
					<button type="button">취소</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	</div>
	</div>
	<script src="/resources/js/imageLoad.js"></script>
</body>
</html>