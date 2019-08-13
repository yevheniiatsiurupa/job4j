<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/delete" method="post">
    Deleting User with id = <%=request.getParameter("id")%>: <br/> <br/>
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>"/>
    <input type="submit" value="Confirm" />
</form>
</body>
</html>
