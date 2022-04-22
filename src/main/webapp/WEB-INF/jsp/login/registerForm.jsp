<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Sign Up</h1>

<form action="/login/registerSubmit" method="POST">
    First Name <input type="text" name="firstName" id="firstNameId" value="${form.firstName}">
    <c:forEach items='${bindingResult.getFieldErrors("firstName")}' var="error">
        <div style="color:red;">${error.getDefaultMessage()}</div>
    </c:forEach>
    <br>
    Last Name <input type="text" name="lastName" id="lastNameId" value="${form.lastName}">
    <c:forEach items='${bindingResult.getFieldErrors("lastName")}' var="error">
        <div style="color:red;">${error.getDefaultMessage()}</div>
    </c:forEach>
    <br>
    Password <input type="text" name="password" id="passwordId" value="${form.password}">
    <c:forEach items='${bindingResult.getFieldErrors("password")}' var="error">
        <div style="color:red;">${error.getDefaultMessage()}</div>
    </c:forEach>
    <br>
    Confirm Password <input type="password" name="confirmPassword" id="confirmPasswordId" value="${form.confirmPassword}">
    <c:forEach items='${bindingResult.getFieldErrors("confirmPassword")}' var="error">
        <div style="color:red;">${error.getDefaultMessage()}</div>
    </c:forEach>
    <br>

    <br>
    <button type="submit">Submit</button>
</form>