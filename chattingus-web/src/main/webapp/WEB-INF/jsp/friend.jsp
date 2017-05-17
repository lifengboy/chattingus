<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>我的好友</title>
    <link href="/semantic-ui/semantic.min.css" rel="stylesheet" />

</head>
<body>
<h1>欢迎您</h1>
你的ip是：${user.ip}
用户名是：${user.username},us号是<span id="userIdSpan">${user.userId}</span>,昵称是：<span id="nickSpan">${user.nick}</span><br/><br/>
<div id="message"></div>
<div class="ui divider"></div>
<div class="ui accordion">
    <div class="active ui header title" id="friendNameDiv">
        <i class="dropdown icon"></i>
        我的好友
    </div>
    <div class="active content" id="friendContentDiv">
        <div class="ui selection list" id="friendDiv">
        </div>
    </div>
</div>
<div class="ui divider"></div>

<!--多对多-->
<div class="ui divider"></div>
<div class="ui accordion">
    <div class="active ui header title">
        <i class="dropdown icon"></i>
        一起聊
    </div>
    <div class="active content">
        <div class="ui selection list">
            <div class='item' onclick='startMany2ManyChatting();'>
                <img class='ui avatar image' src='/src/image/man.jpg'>
                <div class='content'>
                    <a class='header'>加入一起聊</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="ui divider"></div>

<!--留言提示框-->
<div class="ui modal" id="notOnlineTip">
    <i class="close icon"></i>
    <div class="header">
        提示
    </div>
    <div class="image content">
        <div class="image">
            <img class='ui image' style='-webkit-filter:grayscale(1)' src='/src/image/man.jpg'>
        </div>
        <div class="description" style="font-size: 16px;margin-top: 13px;" id="tipsContent">
        </div>
    </div>
    <div class="actions">
        <div class="ui button" id="giveUp">算了</div>
        <div class="ui button" id="ok">OK</div>
    </div>
</div>

<!--留言聊天框-->
<div class="ui modal" id="Chattingus">
    <i class="close icon"></i>
    <div class="header" style="font-size: 14px;" id="headerContent">
    </div>
    <div id="histories" style="_height:100px;min-height:100px;max-height: 350px;overflow: auto"></div>
    <div class="actions">
        <form class="ui reply form">
            <div class="field" >
                <textarea id="messageContent" rows="3"></textarea>
            </div>
            <div class="ui primary submit icon button" id="clickClose" style="margin-bottom: 5px;">关闭</div>
            <div class="ui primary submit icon button" id="sendMessage" style="margin-bottom: 5px;">点击发送</div>
        </form>
    </div>
</div>

<script type="text/javascript" src="/src/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/src/js/friend.js"></script>
<script type="text/javascript" src="/src/js/message.js"></script>
<script type="text/javascript" src="/semantic-ui/semantic.js"></script>
<script src="/src/js/utils/template.js"></script>
</body>
</html>
