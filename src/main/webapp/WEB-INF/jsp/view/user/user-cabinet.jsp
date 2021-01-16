<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="user.title"/></title>
    <style>
        <%@include file="/WEB-INF/css/home.css" %>
    </style>
    <style>
        <%@include file="/WEB-INF/css/user-cabinet.css" %>
    </style>
    <%@include file="../subsidiary/bootstrap.jsp" %>
</head>

<body>
<c:if test="${sessionScope.user == null}">
    ${sessionScope.isLogout = true}
    <c:redirect url="home?command=home"/>
</c:if>
<header>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container d-flex justify-content-between">
            <a href="home?command=cabinet" class="navbar-brand d-flex align-items-center">
                <img class="mb-4" src="<c:url value="/img/mortarboard.png"/>" width="64" height="64">
                <strong><fmt:message key="user.name"/></strong>
            </a>
        </div>
        <%@include file="../subsidiary/form-select-language.jsp" %>
</header>

<main role="main" class="container">
    <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">
        <img class="mr-3" src="<c:url value="/img/user.png"/>" alt="" width="48" height="48">
        <div class="lh-100">
            <h6 class="mb-0 text-white lh-100"><fmt:message key="user.name"/></h6>
            <c:choose>
                <c:when test="${sessionScope.user.userStatus == 'UNKNOWN'}">
                    <small style="color: gold"><fmt:message key="user.status.unknown"/></small>
                </c:when>
                <c:when test="${sessionScope.user.userStatus == 'ENROLLED'}">
                    <small style="color: #169e24"><fmt:message key="user.status.enrolled"/></small>
                </c:when>
                <c:when test="${sessionScope.user.userStatus == 'NO_ENROLLED'}">
                    <small style="color: red"><fmt:message key="user.status.noEnrolled"/></small>
                </c:when>
            </c:choose>
        </div>
    </div>
    <%--User data--%>
    <%@include file="../user/user-data-cabinet.jsp" %>
    <%--Certifiacate--%>
    <%@include file="../user/user-certificate-cabinet.jsp" %>
    <%--Action user--%>
    <%@include file="../user/user-action-cabinet.jsp" %>
</main>
<%@include file="../subsidiary/footer.jsp" %>
</body>
</html>

