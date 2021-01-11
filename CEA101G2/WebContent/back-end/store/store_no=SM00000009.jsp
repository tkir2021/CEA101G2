<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>哈必夫異國料理</title>
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
            var picinfo = "img/back_system/Store_pic/09/store09_0" + event.target.id + ".png"
            // alert(picinfo)
            var bigPic = document.getElementById('right').innerHTML = "<img src=" + picinfo + ">"
        }
    </script>
</head>

<body>
    <h1>哈必夫異國料理</h1>
    <P>這間位於桃園中壢中北路二段上的哈必夫異國料理，是一間相當推薦的中壢後站美食好選擇。
	哈必夫異國料理的餐點有排餐、義大利麵、焗烤、異國炒飯、鍋燒類和燉飯類，價格80元起。
	推薦哈必夫異國料理的印度咖哩焗烤飯，份量十足，味道也還滿不錯的，喜歡吃焗烤的朋友可以試試。
	點主餐還可以加價69元加購沙拉區，沙拉、水果、小菜、湯品、汽水和麵包通通無限量供應吃到飽唷！</P>
    <div id="all">
        <div id="left">
            <img src="img/back_system/Store_pic/09/store09_01.png" alt="" id="1" onclick="showBig()">
            <img src="img/back_system/Store_pic/09/store09_02.png" alt="" id="2" onclick="showBig()">
            <img src="img/back_system/Store_pic/09/store09_03.png" alt="" id="3" onclick="showBig()">
            <img src="img/back_system/Store_pic/09/store09_04.png" alt="" id="4" onclick="showBig()">
            <img src="img/back_system/Store_pic/09/store09_05.png" alt="" id="5" onclick="showBig()">
        </div>
        <div id="right">
            <img src="img/back_system/Store_pic/09/store09_01.png" alt="">
        </div>
    </div>
</body>

</html>