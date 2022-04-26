<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/employee/all">Back to all</a> / <a href="/employee/edit/${employee.id}">Edit</a>

<h1>Employee #${employee.id}</h1>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Name</strong></div>
    <div class="col-sm-5">${employee.firstName} ${employee.lastName}</div>
</div>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Title</strong></div>
    <div class="col-sm-5">${employee.title}</div>
</div>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Username</strong></div>
    <div class="col-sm-5">${employee.username}</div>
</div>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Roles</strong></div>
    <div class="col-sm-5">
    <c:forEach items="${employee.roles}" var="role">
        <p>[${role.employeeRole}]</p>
    </c:forEach></div>
</div>
<div class="row mb-3">
    <div class="col-sm-2 text-end"><strong>Created</strong></div>
    <div class="col-sm-5"><fmt:formatDate type="both" pattern="EEE, MMM dd, yyyy HH:mm" value="${employee.createTime}" /></div>
</div>

<jsp:include page="../include/footer.jsp" />