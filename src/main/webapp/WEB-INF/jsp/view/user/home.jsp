<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="home.title"/></title>
    <style>
        <%@include file="/WEB-INF/css/home.css" %>
    </style>
    <%@include file="../subsidiary/bootstrap.jsp" %>
</head>
<body>
<header>
    <%@include file="../subsidiary/header.jsp" %>
</header>

<main role="main">

    <c:if test="${sessionScope.isLogout == true}">
        <div class="alert alert-danger alert-dismissible fade show">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <fmt:message key="home.sessionOut"/>
        </div>
    </c:if>
    <c:if test="${sessionScope.notFound == true}">
        <div class="alert alert-danger alert-dismissible fade show">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <fmt:message key="home.error"/>
        </div>
    </c:if>
    <section class="jumbotron text-center">
        <div class="container">
            <h1><fmt:message key="home.label"/></h1>
            <p class="lead text-muted"><fmt:message key="home.label.description"/></p>
            <p>
            <div class="form-signin">
                <form class="needs-validation" action="home?command=SIGN_IN" method="post">
                    <label class="sr-only"><fmt:message key="home.login"/></label>
                    <input style="margin-bottom: 5px" class="form-control" type="text" name="signIn_login"
                           placeholder= <fmt:message key="home.login"/>
                            pattern="^[a-zA-Z0-9]+$"
                           minlength="2" maxlength="25" required>

                    <label class="sr-only"><fmt:message key="home.password"/></label>
                    <input class="form-control" type="password" name="signIn_password" placeholder=
                    <fmt:message key="home.password"/> id="inputPassword" minlength="8"
                           maxlength="255" required>

                    <button class="btn btn-lg btn-primary btn-block" type="submit" id="btn_signin">
                        <fmt:message key="home.button.signin"/></button>
                </form>
                <a href="home?command=SIGN_UP_PAGE" style="text-decoration: none">
                    <button class="btn btn-lg btn-primary btn-block" type="submit"
                            id="btn_signup"><fmt:message key="home.button.signup"/>
                    </button>
                </a>
                <br>
                <a href="home?command=FORGOT_PASSWORD_PAGE"><fmt:message key="home.forgotPassword"/></a>
            </div>
        </div>
    </section>
    <%--DESCRIPTIOM FACULTIES--%>
    <%@include file="../subsidiary/home-faculty.jsp" %>
    <%--THE END DESCRIPTION FACULTIES--%>
</main>
<%@include file="../subsidiary/footer.jsp" %>
</body>
</html>

