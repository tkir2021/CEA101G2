        /*  ---------------------------------------------------
            Template Name: Directing
            Description:  Directing directory listing HTML Template
            Author: Colorlib
            Author URI: https://colorlib.com
            Version: 1.0
            Created: Colorlib
        ---------------------------------------------------------  */

        'use strict';

        (function($) {

            /*------------------
                Preloader
            --------------------*/
            $(window).on('load', function() {
                $(".loader").fadeOut();
                $("#preloder").delay(200).fadeOut("slow");
            });

            /*------------------
                Background Set
            --------------------*/
            $('.set-bg').each(function() {
                var bg = $(this).data('setbg');
                $(this).css('background-image', 'url(' + bg + ')');
            });


            /*------------------
        Single Product
    --------------------*/
            $('.listing__details__gallery__slider img').on('click', function() {

                var imgurl = $(this).data('imgbigurl');
                var bigImg = $('.listing__details__gallery__item__large').attr('src');
                if (imgurl != bigImg) {
                    $('.listing__details__gallery__item__large').attr({
                        src: imgurl
                    });
                }
            });


        })(jQuery);