<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div style="margin:0 25% 0 25%; width:50%;">
<form method="post">
    <p>Логин:<input class="form-control" maxlength="20" name="login" type="text"></p>
    <p>Пароль:<input class="form-control" maxlength="15" name="password" type="password"></p>
    <p><a><input name="loginButton" type="submit" class="btn btn-success btn-lg" value="Войти"></a></p>
</form>
</div>
</body>
</html>