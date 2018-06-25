<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="includes/header.jsp" %>
<body>
<%@include file="includes/left_side_main_menu.jsp" %>
<%@include file="includes/top_main_menu.jsp" %>
<div class="offcanvas-wrapper">
    <div class="page-title">
        <div class="container">
            <div class="column">
                <h1><spring:message code="com.item.reservation.tool.change.password.title"/></h1>
            </div>
        </div>
    </div>
    <div class="container padding-bottom-3x mb-2">
        <div class="row justify-content-center">
            <div class="col-lg-8 col-md-10">
                <%@include file="includes/messages.jsp" %>
                <p><spring:message code="com.item.reservation.tool.change.password.info"/></p>
                <form:form class="card mt-4" action="/changePassword" modelAttribute="changePasswordForm" method="POST">
                    <spring:bind path="*">
                        <c:if test="${status.errorCode eq 'ComparePasswords'}">
                            <div class="alert alert-danger alert-dismissible fade show text-center margin-bottom-1x">
                                <span class="alert-close" data-dismiss="alert"></span>
                                <i class="icon-ban"></i>&nbsp;&nbsp; <form:errors htmlEscape="false" />
                            </div>
                        </c:if>
                    </spring:bind>
                    <div class="card-body">
                        <spring:bind path="password">
                            <c:choose>
                                <c:when test="${!status.error && status.value ne null}">
                                    <c:set value="has-success" var="newPasswordDivClass"/>
                                    <c:set value="form-control-success" var="newPasswordInputClass"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set value="${status.error ? 'has-danger' : ''}" var="newPasswordDivClass"/>
                                    <c:set value="${status.error ? 'form-control-danger' : ''}" var="newPasswordInputClass"/>
                                </c:otherwise>
                            </c:choose>
                            <div class="input-group has-danger">
                                <div class="form-control-feedback">
                                    <form:errors path="password"/>
                                </div>
                            </div>
                            <div class="form-group input-group ${newPasswordDivClass}">
                                <spring:message code="com.item.reservation.tool.change.new.password" var="passwordPlaceholder"/>
                                <form:input class="form-control ${newPasswordInputClass}" type="password" placeholder="${passwordPlaceholder}" path="password"/>
                                <span class="input-group-addon"><i class="icon-lock"></i></span>
                            </div>
                        </spring:bind>
                        <spring:bind path="passwordRepeat">
                            <c:choose>
                                <c:when test="${!status.error && status.value ne null}">
                                    <c:set value="has-success" var="passwordRepeatDivClass"/>
                                    <c:set value="form-control-success" var="passwordRepeatInputClass"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set value="${status.error ? 'has-danger' : ''}" var="passwordRepeatDivClass"/>
                                    <c:set value="${status.error ? 'form-control-danger' : ''}" var="passwordRepeatInputClass"/>
                                </c:otherwise>
                            </c:choose>
                            <div class="input-group has-danger">
                                <div class="form-control-feedback"><form:errors path="passwordRepeat"/></div>
                            </div>
                            <div class="form-group input-group ${passwordRepeatDivClass}">
                                <spring:message code="com.item.reservation.tool.change.repeat.new.password" var="repeatPasswordPlaceholder"/>
                                <form:input class="form-control ${passwordRepeatInputClass}" type="password" placeholder="${repeatPasswordPlaceholder}" path="passwordRepeat"/>
                                <span class="input-group-addon"><i class="icon-lock"></i></span>
                            </div>
                        </spring:bind>
                    </div>
                    <div class="text-sm-right">
                        <form:button class="btn btn-primary"><spring:message code="com.item.reservation.tool.change.password.button"/> </form:button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    <%@include file="includes/footer.jsp" %>
</div>
<%@include file="includes/side_background.jsp"%>
</body>
</html>