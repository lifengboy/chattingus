<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>添加好友</title>
</head>
<body>
<h1>欢迎您</h1>
你的ip是：${user.ip}
用户名是：${user.username},us号是<span id="userIdSpan">${user.userId}</span>,昵称是：${user.nick}<br/><br/>
<div id="message"></div>
<div class="ui message" id="tips">
    <i class="close icon" id="closetips"></i>
    <center>你可以通过US号码来搜索添加好友哦</center>
</div><br/>
<div class="ui action left icon input" id="sss">
    <i class="search icon"></i>
    <input type="text" id="searchcontent" placeholder="请输入us号码来搜索..." onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
    <div class="ui teal button" id="searchbutton">搜索</div>
</div><br/><br/>
<table class="ui celled table" id="friendtable">

</table>
<script type="text/javascript" src="/src/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/src/js/add-friend.js"></script>
<script src="/src/js/utils/template.js"></script>
</body>
</html>
