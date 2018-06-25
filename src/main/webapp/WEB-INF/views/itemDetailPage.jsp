<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="includes/header.jsp" %>
<body>
<div class="modal fade" id="itemDialog" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"></h4>
                <button class="close js-close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <form:form action="/reserveItem" modelAttribute="reserveItemForm" method="post">
                <div class="modal-body">
                    <form:input id="js-hidden-uuid" path="uuid" type="hidden" value=""/>
                    <div class="col-lg-12 col-md-12 order-md-2">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group row">
                                    <label class="col-2 col-form-label" for="date-input">Date</label>
                                    <div class="col-10">
                                        <form:input path="reservationEnds" class="form-control js-date-val" type="date" id="date-input" value=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-primary btn-sm"><spring:message code="com.item.reservation.tool.item.reserve.button"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<%@include file="includes/left_side_main_menu.jsp" %>
<%@include file="includes/top_main_menu.jsp" %>
<div class="offcanvas-wrapper">
    <div class="page-title">
        <div class="container">
            <div class="column">
                <h1>${item.shortDescription}</h1>
            </div>
        </div>
    </div>
    <div class="container padding-bottom-3x mb-1">
        <div class="row">
            <div class="col-md-6">
                <div class="product-gallery">
                    <c:if test="${not empty item.itemReservations}">
                        <span class="product-badge text-danger"><spring:message code="com.item.reservation.tool.items.reservation"/></span>
                    </c:if>
                    <div class="product-carousel owl-carousel">
                        <c:forEach var="image" items="${item.imagePaths}" varStatus="index">
                            <div data-hash="${index.count}" align="center"><img src="${imageBaseDir}/items/${image}" alt="Item"></div>
                        </c:forEach>
                    </div>
                    <ul class="product-thumbnails">
                        <c:forEach var="image" items="${item.imagePaths}" varStatus="index">
                            <li class="active"><a href="#${index.count}"><img src="${imageBaseDir}/items/${image}" alt="Item"></a></li>
                       </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="col-md-6">
                <div class="padding-top-2x mt-2 hidden-md-up"></div>
                <h2 class="padding-top-1x text-normal">${item.shortDescription}</h2>
                <p>${item.longDescription}</p>
                <security:authorize access="hasAnyRole('ADMIN', 'MODERATOR', 'WORKER')">
                    <c:if test="${not empty item.itemReservations}">
                        <c:set var="user" value="${item.itemReservations[0].user}"/>
                        <hr class="mb-3">
                        <div><spring:message code="com.item.reservation.tool.detail.page.reserved.person"/> <b>${user.firstName} ${user.lastName}</b> ${user.email}</div>
                        <div><spring:message code="com.item.reservation.tool.detail.page.reserved.until"/> <b><fmt:formatDate value="${item.itemReservations[0].reservationEnd}" pattern="yyyy-MM-dd"/></b></div>
                    </c:if>
                </security:authorize>
                <hr class="mb-3">
                <div class="d-flex flex-wrap justify-content-between">
                    <div class="sp-buttons mt-2 mb-2">
                        <security:authorize access="hasAnyRole('ADMIN', 'MODERATOR', 'WORKER', 'VERIFIED')">
                            <c:choose>
                                <c:when test="${not empty item.itemReservations and item.itemReservations[0].userUuid eq userContext.uuid}">
                                    <a class="btn btn-outline-primary" href="/cancelReservation/${item.uuid}"><spring:message code="com.item.reservation.tool.delete.reservation.cancel.button"/></a>
                                </c:when>
                                <c:otherwise>
                                    <div><spring:message code="com.item.reservation.tool.items.contact.person.detail"/>: <b>${item.contactPerson}</b></div>
                                    <button ${not empty item.itemReservations ? 'disabled': ''} data-uuid="${item.uuid}" data-title="${item.shortDescription}" class="btn btn-primary js-open-dialog"><i class="icon-bag"></i>
                                        <c:choose>
                                            <c:when test="${not empty item.itemReservations}">
                                                <spring:message code="com.item.reservation.tool.item.reserve.reserved"/>
                                            </c:when>
                                            <c:otherwise>
                                                <spring:message code="com.item.reservation.tool.item.reserve.button"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </security:authorize>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="includes/footer.jsp" %>
</div>
<%@include file="includes/side_background.jsp"%>
</body>
</html>