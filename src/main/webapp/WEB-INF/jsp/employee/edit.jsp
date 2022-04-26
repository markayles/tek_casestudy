<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/employee/view/${employee.id}">Back to employee</a>

<h1>Editing employee #${employee.id}</h1>

<form action="/employee/editSubmit" method="POST">
    <input type="hidden" name="id" id="id" value="${employee.id}">

    <div class="row mb-3">
        <label for="firstName" class="col-sm-2 col-form-label text-end">First Name</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="firstName" id="firstName" value="${employee.firstName}">
        </div>
    </div>
    <div class="row mb-3">
        <label for="lastName" class="col-sm-2 col-form-label text-end">Last Name</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="lastName" id="lastName" value="${employee.lastName}">
        </div>
    </div>
    <div class="row mb-3">
        <label for="title" class="col-sm-2 col-form-label text-end">Title</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="title" id="title" value="${employee.title}">
        </div>
    </div>
    <div class="row mb-3">
        <label for="username" class="col-sm-2 col-form-label text-end">Username</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="username" id="username" value="${employee.username}">
        </div>
    </div>
    <div class="row mb-3">
        <label for="password" class="col-sm-2 col-form-label text-end">Password</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="password" id="password">
        </div>
    </div>
    <div class="row mb-3">
        <label for="confirmPassword" class="col-sm-2 col-form-label text-end">Confirm Password</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="confirmPassword" id="confirmPassword">
        </div>
        <div class="col-sm-4">
            <c:if test="${not empty passwordError}">
                <span style="color:red;">${passwordError}</span><br>
            </c:if>
        </div>
    </div>
    <div class="row mb-3">
        <label class="col-sm-2 col-form-label text-end">Roles</label>
        <div class="col-sm-3">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="role1" name="roles" value="NEW_EMPLOYEE">
                <label class="form-check-label" for="role1">
                    NEW EMPLOYEE
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="role2" name="roles" value="EMPLOYEE">
                <label class="form-check-label" for="role1">
                    EMPLOYEE
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="role3" name="roles" value="MANAGER">
                <label class="form-check-label" for="role1">
                    MANAGER
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="role4" name="roles" value="ADMIN">
                <label class="form-check-label" for="role1">
                    ADMIN
                </label>
            </div>
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-sm-2"></div>
        <div class="col-sm-3">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>
</form>


<script>

    <c:forEach items="${employee.roles}" var="role">
        console.log("${role.employeeRole} -> " + $("input[name='roles'][value='${role.employeeRole}']"));
        $("input[name='roles'][value='${role.employeeRole}']").prop('checked', true);
    </c:forEach>

</script>

<jsp:include page="../include/footer.jsp" />