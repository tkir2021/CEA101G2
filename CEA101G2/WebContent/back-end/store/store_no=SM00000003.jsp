<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>田園美食屋</title>
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
            var picinfo = "img/back_system/Store_pic/03/store03_0" + event.target.id + ".png"
            // alert(picinfo)
            var bigPic = document.getElementById('right').innerHTML = "<img src=" + picinfo + ">"
        }
    </script>
</head>

<body>
    <h1>田園美食屋</h1>
    <P>中壢中央大學後門，美食一條街
	在地人才曉得的巷內「田園美食屋」
	幾樣家常簡餐：牛肉麵、雞排、起司豬排等
	抓住學生與鄰近家庭的胃
	美味不需要太複雜
	客人也甘願曬日頭等待</P>
	<p>環境還蠻舒適，不至於太擁擠
	冷氣涼，也有冷熱飲一杯可喝，飯不夠可續
	啊～這是學校週邊餐館的小福利XD
	懷念當學生的滋味</p>
    <div id="all">
        <div id="left">
            <img src="img/back_system/Store_pic/03/store03_01.png" alt="" id="1" onclick="showBig()">
            <img src="img/back_system/Store_pic/03/store03_02.png" alt="" id="2" onclick="showBig()">
            <img src="img/back_system/Store_pic/03/store03_03.png" alt="" id="3" onclick="showBig()">
            <img src="img/back_system/Store_pic/03/store03_04.png" alt="" id="4" onclick="showBig()">
            <img src="img/back_system/Store_pic/03/store03_05.png" alt="" id="5" onclick="showBig()">
        </div>
        <div id="right">
            <img src="img/back_system/Store_pic/03/store03_01.png" alt="">
        </div>
    </div>
</body>

</html>