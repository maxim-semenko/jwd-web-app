<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:if test="${sessionScope.User != null}">
    <jsp:forward page="WEB-INF/jsp/view/user/user-cabinet.jsp"/>
</c:if>
<jsp:forward page="WEB-INF/jsp/view/user/home.jsp"/>
</body>
</html>
