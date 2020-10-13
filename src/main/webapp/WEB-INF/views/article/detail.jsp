<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<div class="grid-Wrapper">
		<div class="grid">
			<div class="grid-sizer"></div>
			<div class="gutter-sizer"></div>
			<div class="detail-item detail-width6">
				<h2 class="pad-top10 pad-bottom10">${articleDTO.bnTitle}</h2>
			</div>
				<table class="detailTbl tbl-lg">
					<tr>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
						<td>좋아요</td>
					</tr>
					<tr> 
						<td>${articleDTO.insertId}</td>
						<td>${articleDTO.insertDay}</td>
						<td>${articleDTO.readcount}</td>
						<td>${articleDTO.heart}</td>
					</tr>
					<tr>
						<td colspan="4" style="min-height: 400px;">${articleDTO.content }</td>
					</tr>
					<tr>
						<td colspan="4" style="height: 100px; border-bottom: 1px solid;">
						<div>
							<button class="btn btn-lg btn-blue" onclick="">좋아요</button>
							<a class="btn btn-lg btn-blue" onclick="">신고</a>
							<a class="btn btn-lg btn-yellow" onclick="">댓글등록</a>
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="height: 50px;">Comment</td>
					</tr>
				</table>
			</div>
			<div class="grid-item grid-item--width6">
				<div class="text-center pad-top10 pad-bottom20">
					<input id="addBtn" type="button" class="btn btn-md btn-blue" value="전송">
					<input type="button" class="btn btn-md btn-grey" value="목록으로" onclick="window.location='/article'" />
					<input id="addBtn" type="button" class="btn btn-md btn-blue" value="수정">
					<input id="addBtn" type="button" class="btn btn-md btn-blue" value="삭제">
				</div>
			</div>
			<div class="detail-item detail-width6">
				<table class="detailTbl tbl-lg">
					<tr>	
						<th style="min-width: 100px;">제목</th>
						<th style="min-width: 100px;">글쓴이</th>
						<th style="min-width: 130px;">작성일</th>
						<th style="min-width: 130px;">조회수</th>
						<th style="min-width: 130px;">좋아요</th>
					</tr>
					<c:forEach var="articleDTO" items="${list}">
						<tr>
							<td>${articleDTO.bnTitle}</td>
							<td>${articleDTO.insertId}</td>
							<td>${articleDTO.insertDay}</td>
							<td>${articleDTO.readcount}</td>
							<td>${articleDTO.heart}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="detail-item detail-width6">
				<h2>더보기</h2>
			</div>
		</div>
</body>
</html>