<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: salmon">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/login" method="post">
    <table>
        <tr>
            <td align="right" valign="top">Authorization: </td>
        </tr>
        <tr><td></td></tr>
        <tr>
            <td align="right" valign="top">Login: </td>
            <td align="left" valign="top"> <input type="text" name="login" /></td>
        </tr>
        <tr>
            <td align="right" valign="top">Password: </td>
            <td align="left" valign="top"> <input type="password" name="password" /></td>
        </tr>
        <tr><td></td></tr>
        <tr>
            <td align="right" valign="top"><input type="submit" value="Log in"/></td>
        </tr>
    </table>
</form>
<form action="${pageContext.request.contextPath}/create" method="get">
    <input type="submit" value="Create account"/>
</form>
</body>
</html>
