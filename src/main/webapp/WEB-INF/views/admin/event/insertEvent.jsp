<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert Event</title>
</head>
<body>

<!-- 디자인 변경,   술 선택 셀렉트문 가져오기, 술 선택 아래 --- 변경
	에디터 변경
 -->

<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		
		<form action="/admin/event/insertEventPro" method="post">
			<table class="tableCss table">
				<tr>
					<th>제목</th>
					<td colspan="2"><input type="text" name="eventName" size="150"/></td>
				</tr>
				<tr>
					<th>술 검색</th>
					<td><input id="dkSch" name="dkSch" /></td>
					<td style=""><button>검색</button></td>
				</tr>
				<tr>
					<th>술 선택</th>
					<td colspan="2">
						<select>
							<option value="">선택</option>
						</select></td>
				</tr>
				<tr>
					<td>
						<hr width="620px" color="#808080"/>
					</td>
				</tr>
				<tr>
					<td colspan="3"><textarea name="content" style="width:620px; height:300px"></textarea></td>
				</tr>
			</table>
			<div>
				<input type="submit" class="btn-md" value="등록" />
				<input type="submit" class="btn-md" value="취소" />
				
			</div>
		</form>
		
		
	</div>
</div>

</body>
</html>