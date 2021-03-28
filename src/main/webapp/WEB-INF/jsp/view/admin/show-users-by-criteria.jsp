<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>
<html>
<head>
    <title><fmt:message key="admin.searchTitle"/></title>
    <%@include file="../subsidiary/bootstrap.jsp" %>
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
                <a href="controller?command=admin/all-users" style="text-decoration: none">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        <fmt:message key="admin.back"/>
                    </button>
                </a>
<%--                <form action="<c:url value="/controller"/>" method="get">--%>
<%--                 --%>
<%--                </form>--%>

            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
    <table class="table table-striped table-bordered">
        <form class="needs-validation" action="/controller" method="get">
            <label><input hidden name="command" value="find-users-by-criteria"></label>
            <button class="btn btn-success btn-block" type="submit">
                Найти
            </button>
            <thead>
            <tr>
                <th style="max-width:100px">
                    <label><input type="number" min="1" class="form-control" name="userId"
                                  placeholder="<fmt:message key="user.id"/>"></label>
                </th>
                <th style="max-width:180px">
                    <label><input type="text" minlength="2" maxlength="25" class="form-control"
                                  name="userLogin" pattern="^[a-zA-Z0-9]+$"
                                  placeholder="<fmt:message key="home.login"/>"></label>
                </th>
                <th style="max-width:180px">
                    <label><input type="text" minlength="2" maxlength="25" class="form-control"
                                  name="userFirstname"
                                  pattern="^([А-Я][а-я]{1,25}|[A-Z][a-z]{1,25})$"
                                  placeholder="<fmt:message key="signup.firstname"/>"></label>
                </th>
                <th style="max-width:180px">
                    <label><input type="text" minlength="2" maxlength="25" class="form-control"
                                  name="userLastname" pattern="^([А-Я][а-я]{1,25}|[A-Z][a-z]{1,25})$"
                                  placeholder="<fmt:message key="signup.lastname"/>"></label></th>
                <th style="max-width:180px">
                    <label><input type="email" minlength="4" maxlength="25" class="form-control"
                                  name="userEmail" placeholder="<fmt:message key="signup.email"/>"></label></th>
                <th><label><input type="number" min="1" max="100" class="form-control" name="userAverageScore"
                                  placeholder="<fmt:message key="signup.averageScore"/>"></label>
                </th>
                <th><label><input type="number" min="1" max="100" class="form-control" name="userRussianScore"
                                  placeholder="<fmt:message key="signup.russianScore"/>"></label>
                </th>
                <th><label><input type="number" min="1" max="100" class="form-control" name="userMathScore"
                                  placeholder="<fmt:message key="signup.mathScore"/>"></label>
                </th>
                <th><label><input type="number" min="1" max="100" class="form-control" name="userPhysicsScore"
                                  placeholder="<fmt:message key="signup.physicsScore"/>"></label>
                </th>
                <th><label><input type="number" min="1" max="4" class="form-control" name="userFacultyId"
                                  placeholder="<fmt:message key="signup.faculty"/>"></label></th>
            </tr>
            </thead>
        </form>
        <tbody>
        <c:forEach var="user" items="${requestScope.usersByCriteria}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.firstname}"/></td>
                <td><c:out value="${user.lastname}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.averageScore}"/></td>
                <td><c:out value="${user.russianExamScore}"/></td>
                <td><c:out value="${user.mathExamScore}"/></td>
                <td><c:out value="${user.physicsExamScore}"/></td>
                <td><c:out value="${user.facultyId}"/></td>
                <td>
                    <form action="controller?command=remove-user-by-admin" method="post">
                        <label><input type="number" hidden name="id" value="${user.id}"></label>
                        <button class="btn btn-success btn-block" type="submit">
                            <fmt:message key="admin.allUsers.delete"/>
                        </button>
                    </form>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>