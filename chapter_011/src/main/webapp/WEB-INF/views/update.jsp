<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/edit" method="post">
    Update User with id = <c:out value="${param.id}"/>: <br/> <br/>
    Name: <input type="text" name="name" value="<c:out value="${param.name}"/>"/> <br/>
    Login: <input type="text" name="login" value="<c:out value="${param.login}"/>"/> <br/>
    Email: <input type="text" name="email" value="<c:out value="${param.email}"/>"/> <br/>
    <input type="hidden" name="id" value="<c:out value="${param.id}"/>"/>
    <input type="submit" />
</form>
</body>
</html>