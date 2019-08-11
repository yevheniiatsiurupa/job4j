<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/create" method="post">
    Create User: <br/> <br/>
    Name: <input type="text" name="name" />
    <input type="submit" />
</form>
</body>
</html>
