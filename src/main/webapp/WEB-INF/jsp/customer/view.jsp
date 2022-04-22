<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<a href="/customer/all">Back to all</a><br>
<a href="/customer/edit/${customer.id}">Edit</a>

<h1>Customer #${customer.id}</h1>
<p><strong>Name: </strong> ${customer.firstName} ${customer.lastName}</p>

<h1>Addresses</h1>
<a href="/customer/createAddress/${customer.id}">Add Address</a>


<c:forEach items="${customer.addresses}" var="address">
    <p><strong>${address}</strong> <a href="/address/view/${address.id}/">View</a></p>
</c:forEach>

<h1>Work Orders</h1>

<c:forEach items="${customer.workOrders}" var="workOrder">
    <p><strong>${workOrder}</strong> <a href="/workorder/view/${workOrder.id}/">View</a></p>
</c:forEach>