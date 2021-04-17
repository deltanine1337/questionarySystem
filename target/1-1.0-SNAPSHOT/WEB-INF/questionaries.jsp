<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">
   <title>Анкеты</title>
   <link rel="stylesheet" type="text/css" href="style.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
            type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
   <script src="js/script.js" type="text/javascript"></script>
   <script type="text/javascript">
       $(document).ready(function() {
           var quests = '${quests}';
           var split = quests.split(",");
           var users = '${users}';
           var usersSplit = users.split("@");
           $.unique(usersSplit);
           var url_string = window.location.href;
           var url = new URL(url_string);
           var role = url.searchParams.get("role");
           if (role == "admin") {
               $('#addB').prop("disabled", false);
               $('body').append('<form method="post"><p align="center">Просмотреть ответы пользователя ' +
                   '<select class="custom-select" style="width: 150px;" id="userSelector" name="userSelector" size="1"></select> в анкете ' +
                   '<select class="custom-select" style="width: 150px;" id="queSelector" name="queSelector" size="1"></select>    ' +
                   '<input name="userButton" class="btn btn-success btn-lg" type="submit" value="Просмотреть"></p></form>');
               $.each(usersSplit, function (index, value) {
                   $('#userSelector').append('<option>' + value + '</option>');
               });
           }
           $.each(split, function (index, value) {
               if(role == "admin"){
                   $('body').append('<p style="padding-top:3px;padding-left:15px;"><a class="btn btn-primary btn-lg active" role="button" aria-pressed="true" href="/questions?quest=' + value + '">'
                       + value + '</a></p>');
                   $('#queSelector').append('<option>' + value + '</option>');
               }
               else {
                   $('body').append('<p style="padding-top:3px;padding-left:15px;">' + value + '<a href="/fillQuestionary?role=' + role + '&quest=' + value + '">       <button class="btn btn-outline-success" name="fillButton" type="submit" value="' + value + '">Заполнить анкету</button></a></p>');
               }
           });
           $('body p:last').remove();
           $('#userSelector option:last').remove();
           $('#queSelector option:last').remove();
});
</script>
</head>
<body>
<p align="center"><a href="#modal1" class="open_modal"><input disabled id="addB" name="addButton" class="btn btn-success btn-lg" type="button" value="Добавить анкету"></a></p>
<p id="output"></p>
<div id="modal1" class="modal_div">
<span class="modal_close">✖</span>
    <form method="post">
        <p style="padding-top:20px;"><input class="form-control" type="text" placeholder="Введите название анкеты"  name="questName"></p>
        <p align="center" style="padding-top:5px;"><a href="/questionaries"><button class="btn btn-success btn-sm" type="submit">Добавить</button></a></p>
    </form>
</div>
<div id="overlay"></div>
</body>
</html>