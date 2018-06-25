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
                <h1><spring:message code="com.item.reservation.tool.item.user.reservation.title"/></h1>
            </div>
        </div>
    </div>
    <div class="container padding-bottom-3x mb-1">
        <%@include file="includes/messages.jsp"%>
        <div class="isotope-grid cols-4 mb-2">
            <div class="gutter-sizer"></div>
            <div class="grid-sizer"></div>
            <c:forEach var="item" items="${items}">
                <div class="grid-item">
                    <div class="product-card">
                        <div class="product-badge text-danger"><spring:message code="com.item.reservation.tool.items.reservation"/></div>
                        <a class="product-thumb" href="/itemDetail/${item.uuid}">
                            <img src="${imageBaseDir}/items/${item.imagePaths[0]}" alt="${item.shortDescription}">
                        </a>
                        <h3 class="product-title"><a href="/itemDetail/${item.uuid}">${item.shortDescription}</a></h3>
                        <div class="product-buttons">
                            <a class="btn btn-outline-primary btn-sm" href="/cancelReservation/${item.uuid}"><spring:message code="com.item.reservation.tool.delete.reservation.cancel.button"/></a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <%@include file="includes/footer.jsp" %>
</div>
<%@include file="includes/side_background.jsp"%>
</body>
</html>