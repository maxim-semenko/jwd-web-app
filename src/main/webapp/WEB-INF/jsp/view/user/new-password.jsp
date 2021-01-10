<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>

<html>
<head>
    <title><fmt:message key="password.title"/></title>
    <%@include file="../subsidiary/bootstrap.jsp" %>

</head>
<body>
<header>
    <%@include file="../subsidiary/header.jsp" %>
</header>
<section class="jumbotron text-center">
    <div class="container">
        <h1><fmt:message key="password.header"/></h1>
        <p class="lead text-muted"><fmt:message key="password.description"/></p>
        <div class="form-signin">
            <c:if test="${requestScope.errorCode == true}">
                <div class="alert alert-danger alert-dismissible fade show">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <fmt:message key="password.error"/>
                </div>
            </c:if>
            <form class="needs-validation" action="home?command=CHANGE_PASSWORD" method="post">
                <label class="sr-only"></label>
                <input style="margin-bottom: 5px" class="form-control" type="text" name="inputCheckCode"
                       placeholder="Проверочный код"
                       minlength="2" maxlength="25" required>

                <label class="sr-only"><fmt:message key="password.header"/></label>
                <input class="form-control" type="password" name="newPassword" placeholder=
                <fmt:message key="home.password"/> id="inputPassword" minlength="8"
                       maxlength="255" required>

                <button class="btn btn-lg btn-primary btn-block" type="submit" id="btn_signin" style="margin-top: 1%">
                    <fmt:message key="password.submit"/></button>
            </form>
        </div>
    </div>
</section>
</body>
</html>
