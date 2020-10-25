<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="/resources/js/formCheck.js"></script>

	<c:choose>
		<c:when test="${status eq 'user'}">
			<c:set var="formAction" value="/member/modifyUserPro" />
		</c:when>
		<c:otherwise>
			<c:set var="formAction" value="/member/modifySalesPro" />
		</c:otherwise>
	</c:choose>
	
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">

				<div class="mem-find margin-top20">
					<h3 class="pad-y10">회원정보 수정</h3>
					<div class="searchPanel">
					<form method="post" name="inputForm" action="${formAction}" enctype="multipart/form-data" accept-charset="utf-8">
						<div class="ssec pad-top15">
							<p class="s_label">아이디</p>
							<p class="s_label">${memberDTO.id}</p>
							<input type="hidden" name="id" value="${memberDTO.id}">
						</div>
						<div class="ssec pad-top15">
							<p class="s_label">이름</p>
							<p class="s_label">${memberDTO.name}</p>
							<input type="hidden" name="name" value="${memberDTO.name}">
						</div>
						<div class="ssec pad-top15">
							<p class="s_label fl">비밀번호</p>
							<input type="password" name="pw" msg="비밀번호를" class="input required" style="width:273px">
						</div>
						<div class="ssec pad-top15">
							<p class="s_label fl">비밀번호 확인</p>
							<input type="password" name="pwCh" msg="비밀번호 확인을" class="input required" style="width:273px">
						</div>
						<div class="ssec pad-top15">
							<p class="s_label fl">닉네임</p>
							<input type="text" name="nickName" msg="닉네임을" class="input required" value="${memberDTO.nickName}" id="nickName" style="width:273px">
							<div class="loginLabel" id="nickName_label"></div>
						</div>
						<div class="ssec pad-top15">
							<p class="s_label fl">생년월일 (YYMMDD + 주민등록 뒷번호 1자리)</p>
							
							<c:set var="len" value = "${fn:length(memberDTO.birth)}"/>
							<c:set var="birth" value="${memberDTO.birth}"/> 
							<c:set var="birthfrist" value="${fn:substring(birth,0,len-1)}"/>
							<c:set var="KakaoTest" value="${fn:substring(birth,0,len-5)}"/>
							<c:set var="birthsecond" value="${fn:substring(birth,len-1,len)}"/>
							<c:if test="${KakaoTest != '20'}">
								<c:set var="birthReadOnly" value="readonly"/> 
							</c:if>

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
						
						<c:set var="address" value="${fn:split(memberDTO.address,',')}"/> 
						<c:set var="addressLen" value = "${fn:length(address)}"/>
						<c:set var="addressfrist" value="${address[0]}"/>
						<c:set var="addresssecond" value="${address[addressLen-1]}"/>
						<c:set var="addrName" value = ""/>
						<c:if test="${status eq 'salse'}">
							<c:set var="addrName" value = "사업장 " />
						</c:if>
							
						<div class="ssec pad-top15">
							<p class="s_label fl">${addrName}주소 <button type="button" class="btn btn-sm btn-dark margin-bottom5" id="find_address">주소찾기</button></p>
							<input type="text" name="address" class="input margin-bottom5" value="${addressfrist}" style="width:273px">
							<input type="text" name="address" class="input" value="${addresssecond}" style="width:273px">
						</div>		
						
						<c:if test="${status eq 'salse'}">
						<div class="ssec pad-top15">
							<p class="s_label fl">사업자 번호</p>
							<input type="file" name="licenseNum" class="input required" value="${memberDTO.licenseNum}" msg="사업자 번호를" style="width:273px">
						</div>		
						<div class="ssec pad-top15">
							<p class="s_label fl">사업자 등록증</p>
							<input type="file" name="file" class="input" style="width:273px">
						</div>		
						</c:if>
						
						<div class="ssec pad-top15">
							<input type="button" class="searchBtn btn btn-lg btn-blue" onclick="insert()" value="회원정보 수정">
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/resources/js/memberForm.js"></script>
	<script src="/resources/js/imageLoad.js"></script>
	<script type="text/javascript">
		function insert(){
			console.log($("form[name='inputForm']"));
			if (checkFormjquery()) {
				console.log("inInsert");
				$("form[name='inputForm']").submit();
			}
		}
	</script>