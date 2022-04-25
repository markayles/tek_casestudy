<jsp:include page="../include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h3>Thank you for registering, ${employeeName}.</h3>
<p>Your username is: <strong>${username}</strong></p>

<p>Your manager is required to modify your account to grant you necessary permissions.</p>
<p>Please get in contact with them before proceeding.</p>
<a href="/">Return to index</a>

<jsp:include page="../include/footer.jsp" />