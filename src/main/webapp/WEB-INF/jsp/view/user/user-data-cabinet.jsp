<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>

<div class="my-3 p-3 bg-white rounded shadow-sm">
    <h6 class="border-bottom border-gray pb-2 mb-0"><fmt:message key="signup.heading"/></h6>
    <div class="media text-muted pt-3">
        <%@include file="../subsidiary/pink-box.jsp" %>
        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <strong class="d-block text-gray-dark"><fmt:message key="home.login"/></strong>
            ${sessionScope.user.login}
        </p>
    </div>
    <div class="media text-muted pt-3">
        <%@include file="../subsidiary/pink-box.jsp" %>
        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <strong class="d-block text-gray-dark"><fmt:message key="signup.email"/></strong>
            ${sessionScope.user.email}
        </p>
    </div>
    <div class="media text-muted pt-3">
        <%@include file="../subsidiary/pink-box.jsp" %>
        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <strong class="d-block text-gray-dark"><fmt:message key="signup.firstname"/></strong>
            ${sessionScope.user.firstname}
        </p>
    </div>
    <div class="media text-muted pt-3">
        <%@include file="../subsidiary/pink-box.jsp" %>
        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <strong class="d-block text-gray-dark"><fmt:message key="signup.lastname"/></strong>
            ${sessionScope.user.lastname}
        </p>
    </div>

    <div class="media text-muted pt-3">
        <%@include file="../subsidiary/pink-box.jsp" %>
        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <strong class="d-block text-gray-dark"><fmt:message key="signup.faculty"/></strong>
            <c:choose>
                <c:when test="${sessionScope.user.facultyId == 1}">
                    <fmt:message key="label.faculty1"/>
                </c:when>
                <c:when test="${sessionScope.user.facultyId == 2}">
                    <fmt:message key="label.faculty2"/>
                </c:when>
                <c:when test="${sessionScope.user.facultyId == 3}">
                    <fmt:message key="label.faculty3"/>
                </c:when>
                <c:when test="${sessionScope.user.facultyId == 4}">
                    <fmt:message key="label.faculty4"/>
                </c:when>
            </c:choose>
        </p>
    </div>
</div>
