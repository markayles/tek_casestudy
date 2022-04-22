<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<a href="/employee/view/${employee.id}">Back to employee</a>

<h1>Editing employee #${employee.id}</h1>

<form action="/employee/editSubmit" method="POST">
    <input type="hidden" name="id" id="id" value="${employee.id}">

    First Name <input type="text" name="firstName" id="firstName" value="${employee.firstName}"><br>

    Last Name <input type="text" name="lastName" id="lastName" value="${employee.lastName}"><br>

    Title <input type="text" name="title" id="title" value="${employee.title}"><br>

    Username <input type="text" name="username" id="username" value="${employee.username}"><br>

    Password <input type="password" name="password" id="password"><br>

    Confirm Password <input type="password" name="confirmPassword" id="confirmPassword"><br>
    <c:if test="${not empty passwordError}">
        <span style="color:red;">${passwordError}</span><br>
    </c:if>

    <button type="submit">Submit</button>
</form>