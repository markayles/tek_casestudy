<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/customer/view/${customer.id}">Back to customer</a>

<h1>Editing customer #${customer.id}</h1>

<form action="/customer/editSubmit" method="POST">

    <input type="hidden" name="id" id="id" value="${customer.id}">

    <div class="row mb-3">
        <label for="firstName" class="col-sm-2 col-form-label text-end">First Name</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="firstName" id="firstName" value="${customer.firstName}">
        </div>
    </div>
    <div class="row mb-3">
        <label for="lastName" class="col-sm-2 col-form-label text-end">Last Name</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="lastName" id="lastName" value="${customer.lastName}">
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