<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ответы пользователя</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/javascript">
        $(document).ready(function() {
            var questions = '${questionFormulations}';
            var answers = '${answerFormulations}';
            var ansSplit = answers.split("|");
            var split1 = questions.split("@");

            var url_string = window.location.href;
            var url = new URL(url_string);
            var quest = url.searchParams.get("quest");
            var user = url.searchParams.get("role");
            $('<h4 class="h4" align="center">Анкета:  ' + quest + '      Пользователь:  ' + user + '</h4>').appendTo('body');
            $.each(ansSplit, function (index, value) {
                $('<h5 class="h5">' + (index+1) + '. ' + split1[index] + '</h5>').appendTo('body');
                var ansSplitSplit = value.split("@");
                $.each(ansSplitSplit, function (index, value) {
                    $('<p style="margin-left: 30px;">' + value + '</p>').appendTo('body');
                });
            });
            $('body').append('<p align="center"><a href="/questionaries?role=admin"><button style="margin-top:5px; text-align: center;" class="btn btn-warning btn-lg">Назад</button></a></p>');
        });
    </script>
</head>
<body>

</body>
</html>
