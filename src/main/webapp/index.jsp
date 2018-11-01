<%@ page pageEncoding = "UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="bootstrap/css/custom.css" rel="stylesheet">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

        <title>Bookmakers</title>

    </head>
    <body>
        <c:choose>
            <c:when test="${!empty sessionScope.user}">

                <p> Hello </p>

            </c:when>
            <c:otherwise>
                
                <div class="wrapper animated bounce">
                    <h1>Bookmakers</h1>
                    <hr>
                    <form method="post" action="login">
                        <label id="icon" for="password"><i class="fa fa-user"></i></label>
                        <input type="text" placeholder="Username" id="username" name="userlogin">
                        <label id="icon" for="password"><i class="fa fa-key"></i></label>
                        <input type="password" placeholder="Password" id="password" name="pwdlogin">
                        <input type="submit" value="Sign In">
                        <hr>
                        <div class="crtacc"><a href="#">Create Account</a></div>
                    </form>
                </div>

            </c:otherwise>
        </c:choose>
    </body>
    <script src="bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</html>