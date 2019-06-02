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
    <title>Taxi-Confirm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp" />

<div class="container bg-white border-dark rounded-lg">

    <div class="d-flex flex-row justify-content-center">
        <h4><fmt:message key="m.confirm.ride"/></h4>
    </div>
    <div class="d-flex flex-row justify-content-center">
        <fmt:message key="m.confirm.1"/>
    </div>
    <div class="d-flex flex-row justify-content-center">
        <div class="p-2 "> <fmt:message key="l.from"/></div>
        <div class="p-2">${sessionScope.current_ride.addressFrom}</div>
    </div>
    <div class="d-flex flex-row justify-content-center">
        <div class="p-2"><fmt:message key="l.to"/></div>
        <div class="p-2">${sessionScope.current_ride.addressTo}</div>
    </div>
    <div class="d-flex flex-row justify-content-center">
        <div class="p-2"><fmt:message key="l.total.distance"/></div>
        <div class="p-2">
            ${sessionScope.current_ride.distance}
            <fmt:message key="l.km"/>
        </div>
    </div>
    <div class="d-flex flex-row justify-content-center">
        <div class="p-2"><fmt:message key="l.car.type"/></div>
        <div class="p-2"><fmt:message key="${sessionScope.current_ride.driver.carType.message}"/></div>
    </div>


    <div class="d-flex flex-row justify-content-center">
        <div class="p-2">
            <c:if test="${sessionScope.app_lang == 'en'}">
                <c:out value="Your driver, "/>
                <b>${sessionScope.current_ride.driver.firstNameEn}
                        ${sessionScope.current_ride.driver.lastNameEn}</b>
            </c:if>
            <c:if test="${sessionScope.app_lang == 'ua'}">
                <c:out value="Вашводій, "/>
                <b>${sessionScope.current_ride.driver.firstNameUa}
                        ${sessionScope.current_ride.driver.lastNameUa}</b>
            </c:if>
        </div>
    </div>
    <div class="d-flex flex-row justify-content-center">
        <div class="p-2">
            <fmt:message key="m.wait.1"/>
            <c:out value="${sessionScope.time_to_wait}"/>
            <fmt:message key="m.minutes"/>
        </div>
    </div>
    <div class="d-flex flex-row justify-content-center">
        <div class="p-2">
            <fmt:message key="m.discount"/>
        </div>
        <div class="p-2">
            ${sessionScope.current_ride.user.personalDiscount+sessionScope.current_ride.user.additionalDiscount}
            <c:out value="%"/>
        </div>
    </div>

    <c:if test="${not empty sessionScope.current_ride.specials}">
        <div class="d-flex flex-row justify-content-center">
            <div class="p-2">
                <fmt:message key="m.specials"/>
            </div>

        </div>
        <c:if test="${sessionScope.app_lang == 'en'}">
            <c:forEach items="${sessionScope.current_ride.specials}" var="special">
                <div class="d-flex flex-row justify-content-center">
                    <div class="p-2">
                        <c:out value="${special.nameEn}"/>
                    </div>
                </div>
                <div class="d-flex flex-row justify-content-center">
                    <div class="p-2">
                        <c:out value="${special.descriptionEn}"/>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${sessionScope.app_lang == 'ua'}">
            <c:forEach items="${sessionScope.current_ride.specials}" var="special">
                <div class="d-flex flex-row justify-content-center">
                    <div class="p-2">
                        <c:out value="${special.nameUa}"/>
                    </div>
                </div>
                <div class="d-flex flex-row justify-content-center">
                    <div class="p-2">
                        <c:out value="${special.descriptionUa}"/>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </c:if>

    <div class="d-flex flex-row justify-content-center">
        <div class="p-2">
            <fmt:message key="m.total.price"/>
        </div>
        <div class="p-2">
            ${sessionScope.current_ride.cost}
            <fmt:message key="l.uah"/>
        </div>
    </div>
    <div class="d-flex flex-row justify-content-center">
        <form class="p-2 "  action="${pageContext.request.contextPath}/action/cancellRide" method="post">
            <button class="btn btn-danger" type="submit" id="cancell-btn" ><fmt:message key="l.cancell"/></button>
        </form>
        <form class="p-2 "  action="${pageContext.request.contextPath}/action/confirmRide" method="post">
            <button class="btn btn-success" type="submit" id="proceed-btn" ><fmt:message key="l.confirm"/></button>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>