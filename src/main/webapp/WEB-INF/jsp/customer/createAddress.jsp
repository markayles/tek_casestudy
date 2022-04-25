<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1>Adding address for customer #${customer.id} - ${customer.firstName} ${customer.lastName}</h1>

<form action="/customer/createAddressSubmit" method="POST">
    <input type="hidden" name="customerId" id="customerId" value="${customer.id}">

    Street: <input type="text" class="text" name="street" id="street"><br>
    City: <input type="text" class="text" name="city" id="city"><br>
    State: <input type="text" class="text" name="state" id="state"><br>
    Zip: <input type="text" class="text" name="zip" id="zip"><br>

    <button type="submit">Create</button>

</form>

<jsp:include page="../include/footer.jsp" />