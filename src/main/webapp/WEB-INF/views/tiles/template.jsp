<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Tiles_Template</title>
    <link href="/resources/css/base.css" rel="stylesheet">
    <link href="/resources/css/button.css" rel="stylesheet">
    <style>
        #header{            
            height:50px;
            text-align: center;
            background-color: aqua;
        }
        #left{
            float:left;
             width:15%;
            background-color: gray;
        }
        #main{
            float:left;
             width:85%;
        }
        #footer{
            width: 100%;
            height: 50px;            
            text-align: center;
            background-color: orange;
            clear:both;
        }
         #left, #main{ 
               min-height: 600px;
         } 
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
    <div style="width:100%; height:100%;">
    <div id="header"><tiles:insertAttribute name="header" /></div>
		<!-- #contentwrap{width:100%;width:auto;margin:0
		auto;background:#fff;padding-bottom:20px}
		#content{float:right;width:892px;text-align:left;min-height:500px;background:#fff}
		#main_content{width:100%;margin:0 auto;} -->
		<div id="container">
			<div id="contentwrap">
				<div id="content">
					<div id="main_content">
						<div id="main">
							<tiles:insertAttribute name="body" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="container:after"></div>
		<div id="footer"><tiles:insertAttribute name="footer" /></div>
    </div>
    <script type="text/javascript">
        $(function() {
 
        });    
    </script>    
</body>
</html>