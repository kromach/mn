<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<script src="https://cdn.jsdeliver.net/sockjs/1/sockjs.min.js"></script>
<script type="text/javascript">
var sock = new SockJS("<c:url value="/ehco"/>");
//메세지보내는 메서드
sock.onmessage = onMessage;
// 연결끊을때 사용하는 메서드
sock.onclose = onClose;
$(function(){
	
});
console.log('chatting data : ')
</script>
<button>123123</button>

</html>