<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1>All Employees</h1>

<table class="table table-striped">
    <thead>
        <th scope="col" style="width: 5%">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Title</th>
        <th scope="col">Username</th>
        <th scope="col">Roles</th>
        <th scope="col" style="width: 10%">Work Orders</th>
        <th scope="col" style="width: 10%"></th>
    </thead>

    <tbody>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.firstName} ${employee.lastName}</td>
            <td>${employee.title}</td>
            <td>${employee.username}</td>
            <td>
                <c:forEach items="${employee.roles}" var="role">
                    [${role.employeeRole}]
                </c:forEach>
            </td>
            <td>${employee.workOrders.size()}</td>
            <td><a href="/employee/view/${employee.id}/">View</a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>

<jsp:include page="../include/footer.jsp" />