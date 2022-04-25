<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/workorder/create">Create New Work Order</a>

<h1>All Work Orders</h1>

<table class="table table-striped">
    <thead>
        <tr>
            <th scope="col" style="width: 5%">ID</th>
            <th scope="col" style="width: 10%">Status</th>
            <th scope="col" style="width: 25%">Type</th>
            <th scope="col" style="width: 20%">Created</th>
            <th scope="col" style="width: 15%">Customer</th>
            <th scope="col" style="width: 20%">Address</th>
            <th scope="col" style="width: 5%"></th>
        </tr>
    </thead>
    <tbody>

    <c:forEach items="${workOrders}" var="workOrder">
<%--        <p><strong>#${workOrder.id}</strong> ${workOrder.status} - ${workOrder.type} <a href="/workorder/view/${workOrder.id}/">View</a></p>--%>
        <tr
            <c:choose>
                <c:when test = "${workOrder.status == 'pending'}">
                    class="table-warning"
                </c:when>
                <c:when test = "${workOrder.status == 'complete'}">
                    class="table-secondary"
                </c:when>
                <c:when test = "${workOrder.status == 'new'}">
                    class="table-success"
                </c:when>
                <c:when test = "${workOrder.status == 'inprogress'}">
                    class="table-danger"
                </c:when>
                <c:otherwise>
                    No comment sir...
                </c:otherwise>
            </c:choose>
        >
            <th scope="row">${workOrder.id}</th>
            <td>${workOrder.status}</td>
            <td>${workOrder.type}</td>
            <td><fmt:formatDate type="both" pattern="EEE, MMM dd, yyyy HH:mm" value="${workOrder.createTime}" /></td>
            <td>${workOrder.customer.firstName} ${workOrder.customer.lastName}</td>
            <td>${workOrder.address.street} ${workOrder.address.city} ${workOrder.address.state}</td>
            <td><a href="/workorder/view/${workOrder.id}/">View</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>


<jsp:include page="../include/footer.jsp" />