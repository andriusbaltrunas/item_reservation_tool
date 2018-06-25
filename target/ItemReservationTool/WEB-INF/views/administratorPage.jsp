<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="includes/header.jsp" %>
<body>
<%@include file="includes/left_side_main_menu.jsp" %>
<%@include file="includes/top_main_menu.jsp" %>
<c:set var="url" value="#"/>
<div class="offcanvas-wrapper">
    <div class="page-title">
        <div class="container">
            <div class="column">
                <h1>
                    <security:authorize access="hasRole('ADMIN')">
                        <c:set var="url" value="/promoteUserToModerator"/>
                        <spring:message code="com.item.reservation.tool.users.promote.to.moderator"/>
                    </security:authorize>
                    <security:authorize access="hasRole('MODERATOR')">
                        <c:set var="url" value="/promoteUserToWorker"/>
                        <spring:message code="com.item.reservation.tool.users.promote.to.worker"/>
                    </security:authorize>
                </h1>
            </div>
        </div>
    </div>
    <div class="container padding-bottom-3x">
        <%@include file="includes/messages.jsp" %>
        <div class="row">
            <div class="col-lg-12 col-md-12 order-md-2">
                <c:choose>
                    <c:when test="${not empty users}">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th><spring:message code="com.item.reservation.tool.registration.firstName"/></th>
                                    <th><spring:message code="com.item.reservation.tool.registration.lastName"/></th>
                                    <th><spring:message code="com.item.reservation.tool.registration.email"/></th>
                                    <th><spring:message code="com.item.reservation.tool.users.role.field"/></th>
                                    <th><spring:message code="com.item.reservation.tool.users.action.field"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${users}" var="user">
                                    <tr>
                                        <td>${user.firstName}</td>
                                        <td>${user.lastName}</td>
                                        <td>${user.email}</td>
                                        <td>
                                            <c:forEach items="${user.userRoles}" var="role">
                                                <p>${role}</p>
                                            </c:forEach>
                                        </td>
                                        <td><a class="btn btn-outline-primary" href="${url}/${user.uuid}"><spring:message code="com.item.reservation.tool.users.promote.button"/></a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-info alert-dismissible fade show text-center margin-bottom-1x">
                            <span class="alert-close" data-dismiss="alert"></span>
                            <i class="icon-layers"></i>&nbsp;&nbsp;<spring:message code="com.item.reservation.tool.users.empty.list"/>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <%@include file="includes/footer.jsp" %>
</div>
<%@include file="includes/side_background.jsp"%>
</body>
</html>