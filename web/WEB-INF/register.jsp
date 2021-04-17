<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
<div style="margin:0 25% 0 25%; width:50%;">
    <form method="post">
        <p>Логин:<input class="form-control" maxlength="20" name="login" type="text"></p>
        <p>Пароль:<input class="form-control" maxlength="15" name="password" type="password"></p>
        <p>Роль:<select id="roleSelector" class="custom-select" name="roleSelector" size="1">
            <option>ADMIN</option>
            <option>USER</option>
        </select></p>
        <p align="center"><a><input name="regButton" class="btn btn-success btn-lg" type="submit" value="Зарегистрироваться"></a></p>
    </form>
</div>
</body>
</html>