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
        Navigation
    --------------------*/
            $(".mobile-menu").slicknav({
                prependTo: '#mobile-menu-wrap',
                allowParentLinks: true
            });

            /*--------------------------
            Testimonial Slider
            ----------------------------*/
            var testimonialSlider = $(".testimonial__slider");
            testimonialSlider.owlCarousel({
                loop: true,
                margin: 0,
                items: 1,
                dots: false,
                nav: true,
                navText: ["<span class='arrow_left'><span/>", "<span class='arrow_right'><span/>"],
                smartSpeed: 1200,
                autoHeight: false,
                autoplay: false,
                startPosition: 'URLHash',
                animateOut: 'fadeOut',
                animateIn: 'fadeIn',
            });

            /*-----------------------------
                Listing Details Slider
            -------------------------------*/
            $(".listing__details__gallery__slider").owlCarousel({
                loop: true,
                margin: 20,
                items: 4,
                dots: true,
                smartSpeed: 1200,
                autoHeight: false,
                autoplay: true,
            });

            /*-----------------------
        Price Range Radius
    ------------------------ */
            var rangeSlider = $(".price-range-radius"),
                radius = $("#radius");
            rangeSlider.slider({
                range: 'min',
                min: 0,
                max: 2,
                value: 1,
                slide: function(event, ui) {
                    radius.val(ui.value + 'km');
                }
            });
            radius.val(rangeSlider.slider("value") + 'km');

            /*-----------------------
        Price Range Slider
    ------------------------ */
            var rangeSliderPrice = $(".price-range"),
                minamount = $("#minamount");
            rangeSliderPrice.slider({
                range: 'min',
                min: 0,
                max: 80,
                value: 20,
                slide: function(event, ui) {
                    minamount.val('$' + ui.value);
                }
            });
            minamount.val('$' + rangeSliderPrice.slider("value"));

            /*--------------------------
                Select
            ----------------------------*/
            $("select").niceSelect();

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

            /*-------------------
        Quantity change
    --------------------- */
            $(".nice-scroll").niceScroll({
                cursorcolor: "#a8a8a8",
                cursorwidth: "8px",
                background: "rgba(168, 168, 168, 0.3)",
                cursorborder: "",
                autohidemode: false,
                horizrailenabled: false
            });

            $(".filter.nice-scroll").niceScroll({
                cursorcolor: "#a8a8a8",
                cursorwidth: "8px",
                background: "rgba(168, 168, 168, 0.3)",
                cursorborder: "",
                autohidemode: true,
                horizrailenabled: false
            });

            /*------------------
        Barfiller
    --------------------*/
            $('#bar1').barfiller({
                barColor: "#f03250",
            });

            $('#bar2').barfiller({
                barColor: "#f03250",
            });

            $('#bar3').barfiller({
                barColor: "#f03250",
            });

            $('#bar4').barfiller({
                barColor: "#f03250",
            });

            $('#bar5').barfiller({
                barColor: "#f03250",
            });

            /*------------------
        Magnific
    --------------------*/
            $('.video-popup').magnificPopup({
                type: 'iframe'
            });





            /*------------------
            本周熱門店家
            -------------------*/
            // let div = $("<div/>").addClass("listing__item");
            // $("#bellaTry").append(div);

            // let div1 = $("<div/>").addClass("listing__item__pic set-bg");
            // let div2 = $("<div/>").addClass("listing__item__text");
            // $("#bellaTry .listing__item").append(div1, div2);

            // let div3 = $("<div/>").addClass("listing__item__text__inside");
            // let div4 = $("<div/>").addClass("listing__item__text__info");
            // $("#bellaTry .listing__item .listing__item__text").append(div3, div4);

            /*------------------
                 平台緣起動畫
            -------------------*/
            $(document).ready(function() {
                // 在平台緣起#to_position加上click事件
                $('#to_position').click(function() {
                    // 讓捲軸用動畫的方式移動到下方 #position 的 top 位置
                    // 並加入動畫效果
                    $('html').animate({
                        scrollTop: $('#position').offset().top
                    }, 1000); // 調整秒數(毫秒)
                });

                /*------------------
                       	至頂動畫
                 -------------------*/
                $(".toTop").click(function() {
                    $("html").animate({
                        scrollTop: $("body").offset().top
                    }, 1000);
                });

                /*------------------
                      	瓶中信
              -------------------*/

                $(".gamebottle").click(function() {
                    $(".playGame").css({
                        "background": "rgba(0, 0, 0, 0.5)",
                        "z-index": 8888,
                    });
                });
            });
        })(jQuery);