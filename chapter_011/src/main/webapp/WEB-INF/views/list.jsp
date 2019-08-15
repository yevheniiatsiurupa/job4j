<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <th>Role</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <c:if test="${requestScope.answer=='ok'}">
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td align="right" valign="top"><c:out value="${user.name}"/> </td>
                <td align="right" valign="top"><c:out value="${user.login}"/> </td>
                <td align="right" valign="top"><c:out value="${user.email}"/> </td>
                <td align="right" valign="top"><c:out value="${user.role}"/> </td>
                <td align="left" valign="center">
                    <form action="${pageContext.request.contextPath}/edit" method="get">
                        <input type="hidden" name="id" value="<c:out value="${user.id}"/>" />
                        <input type="hidden" name="name" value="<c:out value="${user.name}"/>" />
                        <input type="hidden" name="login" value="<c:out value="${user.login}"/>" />
                        <input type="hidden" name="email" value="<c:out value="${user.email}"/>" />
                        <input type="hidden" name="role" value="<c:out value="${user.role}"/>" />
                        <input type="submit" value="update" />
                    </form>
                </td>
                <td align="right" valign="center">
                    <form action="${pageContext.request.contextPath}/delete" method="get">
                        <input type="hidden" name="id" value="<c:out value="${user.id}"/>" />
                        <input type="submit" value="delete" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${requestScope.answer!='ok'}">
        <tr>
            <td><c:out value="${requestScope.answer}"/></td>
        </tr>
    </c:if>
</table>
<br/> <br/>
<form action="${pageContext.request.contextPath}/logout" method="get">
    <input type="submit" value="Log out" />
</form>
</body>
</html>
