<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<a href="/employee/all">Back to all</a><br>
<a href="/employee/edit/${employee.id}">Edit</a>

<h1>Employee #${employee.id}</h1>
<p><strong>Name: </strong> ${employee.firstName} ${employee.lastName}</p>
<p><strong>Title: </strong> ${employee.title}</p>
<p><strong>Username: </strong> ${employee.username}</p>
<p><strong>Created: </strong> <fmt:formatDate type="both" pattern="EEE, MMM dd, yyyy HH:mm" value="${employee.createTime}" /></p>