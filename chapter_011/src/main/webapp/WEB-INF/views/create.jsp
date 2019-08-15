<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/create" method="post">
    <table>
        <tr>
            <td align="right" valign="top">Create User: </td>
        </tr>
        <tr><td></td></tr>
        <tr>
            <td align="right" valign="top">Name: </td>
            <td align="left" valign="top"> <input type="text" name="name" /></td>
        </tr>
        <tr>
            <td align="right" valign="top">Login: </td>
            <td align="left" valign="top"> <input type="text" name="login" /></td>
        </tr>
        <tr>
            <td align="right" valign="top">Email: </td>
            <td align="left" valign="top"> <input type="text" name="email" /></td>
        </tr>
        <tr>
            <td align="right" valign="top">Password: </td>
            <td align="left" valign="top"> <input type="password" name="password" /></td>
        </tr>
        <c:if test="${sessionScope.role == 'admin'}">
            <tr>
                <td align="right" valign="top">Role: </td>
                <td align="left" valign="top"> <select name="role">
                    <option value="user">user</option>
                    <option value="admin">admin</option>
                </select></td>
            </tr>
        </c:if>
        <tr><td></td></tr>
        <tr>
            <td align="right" valign="top"><input type="submit" /></td>
        </tr>
    </table>
    <c:if test="${sessionScope.role != 'admin'}">
        <input type="hidden" name="role" value="user"/>
    </c:if>
    </form>
</body>
</html>
