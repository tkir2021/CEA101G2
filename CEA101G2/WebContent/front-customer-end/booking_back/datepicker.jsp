<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <title>取DatePicker的日期</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <!-- <link rel="stylesheet" href="/resources/demos/style.css" /> -->
</head>

<body>
    <div class="calendar"></div>
    <input class="bookingDate" />
    <div class="datepicker"></div>
    <script>
        // $('.datepicker').datepicker({
        //     numberOfMonths: 2,
        //     dateFormat: "yy-mm-dd",
        //     onSelect: function(dateText) {
        //         $(".bookingDate").val(dateText);
        //     }
        // });

        $(".calendar").datepicker({
            numberOfMonths: 2,
            dateFormat: "yy-mm-dd",
            onSelect: function(date) {
                $(".bookingDate").val(date);
            }
        });

        // $(".bookingDate").val($(".calendar").datepicker('getDate'));
    </script>
</body>