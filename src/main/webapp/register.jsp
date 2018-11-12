<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="bootstrap/css/custom.css" rel="stylesheet">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

        <title>Bookmakers</title>

    </head>

    <body>
        <div class="wrapper animated bounce">
            <h1>Bookmakers</h1>
            <hr>
            <form method="post" action="register" oninput='pwdregisterconfirm.setCustomValidity(pwdregisterconfirm.value != pwdregister.value ? "Passwords do not match." : "")'>
                <label id="icon" for="username"><i class="fa fa-user"></i></label>
                <input type="text" placeholder="Username" id="username" name="userregister">

                <label id="icon" for="mail"><i class="fa fa-envelope-o"></i></label>
                <input type="text" placeholder="Mail" id="mail" name="mailregister">

                <label id="icon" for="password"><i class="fa fa-key"></i></label>
                <input type="password" placeholder="Password" id="password" name="pwdregister">

                <label id="icon" for="passwordconfirm"><i class="fa fa-key"></i></label>
                <input type="password" placeholder="Confirm password" id="passwordconfirm" name="pwdregisterconfirm">

                <input type="submit" value="Register">
                <hr>
                <div class="crtacc"><a href="/">Login</a></div>
            </form>
        </div>
    </body>

    <script src="bootstrap/js/jquery-3.3.1.slim.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

</html>