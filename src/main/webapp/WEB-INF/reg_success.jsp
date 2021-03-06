<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>


<html>
<head>
    <title>Taxi-Registered</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css">

</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>

<div class="container bg-white border-dark rounded-lg">
    <div class="alert-success h4" align="center">
        <fmt:message key="m.user.registred"/>
    </div>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/action/home"><fmt:message key="l.home.ref"/>/a>
</div>

<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>
