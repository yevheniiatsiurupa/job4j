<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/delete" method="post">
    Deleting User with id = <c:out value="${param.id}"/>: <br/> <br/>
    <input type="hidden" name="id" value="<c:out value="${param.id}"/>"/>
    <input type="submit" value="Confirm" />
</form>
</body>
</html>
