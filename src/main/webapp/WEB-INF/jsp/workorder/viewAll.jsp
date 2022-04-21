<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${workOrders}" var="workOrder">
    <p>${workOrder}</p>
</c:forEach>