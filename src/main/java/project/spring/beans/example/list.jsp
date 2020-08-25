<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>  
	<meta charset="UTF-8">
	<title>${count}</title>
	<link href="/Spring/resources/style.css" rel="stylesheet" type="text/css">
</head>
<%
%>
<body>
	<br>
	<h1 align="center">게시판</h1>
	<!--게시글이 없을때  -->
	<c:if test="${count==0}">
	<table>
		<tr>
			<td colspan="2">
				<c:if test="${sessionScope.memId !=null }">
				<button onclick="window.location = '/Spring/board/writeForm.git'">글쓰기</button>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>게시글이 없습니다.</td>
		</tr>
	</table>
	</c:if>
	<c:if test="${count>0}">
	<table>  
		<tr>
			<td colspan="7">
					<c:if test="${sessionScope.memId !=null }">
						<button onclick="window.location = '/Spring/member/main.git'">메인</button>
						<button onclick="window.location = '/Spring/board/writeForm.git'">글쓰기</button>
						<button onclick="window.location = '/Spring/board/list.git?searchSelect=writer&search=${sessionScope.memId}'">나의글</button>
						<button onclick="window.location = '/Spring/board/list.git'">리스트</button>
						<button onclick="window.location = '/Spring/member/logout.git'">로그아웃</button>
						<c:if test="${sessionScope.memId eq 'admin' }">
							<button onclick="window.location = '/Spring/member/listadmin.git'">[관리자/회원리스트]</button>
						</c:if>
					</c:if>
					<c:if test="${sessionScope.memId ==null }">
						<button onclick="window.location='/Spring/member/loginForm.git'">로그인</button>
						<button onclick="window.location='/Spring/member/main.git'">메인</button>
					</c:if>
			</td>
		</tr>
		<tr>	
			<td colspan="7">
				<form action="/Spring/board/list.git" method="get">
					<select name = "searchSelect">
						<option name="writer" value="writer" selected="selected">작성자</option>
						<option name="content" value="content">내용</option>
					</select>
					<input type="text" name="search"/>
					<input type="submit" value="검색"/>
				</form>
			</td>
		</tr>
		<tr>
			<td>NO</td>
		<!-- 	<td>IMG</td> -->
			<td>제목</td>
			<td>작성자</td>
			<td>시간</td>
			<td>조회수</td> 
		</tr> 
			<c:set var="number" value="${pageVO.number}"/>
			<c:forEach items="${articleList }" var="article" >
			<tr>
			<td>
				${number}
				<c:set var="number" value="${number -1 }"/>
			</td> 
			<td align="left">
				<c:set var = "wid" value="0" /> 
				<c:if test="${article.re_level>0}">
					<c:set var = "wid" value="${8*article.re_level}" />										
					<img src = "/Spring/resources/img/tabImg.PNG" width="${wid}"/>
					<img src = "/Spring/resources/img/replyImg.png" width="11"/>
				</c:if>
				<a href="content.git?num=${article.num}&pageNum=${pageNum}">
				
			<%-- <c:if test="${article.secret eq 'on'}">
			[비밀글]
			</c:if> --%>
			${article.subject }
			</a></td>
			<td><a href="mailto:${article.email}">${article.writer}</a></td>
			<td>${article.reg }</td>
			<td>${article.readcount }</td>
			</tr>
		</c:forEach>
		</table>
		</c:if>
		<%--Pager --%>
		<div align="center" class="pageNums">
			<!-- 게시글이 있을때만 보여주기 -->
			<c:if test="${count>0}">
				<!-------------------------------------------------------------------------->
				<c:if test="${pageVO.startPage > pageVO.pageBlock}">
					<a href="/Spring/board/list.git?pageNum=${pageVO.startPage-pageVO.pageBlock}">&lt;</a>
				</c:if>
				<c:forEach var="i" begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1"> 
							<a href="/Spring/board/list.git?pageNum=${i}" class="pageNums">&nbsp;${i}&nbsp;</a>
				</c:forEach>
				<c:if test="${pageVO.endPage < pageVO.pageCount}">
					<a href="/Spring/board/list.git?pageNum=${pageVO.startPage+pageVO.pageBlock}">&gt;</a>
				</c:if>
				<!-------------------------------------------------------------------------->
			</c:if>
		</div>
</body>
</html>