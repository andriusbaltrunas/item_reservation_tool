<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--HERE IS MOBILE MENU-->
<div class="offcanvas-container" id="mobile-menu">
  <a class="account-link" href="/changePassword">
    <div class="user-ava">
      <img src="${imageBaseDir}/account/defaultavatar.jpg" alt="${userContext.firstName} ${userContext.lastName}">
    </div>
    <div class="user-info">
      <h6 class="user-name">${userContext.firstName} ${userContext.lastName}</h6>
    </div>
  </a>
  <a class="account-link" href="/welcome">
    <div class="user-info">
      <h6 class="user-name"><spring:message code="com.item.reservation.tool.item.page"/></h6>
    </div>
  </a>
  <security:authorize access="hasAnyRole('ADMIN', 'MODERATOR')">
    <a class="account-link" href="/admin/administrateUser">
      <div class="user-info">
        <h6 class="user-name"><spring:message code="com.item.reservation.tool.item.administrator.page"/></h6>
      </div>
    </a>
  </security:authorize>
  <security:authorize access="hasAnyRole('ADMIN', 'MODERATOR', 'WORKER')">
    <a class="account-link" href="/createItem">
      <div class="user-info">
        <h6 class="user-name"><spring:message code="com.item.reservation.tool.item.create.new.page"/></h6>
      </div>
    </a>
  </security:authorize>
  <security:authorize access="hasAnyRole('ADMIN', 'MODERATOR', 'WORKER', 'VERIFIED')">
    <a class="account-link" href="/reservedItem">
      <div class="user-info">
        <h6 class="user-name"><spring:message code="com.item.reservation.tool.users.reserved.items"/></h6>
      </div>
    </a>
    <a class="account-link" href="/blockedUsers">
      <div class="user-info">
        <h6 class="user-name"><spring:message code="com.item.reservation.tool.item.blocked.users"/></h6>
      </div>
    </a>
  </security:authorize>
  <a class="account-link" href="/logout">
      <div class="user-info">
        <i class="icon-unlock"></i><spring:message code="com.item.reservation.tool.item.logout"/>
      </div>
  </a>
</div>
<header class="navbar navbar-sticky">
  <form class="site-search" method="get">
    <input type="text" name="site_search" placeholder="Type to search...">
    <div class="search-tools"><span class="clear-search">Clear</span><span class="close-search"><i class="icon-cross"></i></span></div>
  </form>
  <div class="site-branding">
    <div class="inner">
      <a class="offcanvas-toggle cats-toggle" href="#item-menu" data-toggle="offcanvas"></a>
      <a class="offcanvas-toggle menu-toggle" href="#mobile-menu" data-toggle="offcanvas"></a>
      <a class="site-logo" href="#"><img src="${imageBaseDir}/logo/logo.png" alt="Unishop"></a>
    </div>
  </div>

  <%@include file="toolbar.jsp"%>
</header>