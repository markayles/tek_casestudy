<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/workorder/view/${workOrder.id}">Back to work order</a>

<h1>Editing work order #${workOrder.id}</h1>

<form action="/workorder/editSubmit" method="POST">
    <input type="hidden" name="id" id="id" value="${workOrder.id}">

    <div class="row mb-3">
        <label for="type" class="col-sm-2 col-form-label text-end">Type</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="type" id="type" value="${workOrder.type}">
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
            <select class="form-select" name="customerId" id="customerId">
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
            <select class="form-select" name="customerAddressId" id="customerAddressId">
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
<br><br>
<form action="/workorder/addEmployee" method="POST" id="employeeForm">
    <input type="hidden" name="workOrderId" value="${workOrder.id}">
    <div class="row mb-3">
        <label for="employeeId" class="col-sm-2 col-form-label text-end">Assign Employee (ID)</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="employeeId" id="employeeId">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-sm-2"></div>
        <div class="col-sm-3">
            <button type="submit" class="btn btn-primary">Assign Employee</button>
        </div>
    </div>
    <span id="employeeIdError" style="color:red;">You must enter an employee ID</span>
    <span id="employeeIdSuccess" style="color:green;">Success!</span>
</form>

<script>
    $("input[name='status'][value='${workOrder.status}']").prop('checked', true);
    $("#customerId option[value='${workOrder.customer.id}']").prop('selected', true);

    $("#employeeIdSuccess").hide();
    $("#employeeIdError").hide();

    function getCustomerAddresses() {
        let customerId = $("#customerId").val();
        $.get("/customer/getAddressesForCustomer/" + customerId, function(data){
            $("#customerAddressId").empty();

            let _selectOptions = "";
            $.each(data, function(key, value){
                _selectOptions += "<option value=\"" + value.id + "\">(" + value.id +
                    ") " + value.street + " " + value.city + ", " + value.state + " " + value.zip + "</option>";
            });

            $("#customerAddressId").append(_selectOptions);
        })
    }
    getCustomerAddresses();
    $("#customerAddressId option[value='${workOrder.address.id}']").prop('selected', true);


    $("#customerId").change(function(){
        getCustomerAddresses();
    });

    $("#employeeForm").submit(function(e) {
        e.preventDefault(); // avoid to execute the actual submit of the form.

        var form = $(this);
        var actionUrl = form.attr('action');

        if($("#employeeId").val() != ""){
            $.ajax({
                type: "POST",
                url: actionUrl,
                data: form.serialize(), // serializes the form's elements.
                success: function(data)
                {
                    $("#employeeId").val("");
                    $("#employeeIdSuccess").show().delay(1500).fadeOut();
                    $("#employeeIdError").hide();
                }
            });
        }else{
            $("#employeeIdError").show();
        }


    });
</script>

<jsp:include page="../include/footer.jsp" />