<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>大直態芮</title>
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
            var picinfo = "img/back_system/Store_pic/05/store05_0" + event.target.id + ".png"
            // alert(picinfo)
            var bigPic = document.getElementById('right').innerHTML = "<img src=" + picinfo + ">"
        }
    </script>
</head>

<body>
    <h1>大直態芮</h1>
    <P>首先需要先告知這次用餐是法國食品協會主辦的法國奶油活動餐敘, 菜單是Taïrroir 態芮主廚何順凱和廚師團隊特別研發, 當天用餐有些菜餚並沒有在餐廳菜單上.
     Taïrroir 態芮位於劍南捷運站三號出口的美麗華附近, 離”萬豪酒店”不遠”, 停車場就在旁邊, 非常方便與其他內湖餐廳最不一樣的地方是Taïrroir 態芮位於大樓6樓, 而非在1樓. 
     	出電梯後, 玫瑰金色調裝潢入目, 整間餐廳的裝潢非常 Sex and the City, 非常適合姐妹們聚會或是典型約會餐廳.</P>
    <div id="all">
        <div id="left">
            <img src="img/back_system/Store_pic/05/store05_01.png" alt="" id="1" onclick="showBig()">
            <img src="img/back_system/Store_pic/05/store05_02.png" alt="" id="2" onclick="showBig()">
            <img src="img/back_system/Store_pic/05/store05_03.png" alt="" id="3" onclick="showBig()">
            <img src="img/back_system/Store_pic/05/store05_04.png" alt="" id="4" onclick="showBig()">
            <img src="img/back_system/Store_pic/05/store05_05.png" alt="" id="5" onclick="showBig()">
        </div>
        <div id="right">
            <img src="img/back_system/Store_pic/05/store05_01.png" alt="">
        </div>
    </div>
</body>

</html>