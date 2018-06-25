<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty errorMsg}">
  <div class="alert alert-danger alert-dismissible fade show text-center margin-bottom-1x">
    <span class="alert-close" data-dismiss="alert"></span>
    <i class="icon-ban"></i>&nbsp;&nbsp; ${errorMsg}
  </div>
</c:if>
<c:if test="${not empty successMsg}">
  <div class="alert alert-success alert-dismissible fade show text-center margin-bottom-1x">
    <span class="alert-close" data-dismiss="alert"></span>
    <i class="icon-help"></i>&nbsp;&nbsp; ${successMsg}
  </div>
</c:if>
<c:if test="${not empty warningMsg}">
  <div class="alert alert-warning alert-dismissible fade show text-center margin-bottom-1x">
    <span class="alert-close" data-dismiss="alert"></span>
    <i class="icon-bell"></i>&nbsp;&nbsp; ${warningMsg}
  </div>
</c:if>
<c:if test="${not empty alertMsg}">
  <div class="alert alert-primary alert-dismissible fade show text-center margin-bottom-1x">
    <span class="alert-close" data-dismiss="alert"></span>
    <i class="icon-camera"></i>&nbsp;&nbsp; ${alertMsg}
  </div>
</c:if>
<c:if test="${not empty infoAlertMsg}">
  <div class="alert alert-info alert-dismissible fade show text-center margin-bottom-1x">
    <span class="alert-close" data-dismiss="alert"></span>
    <i class="icon-layers"></i>&nbsp;&nbsp;${infoAlertMsg}
  </div>
</c:if>
