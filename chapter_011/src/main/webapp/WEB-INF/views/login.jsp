<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var result = true;
            if ($('#login').val() === '') {
                alert($('#login').attr('placeholder'));
                result = false;
            }
            if ($('#password').val() === '') {
                alert($('#password').attr('placeholder'));
                result = false;
            }
            return result;
        }
    </script>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: salmon">
        <c:out value="${error}"/>
    </div>
</c:if>
<div class="container-fluid">
    <h1 class="col-sm-offset-1">Authorization</h1>
    <br/>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/login" method="post">

        <div class="form-group">
            <label class="control-label col-sm-1" for="login">Login:</label>
            <div class="col-sm-4">
                <input type="text" name="login" class="form-control" id="login" placeholder="Enter login">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-1" for="password">Password:</label>
            <div class="col-sm-4">
                <input type="password" name="password" class="form-control" id="password" placeholder="Enter password">
            </div>
        </div>
        <br/> <br/>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-default" onclick="return validate()">Log in</button>
            </div>
        </div>
    </form>

    <form class="form-horizontal" action="${pageContext.request.contextPath}/create" method="get">
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-default">Create account</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
