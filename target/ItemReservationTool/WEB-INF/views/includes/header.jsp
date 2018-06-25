<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib_libraries.jsp"%>
<spring:url value="/resources/css" var="cssBaseDir"/>
<spring:url value="/resources/js" var="jsBaseDir"/>
<spring:url value="/resources/images" var="imageBaseDir"/>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta charset="utf-8">
    <title>Item reservation tool</title>
    <!-- SEO Meta Tags-->
    <meta name="description" content="Item reservation tool">
    <meta name="keywords" content="Item reservation tool">
    <!-- Mobile Specific Meta Tag-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- Favicon and Apple Icons-->
    <link rel="icon" type="image/x-icon" href="favicon.ico">
    <link rel="icon" type="image/png" href="favicon.png">
    <link rel="apple-touch-icon" href="touch-icon-iphone.png">
    <link rel="apple-touch-icon" sizes="152x152" href="touch-icon-ipad.png">
    <link rel="apple-touch-icon" sizes="180x180" href="touch-icon-iphone-retina.png">
    <link rel="apple-touch-icon" sizes="167x167" href="touch-icon-ipad-retina.png">
    <!-- Vendor Styles including: Bootstrap, Font Icons, Plugins, etc.-->
    <link rel="stylesheet" media="screen" href="${cssBaseDir}/template/vendor.min.css">
    <!-- Main Template Styles-->
    <link id="mainStyles" rel="stylesheet" media="screen" href="${cssBaseDir}/template/styles.min.css">
    <!-- Modernizr-->
    <script src="${jsBaseDir}/template/modernizr.min.js"></script>

<%--
    <link rel="stylesheet" href="${cssBaseDir}/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${cssBaseDir}/bootstrap/bootstrap.css">--%>


</head>