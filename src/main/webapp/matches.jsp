<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<link href="bootstrap/css/custom.css" rel="stylesheet">

<title>Pronostiqueurs</title>

</head>
<body>
	<form id="matches-form">
		<label for="matchday">Journées Numéro : </label> <select
			name="matchday" id="matchday">
			<option value=""></option>
			<c:forEach items="${ form.matchday }" var="matchday">
				<option value="${matchday}">${matchday}</option>
			</c:forEach>
		</select> <label for="status">type de match : </label> <select name="status"
			id="status">
			<option value=""></option>
			<c:forEach items="${ form.status }" var="status">
				<option value="${status}">${status}</option>
			</c:forEach>
		</select> <label for="teamName">Nom d'equipe : </label> <select name="teamName"
			id="teamName">
			<option value=""></option>
			<c:forEach items="${ form.teamName }" var="teamName">
				<option value="${teamName}">${teamName}</option>
			</c:forEach>
		</select> <label for="league">Ligue : </label> <select name="league"
			id="league">
			<option value=""></option>
			<c:forEach items="${ form.league }" var="league">
				<option value="${league}">${league}</option>
			</c:forEach>
		</select>
		
	</form>
	<button id="send">rechercher</button>
</body>

<script type="text/javascript">
$("send").click(function(e){ 
        console.log("bjr");
});
</script>

</html>

