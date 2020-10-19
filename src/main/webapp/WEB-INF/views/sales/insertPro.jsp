<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
${prCode}
</div>
<c:url var="u" value="product/productDetail" >
	<c:param name="prCode" value="${prCode}" />
</c:url>
${u}
<script>
	location.href="${u}";
</script>
