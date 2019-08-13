<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/create" method="post">
    Create User: <br/> <br/>
    Name: <input type="text" name="name" /> <br/>
    Login: <input type="text" name="login" /> <br/>
    Email: <input type="text" name="email" /> <br/>
    <input type="submit" />
</form>
</body>
</html>
