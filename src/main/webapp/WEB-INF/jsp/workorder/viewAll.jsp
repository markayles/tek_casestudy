<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${workOrders}" var="workOrder">
    <p>${workOrder} <a href="/workorder/view/${workOrder.id}/">Edit</a></p>
</c:forEach>