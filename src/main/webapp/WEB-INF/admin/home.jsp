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
            <a class="nav-item nav-link " id="nav-add-user-tab" data-toggle="tab" href="#nav-add-user" role="tab"
               aria-controls="nav-get-ride" aria-selected="true">
                <fmt:message key="l.add.user"/>
            </a>
            <a class="nav-item nav-link " id="nav-add-driver-tab" data-toggle="tab" href="#nav-add-driver" role="tab"
               aria-controls="nav-profile" aria-selected="false">
                <fmt:message key="l.add.driver"/>
            </a>
            <a class="nav-item nav-link btn btn-primary border border-white" id="nav-view-users-tab"
               href="${pageContext.request.contextPath}/action/viewAllUsers"

               aria-controls="nav-profile" aria-selected="false">
                <fmt:message key="l.view.users"/>
            </a>
            <a class="nav-item btn nav-link btn-primary border border-white" id="nav-view-drivers-tab"
               href="${pageContext.request.contextPath}/action/viewAllDrivers"
               aria-controls="nav-profile" aria-selected="false">
                <fmt:message key="l.view.drivers"/>
            </a>
        </div>
    </nav>

    <c:if test="${requestScope.registration_error != null}">
        <div class="alert alert-danger" align="center" role="alert"> <fmt:message key="${requestScope.registration_error}"/> </div>
    </c:if>

    <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade " id="nav-add-user" align="center" role="tabpanel" aria-labelledby="nav-add-user-tab">
            <form action="${pageContext.request.contextPath}/action/admin_register_user" method="post" class="col-md-12" >
                <h4 class="card-title"><fmt:message key="m.register.new.user"/></h4>

                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label" for="user-role">
                        <fmt:message key="l.user.role"/>
                    </label>
                    <select class="form-control col-sm-8" name="user-role" id="user-role">
                        <option value="USER">
                            <fmt:message key="m.role.user"/>
                        </option>
                        <option value="ADMIN">
                            <fmt:message key="m.role.admin"/>
                        </option>
                    </select>
                </div>


                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label"><fmt:message key="l.user.login"/></label>
                    <input type="text"  class="form-control col-sm-8" name="login"
                           placeholder="<fmt:message key="l.login"/>" required
                           pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.login"/></fmt:bundle>"
                    >
                </div>

                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label"><fmt:message key="l.user.password"/></label>
                    <input type="password" class="form-control col-sm-8" name="password"
                           placeholder="<fmt:message key="l.password"/>" required
                           pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.pass"/></fmt:bundle>"
                    >
                </div>

                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label"><fmt:message key="l.user.email"/></label>
                    <input type="email" class="form-control col-sm-8" name="email"
                           placeholder="<fmt:message key="l.email"/>" required
                           pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.email"/></fmt:bundle>"
                    >
                </div>

                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label"><fmt:message key="l.user.f.name.en"/></label>
                    <input type="text" class="form-control col-sm-8" name="f-name-en"
                           placeholder="First name" required
                           pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.f.name.en"/></fmt:bundle>"
                    >
                </div>


                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label"><fmt:message key="l.user.f.name.ua"/></label>
                    <input type="text" class="form-control col-sm-8" name="f-name-ua"
                           placeholder="Ім'я" required
                           pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.f.name.ua"/></fmt:bundle>"
                    >
                </div>


                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label"><fmt:message key="l.user.l.name.en"/></label>
                    <input type="text" class="form-control col-sm-8" name="l-name-en"
                           placeholder="Last name" required
                           pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.l.name.en"/></fmt:bundle>"
                    >
                </div>

                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label"><fmt:message key="l.user.l.name.ua"/></label>
                    <input type="text" class="form-control col-sm-8" name="l-name-ua"
                           placeholder="Прізвище" required
                           pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.l.name.ua"/></fmt:bundle>"
                    >
                </div>

                <button type="submit" class="btn btn-primary"><fmt:message key="l.submit"/></button>
            </form>
        </div>

        <div class="tab-pane fade " align="center" id="nav-add-driver" role="tabpanel" aria-labelledby="nav-add-driver-tab">
            <form action="${pageContext.request.contextPath}/action/admin_register_driver" method="post" class="col-md-12" >
                <h4 class="card-title"><fmt:message key="m.register.new.driver"/></h4>

                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label"><fmt:message key="l.user.f.name.en"/></label>
                    <input type="text" class="form-control col-sm-8" name="d-f-name-en" placeholder="First name" required
                           pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.f.name.en"/></fmt:bundle>"
                    >
                </div>


                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label"><fmt:message key="l.user.f.name.ua"/></label>
                    <input type="text" class="form-control col-sm-8" name="d-f-name-ua" placeholder="Ім'я" required
                           pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.f.name.ua"/></fmt:bundle>"
                    >
                </div>


                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label"><fmt:message key="l.user.l.name.en"/></label>
                    <input type="text" class="form-control col-sm-8" name="d-l-name-en" placeholder="Last name" required
                           pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.l.name.en"/></fmt:bundle>"
                    >
                </div>

                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label"><fmt:message key="l.user.l.name.ua"/></label>
                    <input type="text" class="form-control col-sm-8" name="d-l-name-ua" placeholder="Прізвище" required
                           pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.l.name.ua"/></fmt:bundle>"
                    >
                </div>

                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label"><fmt:message key="l.user.email"/></label>
                    <input type="email" class="form-control col-sm-8" name="d-email"
                           placeholder="<fmt:message key="l.email"/>" required
                           pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.email"/></fmt:bundle>"
                    >
                </div>

                <div class="form-group w-50 form-row" align="left">
                    <label class="col-sm-4 col-form-label" for="driver-car-type">
                        <fmt:message key="l.car.type"/>
                    </label>
                    <select class="form-control col-sm-8" name="car-type" id="driver-car-type">
                        <option value="SEDAN">
                            <fmt:message key="m.type.sedan"/>
                        </option>
                        <option value="WAGON">
                            <fmt:message key="m.type.wagon"/>
                        </option>
                        <option value="VAN">
                            <fmt:message key="m.type.van"/>
                        </option>
                        <option value="SUV">
                            <fmt:message key="m.type.suv"/>
                        </option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary"><fmt:message key="l.submit"/></button>
            </form>



        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>