<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/">Home</a><br>
<a href="/employee/create">Create New Employee</a>

<h1>All Employees</h1>

<c:forEach items="${employees}" var="employee">
    <p>${employee} <a href="/employee/view/${employee.id}/">View</a></p>
</c:forEach>

<jsp:include page="../include/footer.jsp" />