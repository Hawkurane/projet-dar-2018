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
                
            <header class="sticky-top" style="max-width:100%">
                    <nav class="navbar navbar-expand-sm bg-dark navbar-dark py-1">
                        <a class="navbar-brand" href="/"> Bookmakers </a>
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="/matches">Matches</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/standings/La Liga">Standings</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link disabled" href="#">Profile</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link disabled" href="#">Rankings</a>
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
                
                <div class="container" style="margin-top: 20px;">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="card" style="width: 18rem;">
                                <div class="card-header">
                                    Isiaka SANOU
                                </div>
                                <div class="card-body">                                    
                                    Title: Prophet <br>
                                    Rank: #1 <br>
                                    Delivery: 809 (96.6%)
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <p>
                                Le passage de Lorem Ipsum standard, utilisé depuis 1500 "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." Section 1.10.32 du "De Finibus Bonorum et Malorum" de Ciceron (45 av. J.-C.) "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore 
                            </p>
                        </div>
                    </div>
                </div>

                

            
            
            </c:otherwise>
        </c:choose>
    </body>
    <script src="bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/loadIndex.js"></script>
</html>