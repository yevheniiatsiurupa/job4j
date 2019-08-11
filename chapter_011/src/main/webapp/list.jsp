<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page import="java.util.Collection" %>
<%@ page import="ru.job4j.servlets.UserValidationException" %>
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
<table>
    <% try {
        Collection<User> users = ValidateService.getInstance().findAll();
        for (User tmp : users) { %>
    <tr>
        <td align="right" valign="top">User name: <%=tmp.getName()%> </td>
        <td align="left" valign="center">
            <form action="${pageContext.request.contextPath}/edit" method="get">
                <input type="hidden" name="id" value="<%=tmp.getId()%>" />
                <input type="hidden" name="name" value="<%=tmp.getName()%>" />
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
