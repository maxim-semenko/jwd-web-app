<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <div class="row">
        <div class="col-3">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home"
                   role="tab" aria-controls="v-pills-home"
                   aria-selected="true"><fmt:message key="name.faculty1"/>
                </a>
                <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile"
                   role="tab" aria-controls="v-pills-profile"
                   aria-selected="false"><fmt:message key="name.faculty2"/>
                </a>
                <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages"
                   role="tab" aria-controls="v-pills-messages"
                   aria-selected="false"><fmt:message key="name.faculty3"/>
                </a>
                <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings"
                   role="tab" aria-controls="v-pills-settings"
                   aria-selected="false"><fmt:message key="name.faculty4"/>
                </a>
            </div>
        </div>

        <div class="col-9">
            <div class="tab-content" id="v-pills-tabContent">
                <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
                     aria-labelledby="v-pills-home-tab" align="justify">
                    <strong><fmt:message key="label.faculty1"/></strong>
                    <fmt:message key="home.description.faculty1"/>
                </div>
                <div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
                     aria-labelledby="v-pills-profile-tab" align="justify">
                    <strong><fmt:message key="label.faculty2"/></strong>
                    <fmt:message key="home.description.faculty2"/>
                </div>
                <div class="tab-pane fade" id="v-pills-messages" role="tabpanel"
                     aria-labelledby="v-pills-messages-tab" align="justify">
                    <strong><fmt:message key="label.faculty3"/></strong>
                    <fmt:message key="home.description.faculty3"/>
                </div>
                <div class="tab-pane fade" id="v-pills-settings" role="tabpanel"
                     aria-labelledby="v-pills-settings-tab" align="justify">
                    <strong><fmt:message key="label.faculty4"/></strong>
                    <fmt:message key="home.description.faculty4"/>
                </div>
            </div>
        </div>
    </div>
</div>

