<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width6">
			<h1 align="center">회원가입</h1>
			<form method="post" action="signupPro.jsp" enctype="multipart/form-data" name ="inputForm" onsubmit="return check()" accept-charset="utf-8">
			<table>
				<tr>
					<td class="t">아이디*</td>
					<td><input type="text" name="id" required="required"/>
						<input type = "hidden" name = "idConfirm" value="false" />
						<input type = "hidden" name = "nameConfirm" value="false" />
					</td>
				</tr>
				<!--중복 id 체크버튼-->
				<tr>
					<td class="t">아이디 중복체크</td>
					<td><button class="grayButton" style="width: 80px; height: 30px; text-align: center" type="button" onclick="confirmId(this.form)">중복확인</button></td>
				</tr>
				<tr>
					<td class="t">비밀번호*</td>
					<td><input type="password" name="pw" required="required"/></td>
				</tr>
				<tr>
					<td class="t">비밀번호 확인*</td>
					<td><input type="password" name="pwCh" required="required"/></td>
				</tr>
				<tr>
					<td class="t">활동명*</td>
					<td><input type="text" name="name"  required="required"/></td>
				</tr>
				<tr>
					<td class="t">활동명 중복체크</td>
					<td><button class="grayButton" style="width: 80px; height: 30px; text-align: center" type="button" onclick="confirmName(this.form)">중복확인</button></td>
				</tr>
				<tr>
					<td class="t">주민번호*</td> 
					<td>
						<input type="text" name="id_number1" id="idTest1" maxlength="6" size="6" required="required"/>&nbsp;&nbsp;&nbsp;- 
						<input type="text" name="id_number2" id="idTest2"  maxlength="1" size="1" required="required"/>******
					</td>
				</tr>
				<tr>
					<td class="t">채식주의 타입</td>
					<td> 
						<img src="../recipe/imgs/question.png" width="20px" height="20px" onclick="question()" />
						<select name="vegi_type" required="required">
								<option value="none">Non-vegetarian</option>
								<option value="vegan">vegan</option>
								<option value="lacto">Lacto vegetarian</option>
								<option value="ovo">Ovo vegetarian</option>
								<option value="lacto ovo">Lacto-ovo vegetarian</option>
								<option value="pesco">Pesco-vegetarian</option>
								<option value="pollo">Pollo-vegetarian</option>
								<option value="flexitarian">Flexitarian</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="t">프로필 사진</td>
					<td><input type="file" name="profile_img" /></td>
				</tr>		
				<tr>
					<td colspan="2" align="center" style="padding-top: 30px">
						<button class="grayButton" type="submit" style="width: 50px; height: 30px; text-align: center">가입</button>
						<button class="grayButton" type="reset" style="width: 50px; height: 30px; text-align: center" name="reset">재입력</button>
						<button class="grayButton" type="button" style="width: 50px; height: 30px; text-align: center" onclick="window.location='../main.jsp'">취소</button>
					</td> 
				</tr>
			</table> 
		</div>
	</div>
</div>
<div class="chat">
	<a href="/chat"><img src="/resources/img/main/chat.png" ></a>
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