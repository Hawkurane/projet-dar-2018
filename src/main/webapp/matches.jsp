<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div class="row" id="querycontainer">
			<div class="col-sm-12">
				<form id="matches-form">

						<div class="form-group row">
							<label class="col-sm-2 col-form-label" for="matchday">Day n°: </label>
							<div class="col-sm-10">
								<select class="form-control" id="matchday" name="matchday">
									<option value=""></option>
									<c:forEach items="${ form.matchday }" var="matchday">
									<option value="${matchday}">${matchday}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label" for="status">Type of match:</label>
							<div class="col-sm-10">
								<select class="form-control" id="status" name="status">
									<c:forEach items="${ form.status }" var="status">
										<option value="${status}">${status}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label" for="teamName">Team name:</label>
							<div class="col-sm-10">
								<select class="form-control" id="teamname" name="teamName">
								<option value=""></option>
								<c:forEach items="${ form.teamName }" var="teamName">
									<option value="${teamName}">${teamName}</option>
								</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label" for="league">League:</label>
							<div class="col-sm-10">
								<select class="form-control" id="league" name="league">
									<option value=""></option>
									<c:forEach items="${ form.league }" var="league">
										<option value="${league}">${league}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					
					<button type="button" class="btn btn-primary" id="send"> Search </button>
				</form>
				<!--<button id="send">rechercher</button>-->
			</div>
		</div>
	</div>
	<!--
	 <div class="row" id="resultContainer">
		
	 </div>
	 -->
	
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
						/*
						var myJSON = JSON.stringify(data);
						document.getElementById("resultContainer").innerHTML = myJSON;
						*/

						data.forEach(function(element){
							//var html_to_insert = "<div class='row'><div class='col-sm-12'>"+JSON.stringify(element)+"</div></div> <hr>";
							var html_to_insert = "<div class='row'>";
							html_to_insert    += "<div class='row col-sm-12'>";
							html_to_insert    += "<div class='col-sm-2'>Match n°"+element.matchId+".</div>";
							html_to_insert    += "<div class='col-sm-2'>Day n°"+element.matchDay+".</div>";
							html_to_insert    += "<div class='col-sm-8'>Time: "+element.time+". ("+element.status+")</div>";
							html_to_insert    += "</div>";
							html_to_insert    += "<div class='row col-sm-12'>";
							html_to_insert    += "<div class='col-sm-5'>"+element.homeTeamName+"</div>";
							html_to_insert    += "<div class='col-sm-2'>vs.</div>";
							html_to_insert    += "<div class='col-sm-5'>"+element.awayTeamName+"</div>";
							html_to_insert    += "</div>";
							html_to_insert    += "</div>";
							
							document.getElementById("querycontainer").insertAdjacentHTML('beforeend', html_to_insert);
						});



					}
					,'json'
				);
				this.focus();
		});
	</script>
</html>

