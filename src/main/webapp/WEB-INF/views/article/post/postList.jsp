<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/resources/css/selectric.css">
<script src="/resources/js/jquery.selectric.js"></script>
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
			<div class="grid-item grid-item--width6" style="overflow: visible">
				<form action="/article/post/postSearch" name="articleSearch" id="articleSearch">
					<select id="selectOption" name="selectOption" class="selectOption sel short text-left">
						<option value="NICKNAME">작성자</option>
						<option value="CONTENT">내용</option>
						<option value="BN_TITLE">제목</option>
					</select>
					검색어 : <input type="text" name="search" class="search"/>
					<input type="submit" value="검색" class="btn btn-sm btn-blue" />
				</form>
			</div>
			<!--검색이 아닌경우 -->
			<c:if test="${list.size() == 0 }">
				<c:forEach var="articleDTO" items="${list}">
					<c:if test="${not empty articleDTO.thumbNail}">
						<div class="grid-item">
							<a href="/article/detail?idx=${articleDTO.bnIdx }" target="_blank"><img src="${articleDTO.thumbNail}" /></a>
							<a href="/article/detail?idx=${articleDTO.bnIdx }"><figcaption>${articleDTO.bnTitle}</figcaption></a>
						</div>
					</c:if>
					<c:if test="${empty articleDTO.thumbNail }">
						<div class="grid-item">
							<a href="/article/detail?idx=${articleDTO.bnIdx }" target="_blank"><img src="/resources/img/noImage.jpg"/></a>
							<a href="/article/detail?idx=${articleDTO.bnIdx }"><figcaption>${articleDTO.bnTitle}</figcaption></a>
						</div>
					</c:if>
				</c:forEach>
			</c:if>
			<!--검색인경우  -->
			<c:if test="${list.size() != 0 }">
				<c:forEach var="articleDTO" items="${list}">
					<c:if test="${not empty articleDTO.thumbNail}">
						<div class="grid-item">
							<a href="/article/detail?idx=${articleDTO.bnIdx }" target="_blank"><img src="${articleDTO.thumbNail}"/></a>
							<a href="/article/detail?idx=${articleDTO.bnIdx }"><figcaption>${articleDTO.bnTitle}</figcaption></a>
						</div>
					</c:if>
					<c:if test="${empty articleDTO.thumbNail }">
						<div class="grid-item">
							<a href="/article/detail?idx=${articleDTO .bnIdx}" target="_blank"><img src="/resources/img/noImage.jpg"/></a>
							<a href="/article/detail?idx=${articleDTO.bnIdx }"><figcaption>${articleDTO.bnTitle}</figcaption></a>
						</div>
					</c:if>
				</c:forEach>
			</c:if>
	</div>
</div>
<script>
	$(function() {
		// select 형태 바꿔주는 JS 실행
		$(".sel").selectric();
	});
</script>
<script src="/resources/js/imageLoad.js"></script>
</html>