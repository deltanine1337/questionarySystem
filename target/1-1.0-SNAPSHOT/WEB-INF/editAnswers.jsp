<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить ответы</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
            type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var answers = '${answers}';
            var ansSplit = answers.split("@");
            $('<ol>').appendTo('#answers');
            $.each(ansSplit, function (index, value) {
                $('<li>' + value + '</li>').appendTo('#answers');
            });
            $('</ol>').appendTo('#answers');
            $('ol li:last').remove();
        });
    </script>
</head>
<body>
<form method="post">
    <p class="h4" id="answers" style="padding-left:25px;">Ответы:</p>
    <p class="h5" style="padding-left:25px;">Новые ответы:<input class="form-control" placeholder="Введите формулировки ответов через символ &quot;;&quot;" type="text" name="newAnswers"></p>
    <p align="center" style="padding-top:15px;"><a href="/questions"><button class="btn btn-success btn-lg" name="editAnswersButton" type="submit">Изменить ответы</button></a></p>
</form>
</body>
</html>
