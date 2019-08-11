<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.UserValidationException" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/list" method="get">
    <% String name = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("id"));
        String answer;
        User user = new User(name);
        try {
            ValidateService.getInstance().update(user, id);
            answer = String.format("User with id = %d was updated. New name - %s",
                    user.getId(), user.getName());
        } catch (UserValidationException e) {
            answer = e.getMessage();
        } %>
    <%=answer%> <br/>
    <input type="submit" value="Return to users list" />
</form>
</body>
</html>
