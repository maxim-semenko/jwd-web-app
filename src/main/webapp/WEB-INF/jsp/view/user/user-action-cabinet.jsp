<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="my-3 p-3 bg-white rounded shadow-sm">
    <h6 class="border-bottom border-gray pb-2 mb-0"><fmt:message key="user.yourAction"/></h6>
    <div class="media text-muted pt-3">
        <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <div class="d-flex justify-content-between align-items-center w-100">
                <form class="form-inline" method="post" action="home?command=cabinet/edit">
                    <button class="btn btn-success my-2 my-sm-0" type="submit"
                            <c:if test="${requestScope.enrolledList == true}">
                                disabled
                            </c:if>>
                        <fmt:message key="user.yourAction.edit"/>
                    </button>
                </form>
            </div>
        </div>
    </div>

    <div class="media text-muted pt-3">
        <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <div class="d-flex justify-content-between align-items-center w-100">
                <form class="form-inline" action="home?command=cabinet/remove" method="post">
                    <label><input type="number" hidden name="id" value="${sessionScope.user.id}"></label>
                    <button class="btn btn-danger my-2 my-sm-0" type="submit"
                            <c:if test="${requestScope.enrolledList == true}">
                                disabled
                            </c:if>><fmt:message key="user.yourAction.delete"/>
                    </button>
                </form>
            </div>
        </div>
    </div>

    <div class="media text-muted pt-3">
        <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <div class="d-flex justify-content-between align-items-center w-100">
                <form class="form-inline" method="post" action="home?command=sign-out">
                    <button class="btn btn-primary my-2 my-sm-0" type="submit"><fmt:message
                            key="user.yourAction.signOut"/></button>
                </form>
            </div>
        </div>
    </div>
</div>