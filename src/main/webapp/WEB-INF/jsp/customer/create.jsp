<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1>Creating customer</h1>

<form action="/customer/createSubmit" method="POST">

    <div class="row mb-3">
        <label for="firstName" class="col-sm-2 col-form-label text-end">First Name</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="firstName" id="firstName" value="${employee.firstName}">
        </div>
    </div>
    <div class="row mb-3">
        <label for="lastName" class="col-sm-2 col-form-label text-end">Last Name</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="lastName" id="lastName" value="${employee.lastName}">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-sm-1"></div>
        <div class="col-sm-4">
            <p>Next page will create an address for this customer</p>
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