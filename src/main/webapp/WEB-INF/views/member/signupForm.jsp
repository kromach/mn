<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!DOCTYPE html>
<html>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<h1 align="center">회원가입</h1>
			</div>
			<div class="grid-item grid-item--width6">
				<c:if test="${status eq 'salse' }">
					<form method="post" action="/member/signupSalesPro"
						enctype="multipart/form-data" name="inputForm"
						onsubmit="return check()" accept-charset="utf-8">
				</c:if>
				<c:if test="${status eq 'user' }">
					<form method="post" action="/member/signupUserPro"
						enctype="multipart/form-data" name="inputForm"
						onsubmit="return check()" accept-charset="utf-8">
				</c:if>
				<div class="loginWrapper">
					<div class="loginLabel" >아이디</div>
					<input class="loginInput" type="text" name="id" required="required" id="id">
					<div class="loginLabel" id="id_label"></div>
					<div class="loginLabel">
					비밀번호
					</div>
					<input class="loginInput" type="text" name="pw" required="required">
					<div class="loginLabel">
					비밀번호 확인
					</div>
					<input class="loginInput" type="password" name="pwCh"> 
					<div class="loginLabel">
					이름
					</div>
					<input class="loginInput" type="text" name="name" required="required">
					<div class="loginLabel">
					닉네임
					</div>
					<input	class="loginInput" type="text" name="nickName" required="required" id="nickName">
					<div class="loginLabel" id="nickName_label"></div>
					<div class="loginLabel">
					생년월일
					</div>
					<div class="birthWrapper">
					<input class="birthfirstInput" type="text" name="birth" required="required"
						size="7">
					-<input class="birthsecondInput" type="text" name="birth" required="required"
						size="1">
					</div>
					<div class="loginLabel">
					전화번호
					</div>
					<div class = "birthWrapper">
					<input class="telInput"
					type="text" name="tel" size="3" required="required">-<input class="telInput"
					type="text" name="tel" size="3" required="required">-<input class="telInput"
					type="text" name="tel" size="3" required="required">
					</div>
					<c:if test="${status eq 'user' }">
					<div class="loginLabel">
					주소
					</div>
					<input type="text" name="address" class="loginInput" id="address">
					<div class="loginLabel">
					상세 주소
					</div>
					<input type="text" name="address" class="loginInput"> 
					<button type="button" class="btn btn-sm btn-dark" id="find_address">주소찾기</button>
					</c:if>
					<c:if test="${status eq 'salse' }">
						<div class="loginLabel">
						사업장 주소
						</div>
						<input type="text" name="address" class="loginInput" id="address">
						<div class="loginLabel">
						상세 주소
						</div>
						<input type="text" name="address" class="loginInput"> 
						<button type="button" class="btn btn-sm btn-dark" id="find_address">주소찾기</button>
						<div class="loginLabel">
						사업자 등록증
						</div>
						<input type="file" name="file" class="loginInput">
						<div class="loginLabel">
						사업자 번호
						</div>
						<input type="text" name="licenseNum" class="loginInput" required="required">
					</c:if>
					<br><br>
					<button type="submit" class="btn btn-sm btn-grey">가입</button>
					<button type="reset" class="btn btn-sm btn-grey">재입력</button>
					<button type="button" class="btn btn-sm btn-grey" onclick="window.location.href='/'">취소</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#find_address').click(function(event) {
			 new daum.Postcode({
			        oncomplete: function(data) {
			        	{
		                    // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
		                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                    var roadAddr = data.roadAddress; // 도로명 주소 변수
		                    var extraRoadAddr = ''; // 참고 항목 변수

		                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
		                        extraRoadAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가한다.
		                    if (data.buildingName !== '' && data.apartment === 'Y') {
		                        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                    if (extraRoadAddr !== '') {
		                        extraRoadAddr = ' (' + extraRoadAddr + ')';
		                    }
		                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                    document.getElementById('address').value = data.jibunAddress;
		                }
			        }
			    }).open();
		})
		
		
		$('#id').blur(function() {
			var id = $('#id').val();
			$.ajax({
				url:  '${pageContext.request.contextPath}/member/overlapCheck?id='+id,
				type: "get",
				success : function(data){
					if(data){
						$("#id_label").html('<div  style="color: red;font-size: 11px; margin-bottom: 5px;">이미 사용중이거나 탈퇴한 아이디 입니다.</div>');
					}else{
						$("#id_label").empty();
					}
				}
			});
		});
		$('#nickName').blur(function() {
			var nickName = $('#nickName').val();
			$.ajax({
				url:  '${pageContext.request.contextPath}/member/overlapCheck?nickName='+nickName,
				type: "get",
				success : function(data){
					if(data){
						$("#nickName_label").html('<div  style="color: red;font-size: 11px; margin-bottom: 5px;">이미 사용중이거나 탈퇴한 아이디 입니다.</div>');
					}else{
						$("#nickName_label").empty();
					}
				}
			});
		});
	});
	</script>
	<script src="/resources/js/imageLoad.js"></script>
</body>
</html>