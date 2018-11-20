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
	<div id="img-background">
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
							<a class="nav-link" href="/standings/La Liga">Standings</a>
						</li>
						<li class="nav-item">
							<a class="nav-link active" href="/ranking">Rankings</a>
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
				<div class="tab-content">
					<table class="table table-dark">
							<thead>
									<th scope="col">#</th>
									<th scope="col">name</th>
									<th scope="col">score</th>

							</thead>
							<tbody>
								
				<c:forEach items="${ranking}" var="r" varStatus="loop">
						<tr>
							<th scope="row">${r.score}</th>
							
								<c:choose>
										<c:when test="${sessionScope.user.name == r.name}">
											<td><p>${r.name}</p></td>
										</c:when>
										<c:otherwise>
											<td><p class="text-white-50">${r.name}</p></td>
										</c:otherwise>
								</c:choose>
							
							<td>${r.rank}
						</tr>
					</c:forEach>
							</tbody>
					</table>
				</div>
			</div>	
	</div>
</body>
</html>