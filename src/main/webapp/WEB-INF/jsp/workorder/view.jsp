<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<h1>Work Order #${workOrder.id}</h1>
<p><strong>Work order status: </strong> ${workOrder.status}</p>
<p><strong>Work to be done: </strong> ${workOrder.type}</p>
<p><strong>Created: </strong> ${workOrder.createTime}</p>
<p><strong>Updated Last: </strong> ${workOrder.updateTime}</p>

<h1>Notes</h1> <a href="./addNote">Add Note</a>
<c:forEach items="${workOrder.workOrderNotes}" var="note">
    <p><strong>${note.createDate}</strong> by ${note.employee.firstName} ${note.employee.lastName} - ${note.note}</p>
</c:forEach>

<form action="/note/addNote" method="POST" id="noteForm">
    <input type="hidden" name="workOrderId" value="${workOrder.id}">
    <input type="text" name="addNote" id="addNote">
    <button type="submit">Add Note</button>
</form>

<script>
    $("#noteForm").submit(function(e) {

        e.preventDefault(); // avoid to execute the actual submit of the form.

        var form = $(this);
        var actionUrl = form.attr('action');

        $.ajax({
            type: "POST",
            url: actionUrl,
            data: form.serialize(), // serializes the form's elements.
            success: function(data)
            {
                console.log(data); // show response from the php script.
            }
        });

    });
</script>
