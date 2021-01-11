<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>禾麥丼飯Yakiniku Don</title>
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
            var picinfo = "img/back_system/Store_pic/08/store08_0" + event.target.id + ".png"
            // alert(picinfo)
            var bigPic = document.getElementById('right').innerHTML = "<img src=" + picinfo + ">"
        }
    </script>
</head>

<body>
    <h1>禾麥丼飯Yakiniku Don</h1>
    <P>禾麥丼飯（Yakiniku Don）是中壢一家結合日式燒肉與丼飯的平價餐廳，位於健行科大旁，不僅份量足夠，而且百元出頭的價位就能享用到Choice等級的直火燒牛丼，
    	真的是一絕！在丼飯的靈魂－－米飯上，表現得很優秀。更令人值得稱讚的是，連店內提供的飲料和湯品都是多種選擇相當有心，撰風個人超喜歡脆梅金桔飲料，真材實料的金桔榨汁喝得出來，
    	米飯和飲料都能續裝，不用擔心吃不飽。這篇文章雖然是無償邀約文，但我真的能感受到老闆在經營上的真誠與備料的用心，容我放在勸吃名單分類中，相當值得推薦！</P>
    <div id="all">
        <div id="left">
            <img src="img/back_system/Store_pic/08/store08_01.png" alt="" id="1" onclick="showBig()">
            <img src="img/back_system/Store_pic/08/store08_02.png" alt="" id="2" onclick="showBig()">
            <img src="img/back_system/Store_pic/08/store08_03.png" alt="" id="3" onclick="showBig()">
            <img src="img/back_system/Store_pic/08/store08_04.png" alt="" id="4" onclick="showBig()">
            <img src="img/back_system/Store_pic/08/store08_05.png" alt="" id="5" onclick="showBig()">
        </div>
        <div id="right">
            <img src="img/back_system/Store_pic/08/store08_01.png" alt="">
        </div>
    </div>
</body>

</html>