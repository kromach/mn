<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="grid-item grid-item--width6">
				<c:if test="${result eq 'id'}">
					<c:if test="${not empty memberDTO.id}">
						<div class="mem-find margin-top20">
							<div class="searchPanel" style="width:450px;">
								<div class="ssec pad-top15 text-center">
									<h3>${memberDTO.name}님의 ID는 <span class="search-text">${memberDTO.id}</span>입니다.</h3>
								</div>
								<div class="ssec pad-top15 text-center">
									<a href="/member/login" class="searchBtn btn btn-lg btn-blue">로그인 하러가기</a>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${empty memberDTO.id}">
						<script type="text/javascript">
							alert('검색 된 회원정보가 없습니다.');
							history.go(-1);
						</script>
					</c:if>
				</c:if>
				<c:if test="${result eq 'pw'}">
					<c:if test="${not empty memberDTO.pw}">
						<div class="mem-find margin-top20">
							<div class="searchPanel" style="width:450px;">
								<div class="ssec pad-top15 text-center">
									<h3>${memberDTO.name}님의 비밀번호는 <span class="search-text">${memberDTO.pw}</span>입니다.</h3>
								</div>
								<div class="ssec pad-top15 text-center">
									<a href="/member/login" class="searchBtn btn btn-lg btn-blue">로그인 하러가기</a>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${empty memberDTO.pw}">
						<script type="text/javascript">
							alert('검색 된 회원정보가 없습니다.');
							history.go(-1);
						</script>
					</c:if>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>