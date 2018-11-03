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
        <link href="open-iconic-master/font/css/open-iconic-bootstrap.css" rel="stylesheet">

        <title>Bookmakers</title>

    </head>
    <body>
        <c:choose>
            <c:when test="${!empty sessionScope.user}">

                <div class="wrapper animated bounce">
                    <h1>Bookmakers</h1>
                    <hr>
                    <form method="post" action="login">
                        <label id="icon" for="username"><i class="fa fa-user"></i></label>
                        <input type="text" placeholder="Username" id="username" name="userlogin">
                        <label id="icon" for="password"><i class="fa fa-key"></i></label>
                        <input type="password" placeholder="Password" id="password" name="pwdlogin">
                        <input type="submit" value="Sign In">
                        <hr>
                        <div class="crtacc"><a href="register.jsp">Create Account</a></div>
                    </form>
                </div>
                

            </c:when>
            <c:otherwise>
                
                <header>
                    <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top py-1">
                        <a class="navbar-brand" href="/"> Bookmakers </a>
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="matches.jsp">Matches</a>
                            </li>
                        </ul>
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <form method="post" action="logout">
                                    <button type="submit" class="btn btn-dark btn-outline-light btn-sm">
                                        <span class="oi oi-account-logout"></span> Logout
                                    </button>
                                </form>
                            </li>
                        </ul>
                    </nav>
                </header>
                

            
            
            </c:otherwise>
        </c:choose>
    </body>
    <script src="bootstrap/js/jquery-3.3.1.slim.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</html>