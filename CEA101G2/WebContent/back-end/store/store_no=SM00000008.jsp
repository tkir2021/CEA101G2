<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>麥味登中壢長沙店</title>
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
            var picinfo = "img/back_system/Store_pic/10/store10_0" + event.target.id + ".png"
            // alert(picinfo)
            var bigPic = document.getElementById('right').innerHTML = "<img src=" + picinfo + ">"
        }
    </script>
</head>

<body>
    <h1>麥味登中壢長沙店 (永久歇業)</h1>
    <P>肚子空空來麥味登，吃飽整天精神都加分。
	起床早食來麥味登，新鮮手做快速便利加滿分。
	考試前來麥味登，每科都考滿分再加一分 (想像力加一分)。
	午食優選麥味登，健康飲食再加分。
	咖啡講究麥味登，單一產地 莊園風味再加分。
	上班偷空來麥味登，隨興偷閒充電再加分。
	加盟創業找麥味登，開店專門  人生升級百萬再加分。
	人生找目標來麥味登，老厝邊的明燈，我們一起為大家生活加分。</P>
    <div id="all">
        <div id="left">
            <img src="img/back_system/Store_pic/10/store10_01.png" alt="" id="1" onclick="showBig()">
            <img src="img/back_system/Store_pic/10/store10_02.png" alt="" id="2" onclick="showBig()">
            <img src="img/back_system/Store_pic/10/store10_03.png" alt="" id="3" onclick="showBig()">
            <img src="img/back_system/Store_pic/10/store10_04.png" alt="" id="4" onclick="showBig()">
            <img src="img/back_system/Store_pic/10/store10_05.png" alt="" id="5" onclick="showBig()">
        </div>
        <div id="right">
            <img src="img/back_system/Store_pic/10/store10_01.png" alt="">
        </div>
    </div>
</body>

</html>