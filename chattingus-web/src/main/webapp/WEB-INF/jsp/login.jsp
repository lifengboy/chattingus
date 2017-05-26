<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>欢迎登录ChattingUs</title>
    <link href="/semantic-ui/semantic.min.css" rel="stylesheet" />
    <link rel="shortcut icon" href="/src/image/logo.png">
    <style>
        body{
            background-color: #fffde7;
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

<div class="ui middle aligned center aligned grid" id="login">
    <div class="column">
        <div class="ui large form">
            <div class="ui stacked segment">
                <h1 class="ui teal image header">
                    <div class="content" style="margin-bottom: 20px;">
                        欢迎来到
                    </div><br/>
                    <div class="content" style="margin-bottom: 20px;">
                        ChattingUs
                    </div>
                </h1>

                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input type="text" name="username" placeholder="账户" ng-model="username">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="password" placeholder="密码" ng-model="password">
                    </div>
                </div>
                <div class="ui fluid large teal submit button" id="submit">登录</div>
            </div>
        </div>
        <div class="ui message" style="background-color: white;">
            还没有账号? <a href="/register/main">注册</a>
        </div>
    </div>
</div>

<script type="text/javascript" src="/src/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/src/js/login.js"></script>

</body>
</html>
