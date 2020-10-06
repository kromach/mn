<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
     #star_grade a{
        text-decoration: none;
        color: gray;
    }
    #star_grade a.on{
        color: red;
    }
</style>

<div class="grid-Wrapper">
	<div class="grid2"> 
	
		<div class="detail-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="detail-item detail-width6">
			<h3 class="text-left pad-y10">새 정보 등록</h3>
			<form action="insertPro" name="dkForm" method="post" enctype="multipart/form-data">
				<table class="tableCss tbl-lg text-left">
					<tr>
						<th>주류 종류</th>
						<td>
							<select id="schDkBkind" name="dkBkind" class="required">
								<option value="B001">맥주</option>
							</select>
							<select id="schDkSkind" name="dkSkind" class="required">
								<option value="S001">라거</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>주류명</th>
						<td>
							<input type="text" name="dkName" class="input-md required" />
						</td>
					</tr>
					<tr>
						<th>대표 이미지</th>
						<td>
							<input type="file" name="dkImg" class="input-md required" />
						</td>
					</tr>
					<tr>
						<th>양조장</th>
						<td>
							<input type="text" name="dkPlace" class="input-md required" />
						</td>
					</tr>
					<tr>
						<th>국가/도시</th>
						<td>
							<input type="text" name="dkCountry" class="input-sm required" placeholder="국가"/>
							&nbsp;<input type="text" name="dkCity" class="input-sm required" placeholder="도시"/>
						</td>
					</tr>
					<tr>
						<th>알콜 도수</th>
						<td>
							<input type="number" name="dkPlace" class="input-sm required" /> 도
						</td>
					</tr>
					<tr>
						<th>어울리는 안주</th>
						<td>
							<input type="text" name="dkPlace" class="input-lg required" />
						</td>
					</tr>
					<tr>
						<th>평가</th>
						<td id="star_grade">
							<c:forEach begin="1" end="5" step="1" var="i">
								<p>
									<span class="item${i}"></span>
									<a class="dkItem${i}" href="#" value="1">★</a> 
									<a class="dkItem${i}" href="#" value="2">★</a> 
									<a class="dkItem${i}" href="#" value="3">★</a> 
									<a class="dkItem${i}" href="#" value="4">★</a> 
									<a class="dkItem${i}" href="#" value="5">★</a>
								</p>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>태그</th>
						<td>
							<input type="text" name="dkTag" class="input-lg required" />
							<br/>쉼표로 태그를 구분하여 입력해주세요 ex)마시는,녀석들,맥주,beer
						</td>
					</tr>
					<tr>
						<th>주류 정보</th>
						<td>
							<textarea name="dkPlace" class="required" style="width:98%" ></textarea>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
	</div>
</div>
<script>
	$(function() {
		var msnry = new Masonry('.grid2', {
			itemSelector : '.detail-item',
			columnWidth : '.detail-sizer',
			percentPosition : true,
			gutter: '.gutter-sizer'
		});
		imagesLoaded('.grid2').on('progress', function() {
			msnry.layout();
		});

	});
	
    $('#star_grade a').click(function(){
        $(this).parent().children("a").removeClass("on");  /* 별점의 on 클래스 전부 제거 */ 
        $(this).addClass("on").prevAll("a").addClass("on"); /* 클릭한 별과, 그 앞 까지 별점에 on 클래스 추가 */
        return false;
    });
</script>