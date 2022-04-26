<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/workorder/all">Back to all</a> /
<a href="/workorder/edit/${workOrder.id}">Edit</a>

<h1>Work Order #${workOrder.id}</h1>

<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Type</strong></div>
    <div class="col-sm-5">${workOrder.type}</div>
</div>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Status</strong></div>
    <div class="col-sm-5">${workOrder.status}</div>
</div>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Customer</strong></div>
    <div class="col-sm-5">${workOrder.customer.firstName} ${workOrder.customer.lastName}</div>
</div>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Address</strong></div>
    <div class="col-sm-5">${workOrder.address.street} ${workOrder.address.city} ${workOrder.address.state} ${workOrder.address.zip}</div>
</div>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Created At</strong></div>
    <div class="col-sm-5"><fmt:formatDate type="both" pattern="EEE, MMM dd, yyyy HH:mm" value="${workOrder.createTime}" /></div>
</div>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Updated At</strong></div>
    <div class="col-sm-5"><fmt:formatDate type="both" pattern="EEE, MMM dd, yyyy HH:mm" value="${workOrder.updateTime}" /></div>
</div>

<h2>Assigned Employees</h2>

<c:if test="${not empty workOrder.employees}">
    <c:forEach items="${workOrder.employees}" var="employee">
        <div class="row mb-3">
            <div class="col-sm-2 text-end"><strong>${employee.firstName} ${employee.lastName}</strong></div>
            <div class="col-sm-5">${employee.title}</div>
        </div>
    </c:forEach>
</c:if>
<c:if test="${empty workOrder.employees}">
    <div class="row mb-3">
        <div class="col-sm-2 text-end"><strong>None Assigned</strong></div>
    </div>
</c:if>


<h1>Notes</h1>

<form action="/note/addNote" method="POST" id="noteForm">
    <input type="hidden" name="workOrderId" value="${workOrder.id}">
    <input type="text" name="addNote" id="addNote">
    <button type="submit">Add Note</button> <span id="addNoteError" style="color:red;">You can not add a blank note</span>
</form>

<div id="workOrderNotes">
<%--    <c:forEach items="${workOrder.workOrderNotes}" var="note">--%>
<%--        <p><strong>${note.createDate}</strong> by ${note.employee.firstName} ${note.employee.lastName} - ${note.note}</p>--%>
<%--    </c:forEach>--%>
</div>

<script>
    function getWorkOrderNotes() {
        $("#addNoteError").hide();
        $.get("/note/getNotes/${workOrder.id}", function (data) {

            let _jsonString = "";

            for(var key in data){
                _jsonString += "<div class=\"alert alert-secondary p-2 m-1\" role=\"alert\"><strong>" + data[key].createDate +
                            "</strong> <em>by " + data[key].employee.firstName + "</em> " + data[key].employee.lastName +
                            " - " + data[key].note + "</div>";
            }

            $("#workOrderNotes").html(_jsonString);
        });
    }
    getWorkOrderNotes();


    $("#noteForm").submit(function(e) {

        e.preventDefault(); // avoid to execute the actual submit of the form.

        var form = $(this);
        var actionUrl = form.attr('action');

        if($("#addNote").val() != ""){
            $.ajax({
                type: "POST",
                url: actionUrl,
                data: form.serialize(), // serializes the form's elements.
                success: function(data)
                {
                    $("#addNote").val("");
                    getWorkOrderNotes();
                }
            });
        }else{
            $("#addNoteError").show();
        }


    });
</script>

<jsp:include page="../include/footer.jsp" />
