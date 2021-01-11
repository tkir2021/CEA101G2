<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>好吃泰式料理</title>
    <style type="text/css" media="screen">
        #all {
            width: 800px;
            background-color: red;
            height: auto;
            margin: 0px auto;
        }
        #left {
            width: 300px;
            /* background-color: green; */
            float: left;
        }
        #right {
            width: 500px;
            /* background-color:pink; */
            float: left;
        }
        #left img {
            width: 80px;
            height: 60px;
            margin: 5px;
            opacity: 0.7;
        }
        #left img:hover {
            cursor: pointer;
            opacity: 1;
        }
        #right img {
            width: 100%;
        }
        h1 {
            width: 800px;
            background-color: pink;
            margin: 20px auto;
            /*外距，調整h1置中*/

            text-align: center;
            /*文字內容置中*/

            color: brown;
        }
        p {
            width: 800px;
            margin: 5px auto;
            color: #666;
            letter-spacing: 1px;
            line-height: 24px;
            text-indent: 2em;
            word-spacing: 2px;
            text-align: justify;
        }
    </style>
    <script>
        function showBig() {
            var picinfo = "img/back_system/Store_pic/07/store07_0" + event.target.id + ".png"
            // alert(picinfo)
            var bigPic = document.getElementById('right').innerHTML = "<img src=" + picinfo + ">"
        }
    </script>
</head>

<body>
    <h1>好吃泰式料理</h1>
    <P>泰國菜近年來在台灣頗受歡迎，大街小巷都有泰國餐廳的蹤影，就連大型的知名餐飲品牌也插旗泰式料理的市場，桃園中壢因為外籍移工多大概可以說是泰式料理密度最高的地方，中壢人相對的對泰式料理的要求嚴格，
    	近期在外環道環北路上開了一家泰式料理餐廳：象迦泰式廚房，主打泰式新潮味，除了經典款的泰式料理外，還研發出不少象迦泰式廚房獨門的新泰式料理，加上別具風格的設計裝潢，一開幕甫成為中壢相當令人矚目的泰式料理餐廳</P>
    <p>實際品嘗象迦泰式廚房，口味相當接近正統泰國味，雖然在口味上還是有稍微調整到台灣人能接受的範圍，不過就料理的手法及口味上，還是很完整保留泰式料理的精髓，用餐環境也有別於一般泰國餐廳，用餐起來格外悠閒輕鬆，
    	而且價錢完全就是走平價路線，大啖正宗泰式料理也不用荷包大失血囉！</p>
    <div id="all">
        <div id="left">
            <img src="img/back_system/Store_pic/07/store07_01.png" alt="" id="1" onclick="showBig()">
            <img src="img/back_system/Store_pic/07/store07_02.png" alt="" id="2" onclick="showBig()">
            <img src="img/back_system/Store_pic/07/store07_03.png" alt="" id="3" onclick="showBig()">
            <img src="img/back_system/Store_pic/07/store07_04.png" alt="" id="4" onclick="showBig()">
            <img src="img/back_system/Store_pic/07/store07_05.png" alt="" id="5" onclick="showBig()">
        </div>
        <div id="right">
            <img src="img/back_system/Store_pic/07/store07_01.png" alt="">
        </div>
    </div>
</body>

</html>