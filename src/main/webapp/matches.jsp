<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<link href="bootstrap/css/custom.css" rel="stylesheet">

<title>Pronostiqueurs</title>

</head>
<body>

    <header>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top py-1">
            <a class="navbar-brand" href="/"> Bookmakers </a>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                     <a class="nav-link" href="matches.jsp">Matches</a>
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

	<div class="container" style="margin-top:80px">
		<div class="row">
			<div class="col-sm-12">
				<form id="matches-form">
					<label for="matchday">Journées Numéro : </label> 
					<select	name="matchday" id="matchday">
						<option value=""></option>
						<c:forEach items="${ form.matchday }" var="matchday">
							<option value="${matchday}">${matchday}</option>
						</c:forEach>
					</select>

					<label for="status">type de match : </label>
					<select name="status" id="status">
						<c:forEach items="${ form.status }" var="status">
							<option value="${status}">${status}</option>
						</c:forEach>
					</select>

					<label for="teamName">Nom d'equipe : </label>
					<select name="teamName"	id="teamname">
						<option value=""></option>
						<c:forEach items="${ form.teamName }" var="teamName">
							<option value="${teamName}">${teamName}</option>
						</c:forEach>
					</select>

					<label for="league">Ligue : </label>
					<select name="league" id="league">
						<option value=""></option>
						<c:forEach items="${ form.league }" var="league">
							<option value="${league}">${league}</option>
						</c:forEach>
					</select>
				</form>
				<button id="send">rechercher</button>
			</div>
		</div>
	 </div>
	
</body>


    <script src="bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">

		$("#send").click(function(e){ 
			console.log('sending request to /search -'
					+document.getElementById("matchday").value+'-'
					+document.getElementById("status").value+'- '
					+document.getElementById("teamname").value+'-'
					+document.getElementById("league").value)+'-';
				$.get(
					"/search",
					{
						matchday: document.getElementById("matchday").value,
						status: document.getElementById("status").value,
						teamname: document.getElementById("teamname").value,
						league: document.getElementById("league").value,
					}
					,function( data ) {
						//$( "body" ).append( data )
						console.log(data);
						}, 'json' );
				this.focus();
		});
	</script>
</html>

