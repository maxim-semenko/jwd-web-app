<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>
<html>
<head>
    <title><fmt:message key="admin.allUsers.title"/></title>
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
            <a href="" class="navbar-brand d-flex align-items-center">
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
                <a href="home?command=admin" style="text-decoration: none">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        <fmt:message key="admin.back"/>
                    </button>
                </a>
            </div>
            <div class="col-md-12 text-right mb-3">
                <a href="home?command=admin/search-page" style="text-decoration: none">
                    <button class="btn btn-lg btn-primary btn-block" type="submit"
                            id="btn_signup">Поиск
                    </button>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th><fmt:message key="user.id"/></th>
            <th><fmt:message key="home.login"/></th>
            <th><fmt:message key="signup.firstname"/></th>
            <th><fmt:message key="signup.lastname"/></th>
            <th><fmt:message key="signup.email"/></th>
            <th><fmt:message key="signup.averageScore"/></th>
            <th><fmt:message key="signup.russianScore"/></th>
            <th><fmt:message key="signup.mathScore"/></th>
            <th><fmt:message key="signup.physicsScore"/></th>
            <th><fmt:message key="signup.faculty"/></th>
            <th><fmt:message key="admin.AllUsers.status"/></th>
            <th><fmt:message key="admin.AllUsers.action"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.showAllUsers}">
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
                <td><c:out value="${user.userRole}"/></td>
                <td>
                    <form action="home?command=remove-user-by-admin" method="post">
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
</html>
