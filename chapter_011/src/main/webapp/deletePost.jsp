<%@ page import="ru.job4j.servlets.UserValidationException" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/list" method="get">
    <% int id = Integer.parseInt(request.getParameter("id"));
        String answer;
        try {
            ValidateService.getInstance().delete(id);
            answer = String.format("User with id = %d was deleted.", id);
        } catch (UserValidationException e) {
            answer = e.getMessage();
        }%>
    <%=answer%> <br/>
    <input type="submit" value="Return to users list" />
</form>
</body>
</html>
