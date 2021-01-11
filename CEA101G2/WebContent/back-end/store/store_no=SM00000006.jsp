<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>果然匯</title>
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
            var picinfo = "img/back_system/Store_pic/06/store06_0" + event.target.id + ".png"
            // alert(picinfo)
            var bigPic = document.getElementById('right').innerHTML = "<img src=" + picinfo + ">"
        }
    </script>
</head>

<body>
    <h1>果然匯</h1>
    <P>超人氣多國蔬食buffet「果然匯」提倡蔬食機能餐飲，匯集豐富蔬果及自然食材，結合多國料理方式，用好滋味照顧每位顧客及這片土地。</P>
    <p>因為是開放式廚房且菜色料理很豐富，無論是推薦蔬食料理自助餐、尋找聚餐餐廳、或者想找個好吃的蔬食料理，果然匯總在大家的推薦榜上。

怪不得即便是平日中午，當我們開店前抵達店外時，已看到有許多人排隊等候，當天也有很多組聚餐客人，因此建議大家可以事前打電話預約或者到果然匯官網線上訂位，以免向隅。</p>
    <div id="all">
        <div id="left">
            <img src="img/back_system/Store_pic/06/store06_01.png" alt="" id="1" onclick="showBig()">
            <img src="img/back_system/Store_pic/06/store06_02.png" alt="" id="2" onclick="showBig()">
            <img src="img/back_system/Store_pic/06/store06_03.png" alt="" id="3" onclick="showBig()">
            <img src="img/back_system/Store_pic/06/store06_04.png" alt="" id="4" onclick="showBig()">
            <img src="img/back_system/Store_pic/06/store06_05.png" alt="" id="5" onclick="showBig()">
        </div>
        <div id="right">
            <img src="img/back_system/Store_pic/06/store06_01.png" alt="">
        </div>
    </div>
</body>

</html>