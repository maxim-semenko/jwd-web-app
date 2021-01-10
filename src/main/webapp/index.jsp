<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:if test="${sessionScope.User != null}">
    <c:choose>
        <c:when test = "${sessionScope.User.userRole.getId() == 1}">
            <jsp:forward page="WEB-INF/jsp/view/admin/main-admin.jsp"/>
        </c:when>
        <c:when test = "${sessionScope.User.userRole.getId() == 2}">
            <jsp:forward page="WEB-INF/jsp/view/user/user-cabinet.jsp"/>
        </c:when>
    </c:choose>
</c:if>
<jsp:forward page="WEB-INF/jsp/view/user/home.jsp"/>
</body>
</html>
