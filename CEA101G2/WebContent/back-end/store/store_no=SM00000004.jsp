<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>麥克小姐</title>
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
            var picinfo = "img/back_system/Store_pic/04/store04_0" + event.target.id + ".png"
            // alert(picinfo)
            var bigPic = document.getElementById('right').innerHTML = "<img src=" + picinfo + ">"
        }
    </script>
</head>

<body>
    <h1>麥克小姐</h1>
    <P>麥克小姐早午餐（Miss Mike Brunch）是中央大學後門的一家美式餐廳，以漢堡、義大利麵、燉飯、乳酪蛋吐司等為主，
    	在中央大學附近是具有指標性的一家早午餐及簡餐類型餐廳，寒暑假都座無虛席。麥克小姐的漢堡用料豐富，一端上桌時看到那漢堡塔，
 	 	真的會忍不住發出驚呼。其中值得讚許的是，他們的蔬菜用量非常足夠，深得我的喜愛。</P>
 	 	<p>早午餐系列全天候供應之外，餐點種類也很多樣化呢～
	美式漢堡搭配薯條，還有附飲料，一份吃下來會有飽足感喔～
	飲料是自助式取用，像紅茶可樂這些～喝完可以再續耶！
	唯一要注意的是，有每位低消一份的套餐限制喔！</p>
    <div id="all">
        <div id="left">
            <img src="img/back_system/Store_pic/04/store04_01.png" alt="" id="1" onclick="showBig()">
            <img src="img/back_system/Store_pic/04/store04_02.png" alt="" id="2" onclick="showBig()">
            <img src="img/back_system/Store_pic/04/store04_03.png" alt="" id="3" onclick="showBig()">
            <img src="img/back_system/Store_pic/04/store04_04.png" alt="" id="4" onclick="showBig()">
            <img src="img/back_system/Store_pic/04/store04_05.png" alt="" id="5" onclick="showBig()">
        </div>
        <div id="right">
            <img src="img/back_system/Store_pic/04/store04_01.png" alt="">
        </div>
    </div>
</body>

</html>