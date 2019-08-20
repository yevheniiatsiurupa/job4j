<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <h3 class="col-sm-offset-1">Deleting User with id = <c:out value="${param.id}"/></h3>
    <br/>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/delete" method="post">
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-default">Confirm</button>
            </div>
        </div>
        <input type="hidden" name="id" value="<c:out value="${param.id}"/>"/>
    </form>
</div>
</form>
</body>
</html>
