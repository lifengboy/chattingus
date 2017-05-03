<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>我的好友</title>
    <link href="/semantic-ui/semantic.min.css" rel="stylesheet" />
    <script type="text/javascript" src="/src/jquery-2.1.4.min.js"></script>
</head>
<body>
<h1>欢迎您</h1>
你的ip是：${user.ip}
用户名是：${user.username},us号是${user.userId},昵称是：${user.nick}<br/><br/>
我的好友：
<div class="ui selection list">
    <div class="item">
        <img class="ui avatar image" src="/src/image/man.jpg">
        <div class="content">
            <div class="header">海伦</div>
        </div>
    </div>
    <div class="item">
        <img class="ui avatar image" src="/src/image/man.jpg">
        <div class="content">
            <div class="header">克里斯托</div>
        </div>
    </div>
    <div class="item">
        <img class="ui avatar image" src="/src/image/man.jpg">
        <div class="content">
            <div class="header">丹尼尔</div>
        </div>
    </div>
</div>
</body>
</html>
