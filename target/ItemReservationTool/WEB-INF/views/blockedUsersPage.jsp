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
                <h1><spring:message code="com.item.reservation.tool.users.blocked.title"/></h1>
            </div>
        </div>
    </div>
    <div class="container padding-bottom-3x">
        <div class="row">
            <div class="col-lg-12 col-md-12 order-md-2">
                <c:choose>
                    <c:when test="${not empty blockedUsers}">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th><spring:message code="com.item.reservation.tool.registration.firstName"/></th>
                                    <th><spring:message code="com.item.reservation.tool.registration.lastName"/></th>
                                    <th><spring:message code="com.item.reservation.tool.registration.email"/></th>
                                    <th><spring:message code="com.item.reservation.tool.users.role.field"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${blockedUsers}" var="bUser">
                                   <tr>
                                        <td>${bUser.firstName}</td>
                                        <td>${bUser.lastName}</td>
                                        <td>${bUser.email}</td>
                                       <td>
                                           <c:forEach items="${bUser.userRoles}" var="role">
                                               <p>${role}</p>
                                           </c:forEach>
                                       </td>
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