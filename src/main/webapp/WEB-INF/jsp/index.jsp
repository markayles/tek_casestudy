<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<h1>This is the index page</h1>

<c:if test="${not empty username}">
    <sec:authentication property="principal.username" /><br><br>

    <h2>Hello, ${username}</h2>

    <c:forEach items="${authorities}" var="authority">
        <p>${authority}</p>
    </c:forEach>

    <sec:authorize access="hasAuthority('ADMIN')">
        <h1>ADMIN BOIIIIIIIIIIIIIIIIIIII</h1>
    </sec:authorize>
    <sec:authorize access="hasAuthority('USER')">
        <h1>user BOI</h1>
    </sec:authorize>
</c:if>

<c:if test="${empty username}">
    <h2>empty :(</h2>
</c:if>




<a href="/customer/all">Customers</a><br>
<a href="/workorder/all">Work Orders</a><br>
<a href="/employee/all">Employees</a><br>