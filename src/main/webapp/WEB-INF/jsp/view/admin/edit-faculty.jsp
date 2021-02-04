<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>

<html>
<head>
    <title><fmt:message key="admin.faculty.title"/></title>
    <%@include file="../subsidiary/bootstrap.jsp" %>

</head>
<body>
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
        </div>
    </div>
</div>

<div class="container">
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th><fmt:message key="admin.faculty.name"/></th>
            <th><fmt:message key="admin.faculty.places"/></th>
            <th><fmt:message key="admin.faculty.action"/></th>
        </tr>
        </thead>
        <tbody>
        <form action="home?command=edit-faculty" method="post">
            <c:forEach var="faculty" items="${requestScope.allFaculties}">
            <tr>
                <td><c:out value="${faculty.type}"/></td>
                <td>
                    <label>
                        <input type="number" min="1" class="form-control" id="mathScore" name="countPlaces"
                               value="<c:out value="${faculty.countPlaces}"/>">
                    </label>
                </td>
                <td>
                    <label><input type="number" hidden name="id" value="${faculty.getType().getId()}"></label>
                    <button class="btn btn-success btn-block" type="submit">
                        <fmt:message key="admin.faculty.edit"/>
                    </button>
                </td>
            </tr>
        </form>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="../subsidiary/footer.jsp" %>
</body>
</html>
