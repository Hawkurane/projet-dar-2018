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

<title>Bookmakers</title>

</head>
<body>
        <c:if test="${empty sessionScope.user}">
			<c:redirect url="/"/>
		</c:if>
	            <header class="sticky-top" style="max-width:100%">
                    <nav class="navbar navbar-expand-sm bg-dark navbar-dark py-1">
                        <a class="navbar-brand" href="/"> Bookmakers </a>
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="/matches">Matches</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/standings/La Liga">Standings</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/ranking">Rankings</a>
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
                            <c:when test="${currentleague == ligue}">
                                <a href=${"/standings/".concat(ligue.replaceAll(" ","%20"))} class="nav-link active"> ${ligue} </a>
                            </c:when>
                            <c:otherwise>
                                <a href=${"/standings/".concat(ligue.replaceAll(" ","%20"))} class="nav-link"> ${ligue} </a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
                </ul>
            </nav>
            <div class="tab-content">
                    
                    <table class="table table-dark">
                        <thead>
                            <th scope="col">#</th>
                            <th scope="col">Team Name</th>
                            <th scope="col">Points</th>
                            <th scope="col">Wins</th>
                            <th scope="col">Losses</th>
                            <th scope="col">Draws</th>
                            <th scope="col">Goals (For/Against)</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${standing}" var="s" varStatus="loop">
                                <tr>
                                    <th scope="row">${s.position}</th>
                                    <td> ${s.teamName} </td>
                                    <td> ${s.points} </td>
                                    <td> ${s.won} </td>
                                    <td> ${s.lost} </td>
                                    <td> ${s.draw} </td>
                                    <td> ${s.goalsFor} / ${s.goalsAgainst}
                                    
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

            </div>

        </div>
                

</body>
</html>