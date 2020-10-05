<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div class="grid-Wrapper">
	<div class="grid"> 
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item grid-item--width4 text-left"><input type="text" name="search" /><input type="submit" class="btn btn-sm btn-blue" value="검색"></div>
		<div class="grid-item">이미지<img src=" ${info.prImg}"></div>
		<div class="grid-item">	상품 이름  ${info.prName}</div>		
	</div>
</div>
<script src="/resources/js/imageLoad.js"></script>