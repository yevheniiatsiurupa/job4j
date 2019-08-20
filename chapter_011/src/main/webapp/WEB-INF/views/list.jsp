<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <br/><br/>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/create" method="get">
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-default">Create account</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pageContext.request.contextPath}/logout" method="get">
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-default">Log out</button>
            </div>
        </div>
    </form>
    <br/> <br/>

    <div class="col-sm-6">
        <table class="col-sm-offset-1 table" id="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Login</th>
                <th>Email</th>
                <th>Role</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${requestScope.answer=='ok'}">
                <c:forEach items="${requestScope.users}" var="user">
                    <tr>
                        <td><c:out value="${user.name}"/> </td>
                        <td><c:out value="${user.login}"/> </td>
                        <td><c:out value="${user.email}"/> </td>
                        <td><c:out value="${user.role}"/> </td>
                        <td>
                            <form class="form-horizontal" action="${pageContext.request.contextPath}/edit" method="get">
                                <div class="form-group">
                                    <div class="col-sm-1">
                                        <button type="submit" class="btn btn-default">update</button>
                                    </div>
                                </div>
                                <input type="hidden" name="id" value="<c:out value="${user.id}"/>" />
                                <input type="hidden" name="name" value="<c:out value="${user.name}"/>" />
                                <input type="hidden" name="login" value="<c:out value="${user.login}"/>" />
                                <input type="hidden" name="email" value="<c:out value="${user.email}"/>" />
                                <input type="hidden" name="role" value="<c:out value="${user.role}"/>" />
                            </form>
                        </td>
                        <td>
                            <form class="form-horizontal" action="${pageContext.request.contextPath}/delete" method="get">
                                <div class="form-group">
                                    <div class="col-sm-1">
                                        <button type="submit" class="btn btn-default">delete</button>
                                    </div>
                                </div>
                                <input type="hidden" name="id" value="<c:out value="${user.id}"/>" />
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
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
