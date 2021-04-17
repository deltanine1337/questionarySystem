<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заполнить анкету</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
            type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var questions = '${questions}';
            var answers = '${answers}';
            var nums_of_answers = '${nums_of_answers}';
            var numsSplit = nums_of_answers.split("@");
            var ansSplit = answers.split("|");
            var split1 = questions.split("@");
            var url_string = window.location.href;
            var url = new URL(url_string);
            var user = url.searchParams.get("role");
            $.each(ansSplit, function (index, value) {
                $('<h4 class="h4">' + (index+1) + '. ' + split1[index] + '</h4>').appendTo('body');
                var ansSplitSplit = value.split("@");
                var qindex = index;
                var question = split1[index];
                $.each(ansSplitSplit, function (index, value) {
                    if (numsSplit[qindex] == 1) {
                        $('<p style="margin-left:25px;"><input id="answerInp" type="radio" name="' + qindex + '" value="' + value + '&' + question + '">   ' + value + '</p>').appendTo('body');
                    }
                    else {
                        $('<p style="margin-left:25px;"><input id="answerInp" type="checkbox" value="' + value + '&' + question + '">   ' + value + '</p>').appendTo('body');
                    }

                });
            });
            $('body').append('<p align="center"><small class="text-muted">После заполнения анкеты нажмите на кнопку "Записать результаты, а затем на кнопку "Сохранить"</small></p>');
            $('body').append('<p align="center"><button style="margin-top:15px; text-align: center;" class="btn btn-info btn-lg" id="saveResult">Записать результаты</button></p>');
            $('body').append('<div style="margin-left: 45%;"><form method="post"><input id="user1Answers" name="userAnswers" type="hidden" value=""><a href="/questionaries?role=' + user + '"><button style="margin-top:5px; text-align: center;" class="btn btn-success btn-lg" id="saveButton">Сохранить</button></a></form></div>');

        });
    </script>
    <script type="text/javascript">
        function save() {
            $('#user1Answers').val('');
            $('#answerInp:checked').each(function() {
                $('#user1Answers').val($('#user1Answers').val() + $(this).val() + '@');
            });
            $('#saveButton').attr('disabled',false);
            var url_string = window.location.href;
            var url = new URL(url_string);
            var user = url.searchParams.get("role");

        }
        $(function () {
            $('#saveResult').on('click', save);
        });
    </script>
</head>
<body>

</body>
</html>
