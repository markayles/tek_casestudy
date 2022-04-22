<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="/">Home</a><br>
<a href="/workorder/create">Create New Work Order</a>

<h1>All Work Orders</h1>

<c:forEach items="${workOrders}" var="workOrder">
    <p>${workOrder} <a href="/workorder/view/${workOrder.id}/">View</a></p>
</c:forEach>