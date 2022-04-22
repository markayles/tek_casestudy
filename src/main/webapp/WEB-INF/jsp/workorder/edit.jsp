

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<a href="/workorder/view/${workOrder.id}">Back to work order</a>

<h1>Editing work order #${workOrder.id}</h1>

<form action="/workorder/editSubmit" method="POST">
    <input type="hidden" name="id" id="id" value="${workOrder.id}">
    Work order type: <input type="text" class="text" name="type" id="type" value="${workOrder.type}"><br>
    Work order status: <select name="status" id="status">
    <option value="new" selected>New</option>
    <option value="inprogress">In Progress</option>
    <option value="complete">Complete</option>
</select><br>
    For customer (id): <input type="text" name="customerId" id="customerId" value="${workOrder.customer.id}"><br>

    <button type="submit">Submit</button>

</form>

<script>
    $("#status option[value='${workOrder.status}']").prop('selected', true);
</script>