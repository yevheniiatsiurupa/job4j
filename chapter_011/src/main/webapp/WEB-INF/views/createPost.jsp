<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/list" method="get">
    <%=request.getAttribute("answer")%> <br/>
    <input type="submit" value="Return to users list" />
</form>
</body>
</html>
