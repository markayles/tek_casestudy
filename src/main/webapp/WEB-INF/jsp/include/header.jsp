<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>PlumbManager</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/pub/css/main.css">
</head>
<body>

<%--<nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 fixed-top">--%>
<%--    <div class="container">--%>
<%--        <a href="/" class="navbar-brand">PlumbManager</a>--%>
<%--        <ul class="navbar-nav ms-auto">--%>
<%--            <li class="nav-item">--%>
<%--                <a href="#learn" class="nav-link">What You'll Learn</a>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a href="#questions" class="nav-link">Questions</a>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a href="#instructors" class="nav-link">Instructors</a>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--</nav>--%>

<nav class="navbar navbar-expand-lg py-3 bg-dark navbar-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/">PlumbManager</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasAuthority('EMPLOYEE')">
                        <li class="nav-item">
                            <a class="nav-link" href="/workorder/all">Work Orders</a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('MANAGER')">
                        <li class="nav-item">
                            <a class="nav-link" href="/customer/all">Customers</a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="/employee/all">Employees</a>
                        </li>
                    </sec:authorize>
                    <li class="nav-item">
                        <a class="nav-link" href="/login/logout">Logout</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/login/login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/login/register">Register</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div id="mainContainer">