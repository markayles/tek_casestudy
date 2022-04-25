<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<a href="/workorder/view/${workOrder.id}">Back to work order</a>

<h1>Editing work order #${workOrder.id}</h1>

<form action="/workorder/editSubmit" method="POST">
    <input type="hidden" name="id" id="id" value="${workOrder.id}">

    Work order type:
    <input type="text" class="text" name="type" id="type" value="${workOrder.type}"><br>

    Work order status:
    <select name="status" id="status">
        <option value="new" selected>New</option>
        <option value="inprogress">In Progress</option>
        <option value="complete">Complete</option>
    </select><br>

    For customer (id):
    <select name="customerId" id="customerId">
        <option value="" disabled selected>Select Customer</option>
        <c:forEach items="${customers}" var="customer">
            <option value="${customer.id}">(${customer.id}) ${customer.firstName} ${customer.lastName}</option>
        </c:forEach>
    </select><br>

    Customer address:
    <select name="customerAddressId" id="customerAddressId">
        <c:forEach items="${workOrder.customer.addresses}" var="address">
            <option value="${address.id}">(${address.id}) ${address.street} ${address.city}, ${address.state} ${address.zip}</option>
        </c:forEach>
    </select><br>


    <button type="submit">Submit</button>

</form>

Assign Employee
<form action="/workorder/addEmployee" method="POST" id="employeeForm">
    <input type="hidden" name="workOrderId" value="${workOrder.id}">
    <input type="text" name="employeeId" id="employeeId">
    <button type="submit">Assign Employee</button>
    <span id="employeeIdError" style="color:red;">You must enter an employee ID</span>
    <span id="employeeIdSuccess" style="color:green;">Success!</span>
</form>

<script>
    $("#status option[value='${workOrder.status}']").prop('selected', true);
    $("#customerId option[value='${workOrder.customer.id}']").prop('selected', true);
    $("#customerAddressId option[value='${workOrder.address.id}']").prop('selected', true);

    $("#employeeIdSuccess").hide();
    $("#employeeIdError").hide();


    $("#customerId").change(function(){
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