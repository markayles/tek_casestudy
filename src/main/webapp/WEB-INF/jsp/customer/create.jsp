<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1>Creating customer</h1>

<form action="/customer/createSubmit" method="POST">

    First name: <input type="text" class="text" name="firstName" id="firstName"><br>
    Last name: <input type="text" class="text" name="lastName" id="lastName"><br>

    <p>Next page will create address for this customer</p>

    <button type="submit">Create</button>

</form>

<jsp:include page="../include/footer.jsp" />