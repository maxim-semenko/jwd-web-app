<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="admin.title"/></title>
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
            <a href="" class="navbar-brand d-flex align-items-center">
                <img class="mb-4" src="img/mortarboard.png" width="64" height="64">
                <strong><fmt:message key="admin.header"/></strong>
            </a>
        </div>
        <%@include file="../subsidiary/form-select-language.jsp" %>
</header>

<main role="main" class="container">

    <%--Action user--%>
    <div class="my-3 p-3 bg-white rounded shadow-sm">
        <h6 class="border-bottom border-gray pb-2 mb-0"><fmt:message key="admin.heading"/></h6>
        <div class="media text-muted pt-3">
            <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                <div class="d-flex justify-content-between align-items-center w-100">
                    <form class="form-inline" action="controller?command=generate-enrolled-list" method="post">
                        <button class="btn btn-success my-2 my-sm-0" type="submit"
                                <c:if test="${requestScope.enrolledList == true}">
                                    disabled
                                </c:if>><fmt:message key="admin.makeEnrolledList"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>


        <div class="media text-muted pt-3">
            <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                <div class="d-flex justify-content-between align-items-center w-100">
                    <form class="form-inline" method="post" action="controller?command=admin/all-users">
                        <button class="btn btn-success my-2 my-sm-0" type="submit"><fmt:message
                                key="admin.showAllUsers"/></button>
                    </form>
                </div>
            </div>
        </div>

        <div class="media text-muted pt-3">
            <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                <div class="d-flex justify-content-between align-items-center w-100">
                    <form class="form-inline" method="post" action="controller?command=admin/enrolled-list">
                        <button class="btn btn-success my-2 my-sm-0" type="submit"
                                <c:if test="${requestScope.enrolledList == false}">
                                    disabled
                                </c:if>
                        ><fmt:message key="admin.showEnrolledList"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <div class="media text-muted pt-3">
            <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                <div class="d-flex justify-content-between align-items-center w-100">
                    <form class="form-inline" method="post" action="controller?command=cabinet/edit-faculty-page">
                        <button class="btn btn-primary my-2 my-sm-0" type="submit"
                                <c:if test="${requestScope.enrolledList == true}">
                                    disabled
                                </c:if>><fmt:message key="admin.changeCountPlaces"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <div class="media text-muted pt-3">
            <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                <div class="d-flex justify-content-between align-items-center w-100">
                    <form class="form-inline" method="post" action="controller?command=sign-out">
                        <button class="btn btn-primary my-2 my-sm-0" type="submit">
                            <fmt:message key="admin.exit"/></button>
                    </form>
                </div>
            </div>
        </div>

        <div class="media text-muted pt-3">
            <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                <div class="d-flex justify-content-between align-items-center w-100">
                    <form class="form-inline" action="controller?command=remove-enrolled-list" method="post">
                        <button class="btn btn-danger my-2 my-sm-0" type="submit"
                                <c:if test="${requestScope.enrolledList == false}">
                                    disabled
                                </c:if>><fmt:message key="admin.deleteEnrolledList"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <div class="media text-muted pt-3">
            <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                <div class="d-flex justify-content-between align-items-center w-100">
                    <form class="form-inline" action="controller?command=remove-all-users" method="post">
                        <button class="btn btn-danger my-2 my-sm-0" type="submit">
                            <fmt:message key="admin.deleteAllUsers"/></button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</main>
<%@include file="../subsidiary/footer.jsp" %>
</body>
</html>
