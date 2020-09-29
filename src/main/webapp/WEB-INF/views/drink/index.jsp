<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<div class="grid-Wrapper">
	<div class="grid"> 
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width6 text-left">
			<form action="index" name="schForm" id="schForm">
				<input type="hidden" name="isSearch" value="true"/>
				<div>
				<select id="schDkBkind" name="schDkBkind">
					<option value="B001">맥주</option>
				</select>
				<select id="schDkSkind" name="schDkSkind">
					<option value="S001">라거</option>
				</select>
				검색어 : <input type="text" name="schDkVal"/>
				</div>
				<div>
					<label><input type="checkbox" name="schDkAlcohol" value="0-5" /> 0~5도</label>
					<label><input type="checkbox" name="schDkAlcohol" value="6-10" /> 6~10도</label>
				</div>
				<div>
					<label><input type="radio" name="schDkCountry" value="" /> 전체</label>
					<label><input type="radio" name="schDkCountry" value="K" /> 국내</label>
					<label><input type="radio" name="schDkCountry" value="O" /> 해외</label>
				</div>
				<div><input type="submit" value="검색" class="btn btn-sm btn-blue" /></div>
			</form>
		</div>
		<div class="grid-item"><a onclick="setDkcode('DK00001')"><img src="/resources/img/main/4.jpg" /></a></div>
		<div class="grid-item"><img src="/resources/img/main/3.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/8.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/2.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/1.jpg" /></div>
		<div class="grid-item"><img src="/resources/img/main/9.jpg" /></div>		
		<div class="grid-item grid-item--width2"><img src="/resources/img/main/4.jpg" /></div>
		<div class="grid-item grid-item--width2"><img src="/resources/img/main/8.jpg" /></div>
	</div>
</div>
<script>
	function setDkcode(dkCode) {
		var data = "&" + $("#schForm").serialize();
		
		var url = "detail?dkCode=" + dkCode + data;
		
		console.log(url)
		location.href = url;
	}

</script>

<script src="/resources/js/imageLoad.js"></script>