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
                <h1><spring:message code="com.item.reservation.tool.create.item.title"/></h1>
            </div>
        </div>
    </div>
    <div class="container padding-bottom-3x mb-2">
        <div class="row justify-content-center">
            <div class="col-lg-12 col-md-10">
                <%@include file="includes/messages.jsp" %>
                <p><spring:message code="com.item.reservation.tool.change.password.info"/></p>
                <form:form class="card mt-4" action="/createItem" modelAttribute="createItemForm" method="POST" enctype="multipart/form-data">
                    <div class="card-body">
                        <spring:bind path="shortDescription">
                            <c:choose>
                                <c:when test="${!status.error && status.value ne null}">
                                    <c:set value="has-success" var="shortDescriptionDivClass"/>
                                    <c:set value="form-control-success" var="shortDescriptionInputClass"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set value="${status.error ? 'has-danger' : ''}" var="shortDescriptionDivClass"/>
                                    <c:set value="${status.error ? 'form-control-danger' : ''}" var="shortDescriptionInputClass"/>
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">
                                    <spring:message code="com.item.reservation.tool.create.item.short.description"/>
                                </label>
                                <div class="col-sm-10 ${shortDescriptionDivClass}">
                                    <form:input path="shortDescription" class="form-control ${shortDescriptionInputClass}" type="text" />
                                </div>
                                <label class="col-sm-2 col-form-label"></label>
                                <div class="col-sm-10 has-danger">
                                    <div class="form-control-feedback"><form:errors path="shortDescription"/></div>
                                </div>
                            </div>
                        </spring:bind>


                        <spring:bind path="contactPerson">
                            <c:choose>
                                <c:when test="${!status.error && status.value ne null}">
                                    <c:set value="has-success" var="contactPersonDivClass"/>
                                    <c:set value="form-control-success" var="contactPersonInputClass"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set value="${status.error ? 'has-danger' : ''}" var="contactPersonDivClass"/>
                                    <c:set value="${status.error ? 'form-control-danger' : ''}" var="contactPersonInputClass"/>
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">
                                    <spring:message code="com.item.reservation.tool.create.item.contact.person"/>
                                </label>
                                <div class="col-sm-10 ${contactPersonDivClass}">
                                    <form:input path="contactPerson" class="form-control ${contactPersonInputClass}" type="text" />
                                </div>
                                <label class="col-sm-2 col-form-label"></label>
                                <div class="col-sm-10 has-danger">
                                    <div class="form-control-feedback"><form:errors path="contactPerson"/></div>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="longDescription">
                            <c:choose>
                                <c:when test="${!status.error && status.value ne null}">
                                    <c:set value="has-success" var="longDescriptionDivClass"/>
                                    <c:set value="form-control-success" var="longDescriptionInputClass"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set value="${status.error ? 'has-danger' : ''}" var="longDescriptionDivClass"/>
                                    <c:set value="${status.error ? 'form-control-danger' : ''}" var="longDescriptionInputClass"/>
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">
                                    <spring:message code="com.item.reservation.tool.create.item.long.description"/>
                                </label>
                                <div class="col-sm-10 ${longDescriptionDivClass}">
                                    <form:textarea path="longDescription" rows="5" class="form-control ${longDescriptionInputClass}" type="text" />
                                </div>
                                <label class="col-sm-2 col-form-label"></label>
                                <div class="col-sm-10 has-danger">
                                    <div class="form-control-feedback"><form:errors path="longDescription"/></div>
                                </div>
                            </div>
                        </spring:bind>
                        <div class="form-group row">
                            <label class="col-2 col-form-label" for="file-input"><spring:message code="com.item.reservation.tool.create.item.image"/></label>
                            <div class="col-10">
                                <div class="custom-file">
                                    <form:input path="images" class="custom-file-input" type="file" id="file-input"/>
                                    <span class="custom-file-control"></span>
                                </div>
                                <label class="col-sm-2 col-form-label"></label>
                                <div class="col-sm-10 has-danger">
                                    <div class="form-control-feedback"><form:errors path="images"/></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label" for="file-input"><spring:message code="com.item.reservation.tool.create.item.image"/></label>
                            <div class="col-10">
                                <div class="custom-file">
                                    <form:input path="images" class="custom-file-input" type="file" id="file-input"/>
                                    <span class="custom-file-control"></span>
                                </div>
                                <label class="col-sm-2 col-form-label"></label>
                                <div class="col-sm-10 has-danger">
                                    <div class="form-control-feedback"><form:errors path="images"/></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label" for="file-input"><spring:message code="com.item.reservation.tool.create.item.image"/></label>
                            <div class="col-10">
                                <div class="custom-file">
                                    <form:input path="images" class="custom-file-input" type="file" id="file-input"/>
                                    <span class="custom-file-control"></span>
                                </div>
                                <label class="col-sm-2 col-form-label"></label>
                                <div class="col-sm-10 has-danger">
                                    <div class="form-control-feedback"><form:errors path="images"/></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label" for="file-input"><spring:message code="com.item.reservation.tool.create.item.image"/></label>
                            <div class="col-10">
                                <div class="custom-file">
                                    <form:input path="images" class="custom-file-input" type="file" id="file-input"/>
                                    <span class="custom-file-control"></span>
                                </div>
                                <label class="col-sm-2 col-form-label"></label>
                                <div class="col-sm-10 has-danger">
                                    <div class="form-control-feedback"><form:errors path="images"/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="text-sm-right">
                        <form:button class="btn btn-primary"><spring:message code="com.item.reservation.tool.create.item.button"/> </form:button>
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