<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1>Creating work order</h1>

<form action="/workorder/createSubmit" method="POST">
    <div class="row mb-3">
        <label for="type" class="col-sm-2 col-form-label text-end">Type</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="type">
        </div>
    </div>
    <div class="row mb-3">
        <label class="col-sm-2 col-form-label text-end">Status</label>
        <div class="col-sm-5">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="status" id="inlineRadio1" value="new">
                <label class="form-check-label" for="inlineRadio1">New</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="status" id="inlineRadio2" value="pending">
                <label class="form-check-label" for="inlineRadio2">Pending</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="status" id="inlineRadio3" value="inprogress">
                <label class="form-check-label" for="inlineRadio3">In Progress</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="status" id="inlineRadio4" value="complete">
                <label class="form-check-label" for="inlineRadio4">Complete</label>
            </div>
        </div>
    </div>
    <div class="row mb-3">
        <label for="customerId" class="col-sm-2 col-form-label text-end">Customer</label>
        <div class="col-sm-3">
            <select class="form-select" name="customer" id="customerId">
                <option value="" disabled selected>Select Customer</option>
                <c:forEach items="${customers}" var="customer">
                    <option value="${customer.id}">(${customer.id}) ${customer.firstName} ${customer.lastName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="row mb-3">
        <label for="customerAddressId" class="col-sm-2 col-form-label text-end">Customer Address</label>
        <div class="col-sm-3">
            <select class="form-select" name="customer" id="customerAddressId">
<%--                Intentionall blank - filled by js --%>
            </select>
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-sm-2"></div>
        <div class="col-sm-3">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>
</form>

<script>
    $("#customerId").change(function(){
        let customerId = $("#customerId").val();
        console.log("customer id " + customerId);
        $.get("/customer/getAddressesForCustomer/" + customerId, function(data){
            $("#customerAddressId").empty();

            let _selectOptions = "";
            $.each(data, function(key, value){
                _selectOptions += "<option value=\"" + value.id + "\">(" + value.id +
                    ") " + value.street + " " + value.city + ", " + value.state + " " + value.zip + "</option>";
            });

            $("#customerAddressId").append(_selectOptions);
        })
    });
</script>

<jsp:include page="../include/footer.jsp" />


