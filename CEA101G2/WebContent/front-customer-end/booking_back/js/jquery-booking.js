        //訂位的JS

        $(document).ready(function() {
            $(".calendar").datepicker({
                numberOfMonths: 2,
                dateFormat: "yy-mm-dd",
                minDate: -20,
                maxDate: "+1M +10D",
                onSelect: function(date) {
                    $(".bookingDate").val(date);
                }
            });
        });