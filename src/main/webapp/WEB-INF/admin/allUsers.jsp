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
    <title>Taxi-Users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/buzina-pagination.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp" />

<div class="container bg-white border-dark rounded-lg">

    <c:if test="${null || empty requestScope.all_users}">
        <h4><fmt:message key="m.empty.users"/></h4>
    </c:if>

    <c:if test="${not null && not empty requestScope.all_users}">
        <div class="container">
            <div class="row">
                <div class="col-md-2 border border-dark">
                    <b><fmt:message key="l.user.f.name"/></b>
                </div>
                <div class="col-md-2 border border-dark">
                    <b><fmt:message key="l.user.l.name"/></b>
                </div>
                <div class="col-md-2 border border-dark">
                    <b><fmt:message key="l.user.login"/></b>
                </div>
                <div class="col-md-4 border border-dark">
                    <b><fmt:message key="l.user.email"/></b>
                </div>
                <div class="col-md-2">

                </div>
            </div>
        </div>

        <div id="pagination" class="container ">
            <c:forEach var="user" items="${requestScope.all_users}">
                <div class="row">
                    <c:if test="${user.role.name == 'user' && sessionScope.app_lang == 'en'}">
                        <div class="col-md-2 border border-dark">
                                ${user.firstNameEn}
                        </div>
                        <div class="col-md-2 border border-dark">
                                ${user.lastNameEn}
                        </div>
                        <div class="col-md-2 border border-dark">
                                ${user.login}
                        </div>
                        <div class="col-md-4 border border-dark">
                                ${user.email}
                        </div>
                        <div class="col-md-2">
                            <a href="${pageContext.request.contextPath}/action/viewUserDetails?userid=${user.id}" class="btn btn-primary">
                                <fmt:message key="l.details"/>
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${user.role.name == 'user' && sessionScope.app_lang == 'ua'}">
                        <div class="col-md-2 border border-dark">
                                ${user.firstNameUa}
                        </div>
                        <div class="col-md-2 border border-dark">
                                ${user.lastNameUa}
                        </div>
                        <div class="col-md-2 border border-dark">
                                ${user.login}
                        </div>
                        <div class="col-md-4 border border-dark">
                                ${user.email}
                        </div>
                        <div class="col-md-2">
                            <a href="${pageContext.request.contextPath}/action/viewUserDetails?userid=${user.id}" class="btn btn-primary">
                                <fmt:message key="l.details"/>
                            </a>
                        </div>
                    </c:if>
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
            itemsOnPage: 25,
            prevnext: true,
            prevText: "Prev",
            nextText: "Next"
        });
    });
</script>
</body>
</html>