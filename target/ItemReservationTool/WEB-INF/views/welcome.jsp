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
        <h1><spring:message code="com.item.reservation.tool.item.page.title"/></h1>
      </div>
    </div>
  </div>


  <div class="container padding-bottom-3x mb-1">
    <%@include file="includes/messages.jsp"%>
    <c:choose>
      <c:when test="${not empty items}">
        <!-- Shop Toolbar-->
        <div class="shop-toolbar padding-bottom-1x mb-2">
          <div class="column">
            <div class="shop-sorting">
              <label for="sorting">Sort by:</label>
              <select class="form-control" id="sorting">
                <option>Popularity</option>
                <option>Low - High Price</option>
                <option>High - Low Price</option>
                <option>Avarage Rating</option>
                <option>A - Z Order</option>
                <option>Z - A Order</option>
              </select><span class="text-muted">Showing:&nbsp;</span><span>1 - 12 items</span>
            </div>
          </div>
          <div class="column">
            <div class="shop-view"><a class="grid-view active" href="#"><span></span><span></span><span></span></a><a class="list-view" href="#"><span></span><span></span><span></span></a></div>
          </div>
        </div>
        <div class="isotope-grid cols-4 mb-2">
          <div class="gutter-sizer"></div>
          <div class="grid-sizer"></div>
          <c:forEach var="item" items="${items}">
            <div class="grid-item">
              <div class="product-card">
                <c:if test="${not empty item.itemReservations}">
                  <div class="product-badge text-danger"><spring:message code="com.item.reservation.tool.items.reservation"/></div>
                </c:if>
                <a class="product-thumb" href="/itemDetail/${item.uuid}">
                  <img src="${imageBaseDir}/items/${item.imagePaths[0]}" alt="${item.shortDescription}">
                </a>
                <h3 class="product-title"><a href="/itemDetail/${item.uuid}">${item.shortDescription}</a></h3>
                <div class="product-buttons">
                  <security:authorize access="hasAnyRole('ADMIN', 'MODERATOR', 'WORKER', 'VERIFIED')">
                    <c:choose>
                      <c:when test="${not empty item.itemReservations and item.itemReservations[0].userUuid eq userContext.uuid}">
                        <a class="btn btn-outline-primary btn-sm" href="/cancelReservation/${item.uuid}"><spring:message code="com.item.reservation.tool.delete.reservation.cancel.button"/></a>
                      </c:when>
                      <c:otherwise>
                        <button class="btn btn-outline-primary btn-sm js-open-dialog" data-uuid="${item.uuid}" data-title="${item.shortDescription}" ${not empty item.itemReservations ? 'disabled' :''}>
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
          </c:forEach>
        </div>
        <!-- Pagination-->
        <nav class="pagination">
          <div class="column">
            <ul class="pages">
              <li class="active"><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li>...</li>
              <li><a href="#">12</a></li>
            </ul>
          </div>
          <div class="column text-right hidden-xs-down"><a class="btn btn-outline-secondary btn-sm" href="#">Next&nbsp;<i class="icon-arrow-right"></i></a></div>
        </nav>
      </c:when>
      <c:otherwise>
        <div class="alert alert-primary alert-dismissible fade show text-center margin-bottom-1x">
          <span class="alert-close" data-dismiss="alert"></span>
          <i class="icon-camera"></i>&nbsp;&nbsp; <spring:message code="com.item.reservation.tool.item.list.empty"/>
        </div>
      </c:otherwise>
    </c:choose>
  </div>
  <%@include file="includes/footer.jsp" %>
</div>
<%@include file="includes/side_background.jsp"%>
</body>
</html>