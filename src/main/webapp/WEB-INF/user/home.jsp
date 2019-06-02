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
    <title>Taxi-Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body class="body-map">
<jsp:include page="/WEB-INF/header.jsp" />

<div class="container bg-white border-dark rounded-lg">
    <nav>
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
            <a class="nav-item nav-link " id="nav-get-ride-tab" data-toggle="tab" href="#nav-get-ride" role="tab"
               aria-controls="nav-get-ride" aria-selected="true">
                <fmt:message key="l.get.ride"/>
            </a>
            <a class="nav-item nav-link " id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab"
               aria-controls="nav-profile" aria-selected="false">
                <fmt:message key="l.profile"/>
            </a>
        </div>
    </nav>

    <c:if test="${requestScope.get_ride_error != null}">
        <div class="alert alert-danger" align="center" role="alert">
            <fmt:message key="${requestScope.get_ride_error}"/>
        </div>
    </c:if>
    <c:if test="${sessionScope.current_ride != null}">
        <div class="alert alert-danger" align="center" role="alert">
            <fmt:message key="e.not.finished.ride"/>
            <a class="btn btn-danger" href="${pageContext.request.contextPath}/action/findRide">
                <fmt:message key="l.continue"/>
            </a>
        </div>
    </c:if>

    <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade " id="nav-get-ride" align="center" role="tabpanel" aria-labelledby="nav-get-ride-tab">

            <div class="row h-75">
                <div class="col-md-4 h-auto">

                    <div class="form-group form-row" align="left">
                        <label class="col-sm-3 col-form-label">
                            <fmt:message key="l.from"/>
                        </label>
                        <input type="text" class="form-control col-sm-9" id="start" placeholder="<fmt:message key="l.start.addr"/>">
                    </div>

                    <div class="form-group form-row" align="left">
                        <label class="col-sm-3 col-form-label">
                            <fmt:message key="l.to"/>
                        </label>
                        <input type="text" class="form-control col-sm-9" id="end" placeholder="<fmt:message key="l.end.addr"/>">
                    </div>

                    <div class="form-group form-row" align="left">
                        <label class="col-sm-3 col-form-label" for="car-type">
                            <fmt:message key="l.car.type"/>
                        </label>
                        <select class="form-control col-sm-5" name="car-type" id="car-type">
                            <option value="SEDAN">
                                <fmt:message key="m.type.sedan"/>
                            </option>
                            <option value="Wagon">
                                <fmt:message key="m.type.wagon"/>
                            </option>
                            <option value="VAN">
                                <fmt:message key="m.type.van"/>
                            </option>
                            <option value="SUV">
                                <fmt:message key="m.type.suv"/>
                            </option>
                        </select>
                        <input  value="<fmt:message key="l.calc.route"/>" onclick="calculateAndDisplayRoute(directionsService, directionsDisplay)"
                                type="button" class="col-sm-4 btn btn-primary">
                    </div>

                    <div class="row pt-4" hidden id="hiddenDetailsCheckout">
                        <div class="col-sm-12" align="left">
                            <p>
                                <fmt:message key="m.route.1"/>
                            </p>
                            <p>
                                <fmt:message key="l.from"/>
                                <span  id="from"></span></p>
                            <p>
                                <fmt:message key="l.to"/>
                                <span  id="to"></span></p>
                        </div>

                        <div class="col-sm-8" align="left">
                            <p>
                                <fmt:message key="l.total.distance"/>
                                <span  id="total-dist"></span>
                                <fmt:message key="l.km"/>

                            </p>
                            <p data-toggle="tooltip" data-placement="bottom" title="<fmt:message key="m.prelim.popup"/>">
                                <fmt:message key="m.prelim.price"/>
                                <span  id="total-cost"></span>
                                <fmt:message key="l.uah"/>

                            </p>
                        </div>

                        <form class="col-sm-4 "  action="${pageContext.request.contextPath}/action/findRide" method="post">
                            <input type='hidden' id='hiddenDistance' name='distance' value='' />
                            <input type='hidden' id='hiddenPrecost' name='precost' value='' />
                            <input type='hidden' id='hiddenRouteStart' name='routeStart' value='' />
                            <input type='hidden' id='hiddenRouteEnd' name='routeEnd' value='' />
                            <input type='hidden' id='hiddenCarType' name='carType' value='' />
                            <p> </p> <%--excellent! :( --%>
                            <button class="col-sm-12 align-bottom btn btn-primary" type="submit" id="checkout-btn" disabled>
                                <fmt:message key="l.continue"/>
                            </button>
                        </form>
                    </div>

                </div>
                <div id="map" class="col-md-8 rounded-lg shadow border-dark" ></div>
            </div>

        </div>

        <div class="tab-pane fade " align="center" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
            <div class="col-md-6 align-content-center">
                <c:if test="${sessionScope.app_lang == 'ua'}">
                    <h4 class="pt-2" align="left">Мій профіль:</h4>
                    <div class="row align-content-center">
                        <div class="col-md-4" align="left">
                            <fmt:message key="l.user.f.name"/>
                        </div>
                        <div class="col-md-8" align="left">
                                ${sessionScope.user.firstNameUa}
                        </div>
                    </div>

                    <div class="row align-self-md-center">
                        <div class="col-md-4" align="left">
                            <fmt:message key="l.user.l.name"/>
                        </div>
                        <div class="col-md-8" align="left">
                                ${sessionScope.user.lastNameUa}
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.app_lang == 'en'}">
                    <h4 class="pt-2" align="left">My profile:</h4>
                    <div class="row align-content-center">
                        <div class="col-md-4" align="left">
                            <fmt:message key="l.user.f.name"/>
                        </div>
                        <div class="col-md-8" align="left">
                                ${sessionScope.user.firstNameEn}
                        </div>
                    </div>

                    <div class="row align-self-md-center">
                        <div class="col-md-4" align="left">
                            <fmt:message key="l.user.l.name"/>
                        </div>
                        <div class="col-md-8" align="left">
                                ${sessionScope.user.lastNameEn}
                        </div>
                    </div>
                </c:if>
                <div class="row align-content-center">
                    <div class="col-md-4" align="left">
                        <fmt:message key="l.user.email"/>
                    </div>
                    <div class="col-md-8" align="left">
                        ${sessionScope.user.email}
                    </div>
                </div>
                <div class="row align-content-center">
                    <div class="col-md-4" align="left">
                        <fmt:message key="l.user.login"/>
                    </div>
                    <div class="col-md-8" align="left">
                        ${sessionScope.user.login}
                    </div>
                </div>
                <div class="row align-content-center">
                    <div class="col-md-4" align="left">
                        <fmt:message key="l.user.money.spent"/>
                    </div>
                    <div class="col-md-8" align="left">
                        ${sessionScope.user.moneySpent}
                        <fmt:message key="l.uah"/>
                    </div>
                </div>
                <div class="row align-content-center">
                    <div class="col-md-4" align="left">
                        <fmt:message key="l.user.rides.count"/>
                    </div>
                    <div class="col-md-8" align="left">
                        ${sessionScope.user.ridesCount}
                    </div>
                </div>
                <div class="row align-content-center">
                    <div class="col-md-4" align="left">
                        <fmt:message key="l.user.personal.discount"/>
                    </div>
                    <div class="col-md-8" align="left">
                        ${sessionScope.user.personalDiscount}
                        <c:out value="%"/>
                    </div>
                </div>
                <div class="row align-content-center">
                    <div class="col-md-4" align="left">
                        <fmt:message key="l.user.additional.discount"/>
                    </div>
                    <div class="col-md-8" align="left">
                        ${sessionScope.user.additionalDiscount}
                        <c:out value="%"/>
                    </div>
                </div>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/action/rideHistory">
                    <fmt:message key="l.viev.rides.history"/>
                </a>


            </div>
        </div>
    </div>

