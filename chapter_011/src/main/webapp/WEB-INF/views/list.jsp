<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.validation.ValidateService" %>
<%@ page import="java.util.Collection" %>
<%@ page import="ru.job4j.servlets.validation.UserValidationException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users page</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/create" method="get">
    <input type="submit" value="Create new user" />
</form>
<br/>
<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <% try {
        Collection<User> users = ValidateService.getInstance().findAll();
        for (User tmp : users) { %>
    <tr>
        <td align="right" valign="top"><%=tmp.getName()%> </td>
        <td align="right" valign="top"><%=tmp.getLogin()%> </td>
        <td align="right" valign="top"><%=tmp.getEmail()%> </td>
        <td align="left" valign="center">
            <form action="${pageContext.request.contextPath}/edit" method="get">
                <input type="hidden" name="id" value="<%=tmp.getId()%>" />
                <input type="hidden" name="name" value="<%=tmp.getName()%>" />
                <input type="hidden" name="login" value="<%=tmp.getLogin()%>" />
                <input type="hidden" name="email" value="<%=tmp.getEmail()%>" />
                <input type="submit" value="update" />
            </form>
        </td>
        <td align="right" valign="center">
            <form action="${pageContext.request.contextPath}/delete" method="get">
                <input type="hidden" name="id" value="<%=tmp.getId()%>" />
                <input type="submit" value="delete" />
            </form>
        </td>
    </tr>
    <% }
    } catch (UserValidationException e) {%>
    <tr>
        <td><%=e.getMessage()%></td>
    </tr>
    <% } %>
</table>
</body>
</html>
