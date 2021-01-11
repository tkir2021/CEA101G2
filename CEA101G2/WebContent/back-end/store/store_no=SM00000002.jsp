<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Daddys Hamburg</title>
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
            var picinfo = "img/back_system/Store_pic/02/store02_0" + event.target.id + ".png"
            // alert(picinfo)
            var bigPic = document.getElementById('right').innerHTML = "<img src=" + picinfo + ">"
        }
    </script>
</head>

<body>
    <h1>Daddys Hamburg</h1>
    <P>Daddy’s美式餐廳位於中壢區中央路170號，是中央大學後門的速食餐廳，於2013年成立，這家餐廳幾乎是後門的最後一家餐廳了，但依然很受學生族群的喜愛，簡約設計帶點美式風格的裝潢相當有特色。</P>
    <p>跟3、4佰塊的現做漢堡相比，漢堡肉當然是沒有那麼厚實，但以$99的角度來看，C/P值超高
	一口咬下僅管沒有如流水般的肉汁流出，但肉質不會乾柴，甚至冷掉之後之也不會有肉腥味或者乾掉
	至於特製的墨西哥風味醬，有著墨西哥香料的明顯香氣，且帶有些微辣度，吃起來挺夠味的！
	Ps.這漢堡的份量有夠大，我最後打包了近1/3回家吃，如果是小鳥胃的人建議單點就好哦～</p>
    <div id="all">
        <div id="left">
            <img src="img/back_system/Store_pic/02/store02_01.png" alt="" id="1" onclick="showBig()">
            <img src="img/back_system/Store_pic/02/store02_02.png" alt="" id="2" onclick="showBig()">
            <img src="img/back_system/Store_pic/02/store02_03.png" alt="" id="3" onclick="showBig()">
            <img src="img/back_system/Store_pic/02/store02_04.png" alt="" id="4" onclick="showBig()">
            <img src="img/back_system/Store_pic/02/store02_05.png" alt="" id="5" onclick="showBig()">
        </div>
        <div id="right">
            <img src="img/back_system/Store_pic/02/store02_01.png" alt="">
        </div>
    </div>
</body>

</html>