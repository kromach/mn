<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(function() {
		$("#header").removeClass("header_color").addClass("main_header");
		
		var img_random = Math.ceil(Math.random()*7);
		$(".main_header").css({"background":"url(/resources/img/main/main" + img_random + ".jpg) no-repeat center center"});
		
		$(".t" + (img_random % 2)).removeClass("display-none")
	});
</script>
<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<c:forEach var="mainVO" items="${main}">
			<div class="grid-item">
				<a href="${mainVO.aLinkUri}">
					<img src="${mainVO.imgUri}" />
				</a>
			</div>
		</c:forEach>
		<input type="hidden" id="index" value="0">
	</div>
</div>
<!-- <div class="chat">
	<a href="/chat"><img src="/resources/img/main/chat.png" ></a>
</div> -->
<script type="text/javascript">
let isEnd = false;
$(function(){
	$(window).scroll(function() {
		 let $window = $(this);
         let scrollTop = $window.scrollTop();
         let windowHeight = $window.height();
         let documentHeight = $(document).height();
         var context = window.location.pathname.substring(0,
					window.location.pathname.indexOf("/", 2));
		 var index = $('#index').val();
         if( scrollTop + windowHeight +100 > documentHeight ){
        	 fetchList(context,index);
			}
		})
})

function fetchList(context,index){
	if(isEnd == true){
		return;
	}
	$.ajax({
		url : context + '/reload',
		data : 'index='+index,
		type : "post",
		success : function(data) {
			if(data==""){
				isEnd = true;
				console.log('end');
			}else{
				
				var $grid = $('.grid').masonry({
					itemSelector : '.grid-item',
					columnWidth : '.grid-sizer',
					percentPosition : true,
					gutter: '.gutter-sizer'
				});
				for(var i in data){
					var el = '<div class="grid-item"><a href="'+data[i].aLinkUri+'"><img src="'+data[i].imgUri+'"/></a></div>';
					$grid.append( el ).masonry( 'appended', el ,true);
					$grid.append( el ).masonry( 'reloadItems' );
					//$('.grid').children(":last").after(el).msnry('appended', el).msnry('layout');
					
					// 재훈 테스트
					//$grid.append( el ).masonry('appended', el).masonry('reloadItems');	
					 
				}
				
				// 재훈 테스트
				// masonry 재 실행 
    			//$grid.masonry();
				
				$('#index').val(Number(index)+1);
			}
		}
	});
}
</script>
<script src="/resources/js/imageLoad.js"></script>