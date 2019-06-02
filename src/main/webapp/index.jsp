<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Taxi-Hello</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>

<div class="container bg-white border-dark rounded-lg">
    <h4 class="text-center pt-2"><fmt:message key="t.hello"/></h4>
    <p class="text-justify pt-2 pb-2">
        <fmt:message key="m.welcome"/>
    </p>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/action/viewSpecials"><fmt:message key="m.special.offers"/> </a>
</div>

<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>