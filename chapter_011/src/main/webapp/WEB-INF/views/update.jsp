<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var result = true;
            if ($('#name').val() === '') {
                alert('Enter name');
                result = false;
            }
            if ($('#login').val() === '') {
                alert('Enter login');
                result = false;
            }
            if ($('#password').val() === '') {
                alert('Enter password');
                result = false;
            }
            if ($('#email').val() === '') {
                alert('Enter email');
                result = false;
            }
            return result;
        }
    </script>

    <script>
        $(document).ready(function(){
            $.ajax({
                url: './city',
                dataType: 'json',
                success: function(data, status, settings){
                    getCountries(data)
                }});
        });
    </script>

    <script>
        function getCountries (data) {
            var arr = data['countries'];
            for (i = 0; i < arr.length; i++) {
                var country = arr[i];
                var opt = document.createElement('option');
                opt.innerHTML = country;
                opt.value = country;
                document.getElementById('country').appendChild(opt);
            }
        }
    </script>

    <script>
        function deleteOptions() {
            var parent = document.getElementById('city');
            var nodes = parent.childNodes;
            for (var i = 0; i < nodes.length; i++) {
                var elem = nodes[i];
                if (elem.nodeValue !== '') {
                    parent.removeChild(elem);
                    i--;
                }
            }
        }

        function getCities() {
            deleteOptions();
            var countryVal = $('#country').val();
            $.ajax({
                type: 'POST',
                url:'./city',
                data: countryVal,
                datatype: 'json',
                success: function (data, status, settings) {
                    var arr = data['cities'];
                    for (var i = 0; i < arr.length; i++) {
                        var country = arr[i];
                        var opt = document.createElement('option');
                        opt.innerHTML = country;
                        opt.value = country;
                        document.getElementById('city').appendChild(opt);
                    }
                }
            })
        }
    </script>
</head>
<body>
    <div class="container-fluid">
        <h3 class="col-sm-offset-1">Update User with id = <c:out value="${param.id}"/>:</h3>
        <br/>
        <form class="form-horizontal" action="${pageContext.request.contextPath}/edit" method="post">

            <div class="form-group">
                <label class="control-label col-sm-1" for="name">Name:</label>
                <div class="col-sm-4">
                    <input type="text" name="name" class="form-control" id="name" value="<c:out value="${param.name}"/>">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1" for="login">Login:</label>
                <div class="col-sm-4">
                    <input type="text" name="login" class="form-control" id="login" value="<c:out value="${param.login}"/>">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1" for="email">Email:</label>
                <div class="col-sm-4">
                    <input type="text" name="email" class="form-control" id="email" value="<c:out value="${param.email}"/>">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1" for="password">Password:</label>
                <div class="col-sm-4">
                    <input type="password" name="password" class="form-control" id="password" >
                </div>
            </div>

            <c:if test="${sessionScope.role == 'admin'}">
                <div class="form-group">
                    <label class="control-label col-sm-1" for="role">Role:</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="role" name="role">
                            <option value="user">user</option>
                            <option value="admin">admin</option>
                        </select>
                    </div>
                </div>
            </c:if>

            <div class="form-group">
                <label class="control-label col-sm-1" for="country">Country:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="country" name="country" onchange="getCities()">
                        <option value="" disabled selected>Select your option</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1" for="city">City:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="city" name="city">
                        <option value="" disabled selected>Select your option</option>
                    </select>
                </div>
            </div>

            <br/> <br/>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <button type="submit" class="btn btn-default" onclick="return validate()">Submit</button>
                </div>
            </div>

            <input type="hidden" name="id" value="<c:out value="${param.id}"/>"/>
            <c:if test="${sessionScope.role != 'admin'}">
                <input type="hidden" name="role" value="user"/>
            </c:if>

        </form>
    </div>
</body>
</html>