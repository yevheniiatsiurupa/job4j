
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/edit" method="post">
    Update User with id = <%=request.getParameter("id")%>: <br/> <br/>
    Name: <input type="text" name="name" value="<%=request.getParameter("name")%>"/> <br/>
    Login: <input type="text" name="login" value="<%=request.getParameter("login")%>"/> <br/>
    Email: <input type="text" name="email" value="<%=request.getParameter("email")%>"/> <br/>
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>"/>
    <input type="submit" />
</form>
</body>
</html>