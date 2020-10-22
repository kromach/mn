<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="grid-Wrapper">
	<div class="grid">  
		<div class="grid-sizer"></div>
		<div class="gutter-sizer"></div>
		<div class="grid-item--width6">
			<h1 class="text-left pad-y10">이벤트 목록</h1>
		</div>
		<c:if test="${count == 0 }">
			<div class="grid-item--width6">
				<p class="text-center font-bold" style="font-size: 18px; padding: 150px 0;color: #069;"><i class="fas fa-exclamation-triangle"></i> 진행 중인 이벤트가 없습니다.</p>
			</div>
		</c:if>
 		<c:forEach var="eventList" items="${eventList}">
			<div class="grid-item">
				<a href="/event/detail?eventCode=${eventList.eventCode }">
					<img src="${eventList.thumImg}" onerror="this.src='/resources/img/noImage.jpg'"/>
				</a>
			</div>
		</c:forEach>
 		<input type="hidden" id="index" value="0">
	</div>
</div>
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
		async: false,
		success : function(data) {
			if(data==""){
				isEnd = true;
				console.log('end');
			}else{
				console.log(data);
				$('#index').val(Number(index)+1);
				var $grid = $('.grid').masonry({
					itemSelector : '.grid-item',
					columnWidth : '.grid-sizer',
					percentPosition : true,
					gutter: '.gutter-sizer'
				});
				for(var i in data){
					var el = '<div class="grid-item"><a href="'+data[i].eventCodei+'"><img src="'+data[i].thumImg+'"/></a></div>';
					$grid.append( el ).masonry( 'appended', el ,true);
				}
				// 재훈 테스트
				// masonry 재 실행 
				// $grid.masonry();
    			$grid.masonry( 'reloadItems' );
    			$grid.masonry();
			}
		}
	});
}
</script>
<script src="/resources/js/imageLoad.js"></script>