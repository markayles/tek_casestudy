<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1>Adding address for customer #${customer.id} - ${customer.firstName} ${customer.lastName}</h1>

<form action="/customer/createAddressSubmit" method="POST">
    <input type="hidden" name="customerId" id="customerId" value="${customer.id}">

    <div class="row mb-3">
        <label for="street" class="col-sm-2 col-form-label text-end">Street</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="street" id="street">
        </div>
    </div>
    <div class="row mb-3">
        <label for="city" class="col-sm-2 col-form-label text-end">City</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="city" id="city">
        </div>
    </div>
    <div class="row mb-3">
        <label for="state" class="col-sm-2 col-form-label text-end">State</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="state" id="state">
        </div>
    </div>
    <div class="row mb-3">
        <label for="zip" class="col-sm-2 col-form-label text-end">Zip Code</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="zip" id="zip">
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