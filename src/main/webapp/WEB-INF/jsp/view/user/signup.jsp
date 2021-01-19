<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>

<html>
<head>
    <title><fmt:message key="signup.title"/></title>
    <style>
        <%@include file="/WEB-INF/css/signup.css" %>
    </style>
    <%@include file="../subsidiary/bootstrap.jsp" %>
</head>
<body class="bg-light">
<header>
    <%@include file="../subsidiary/header.jsp" %>
</header>

<div class="container">
    <div class="py-5 text-center">
        <img class="d-block mx-auto mb-4" src="img/BGUIR-logo.png" alt="" width="180" height="180">
        <h1><fmt:message key="signup.name"/></h1>
        <p class="lead"><fmt:message key="signup.label"/></p>
    </div>
    <div class="row">
        <div class="col-md-12 order-md-1">
            <h4 class="mb-3"><fmt:message key="signup.heading"/></h4>
            <c:if test="${requestScope.checkExistUser == true}">
                <div class="alert alert-danger alert-dismissible fade show">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <fmt:message key="signup.error"/>
                </div>
            </c:if>
            <form class="needs-validation" action="home?command=sign-up-user" method="post">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="firstName"><fmt:message key="signup.firstname"/></label>
                        <input required type="text" class="form-control" id="firstName" name="firstname"
                               pattern="^([А-Я][а-я]{1,25}|[A-Z][a-z]{1,25})$" minlength="2" maxlength="25">
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName"><fmt:message key="signup.lastname"/></label>
                        <input required type="text" class="form-control" id="lastName" name="lastname"
                               pattern="^([А-Я][а-я]{1,25}|[A-Z][a-z]{1,25})$" minlength="2" maxlength="25">
                    </div>
                </div>

                <div class="mb-3">
                    <label for="login"><fmt:message key="home.login"/></label>
                    <div class="input-group">
                        <input required type="text" class="form-control" id="login" name="login"
                               pattern="^[a-zA-Z0-9]+$" minlength="2" maxlength="25">
                    </div>
                </div>

                <div class="mb-3">
                    <label for="login"><fmt:message key="home.password"/></label>
                    <div class="input-group">
                        <label for="password"></label>
                        <input required type="password" class="form-control" id="password" name="password" minlength="8"
                               maxlength="255">
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email"><fmt:message key="signup.email"/></label>
                    <input type="email" class="form-control" id="email" placeholder="you@gmail.com" name="email"
                           required minlength="2"
                           maxlength="25">
                </div>

                <div class="mb-3">
                    <label for="averageScore"><fmt:message key="signup.averageScore"/></label>
                    <input type="number" min="0" max="100" class="form-control" id="averageScore" name="averageScore"
                           required>
                </div>

                <div class="mb-3">
                    <label for="russianScore"><fmt:message key="signup.russianScore"/></label>
                    <input type="number" min="0" max="100" class="form-control" id="russianScore" name="russianScore"
                           required>
                </div>

                <div class="mb-3">
                    <label for="mathScore"><fmt:message key="signup.mathScore"/></label>
                    <input type="number" min="0" max="100" class="form-control" id="mathScore" name="mathScore"
                           required>
                </div>

                <div class="mb-3">
                    <label for="physicsScore"><fmt:message key="signup.physicsScore"/></label>
                    <input type="number" min="0" max="100" class="form-control" id="physicsScore" name="physicsScore"
                           required>
                </div>

                <hr class="mb-4">
                <h4 class="mb-3"><fmt:message key="signup.faculty"/></h4>
                    <%@include file="../user/signup-faculty.jsp" %>
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">
                    <fmt:message key="signup.button"/>
                </button>
            </form>
        </div>
    </div>

    <footer class="text-muted">
        <div class="container">
            <p class="mt-5 mb-3 text-muted" align="center"><fmt:message key="label.bsuir"/></p>
        </div>
    </footer>
</div>
</body>
</html>
