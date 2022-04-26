<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/customer/create">Create New Customer</a>

<h1>All Customers</h1><table class="table table-striped">
    <thead>
    <th scope="col" style="width: 5%">ID</th>
    <th scope="col">Name</th>
    <th scope="col">Addresses</th>
    <th scope="col" style="width: 10%">Work Orders</th>
    <th scope="col" style="width: 10%"></th>
    </thead>

    <tbody>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.firstName} ${customer.lastName}</td>
            <td>
                <c:forEach items="${customer.addresses}" var="address">
                    ${address.street} ${address.city}, ${address.state} ${address.zip}<br>
                </c:forEach>
            </td>
            <td>${customer.workOrders.size()}</td>
            <td><a href="/customer/view/${customer.id}/">View</a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>

<jsp:include page="../include/footer.jsp" />