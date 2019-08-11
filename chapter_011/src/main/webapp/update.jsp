
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/edit" method="post">
    Update User with id = <%=request.getParameter("id")%>: <br/> <br/>
    Name: <input type="text" name="name" value="<%=request.getParameter("name")%>"/>
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>"/>
    <input type="submit" />
</form>
</body>
</html>