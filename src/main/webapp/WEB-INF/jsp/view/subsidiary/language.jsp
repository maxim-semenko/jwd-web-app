<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="current" value="${param.ddlLanguage}" scope="session"/>
<c:if test="${not empty current }">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>
<fmt:setBundle basename="page" scope="session"/>
