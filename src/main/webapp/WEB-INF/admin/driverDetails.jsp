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
    <title>Taxi-Driver-details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp" />

<div class="container bg-white border-dark rounded-lg">
    <c:if test="${null || empty sessionScope.driver_info}">
        <h4><fmt:message key="m.error.details"/></h4>
    </c:if>

    <c:if test="${not null && not empty sessionScope.driver_info && sessionScope.app_lang == 'en'}">
        <div class="container">
            <div class="row">
                <div class="col border border-dark">
                    <b><fmt:message key="l.user.f.name"/></b><br>
                        ${sessionScope.driver_info.firstNameEn}
                </div>
                <div class="col border border-dark">
                    <b><fmt:message key="l.user.l.name"/></b><br>
                        ${sessionScope.driver_info.lastNameEn}
                </div>
                <div class="col border border-dark">
                    <b><fmt:message key="l.car.type"/></b>
                    <fmt:message key="${sessionScope.driver_info.carType.message}"/>
                </div>
                <div class="col-md-4 border border-dark">
                    <b><fmt:message key="l.user.email"/></b>
                        ${sessionScope.driver_info.email}
                </div>
                <div class="col border border-dark">
                    <b><fmt:message key="l.user.rides.count"/></b><br>
                        ${sessionScope.driver_info.ridesCount}
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${not null && not empty sessionScope.driver_info && sessionScope.app_lang == 'ua'}">
        <div class="container">
            <div class="row">
                <div class="col border border-dark">
                    <b><fmt:message key="l.user.f.name"/></b><br>
                        ${sessionScope.driver_info.firstNameUa}
                </div>
                <div class="col border border-dark">
                    <b><fmt:message key="l.user.l.name"/></b><br>
                        ${sessionScope.driver_info.lastNameUa}
                </div>
                <div class="col border border-dark">
                    <b><fmt:message key="l.car.type"/></b>
                    <fmt:message key="${sessionScope.driver_info.carType.message}"/>
                </div>
                <div class="col-md-4 border border-dark">
                    <b><fmt:message key="l.user.email"/></b>
                        ${sessionScope.driver_info.email}
                </div>
                <div class="col border border-dark">
                    <b><fmt:message key="l.user.rides.count"/></b><br>
                        ${sessionScope.driver_info.ridesCount}
                </div>
            </div>
        </div>
    </c:if>


    <c:if test="${null || empty sessionScope.driver_info.ridesHistory}">
        <h4><fmt:message key="m.empty.driver.rides"/></h4>
    </c:if>

    <c:if test="${not null && not empty sessionScope.driver_info.ridesHistory}">
        <div id="pagination" class="container ">
            <c:forEach var="ride" items="${sessionScope.driver_info.ridesHistory}">
                <div class="d-inline-flex p-2">
                    <div class="container-fluid p-2 border-success  ">
                        <c:if test="${sessionScope.app_lang == 'en'}">
                            <div >
                                <h4>${ride.dateTime}</h4>
                            </div>
                            <div >
                                Driver had a ride with ${ride.user.firstNameEn} ${ride.user.lastNameEn}
                            </div>
                            <div >
                                From ${ride.addressFrom}
                            </div>
                            <div >
                                To ${ride.addressTo}
                            </div>
                            <div >
                                Totalling: ${ride.distance} km, ${ride.cost} uah
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.app_lang == 'ua'}">
                            <div >
                                <h4>${ride.dateTime}</h4>
                            </div>
                            <div >
                                Водій здійснив поїзку. Клієнт: ${ride.user.firstNameEn} ${ride.user.lastNameEn}
                            </div>
                            <div >
                                Від ${ride.addressFrom}
                            </div>
                            <div >
                                До ${ride.addressTo}
                            </div>
                            <div >
                                Загалом: ${ride.distance} км, ${ride.cost} грн
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

</div>

<jsp:include page="/WEB-INF/footer.jsp"/>

<script src="${pageContext.request.contextPath}/res/js/buzina-pagination.min.js"></script>

<script>
    $(document).ready(function(){
        $('#pagination').buzinaPagination({
            itemsOnPage: 9,
            prevnext: true,
            prevText: "Prev",
            nextText: "Next"
        });
    });
</script>

</body>
</html>