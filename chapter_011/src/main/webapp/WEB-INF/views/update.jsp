<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/edit" method="post">
    Update User with id = <c:out value="${param.id}"/>: <br/> <br/>
    <table>
        <tr>
            <td align="right" valign="top">Name: </td>
            <td align="left" valign="top"> <input type="text" name="name" value="<c:out value="${param.name}"/>"/></td>
        </tr>
        <tr>
            <td align="right" valign="top">Login: </td>
            <td align="left" valign="top"> <input type="text" name="login" value="<c:out value="${param.login}"/>"/></td>
        </tr>
        <tr>
            <td align="right" valign="top">Email: </td>
            <td align="left" valign="top"> <input type="text" name="email" value="<c:out value="${param.email}"/>"/></td>
        </tr>
        <tr>
            <td align="right" valign="top">Password: </td>
            <td align="left" valign="top"> <input type="password" name="password" value="<c:out value="${param.password}"/>"/></td>
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
            <td align="right" valign="top"><input type="submit" value="Update"/></td>
        </tr>
        <input type="hidden" name="id" value="<c:out value="${param.id}"/>"/>
        <c:if test="${sessionScope.role != 'admin'}">
        <input type="hidden" name="role" value="user"/>
        </c:if>
</form>
</body>
</html>