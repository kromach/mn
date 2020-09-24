<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/resources/css/base.css" rel="stylesheet">
<link href="/resources/css/button.css" rel="stylesheet">
<!-- BooStrap CDN CSS only -->
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
crossorigin="anonymous">
<!-- Ajax -->	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- JQuery  -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.js"
	integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
	crossorigin="anonymous"></script>
<!-- 메뉴스타일 -->
<style>
.ham-menu, .ham-menu span {
  -webkit-transition: all 500ms ease-out;
  -moz-transition: all 500ms ease-out;
  -o-transition: all 500ms ease-out;
  transition: all 500ms ease-out;
  cursor: pointer;
}
.ham-menu {border: 2px solid #666; width: 32px; padding: 1px; border-radius: 3px; position: absolute; right: 5px; top: 5px; }
.ham-menu .line {border: 3px solid #666; line-height: 0; font-size: 0; display: block; margin: 2px;}
.ham-menu .content {border: 3px solid #666; line-height: 0; font-size: 12; display: block; margin: 2px;}
.ham-menu:hover {border-color: #99f;}
.ham-menu:hover span {border-color: #ccf;}
.dropDownMenu
{border: 2px solid #666; width: 40px; margin-top:30px; 
padding: 1px; border-radius: 3px; position: absolute; 
right: 5px; top: 5px; }
</style>
<h1>마시는 녀석들</h1>
<div class="ham-menu">
  <span class="line"></span>
  <span class="line"></span>
  <span class="line"></span>
</div>
<div class="dropDownMenu">
	<span class='content'>상품관리</span>
	<span class='content'>상품관리</span>
	<span class='content'>상품관리</span>
	<span class='content'>상품관리</span>
	<span class='content'>상품관리</span>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$(".dropDownMenu").hide();
	$(".ham-menu").hover(function() {
		$(".dropDownMenu").show();
	}, function() {
		$(".dropDownMenu").hide();
	})
});
</script>

