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
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="open-iconic-master/font/css/open-iconic-bootstrap.css"
	rel="stylesheet">

<title>Pronostiqueurs</title>

</head>
<body>
	<ul>
        ligue
		<c:forEach items="${ form }" var="ligue">
		<li>
		      ${ligue}
		</li>
		</c:forEach>

	</ul>
	
	<ul>
        stanging
        <c:forEach items="${ standing }" var="ligue">
        <li>
              ${}
        </li>
        </c:forEach>

    </ul>
</body>
</html>