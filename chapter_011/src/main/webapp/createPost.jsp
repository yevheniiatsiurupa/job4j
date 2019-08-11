<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.UserValidationException" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/list" method="get">
    <% String name = request.getParameter("name");
        String answer;
        User user = new User(name);
        try {
            ValidateService.getInstance().add(user);
            answer = "User was added. User id = " + user.getId();
        } catch (UserValidationException e) {
            answer = e.getMessage();
        } %>
    <%=answer%> <br/>
    <input type="submit" value="Return to users list" />
</form>
</body>
</html>
