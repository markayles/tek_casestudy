<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="/">Home</a><br>
<a href="/customer/create">Create New Customer</a>

<h1>All Customers</h1>

<c:forEach items="${customers}" var="customer">
    <p>${customer} <a href="/customer/view/${customer.id}/">View</a></p>
</c:forEach>