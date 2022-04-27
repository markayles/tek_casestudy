<jsp:include page="include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--<h1>This is the index page</h1>--%>

<%--<c:if test="${not empty username}">--%>
<%--    <sec:authentication property="principal.username"/><br><br>--%>

<%--    <h2>Hello, ${username}</h2>--%>

<%--    <c:forEach items="${authorities}" var="authority">--%>
<%--        <p>${authority}</p>--%>
<%--    </c:forEach>--%>

<%--    &lt;%&ndash;    <c:forEach items="${principal}" var="principal">&ndash;%&gt;--%>
<%--    <p>${principal}</p>--%>
<%--    &lt;%&ndash;    </c:forEach>&ndash;%&gt;--%>


<%--    <sec:authorize access="hasAuthority('ADMIN')">--%>
<%--        <h1>ADMIN</h1>--%>
<%--    </sec:authorize>--%>
<%--    <sec:authorize access="hasAuthority('USER')">--%>
<%--        <h1>user BOI</h1>--%>
<%--    </sec:authorize>--%>
<%--</c:if>--%>

<%--<c:if test="${empty username}">--%>
<%--    <h2>empty :(</h2>--%>
<%--</c:if>--%>


<%--<a href="/customer/all">Customers</a><br>--%>
<%--<a href="/workorder/all">Work Orders</a><br>--%>
<%--<a href="/employee/all">Employees</a><br>--%>

<%--<br>--%>

<%--<sec:authorize access="!isAuthenticated()">--%>
<%--    <a href="/login/login">Login</a><br>--%>
<%--    <a href="/login/register">Register</a><br>--%>
<%--</sec:authorize>--%>
<%--<sec:authorize access="isAuthenticated()">--%>
<%--    <a href="/login/logout">Logout</a><br>--%>
<%--</sec:authorize>--%>

<jsp:include page="include/footer.jsp" />