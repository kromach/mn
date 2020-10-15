<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
${dkCode}
</div>
<c:url var="u" value="detail" >
	<c:param name="dkCode" value="${dkCode}" />
</c:url>
${u}
<script>
	location.href="${u}";
</script>
