<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/employee/all">Back to all</a><br>
<a href="/employee/edit/${employee.id}">Edit</a>

<h1>Employee #${employee.id}</h1>
<p><strong>Name: </strong> ${employee.firstName} ${employee.lastName}</p>
<p><strong>Title: </strong> ${employee.title}</p>
<p><strong>Username: </strong> ${employee.username}</p>
<p><strong>Roles: </strong>
<c:forEach items="${employee.roles}" var="role">
    <p>&emsp;${role.employeeRole}</p>
</c:forEach>
</p>
<p><strong>Created: </strong> <fmt:formatDate type="both" pattern="EEE, MMM dd, yyyy HH:mm" value="${employee.createTime}" /></p>

<jsp:include page="../include/footer.jsp" />