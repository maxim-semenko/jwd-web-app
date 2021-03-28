<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>
<html>
<head>
    <title><fmt:message key="admin.allUsers.title"/>
    </title>
    <%@include file="../subsidiary/bootstrap.jsp" %>
    <%@include file="../subsidiary/pdf.jsp" %>
</head>
<body>
<c:if test="${sessionScope.user == null}">
    ${sessionScope.isLogout = true}
    <c:redirect url="controller?command=home"/>
</c:if>

<header>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container d-flex justify-content-between">
            <a href="controller?command=admin" class="navbar-brand d-flex align-items-center">
                <img class="mb-4" src="<c:url value="/img/mortarboard.png"/>" width="64" height="64" alt="">
                <strong><fmt:message key="admin.header"/></strong>
            </a>
        </div>
        <%@include file="../subsidiary/form-select-language.jsp" %>
</header>

<div class="container" style="margin-top: 1%">
    <div class="container d-flex justify-content-center mt-50 mb-50">
        <div class="row">
            <div class="col-md-12 text-right mb-3">
                <a href="controller?command=admin" style="text-decoration: none">
                    <button class="btn btn-lg btn-primary btn-block" type="submit"
                            id="btn_signup"><fmt:message key="admin.back"/>
                    </button>
                </a>
            </div>
            <div class="col-md-12 text-right mb-3">
                <button class="btn btn-lg btn-primary btn-block" id="download"><fmt:message
                        key="admin.downloadPDF"/></button>
            </div>
        </div>
    </div>

    <div id="to_pdf">
        <%@include file="../admin/enrolled-list-1.jsp" %>
        <%@include file="../admin/enrolled-list-2.jsp" %>
        <%@include file="../admin/enrolled-list-3.jsp" %>
        <%@include file="../admin/enrolled-list-4.jsp" %>
    </div>
</div>
<%@include file="../subsidiary/footer.jsp" %>
</body>
</html>
