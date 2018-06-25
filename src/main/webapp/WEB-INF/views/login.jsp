<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="includes/header.jsp" %>
<body>
<header class="navbar navbar-sticky">
    <div class="site-branding">
        <div class="inner">
            <a class="site-logo" href="index.html">
                <img src="${imageBaseDir}/logo/logo.png" alt="Unishop">
            </a>
        </div>
    </div>
</header>
<div class="offcanvas-wrapper">
    <div class="page-title">
        <div class="container">
            <div class="column">
                <h1><spring:message code="com.item.reservation.tool.login.title"/></h1>
            </div>
        </div>
    </div>
    <div class="container padding-bottom-3x mb-2">
        <div class="row">
            <div class="col-md-6 m-auto">
                <%@include file="includes/messages.jsp"%>
                <c:url value="/login" var="loginUrl"/>
                <form:form class="login-box" action="${loginUrl}" modelAttribute="loginForm" method="POST">
                    <c:if test="${param.error ne null}">
                        <div class="alert alert-danger alert-dismissible fade show text-center margin-bottom-1x">
                            <span class="alert-close" data-dismiss="alert"></span>
                            <i class="icon-ban"></i>&nbsp;<spring:message code="com.item.reservation.tool.login.error"/>
                        </div>
                    </c:if>
                    <div class="form-group input-group">
                        <form:input path="email" class="form-control" type="email" placeholder="${emailText}" name="email" required="true"/>
                        <form:errors path="email"/>
                        <span class="input-group-addon"><i class="icon-mail"></i></span>
                    </div>
                    <div class="form-group input-group">
                        <form:input path="password" class="form-control" type="password" placeholder="${passwordText}" name="password" required="true"/>
                        <form:errors path="password"/>
                        <span class="input-group-addon"><i class="icon-lock"></i></span>
                    </div>
                    <div class="text-center text-sm-right">
                        <a href="/registration" class="btn btn-secondary margin-bottom-none"><spring:message code="com.item.reservation.tool.registration.button"/> </a>
                        <form:button class="btn btn-primary margin-bottom-none" type="submit"><spring:message code="com.item.reservation.tool.login.button"/> </form:button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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