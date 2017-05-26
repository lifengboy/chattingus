<%@ taglib prefix="decorate" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link rel="shortcut icon" href="/src/image/logo.png">
    <title><decorate:title /></title>
    <link rel="stylesheet" href="/semantic-ui/semantic.min.css">
    <style>
        .dropdown-menu {
            display: none;
        }
        .ui.fixed.table td, .ui.fixed.table th {
            overflow: inherit;
        }
    </style>
</head>
<body>
<%--导航--%>
<div class="ui container">
    <br>
    <div class="ui five item pointing menu header">
        <a href="javascript:void(0);" id="messageA" class="${nav=='message'?'active':''} item">
            <i class="icon mail"></i>消息
            <div class="floating ui red label">22</div>
        </a>
        <a href="javascript:void(0);" id="friendA" class="${nav=='friend'?'active':''} item">
            <i class="icon users"></i>好友
            <div class="floating ui teal label">22</div>
        </a>
        <a href="javascript:void(0);" id="addMessageA" class="${nav=='add-friend'?'active':''} item">
            <i class="icon user"></i>添加
        </a>
        <a href="javascript:void(0);" class="item">
            <i class="icon setting"></i>设置
        </a>
        <a href="/login/out" class="item">
            <i class="remove circle icon"></i>注销
        </a>
        <div class="right menu">
        </div>
    </div>
    <!--消息的div-->
    <div class="ui tab clearfix active" id="messageDivMenu">
        <div class="ui segment">
            <jsp:include page="/WEB-INF/jsp/message.jsp" />
        </div>
    </div>
    <!--好友的div-->
    <div class="ui tab clearfix" id="friendDivMenu">
        <div class="ui segment">
            <jsp:include page="/WEB-INF/jsp/friend.jsp" />
        </div>
    </div>
    <!--添加的div-->
    <div class="ui tab clearfix" id="addFriendDivMenu">
        <div class="ui segment">
            <jsp:include page="/WEB-INF/jsp/add-friend.jsp" />
        </div>
    </div>
</div>

<div class="ui inverted vertical footer" style="margin: 2em 0">
    <div class="ui center aligned container">
        <div class="ui horizontal inverted small divided link list">
            <p>©2017 李风</p>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/src/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/semantic-ui/semantic.min.js"></script>
<script type="text/javascript" src="/src/js/utils/template.js"></script>
<script type="text/javascript" src="/src/js/message.js"></script>
<script type="text/javascript" src="/src/js/default.js"></script>
</html>
