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
	<div id="img-background">
		<c:if test="${empty sessionScope.user}">
			<c:redirect url="/"/>
		</c:if>
            <header class="sticky-top" style="max-width:100%">
                    <nav class="navbar navbar-expand-sm bg-dark navbar-dark py-1">
                        <a class="navbar-brand" href="/"> Bookmakers </a>
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link active" href="/matches">Matches</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/standings/La Liga">Standings</a>
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
                

		<div class="container" >
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
					<hr>
					<div id="resultContainer">
		
					</div>
				</div>
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
					},
					function( data ) {
						//$( "body" ).append( data )
						console.log(data);
						/*
						var myJSON = JSON.stringify(data);
						document.getElementById("resultContainer").innerHTML = myJSON;
						*/
						var html_to_insert = '';
						data.forEach(function(element){
							//var html_to_insert = "<div class='row'><div class='col-sm-12'>"+JSON.stringify(element)+"</div></div> <hr>";
							
							if(element.bet==null){
								html_to_insert 	  += "<div class='row' style='margin-bottom:10px; text-align: center'>";
								html_to_insert    += "	<div class='card w-100'>";
								html_to_insert    += "		<div class='card-header'>";
								html_to_insert    += "			["+element.league+"] Match n°"+element.matchId+", Day n°"+element.matchDay+", "+element.time+" ("+element.status+")";
								html_to_insert    += "		</div>";
								html_to_insert    += "		<div class='card-body'>";
								html_to_insert    += "			<div class='media'>";
								html_to_insert    += "				<img class='align-self-center mr-3 img-thumbnail rounded-circle scale-down' src="+element.homeTeamLogoUrl+" alt='Home team Logo' style='width:64px;height=64px'>";
								html_to_insert	  += "				<div class='media-body'>";
								html_to_insert    += "					<p class='card-text'>"+element.homeTeamName+" "+element.homeTeamg+" - "+element.awayTeamg+" "+element.awayTeamName+"</p>";
								html_to_insert	  += "				</div>";
								html_to_insert    += "				<img class='align-self-center ml-3 img-thumbnail rounded-circle scale-down' src="+element.awayTeamLogoUrl+" alt='Away team Logo' style='width:64px;height=64px'>";
								html_to_insert    += "			</div>";
								html_to_insert    += "			<div class='row'>";
								html_to_insert    += "				<div class='col-sm-4'>";
								html_to_insert    += "					<button type='button' class='btn btn-primary text-center' onClick='$.post(\"/insert\", {insertType: \"bet\", matchId: "+element.matchId+", bet:\"WIN\"}, function(data){console.log(data)},\"json\")'> Win </button>";
								html_to_insert    += "				</div>";
								html_to_insert    += "				<div class='col-sm-4'>";
								html_to_insert    += "					<button type='button' class='btn btn-primary text-center' onClick='$.post(\"/insert\", {insertType: \"bet\", matchId: "+element.matchId+", bet:\"DRAW\"}, function(data){console.log(data)},\"json\")'> Draw </button>";
								html_to_insert    += "				</div>";
								html_to_insert    += "				<div class='col-sm-4'>";
								html_to_insert    += "					<button type='button' class='btn btn-primary text-center' onClick='$.post(\"/insert\", {insertType: \"bet\", matchId: "+element.matchId+", bet:\"LOSE\"}, function(data){console.log(data)},\"json\")'> Loss </button>";
								html_to_insert    += "				</div>";
								html_to_insert    += "			</div>";
								html_to_insert    += "		</div>";
								html_to_insert    += "	</div>";
								html_to_insert    += "</div>";

								

							}
							
							
						});

						document.getElementById("resultContainer").innerHTML = html_to_insert;

					}
					,'json'
				);
				this.focus();
		});
	</script>
</html>

