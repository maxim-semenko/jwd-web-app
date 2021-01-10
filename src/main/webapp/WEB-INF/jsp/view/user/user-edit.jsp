<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>

<html>
<head>
    <title><fmt:message key="user.edit.title"/></title>
    <%@include file="../subsidiary/bootstrap.jsp" %>
</head>
<c:if test="${sessionScope.user == null}">
    ${sessionScope.isLogout = true}
    <c:redirect url="home?command=HOME_PAGE"/>
</c:if>
<body>
<div class="container">
    <div class="py-5 text-center">
        <img class="d-block mx-auto mb-4" src="img/BGUIR-logo.png" alt="" width="180" height="180">
        <h1><fmt:message key="user.edit.name"/></h1>
        <p class="lead"><fmt:message key="signup.label"/></p>
    </div>
    <div class="row">
        <div class="col-md-12 order-md-1">
            <h4 class="mb-3"><fmt:message key="signup.heading"/></h4>
            <c:if test="${requestScope.checkExistUser == true}">
                <div class="alert alert-danger alert-dismissible fade show">
                    <fmt:message key="signup.error"/>
                </div>
            </c:if>
            <form class="needs-validation" action="home?command=ACCEPT_USER_EDIT" method="post">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="firstName"><fmt:message key="signup.firstname"/></label>
                        <input required type="text" class="form-control" id="firstName" name="firstname"
                               value="${sessionScope.user.firstname}"
                               pattern="^([А-Я][а-я]{1,25}|[A-Z][a-z]{1,25})$" minlength="2" maxlength="25">
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName"><fmt:message key="signup.lastname"/></label>
                        <input required type="text" class="form-control" id="lastName" name="lastname"
                               value="${sessionScope.user.lastname}"
                               pattern="^([А-Я][а-я]{1,25}|[A-Z][a-z]{1,25})$" minlength="2" maxlength="25">
                    </div>
                </div>

                <div class="mb-3">
                    <label for="login"><fmt:message key="home.login"/></label>
                    <div class="input-group">
                        <input required type="text" class="form-control" id="login" name="login"
                               value="${sessionScope.user.login}"
                               pattern="^[a-zA-Z0-9]+$" minlength="2" maxlength="25">
                    </div>
                </div>

                <div class="mb-3">
                    <label for="login"><fmt:message key="home.password"/></label>
                    <div class="input-group">
                        <label for="password"></label>
                        <input required type="password" class="form-control" id="password" name="password" minlength="8"
                               value="${sessionScope.user.password}"
                               maxlength="255">
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email"><fmt:message key="signup.email"/></label>
                    <input type="email" class="form-control" id="email" placeholder="you@gmail.com" name="email"
                           value="${sessionScope.user.email}"
                           required minlength="2"
                           maxlength="25">
                </div>

                <div class="mb-3">
                    <label for="averageScore"><fmt:message key="signup.averageScore"/></label>
                    <input type="number" min="0" max="100" class="form-control" id="averageScore" name="averageScore"
                           value="${sessionScope.user.averageScore}"
                           required>
                </div>

                <div class="mb-3">
                    <label for="russianScore"><fmt:message key="signup.russianScore"/></label>
                    <input type="number" min="0" max="100" class="form-control" id="russianScore" name="russianScore"
                           value="${sessionScope.user.russianExamScore}"
                           required>
                </div>

                <div class="mb-3">
                    <label for="mathScore"><fmt:message key="signup.mathScore"/></label>
                    <input type="number" min="0" max="100" class="form-control" id="mathScore" name="mathScore"
                           value="${sessionScope.user.mathExamScore}"
                           required>
                </div>

                <div class="mb-3">
                    <label for="physicsScore"><fmt:message key="signup.physicsScore"/></label>
                    <input type="number" min="0" max="100" class="form-control" id="physicsScore" name="physicsScore"
                           value="${sessionScope.user.physicsExamScore}"
                           required>
                </div>

                <hr class="mb-4">
                <h4 class="mb-3"><fmt:message key="signup.faculty"/></h4>

                <div class="d-block my-3">
                    <div class="custom-control custom-radio">
                        <input id="FCSN" name="facultySelect" type="radio" class="custom-control-input" value="1"
                               required
                        <c:if test="${sessionScope.user.facultyId == '1'}">
                               checked
                        </c:if>>
                        <label class="custom-control-label" for="FCSN"><fmt:message key="label.faculty1"/></label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input required id="FITC" name="facultySelect" type="radio" class="custom-control-input"
                               value="2" <c:if test="${sessionScope.user.facultyId == '2'}">
                               checked
                        </c:if>>
                        <label class="custom-control-label" for="FITC"><fmt:message key="label.faculty2"/></label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input required id="FCAD" name="facultySelect" type="radio" class="custom-control-input"
                               value="3" <c:if test="${sessionScope.user.facultyId == '3'}">
                               checked
                        </c:if>>
                        <label class="custom-control-label" for="FCAD"><fmt:message key="label.faculty3"/></label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input id="FRE" name="facultySelect" type="radio" class="custom-control-input" value="4"
                               required
                        <c:if test="${sessionScope.user.facultyId == '4'}">
                               checked
                        </c:if>>
                        <label class="custom-control-label" for="FRE"><fmt:message key="label.faculty4"/></label>
                    </div>
                </div>
                <hr class="mb-4">

                <button class="btn btn-success btn-lg btn-block" type="submit">
                    <fmt:message key="user.edit.submit"/>
                </button>
            </form>

            <form action="home?command=USER_CABINET" method="post">
                <button class="btn btn-primary btn-lg btn-block" type="submit">
                    <fmt:message key="user.edit.cancel"/>
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
