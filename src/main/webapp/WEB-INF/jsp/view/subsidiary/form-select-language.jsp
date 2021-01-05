<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form  method="post">
    <div class="col-auto my-1">
        <label class="mr-sm-1" for="inlineFormCustomSelect" style="color: white"><fmt:message
                key="language.select"/></label>
        <select class="custom-select mr-sm-1" id="inlineFormCustomSelect" name="ddlLanguage">
            <option selected><fmt:message key="language.choose"/></option>
            <option value="en_US"><fmt:message key="language.english"/></option>
            <option value="ru_BY"><fmt:message key="language.belorussian"/></option>
            <option value="ru_RU"><fmt:message key="language.russian"/></option>
        </select>

        <input class="btn btn-sm btn-primary btn-block" type="submit"
               value="<fmt:message key="language.accept"/>"
               style="margin-top: 3px; background-color: #888888">
    </div>
</form>
