<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="my-3 p-3 bg-white rounded shadow-sm">
    <h6 class="border-bottom border-gray pb-2 mb-0"><fmt:message key="user.certificate"/></h6>
    <div class="media text-muted pt-3">
        <%@include file="../subsidiary/blue-box.jsp" %>
        <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <div class="d-flex justify-content-between align-items-center w-100">
                <strong class="text-gray-dark"><fmt:message key="signup.averageScore"/></strong>
            </div>
            <span class="d-block">${sessionScope.User.averageScore}</span>
        </div>
    </div>
    <div class="media text-muted pt-3">
        <%@include file="../subsidiary/blue-box.jsp" %>
        <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <div class="d-flex justify-content-between align-items-center w-100">
                <strong class="text-gray-dark"><fmt:message key="signup.russianScore"/></strong>
            </div>
            <span class="d-block">${sessionScope.User.russianExamScore}</span>
        </div>
    </div>
    <div class="media text-muted pt-3">
        <%@include file="../subsidiary/blue-box.jsp" %>
        <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <div class="d-flex justify-content-between align-items-center w-100">
                <strong class="text-gray-dark"><fmt:message key="signup.mathScore"/></strong>
            </div>
            <span class="d-block">${sessionScope.User.mathExamScore}</span>
        </div>
    </div>
    <div class="media text-muted pt-3">
        <%@include file="../subsidiary/blue-box.jsp" %>
        <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <div class="d-flex justify-content-between align-items-center w-100">
                <strong class="text-gray-dark"><fmt:message key="signup.physicsScore"/></strong>
            </div>
            <span class="d-block">${sessionScope.User.physicsExamScore}</span>
        </div>
    </div>
</div>
