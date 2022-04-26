<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<form action="/login/loginSubmit" method="POST">
    <div class="row mb-3">
        <div class="col-sm-1"></div>
        <div class="col-sm-4">
            <c:if test="${not empty param.error}">
                <span style="color:red;">Username and password combination is incorrect</span><br>
            </c:if>
        </div>
    </div>
    <div class="row mb-3">
        <label for="username" class="col-sm-2 col-form-label text-end">Username</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="username" id="username">
        </div>
    </div>
    <div class="row mb-3">
        <label for="password" class="col-sm-2 col-form-label text-end">Password</label>
        <div class="col-sm-3">
            <input type="password" class="form-control" name="password" id="password">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-sm-2"></div>
        <div class="col-sm-3">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>
</form>

<jsp:include page="../include/footer.jsp" />