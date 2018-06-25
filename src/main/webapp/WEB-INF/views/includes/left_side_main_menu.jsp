<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="offcanvas-container" id="item-menu">
  <div class="offcanvas-header">
    <h3 class="offcanvas-title"><spring:message code="com.item.reservation.tool.item.menu"/></h3>
  </div>
  <nav class="offcanvas-menu">
    <ul class="menu">
      <li><span><a href="/welcome"><spring:message code="com.item.reservation.tool.item.page"/></a></span></li>
      <security:authorize access="hasAnyRole('ADMIN', 'MODERATOR')">
        <li><span><a href="/admin/administrateUser"><spring:message code="com.item.reservation.tool.item.administrator.page"/></a></span></li>
      </security:authorize>
      <security:authorize access="hasAnyRole('ADMIN', 'MODERATOR', 'WORKER')">
        <li><span><a href="/createItem"><spring:message code="com.item.reservation.tool.item.create.new.page"/></a></span></li>
      </security:authorize>
      <security:authorize access="hasAnyRole('ADMIN', 'MODERATOR', 'WORKER', 'VERIFIED')">
        <li><span><a href="/reservedItem"><spring:message code="com.item.reservation.tool.users.reserved.items"/></a></span></li>
        <li><span><a href="/blockedUsers"><spring:message code="com.item.reservation.tool.item.blocked.users"/></a></span></li>
      </security:authorize>
    </ul>
  </nav>
</div>