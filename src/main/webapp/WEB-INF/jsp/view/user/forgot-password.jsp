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
        <h1><fmt:message key="password.title"/></h1>
        <p class="lead text-muted"><fmt:message key="password.checkCode"/></p>
        <p>
        <div class="form-signin">
        <c:if test="${requestScope.notFoundUser == true}">
            <div class="alert alert-danger alert-dismissible fade show">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <fmt:message key="home.error"/>
            </div>
        </c:if>
            <form action="home?command=send-message" method="post" class="needs-validation">
                <label class="sr-only"><fmt:message key="home.login"/></label>
                <input style="margin-bottom: 5px" class="form-control" type="text" name="checkLogin"
                       placeholder=
                       <fmt:message key="home.login"/>
                               pattern="^[a-zA-Z0-9]+$"
                       minlength="2" maxlength="25" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit" id="btn_signin">
                    <fmt:message key="password.continue"/>
                </button>
            </form>
            <br>
        </div>
    </div>
</section>
<%@include file="../subsidiary/footer.jsp" %>
</body>
</html>
