        //訂位的JS

        $(document).ready(function() {
        	//var tomorrow = new Date(today.getTime() + 24 * 60 * 60 * 1000); 
        	$(".datepicker").datepicker({
                numberOfMonths: 2,
                dateFormat: "yy-mm-dd",
               minDate : new Date(),
                maxDate: "+30d",
              // startDate: tomorrow,
                beforeShowDay: noSomedays,
                onSelect: function(date) {
                	$(".bookingDate").val(date);
                }
             });
        });
       


      