<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1>Sign Up</h1>

<form action="/login/registerSubmit" method="POST">
    <div class="row mb-3">
        <label for="firstName" class="col-sm-2 col-form-label text-end">First Name</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="firstName" id="firstName" value="${form.firstName}">
        </div>
        <div class="col-sm-4">
            <c:forEach items='${bindingResult.getFieldErrors("firstName")}' var="error">
                <div style="color:red;">- ${error.getDefaultMessage()}</div>
            </c:forEach>
        </div>
    </div>
    <div class="row mb-3">
        <label for="lastName" class="col-sm-2 col-form-label text-end">Last Name</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="lastName" id="lastName" value="${form.lastName}">
        </div>
        <div class="col-sm-4">
            <c:forEach items='${bindingResult.getFieldErrors("lastName")}' var="error">
                <div style="color:red;">- ${error.getDefaultMessage()}</div>
            </c:forEach>
        </div>
    </div>
    <div class="row mb-3">
        <label for="password" class="col-sm-2 col-form-label text-end">Password</label>
        <div class="col-sm-3">
            <input type="password" class="form-control" name="password" id="password">
        </div>
        <div class="col-sm-4">
            <c:forEach items='${bindingResult.getFieldErrors("password")}' var="error">
                <div style="color:red;">- ${error.getDefaultMessage()}</div>
            </c:forEach>
        </div>
    </div>
    <div class="row mb-3">
        <label for="confirmPassword" class="col-sm-2 col-form-label text-end">Confirm Password</label>
        <div class="col-sm-3">
            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword">
        </div>
        <div class="col-sm-4">
            <c:forEach items='${bindingResult.getFieldErrors("confirmPassword")}' var="error">
                <div style="color:red;">- ${error.getDefaultMessage()}</div>
            </c:forEach>
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-sm-2"></div>
        <div class="col-sm-3">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>
</form>

<jsp:include page="../include/footer.jsp" />