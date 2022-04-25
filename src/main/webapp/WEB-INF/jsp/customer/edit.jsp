<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/customer/view/${customer.id}">Back to customer</a>

<h1>Editing customer #${customer.id}</h1>

<form action="/customer/editSubmit" method="POST">

    <input type="hidden" name="id" id="id" value="${customer.id}">
    First name: <input type="text" class="text" name="firstName" id="firstName" value="${customer.firstName}"><br>
    Last name: <input type="text" class="text" name="lastName" id="lastName" value="${customer.lastName}"><br>

    <button type="submit">Submit</button>

</form>

<jsp:include page="../include/footer.jsp" />