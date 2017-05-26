<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>欢迎注册ChattingUs</title>
    <link href="/semantic-ui/semantic.min.css" rel="stylesheet" />
    <link rel="shortcut icon" href="/src/image/logo.png">
    <style>
        body{
            background-color: white;
        }
        body > .grid {
            height: 100%;
        }
        .column {
            max-width: 364px;
        }
    </style>
</head>
<body>
<br/><br/><br/><br/>
<center><h3>请完善注册信息：</h3></center>
<div class="ui container" style="width: 350px;">
    <form id="registerForm" class="ui form" action="/register/doregister" method="post">
        <div class="field">
            <label>用户名：</label>
            <input type="text" id="username" name="username" placeholder="请输入用户名..."><span id="usernameTip"></span>
        </div>
        <div class="field">
            <label>昵称：</label>
            <input type="text" id="nick" name="nick" placeholder="请输入昵称...">
        </div>
        <div class="field">
            <label>密码：</label>
            <input type="text" id="password" name="password" placeholder="请输入密码...">
        </div>
        <div class="field">
            <label>确认密码：</label>
            <input type="text" id="password2" placeholder="请输入确认密码...">
        </div>
        <div class="field">
            <label>性别：</label>
            <select id="gender" name="gender">
                <option value="m">男</option>
                <option value="f">女</option>
            </select>
        </div>
        <button class="ui button" type="button" id="registerButton">注册</button>
    </form>
</div>

<script type="text/javascript" src="/src/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/semantic-ui/semantic.min.js"></script>
<script type="text/javascript" src="/src/js/register.js"></script>

</body>
</html>
