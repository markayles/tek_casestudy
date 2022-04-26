<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/customer/all">Back to all</a> / <a href="/customer/edit/${customer.id}">Edit</a>

<h1>Customer #${customer.id}</h1>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Name</strong></div>
    <div class="col-sm-5">${customer.firstName} ${customer.lastName}</div>
</div>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Addresses</strong></div>
    <div class="col-sm-5">
        <c:forEach items="${customer.addresses}" var="address">
            <p>${address.street} ${address.city}, ${address.state} ${address.zip}</p>
        </c:forEach>
        <a href="/customer/createAddress/${customer.id}">Add Address</a>
    </div>
</div>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Work Orders</strong></div>
    <div class="col-sm-5">
        <c:forEach items="${customer.workOrders}" var="workOrder">
            <p>(${workOrder.id}) [${workOrder.status}] ${workOrder.type} @ ${workOrder.address.street} <a href="/workorder/view/${workOrder.id}/">View</a></p>
        </c:forEach>
    </div>
</div>

<jsp:include page="../include/footer.jsp" />