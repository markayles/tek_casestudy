<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<h1>Creating work order</h1>

<form action="/workorder/createSubmit" method="POST">

    Work order type:
    <input type="text" class="text" name="type" id="type"><br>

    Work order status:
    <select name="status" id="status">
        <option value="new" selected>New</option>
        <option value="inprogress">In Progress</option>
        <option value="complete">Complete</option>
    </select><br>

    For customer:
    <select name="customerId" id="customerId">
        <option value="" disabled selected>Select Customer</option>
        <c:forEach items="${customers}" var="customer">
            <option value="${customer.id}">(${customer.id}) ${customer.firstName} ${customer.lastName}</option>
        </c:forEach>
    </select><br>

    Customer address:
    <select name="customerAddress" id="customerAddress">
    </select><br>


    <button type="submit">Create</button>

</form>

<script>
    $("#customerId").change(function(){
        let customerId = $("#customerId").val();
        $.get("/customer/getAddressesForCustomer/" + customerId, function(data){
            $("#customerAddress").empty();

            let _selectOptions = "";
            $.each(data, function(key, value){
                _selectOptions += "<option value=\"" + value.id + "\">(" + value.id +
                    ") " + value.street + " " + value.city + ", " + value.state + " " + value.zip + "</option>";
            });

            $("#customerAddress").append(_selectOptions);
        })
    });
</script>


