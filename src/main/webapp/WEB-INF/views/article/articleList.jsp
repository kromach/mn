<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.search{
    width: 250px;
    margin-bottom: 5px;
    font-size: 14px;
    position: relative;
    border-radius: 4px;
    height: 22px;
}
.selectOption{
	height: 25px;
	font-size: 12px;
}
</style>
<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<form action="/article/articleSearch" name="articleSearch" id="articleSearch">
					<select id="selectOption" name="selectOption" class="selectOption">
						<option value="writer">작성자</option>
						<option value="content">내용</option>
					</select>
					검색어 : <input type="text" name="search" class="search"/>
					<input type="submit" value="검색" class="btn btn-sm btn-blue" />
					<c:if test="${not empty sessionScope.memId }">
					<input type="button" value="글쓰기" class="btn btn-sm btn-dark" onclick="window.location.href='/article/writeForm'"/>
					</c:if>
				</form>
			</div>
			<div class="grid-item"><img src="/resources/img/main/2.jpg" /></div>
			<div class="grid-item"><img src="/resources/img/main/5.jpg" /></div>
			<div class="grid-item"><img src="/resources/img/main/2.jpg" /></div>
			<div class="grid-item"><img src="/resources/img/main/7.jpg" /></div>
			<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
			<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
			<div class="grid-item"><img src="/resources/img/main/8.jpg" /></div>
			<div class="grid-item"><img src="/resources/img/main/3.jpg" /></div>
	</div>
</div>
<script src="/resources/js/imageLoad.js"></script>
</html>
