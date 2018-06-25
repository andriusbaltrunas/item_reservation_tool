<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="toolbar">
  <div class="inner">
    <div class="tools">
      <div class="search"><i class="icon-search"></i></div>
      <div class="account"><a href="#"></a><i class="icon-head"></i>
        <ul class="toolbar-dropdown">
          <li class="sub-menu-user">
            <div class="user-ava">
              <img src="${imageBaseDir}/account/defaultavatar.jpg" alt="${userContext.firstName} ${userContext.lastName}">
            </div>
            <div class="user-info">
              <h6 class="user-name">${userContext.firstName} ${userContext.lastName}</h6>
            </div>
          </li>
          <li class="sub-menu-separator"></li>
          <li><a href="/changePassword"><spring:message code="com.item.reservation.tool.users.change.password"/></a></li>
        </ul>
      </div>
      <div class="account">
        <a href="/logout" title="Logout"></a>
        <i class="icon-unlock"></i>
      </div>
    </div>
  </div>
</div>