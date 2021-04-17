<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вопросы</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
            type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/javascript">
        $(document).ready(function() {
            var questions = '${questions}';
            var answers = '${answers}';
            var ansSplit = answers.split("|");
            var split1 = questions.split("@");
            var url_string = window.location.href;
            var url = new URL(url_string);
            var quest = url.searchParams.get("quest");
            $.each(ansSplit, function (index, value) {

                    $('<form method="post"><h5 class = "h5">' + (index + 1) + '. ' + split1[index] + '       <a href="/questions?quest=' + quest + '"><button class="btn btn-info" name="deleteButton" type="submit" value="' + split1[index] + '">Удалить вопрос</button>   </a>     <a href="/EditAnswers?quest=' + quest + '&question=' + split1[index] + '"><button class="btn btn-info" name="changeButton" type="submit" value="' + split1[index] + '">Изменить ответы</button></a></h5></form><ul class="list-group">').appendTo('body');

                var ansSplitSplit = value.split("@");
                $.each(ansSplitSplit, function (index, value) {
                    $('<li class="list-group-item">' + value + '</li>').appendTo('body');
                });
                $('</ul>').appendTo('body');
            });

            $.each(split1, function (index, value) {
                $('#questionSelector').append('<option>' + value + '</option>');
            });
            $('select option:last').remove();
});
</script>
<script src="js/script.js" type="text/javascript"></script>
</head>
<body>
<p align="center" style="padding-top:10px;"><a href="#modal1" class="open_modal"><input class="btn btn-primary btn-sm" name="addButton" type="button" value="Добавить вопрос"></a>
<a href="#modal2" class="open_modal"><input class="btn btn-primary btn-sm" name="addButton" type="button" value="Добавить ответы"></a></p>
<p id="output"></p>
<div id="modal1" class="modal_div">
<span class="modal_close">✖</span>
<form method="post">
<p style="padding-top:20px;">Формулировка:<input class="form-control" placeholder="Введите формулировку вопроса" type="text" name="formulation"></p>
<p>Количество ответов:<select class="custom-select" id="numSelector" name="numSelector" size="1">
    <option>Один</option>
    <option>Несколько</option>
</select></p>
<p align="center" style="padding-top:5px;"><a href="/questions"><button class="btn btn-success btn-sm" name="addQuestion" type="submit">Добавить вопрос</button></a></p>
</form>
</div>
<div style="width: 500px; margin-left: -250px;"  id="modal2" class="modal_div">
<span class="modal_close">✖</span>
<form method="post">
<p style="padding-top:20px;">Формулировка ответов:<input class="form-control" placeholder="Введите формулировки ответов через символ &quot;;&quot;" type="text" name="formulation"></p>
<p>Вопрос:<select class="custom-select" id="questionSelector" name="questionSelector" size="1">
</select></p>
<p align="center" style="padding-top:5px;"><a href="/questions"><button class="btn btn-success btn-sm" name="addAnswer" type="submit">Добавить ответы</button></a></p>
</form>
</div>
<div id="overlay"></div>
</body>
</html>