</div>

<script>
    var directionsService;
    var directionsDisplay;
    function initMap() {
        directionsService = new google.maps.DirectionsService;
        directionsDisplay = new google.maps.DirectionsRenderer;
        var map = new google.maps.Map(document.getElementById('map'), {
            draggable: true,
            zoom: 12,
            center: {lat: 50.45, lng: 30.52}
        });
        directionsDisplay.setMap(map);
        directionsDisplay.addListener('directions_changed', function() {
            computeTotalDistance(directionsDisplay.getDirections());
        });
    }

    function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        directionsService.route({
            origin: document.getElementById('start').value,
            destination: document.getElementById('end').value,
            travelMode: 'DRIVING'
        }, function(response, status) {
            if (status === 'OK') {
                directionsDisplay.setDirections(response);
            } else {
                window.alert('Directions request failed due to ' + status);
            }
        });
    }

    function computeTotalDistance(result) {
        var total = 0;
        var myroute = result.routes[0];
        var routeStart = myroute.legs[0].start_address;
        var routeEnd = myroute.legs[myroute.legs.length-1].end_address;

        for (var i = 0; i < myroute.legs.length; i++) {
            total += myroute.legs[i].distance.value;
        }
        total = total / 1000;
        document.getElementById('total-cost').innerHTML = Math.round(total * 9 + 50);
        document.getElementById('hiddenPrecost').value  = total * 9 + 50;
        document.getElementById('total-dist').innerHTML = Math.round(total);
        document.getElementById('from').innerHTML = routeStart;
        document.getElementById('to').innerHTML = routeEnd;
        document.getElementById('hiddenDistance').value = total;
        document.getElementById('hiddenRouteStart').value = routeStart;
        document.getElementById('hiddenRouteEnd').value = routeEnd;
        document.getElementById('hiddenCarType').value = document.getElementById("car-type").value;
        document.getElementById('checkout-btn').disabled = false;
        document.getElementById('hiddenDetailsCheckout').hidden = false;
    }
</script>
<jsp:include page="/WEB-INF/footer.jsp"/>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGBFTE3euKqPB93MhHC67bgevXWj_n1YQ&callback=initMap"></script>

</body>
</html>