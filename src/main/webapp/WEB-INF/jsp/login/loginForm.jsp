<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<form action="/login/loginSubmit" method="POST">

    <c:if test="${not empty param.error}">
        <span style="color:red;">Username and password combination is incorrect</span><br>
    </c:if>

    Username : <input type="text" name="username">
    <br>
    Password : <input type="text" name="password">
    <br>
    <button type="submit">Submit</button>
</form>

<jsp:include page="../include/footer.jsp" />