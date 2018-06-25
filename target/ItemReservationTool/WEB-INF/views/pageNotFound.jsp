<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="includes/header.jsp" %>
<body>
<%@include file="includes/left_side_main_menu.jsp" %>
<%@include file="includes/top_main_menu.jsp" %>

<div class="offcanvas-wrapper">

    <div class="container padding-top-3x padding-bottom-3x mb-1">
        <img class="d-block m-auto" src="${imageBaseDir}/404_art.jpg" style="width: 100%; max-width: 550px;" alt="404">
        <div class="padding-top-1x mt-2 text-center">
            <h3>Page Not Found</h3>
            <p>It seems we can’t find page you are looking for. <a href="/welcome">Go back to Homepage</a><br>Or try using search at the top right corner of the page.</p>
        </div>
    </div>

    <%@include file="includes/footer.jsp" %>
</div>
<%@include file="includes/side_background.jsp"%>
</body>
</html>