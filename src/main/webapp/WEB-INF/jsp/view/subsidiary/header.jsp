<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="collapse bg-dark" id="navbarHeader">
    <div class="container">
        <div class="row">
            <div class="col-sm-8 col-md-7 py-4">
                <h4 class="text-white"><fmt:message key="home.about"/></h4>
                <p align="justify" class="text-muted"><fmt:message key="home.info"/></p>
            </div>
            <div class="col-sm-4 offset-md-1 py-4">
                <h4 class="text-white"><fmt:message key="home.feedback"/></h4>
                <ul class="list-unstyled">
                    <li>
                        <img src="<c:url value="/img/vk.png"/>" width="20" height="20" fill="none"
                             stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                             stroke-width="4" aria-hidden="true" class="mr-2" viewBox="0 0 24 0"
                             focusable="false" style="fill: white;" alt="">
                        </img>
                        <a href="#" class="text-white"><fmt:message key="home.vkontakte"/></a>
                    </li>
                    <li>
                        <img src="<c:url value="/img/instagram.png"/>" width="20" height="20" fill="none"
                             stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                             stroke-width="4" aria-hidden="true" class="mr-2" viewBox="0 0 24 0"
                             focusable="false" alt="">
                        </img>
                        <a href="#" class="text-white"><fmt:message key="home.instagram"/></a>
                    </li>
                    <li>
                        <img src="<c:url value="/img/twitter.png"/>" width="20" height="20" fill="none"
                             stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                             stroke-width="4" aria-hidden="true" class="mr-2" viewBox="0 0 24 0"
                             focusable="false">
                        </img>
                        <a href="#" class="text-white"><fmt:message key="home.twitter"/></a>
                    </li>
                    <li>
                        <img src="<c:url value="/img/facebook.png"/>" width="20" height="20" fill="none"
                             stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                             stroke-width="4" aria-hidden="true" class="mr-2" viewBox="0 0 24 0"
                             focusable="false" alt="">
                        </img>
                        <a href="#" class="text-white"><fmt:message key="home.facebook"/></a>
                    </li>
                    <li>
                        <img src="<c:url value="/img/envelope.png"/>" width="20" height="20" fill="none"
                             stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                             stroke-width="4" aria-hidden="true" class="mr-2" viewBox="0 0 24 0"
                             focusable="false">
                        </img>
                        <a href="#" class="text-white"><fmt:message key="home.email"/></a>
                    </li>
                </ul>
            </div>

        </div>

    </div>
</div>

<div class="navbar navbar-dark bg-dark shadow-sm">
    <div class="container d-flex justify-content-between">
        <a href="home?command=HOME_PAGE" class="navbar-brand d-flex align-items-center">
            <img class="mb-4" src="img/mortarboard.png" width="64" height="64">
            <strong><fmt:message key="home.logo"/></strong>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader"
                aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

    </div>
    <%@include file="../subsidiary/form-select-language.jsp" %>
</div>
