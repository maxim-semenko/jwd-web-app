<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../subsidiary/language.jsp" %>

<html>
<head>
    <title><fmt:message key="signup.title"/></title>
    <%@include file="../subsidiary/bootstrap.jsp" %>

</head>
<body>
<section class="jumbotron text-center"
         style="position: absolute;top: 50%;left: 50%;margin: auto -50% auto auto;transform: translate(-50%, -50%)">
    <div class="container">
        <h1><fmt:message key="signup.success"/></h1>
        <div class="container">
            <a href="home?command=home" style="text-decoration: none">
                <button class="btn btn-lg btn-primary btn-block" type="submit"
                        id="btn_signup"><fmt:message key="signup.back"/></button>
            </a>
        </div>
    </div>
</section>
</body>
</html>
