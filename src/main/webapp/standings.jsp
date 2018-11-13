<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<link href="/bootstrap/css/custom.css" rel="stylesheet">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="/open-iconic-master/font/css/open-iconic-bootstrap.css"
	rel="stylesheet">

<title>Pronostiqueurs</title>

</head>
<body>
	<div id="img-background">
	            <header class="sticky-top" style="max-width:100%">
                    <nav class="navbar navbar-expand-sm bg-dark navbar-dark py-1">
                        <a class="navbar-brand" href="/"> Bookmakers </a>
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="/matches">Matches</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/standings">Standings</a>
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

        <div class="container" style="margin-top: 20px">
            <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                <ul class="navbar-nav">
                    <c:forEach items="${ form }" var="ligue">
                    <li class="nav-items">
                        <c:choose>
                            <c:when test="${currentleague} != ${ligue}">
                                <a href=${"/standings/".concat(ligue.replaceAll(" ","%20"))} class="nav-link"> ${ligue} </a>
                            </c:when>
                            <c:otherwise>
                                <a href=${"/standings/".concat(ligue.replaceAll(" ","%20"))} class="nav-link active"> ${ligue} </a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
                </ul>
            </nav>
            <div class="tab-content">
                    ${currentleague}
                    
                    <table class="table table-dark">
                        <thead>
                            <th scope="col">#</th>
                            <th scope="col">Team Name</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${standing}" var="s" varStatus="loop">
                                <tr>
                                    <th scope="row">${loop.index + 1}</th>
                                    <td> ${s.teamName}
                                </tr>
                            </c:forEach>
                            <!--
                            <tr>
                                <th scope="row">1</th>
                                <td>FC Barcelona</td>
                            </tr>
                            <tr>
                                <th scope="row">2</th>
                                <td>Club Atlético de Madrid</td>
                            </tr>
                            <tr>
                                <th scope="row">3</th>
                                <td>Deportivo Alavés</td>
                            </tr>
                            <tr>
                                <th scope="row">4</th>
                                <td>Sevilla FC</td>
                            </tr>
                            <tr>
                                <th scope="row">5</th>
                                <td>RCD Espanyol de Barcelona</td>
                            </tr>
                            <tr>
                                <th scope="row">6</th>
                                <td>Real Madrid CF</td>
                            </tr>
                            <tr>
                                <th scope="row">7</th>
                                <td>Levante UD</td>
                            </tr>
                            <tr>
                                <th scope="row">8</th>
                                <td>Real Valladolid CF</td>
                            </tr>
                            <tr>
                                <th scope="row">9</th>
                                <td>Girona FC</td>
                            </tr>
                            <tr>
                                <th scope="row">10</th>
                                <td>Real Betis Balompié</td>
                            </tr>
                            <tr>
                                <th scope="row">11</th>
                                <td>Real Sociedad de Fútbol</td>
                            </tr>
                            <tr>
                                <th scope="row">12</th>
                                <td>Getafe CF</td>
                            </tr>
                            <tr>
                                <th scope="row">13</th>
                                <td>SD Eibar</td>
                            </tr>
                            <tr>
                                <th scope="row">14</th>
                                <td>RC Celta de Vigo</td>
                            </tr>
                            <tr>
                                <th scope="row">15</th>
                                <td>Valencia CF</td>
                            </tr>
                            <tr>
                                <th scope="row">16</th>
                                <td>Villarreal CF</td>
                            </tr>
                            <tr>
                                <th scope="row">17</th>
                                <td>Athletic Club</td>
                            </tr>
                            <tr>
                                <th scope="row">18</th>
                                <td>CD Leganés</td>
                            </tr>
                            <tr>
                                <th scope="row">19</th>
                                <td>Rayo Vallecano de Madrid</td>
                            </tr>
                            <tr>
                                <th scope="row">20</th>
                                <td>SD Huesca</td>
                            </tr>
                            -->

                        </tbody>
                    </table>

            </div>

        </div>
                

	</div>
</body>
</html>