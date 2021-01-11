<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>福德涼麵</title>
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
            var picinfo = "img/back_system/Store_pic/01/store01_0" + event.target.id + ".png"
            // alert(picinfo)
            var bigPic = document.getElementById('right').innerHTML = "<img src=" + picinfo + ">"
        }
    </script>
</head>

<body>
    <h1>福德涼麵</h1>
    <P>前一晚待在南京復興站附近的E書漫也沒睡好，
	一早就得離開，昏昏沉沉又肚子餓，
	搜尋附近的早餐店就找到這間福德涼麵，
	走到興安街這兒來，
	發現有不少間小吃店一大早六點就開了耶，
	這裡的地理位置大約在南京復興站和中山國中站的中間</P>
	<p>有一般的涼麵、炸醬涼麵、榨菜肉絲涼麵、乾麵，
	然後涼麵配味噌湯好像變成既定的組合了，
	就像臭豆腐要配麵線一樣(咦)</p>
	<p>店裡座位不多，
	啊就傳統小吃店嘛，
	也是有好幾個人跟我們一樣這麼早就來吃涼麵了呢，
	用餐時間的話更是生意強強滾，是這裡的人氣排隊名店，
	牆上貼著15年前的報紙，
	以前營業時間是從晚上八點到隔天中午一點，
	現在變成24小時營業真是太厲害了</p>
    <div id="all">
        <div id="left">
            <img src="img/back_system/Store_pic/01/store01_01.png" alt="" id="1" onclick="showBig()">
            <img src="img/back_system/Store_pic/01/store01_02.png" alt="" id="2" onclick="showBig()">
            <img src="img/back_system/Store_pic/01/store01_03.png" alt="" id="3" onclick="showBig()">
            <img src="img/back_system/Store_pic/01/store01_04.png" alt="" id="4" onclick="showBig()">
            <img src="img/back_system/Store_pic/01/store01_05.png" alt="" id="5" onclick="showBig()">
        </div>
        <div id="right">
            <img src="img/back_system/Store_pic/01/store01_01.png" alt="">
        </div>
    </div>
</body>

</html>