<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>我的消息</title>
    <link href="/semantic-ui/semantic.min.css" rel="stylesheet" />
</head>
<body>
<h1>欢迎您</h1>
你的ip是：${user.ip}
用户名是：${user.username},us号是<span id="userIdSpan">${user.userId}</span>,昵称是：${user.nick}<br/><br/>
<div id="message"></div>
<input id="text" type="text"/>
<button id="sendButton">发送消息</button>
<br/>我的消息：
<div class="ui list">
    <div class="item">
        <img class="ui avatar image" src="/src/image/man.jpg">
        <div class="content">
            <a class="header">小李</a>
            <div class="description"><a><b>最后一次见到小红是在刚才。</b></a></div>
        </div>
    </div>
    <div class="item">
        <img class="ui avatar image" src="/src/image/man.jpg">
        <div class="content">
            <a class="header">小李</a>
            <div class="description"><a><b>最后一次见到小红是在刚才。</b></a></div>
        </div>
    </div>
    <div class="item">
        <img class="ui avatar image" src="/src/image/man.jpg">
        <div class="content">
            <a class="header">小李</a>
            <div class="description"><a><b>最后一次见到小红是在刚才。</b></a></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/src/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/src/js/message.js"></script>
</body>
</html>
