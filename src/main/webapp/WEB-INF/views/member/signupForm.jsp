<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<div class="grid-Wrapper">
	
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width6">
		<h1 align="center">회원가입</h1><br>
			<div style="display: block; text-align: center;">
					<c:if test="${status eq 'salse' }">
						<form
							style="display: inline-block; border: 2px solid black; padding: 5px; align-content: center;"
							method="post" action="/member/signupSalesPro"
							enctype="multipart/form-data" name="inputForm"
							onsubmit="return check()" accept-charset="utf-8">
					</c:if>
					<c:if test="${status eq 'user' }">
						<form
							style="display: inline-block; border: 2px solid black; padding: 5px; align-content: center;"
							method="post" action="/member/signupUserPro"
							enctype="multipart/form-data" name="inputForm"
							onsubmit="return check()" accept-charset="utf-8">
					</c:if>

					<table style=" border-collapse: separate; border-spacing: 0 10px;">
							<tr>
								<td class="t">아이디*</td>
								<td><input type="text" name="id"></td>
							</tr>
							<tr>
								<td class="t">아이디 중복체크</td>
								<td><button class="button"
										style="width: 80px; height: 30px; text-align: center"
										type="button" onclick="confirmId(this.form)">중복확인</button></td>
							</tr>
							<tr>
								<td class="t">이름*</td>
								<td><input type="text" name="name"></td>
							</tr>
							<tr>
								<td class="t">별명*</td>
								<td><input type="text" name="NICKNAME"></td>
							</tr>
							<tr>
								<td class="t">별명 중복체크</td>
								<td><button class="button"
										style="width: 80px; height: 30px; text-align: center"
										type="button" onclick="confirmId(this.form)">중복확인</button></td>
							</tr>
							<tr>
								<td class="t">비밀번호*</td>
								<td><input type="password" name="pw"  /></td>
							</tr>
							<tr>
								<td class="t">비밀번호 확인*</td>
								<td><input type="password" id="pwCh" /></td>
							</tr>
							<tr>
								<td class="t">생년월일*</td>
								<td><input type="text" name="birth" id="idTest1"
									maxlength="6" size="6" />&nbsp;&nbsp;&nbsp;-
									<input type="text" name="birth" id="idTest2" maxlength="1"
									size="1" />******</td>
							</tr>
							<tr>
									<td class="t">전화번호</td>
									<td><input type="text" name="tel"></td>
								</tr>
							<c:if test="${status eq 'user' }">
								<tr>
									<td class="t">주소</td>
									<td><input type="text" name="address">
										<button class="button"
											style="width: 80px; height: 30px; text-align: center"
											type="button" onclick="confirmId(this.form)">주소찾기</button></td>
								</tr>
							</c:if>
							<c:if test="${status eq 'salse' }">
								<tr>
									<td class="t">사업장 주소</td>
									<td><input type="text" name="address"></td>
								</tr>
								<tr>
									<td>사업자 등록증</td>
									<td><input type="file" name="file"></td>
								</tr>
								<tr>
									<td>사업자 번호</td>
									<td><input type="text" name="licenseNum"></td>
								</tr>
							</c:if>
							<tr>
								<td colspan="2" align="center" style="padding-top: 30px">
									<button class="button" type="submit"
										style="width: 50px; height: 30px; text-align: center">가입</button>
									<button class="button" type="reset"
										style="width: 50px; height: 30px; text-align: center"
										>재입력</button>
									<button class="button" type="button"
										style="width: 50px; height: 30px; text-align: center"
										onclick="window.location='../main.jsp'">취소</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
		</div>
	</div>
</div>

<script src="/resources/js/imageLoad.js"></script>
<!-- 데이터 스크롤해서 붙이는 스크립트  -->
<script type="text/javascript">
$(window).scroll(
	function() {
		// A(B+C) : document 높이 (고정)
		console.log($(document).height());
		// B : browser 높이 (최상단 기본값)
		console.log($(window).height());
		// C : 스크롤 위치
		console.log('SCROLL_TOP' + $(window).scrollTop());
		if ($(window).scrollTop() >= $(document).height()
				- $(window).height() - 100) {
			//호출 메서드
		}
	});
</script>
</body>
</html>