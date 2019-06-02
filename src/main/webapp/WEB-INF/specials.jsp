<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Taxi-Specials</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp" />

<div class="container bg-white border-dark rounded-lg">

    <c:if test="${null || empty applicationScope.all_specials}">
        <h4><fmt:message key="m.no.specials"/></h4>
    </c:if>

    <c:if test="${not null && not empty applicationScope.all_specials}">
        <div>
            <c:forEach var="special" items="${applicationScope.all_specials}">
                <div class="d-inline-flex p-2">
                    <c:if test="${sessionScope.app_lang == 'ua'}">
                        <div class="container-fluid p-2 border-success  ">
                            <div >
                                <h4>${special.nameUa}</h4>
                            </div>
                            <div>
                                    ${special.descriptionUa}
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.app_lang == 'en'}">
                        <div class="container-fluid p-2 border-success  ">
                            <div >
                                <h4>${special.nameEn}</h4>
                            </div>
                            <div>
                                    ${special.descriptionEn}
                            </div>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>