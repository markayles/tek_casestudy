<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/employee/view/${employee.id}">Back to employee</a>

<h1>Editing employee #${employee.id}</h1>

<form action="/employee/editSubmit" method="POST">
    <input type="hidden" name="id" id="id" value="${employee.id}">

    First Name <input type="text" name="firstName" id="firstName" value="${employee.firstName}"><br>

    Last Name <input type="text" name="lastName" id="lastName" value="${employee.lastName}"><br>

    Title <input type="text" name="title" id="title" value="${employee.title}"><br>

    Username <input type="text" name="username" id="username" value="${employee.username}"><br>

    Password <input type="password" name="password" id="password"><br>

    Confirm Password <input type="password" name="confirmPassword" id="confirmPassword"><br>
    <c:if test="${not empty passwordError}">
        <span style="color:red;">${passwordError}</span><br>
    </c:if>

    Roles
    <input type="checkbox" id="role1" name="roles" value="NEW_EMPLOYEE">
    <label for="role1">NEW EMPLOYEE</label>
    <input type="checkbox" id="role2" name="roles" value="EMPLOYEE">
    <label for="role2">EMPLOYEE</label>
    <input type="checkbox" id="role3" name="roles" value="MANAGER">
    <label for="role3">MANAGER</label>
    <input type="checkbox" id="role4" name="roles" value="ADMIN">
    <label for="role4">ADMIN</label>
    <br>

    <button type="submit">Submit</button>
</form>


<script>

    <c:forEach items="${employee.roles}" var="role">
        console.log("${role.employeeRole} -> " + $("input[name='roles'][value='${role.employeeRole}']"));
        $("input[name='roles'][value='${role.employeeRole}']").prop('checked', true);
    </c:forEach>

</script>

<jsp:include page="../include/footer.jsp" />