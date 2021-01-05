<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>

<h6><fmt:message key="label.faculty2"/></h6>
<table class="table table-striped table-bordered table-hover">
    <%@include file="../admin/enrolled-list-thead.jsp" %>
    <tbody>
    <c:forEach var="user" items="${requestScope.showEnrolledList}">
        <c:if test="${user.facultyId == 2}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.firstname}"/></td>
                <td><c:out value="${user.lastname}"/></td>
                <td><c:out value="${user.sumExams}"/></td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>