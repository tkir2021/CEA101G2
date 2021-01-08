        $(document).ready(function() {
            $('.timepicker1, .timepicker2').timepicker({
                timeFormat: 'hh:mm p',
                interval: 60,
                minTime: '0',
                maxTime: '11:00 pm',
                startTime: '7:00',
                default: new Date(),
                dynamic: true,
                dropdown: true,
                scrollbar: false,
            });
        });