<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/list" method="get">
    ${requestScope.answer} <br/>
    <input type="submit" value="Return to users list" />
</form>
</body>
</html>
