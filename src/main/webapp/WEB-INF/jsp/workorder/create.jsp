<h1>Creating work order</h1>

<form action="/workorder/createSubmit" method="POST">

    Work order type: <input type="text" class="text" name="type" id="type"><br>
    Work order status: <select name="status" id="status">
        <option value="new" selected>New</option>
        <option value="inprogress">In Progress</option>
        <option value="complete">Complete</option>
    </select><br>
    For customer (id): <input type="text" name="customerId" id="customerId"><br>

    <button type="submit">Create</button>

</form>