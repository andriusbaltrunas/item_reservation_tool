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
        <h1><spring:message code="com.item.reservation.tool.registration.title"/></h1>
      </div>
    </div>
  </div>
  <div class="container padding-bottom-2x">
    <div class="row justify-content-center">
      <div class="col-lg-8 col-md-10">
        <%@include file="includes/messages.jsp"%>
        <h6 class="text-muted text-normal text-uppercase margin-top-2x">
          <spring:message code="com.item.reservation.tool.registration.required.fields"/>
        </h6>
        <hr class="margin-bottom-1x">
        <form:form action="/registration" class="login-box" method="POST" modelAttribute="registrationForm">
          <spring:bind path="*">
            <c:if test="${status.errorCode eq 'ComparePasswords'}">
              <div class="alert alert-danger alert-dismissible fade show text-center margin-bottom-1x">
                <span class="alert-close" data-dismiss="alert"></span>
                <i class="icon-ban"></i>&nbsp;&nbsp; <form:errors htmlEscape="false" />
              </div>
            </c:if>
          </spring:bind>
          <spring:bind path="firstName">
            <c:choose>
              <c:when test="${!status.error && status.value ne null}">
                <c:set value="has-success" var="firstNameDivClass"/>
                <c:set value="form-control-success" var="firstNameinputClass"/>
              </c:when>
              <c:otherwise>
                <c:set value="${status.error ? 'has-danger' : ''}" var="firstNameDivClass"/>
                <c:set value="${status.error ? 'form-control-danger' : ''}" var="firstNameinputClass"/>
              </c:otherwise>
            </c:choose>
            <div class="form-group row">
              <label class="col-sm-2 col-form-label"><spring:message code="com.item.reservation.tool.registration.firstName"/></label>
              <div class="col-sm-10 ${firstNameDivClass}">
                <form:input path="firstName" class="form-control ${firstNameinputClass}" type="text" />
              </div>
              <label class="col-sm-2 col-form-label"></label>
              <div class="col-sm-10 has-danger">
                <div class="form-control-feedback"><form:errors path="firstName"/></div>
              </div>
            </div>
          </spring:bind>
          <spring:bind path="lastName">
            <c:choose>
              <c:when test="${!status.error && status.value ne null}">
                <c:set value="has-success" var="secondNameDivClass"/>
                <c:set value="form-control-success" var="secondNameinputClass"/>
              </c:when>
              <c:otherwise>
                <c:set value="${status.error ? 'has-danger' : ''}" var="secondNameDivClass"/>
                <c:set value="${status.error ? 'form-control-danger' : ''}" var="secondNameinputClass"/>
              </c:otherwise>
            </c:choose>
            <div class="form-group row">
              <label class="col-sm-2 col-form-label"><spring:message code="com.item.reservation.tool.registration.lastName" /></label>
              <div class="col-sm-10 ${secondNameDivClass}">
                <form:input path="lastName" class="form-control ${secondNameinputClass}" type="text"/>
              </div>
              <label class="col-sm-2 col-form-label"></label>
              <div class="col-sm-10 has-danger">
                <div class="form-control-feedback"><form:errors path="lastName"/></div>
              </div>
            </div>
          </spring:bind>
          <spring:bind path="email">
            <c:choose>
              <c:when test="${!status.error && status.value ne null}">
                <c:set value="has-success" var="emailDivClass"/>
                <c:set value="form-control-success" var="emailInputClass"/>
              </c:when>
              <c:otherwise>
                <c:set value="${status.error ? 'has-danger' : ''}" var="emailDivClass"/>
                <c:set value="${status.error ? 'form-control-danger' : ''}" var="emailInputClass"/>
              </c:otherwise>
            </c:choose>
            <div class="form-group row">
              <label class="col-sm-2 col-form-label"><spring:message code="com.item.reservation.tool.registration.email" /></label>
              <div class="col-sm-10 ${emailDivClass}">
                <form:input path="email" class="form-control ${emailInputClass}" type="email"/>
              </div>
              <label class="col-sm-2 col-form-label"></label>
              <div class="col-sm-10 has-danger">
                <div class="form-control-feedback"><form:errors path="email"/></div>
              </div>
            </div>
          </spring:bind>
          <spring:bind path="password">
            <c:choose>
              <c:when test="${!status.error && status.value ne null}">
                <c:set value="has-success" var="passwordDivClass"/>
                <c:set value="form-control-success" var="passwordInputClass"/>
              </c:when>
              <c:otherwise>
                <c:set value="${status.error ? 'has-danger' : ''}" var="passwordDivClass"/>
                <c:set value="${status.error ? 'form-control-danger' : ''}" var="passwordInputClass"/>
              </c:otherwise>
            </c:choose>
            <div class="form-group row">
              <label class="col-sm-2 col-form-label"><spring:message code="com.item.reservation.tool.registration.password" /></label>
              <div class="col-sm-10 ${passwordDivClass}">
                <form:input path="password" class="form-control ${passwordInputClass}" type="password"/>
              </div>
              <label class="col-sm-2 col-form-label"></label>
              <div class="col-sm-10 has-danger">
                <div class="form-control-feedback">
                  <form:errors path="password"/>
                </div>
              </div>
            </div>
          </spring:bind>
          <spring:bind path="repeatPassword">
            <c:choose>
              <c:when test="${!status.error && status.value ne null}">
                <c:set value="has-success" var="repeatPasswordDivClass"/>
                <c:set value="form-control-success" var="repeatPasswordInputClass"/>
              </c:when>
              <c:otherwise>
                <c:set value="${status.error ? 'has-danger' : ''}" var="repeatPasswordDivClass"/>
                <c:set value="${status.error ? 'form-control-danger' : ''}"
                       var="repeatPasswordInputClass"/>
              </c:otherwise>
            </c:choose>
            <div class="form-group row">
              <label class="col-sm-2 col-form-label"><spring:message code="com.item.reservation.tool.registration.repeat.password" /></label>
              <div class="col-sm-10 ${repeatPasswordDivClass}">
                <form:input path="repeatPassword" class="form-control ${repeatPasswordInputClass}" type="password"/>
              </div>
              <label class="col-sm-2 col-form-label"></label>
              <div class="col-sm-10 has-danger">
                <div class="form-control-feedback"><form:errors path="repeatPassword"/></div>
              </div>
            </div>
          </spring:bind>
          <div class="text-right">
            <form:button class="btn btn-primary margin-bottom-none"><spring:message code="com.item.reservation.tool.registration.button.text"/></form:button>
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