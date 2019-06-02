<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page isELIgnored="false" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/bg.css">

<c:if test="${sessionScope.app_lang == null}">
    <c:set var="app_lang" value="en" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.app_lang}" scope="session"/>
<fmt:setBundle basename="messages" scope="session"/>

<div class="flex-fill">
    <div class="container shadow rounded-bottom nav navbar-expand navbar-dark bg-dark" >
        <ul class="nav " >
            <li class="nav-item">
                <c:if test="${sessionScope.user != null}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/action/home"><fmt:message key="l.home.ref"/></a>
                </c:if >
                <c:if test="${sessionScope.user == null}">
                    <a class="nav-link disabled" href="#"><fmt:message key="l.home.ref"/></a>
                </c:if >
            </li>

            <li class="nav-item">
                <c:if test="${sessionScope.user != null}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/action/logout"><fmt:message key="l.logout.ref"/></a>
                </c:if >
                <c:if test="${sessionScope.user == null}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/login.jsp"><fmt:message key="l.login.ref"/></a>
                </c:if >
            </li>
        </ul>

        <ul class="nav ml-auto" >
            <li>
                <a class="nav-link" href="${pageContext.request.contextPath}/action/lang_ua">ua</a>
            </li>
            <li>
                <a class="nav-link" href="${pageContext.request.contextPath}/action/lang_en">en</a>
            </li>
        </ul>
    </div>
</div>
