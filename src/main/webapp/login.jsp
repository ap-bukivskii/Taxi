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
    <title>Taxi-Login-Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp" />
<div class="container bg-white border-dark rounded-lg">

    <nav>
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
            <a class="nav-item nav-link " id="nav-login-tab" data-toggle="tab" href="#nav-login" role="tab" aria-controls="nav-login" aria-selected="true"><fmt:message key="l.login"/></a>
            <a class="nav-item nav-link " id="nav-register-tab" data-toggle="tab" href="#nav-register" role="tab" aria-controls="nav-register" aria-selected="false"><fmt:message key="l.register"/></a>
        </div>
    </nav>

    <c:if test="${sessionScope.user != null}">
        <div class="alert alert-success" align="center" role="alert"><fmt:message key="m.already.logged"/></div>
    </c:if >
    <c:if test="${requestScope.registration_error != null}">
        <div class="alert alert-danger" align="center" role="alert"> <fmt:message key="${requestScope.registration_error}"/> </div>
    </c:if>
    <c:if test="${requestScope.login_error != null}">
        <div class="alert alert-danger" align="center" role="alert"> <fmt:message key="${requestScope.login_error}"/> </div>
    </c:if>

    <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade " id="nav-login" align="center" role="tabpanel" aria-labelledby="nav-login-tab">
            <c:if test="${sessionScope.user == null}">
                <form class="col-md-12" method="post" action="${pageContext.request.contextPath}/action/login_do">
                    <h4 class="card-title"><fmt:message key="t.enter.login.data"/></h4>

                    <div class="form-group form-row w-50" align="left">
                        <label class="col-sm-4 col-form-label"><fmt:message key="l.user.login"/></label>
                        <input type="text" class="form-control col-sm-8" name="login" placeholder="<fmt:message key="l.login"/>" required
                               pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.login"/></fmt:bundle>"
                        >
                    </div>
                    <div class="form-group form-row w-50" align="left">
                        <label class="col-sm-4 col-form-label"><fmt:message key="l.user.password"/></label>
                        <input type="password" class="form-control col-sm-8" name="password" placeholder="<fmt:message key="l.password"/>" required
                               pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.pass"/></fmt:bundle>"
                        >
                    </div>

                    <button type="submit" class="btn btn-primary"><fmt:message key="l.submit"/></button>

                </form>
            </c:if >
        </div>

        <div class="tab-pane fade " align="center" id="nav-register" role="tabpanel" aria-labelledby="nav-register-tab">
            <c:if test="${sessionScope.user == null}">
                <form action="${pageContext.request.contextPath}/action/register_do" method="post" class="col-md-12" >
                    <h4 class="card-title"><fmt:message key="t.register.user"/></h4>

                    <div class="form-group w-50 form-row" align="left">
                        <label class="col-sm-4 col-form-label"><fmt:message key="l.user.login"/></label>
                        <input type="text"  class="form-control col-sm-8" name="login" placeholder="<fmt:message key="l.login"/>" required
                               pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.login"/></fmt:bundle>"
                        >
                    </div>

                    <div class="form-group w-50 form-row" align="left">
                        <label class="col-sm-4 col-form-label"><fmt:message key="l.user.password"/></label>
                        <input type="password" class="form-control col-sm-8" name="password"  placeholder="<fmt:message key="l.password"/>" required
                               pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.pass"/></fmt:bundle>"
                        >
                    </div>

                    <div class="form-group w-50 form-row" align="left">
                        <label class="col-sm-4 col-form-label"><fmt:message key="l.user.email"/></label>
                        <input type="email" class="form-control col-sm-8" name="email" placeholder="<fmt:message key="l.email"/>" required
                               pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.email"/></fmt:bundle>"
                        >
                    </div>

                    <div class="form-group w-50 form-row" align="left">
                        <label class="col-sm-4 col-form-label"><fmt:message key="l.user.f.name.en"/></label>
                        <input type="text" class="form-control col-sm-8" name="f-name-en" placeholder="First name" required
                               pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.f.name.en"/></fmt:bundle>"
                        >
                    </div>


                    <div class="form-group w-50 form-row" align="left">
                        <label class="col-sm-4 col-form-label"><fmt:message key="l.user.f.name.ua"/></label>
                        <input type="text" class="form-control col-sm-8" name="f-name-ua" placeholder="Ім'я" required
                               pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.f.name.ua"/></fmt:bundle>"
                        >
                    </div>


                    <div class="form-group w-50 form-row" align="left">
                        <label class="col-sm-4 col-form-label"><fmt:message key="l.user.l.name.en"/></label>
                        <input type="text" class="form-control col-sm-8" name="l-name-en" placeholder="Last name" required
                               pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.l.name.en"/></fmt:bundle>"
                        >
                    </div>

                    <div class="form-group w-50 form-row" align="left">
                        <label class="col-sm-4 col-form-label"><fmt:message key="l.user.l.name.ua"/></label>
                        <input type="text" class="form-control col-sm-8" name="l-name-ua" placeholder="Прізвище" required
                               pattern="<fmt:bundle basename="regexps"><fmt:message key="regexp.l.name.ua"/></fmt:bundle>"
                        >
                    </div>

                    <button type="submit" class="btn btn-primary"><fmt:message key="l.submit"/></button>
                </form>
            </c:if>
        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>
