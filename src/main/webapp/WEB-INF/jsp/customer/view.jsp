<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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

<jsp:include page="../include/footer.jsp" />