

var websocket = null;

function submitAddFriend(div, mId, fromUserId, toUserId){
    var params = "mId="+mId+"&fromUserId="+fromUserId+"&toUserId="+toUserId;
    $.ajax({
        type: "post",
        dataType: "json",
        url: '/friend/submitAddFriend',
        data: params,
        success: function (json) {
            if(json.success) {
                alert("添加成功");
                $(div).remove();
            }else{
                alert(json.subMsg);
                $(div).remove();
            }
        }
    });
}

var chattingHistory = [];

var History = function(){

}

History.prototype = {
    changeHistory : function(friendId){
        $("#chatHistory"+friendId).addClass("active").siblings().removeClass("active");
    }
}

function startMany2ManyChatting(){
    $("#headerContent").html("<span>你正在<span style='color: red;'>"+"群聊"+"</span>中</span>");
    $("#clickClose").click(function(){
        $('#Chattingus').modal('hide');
    });
    var myNick = $("#nickSpan").text();
    if(!chattingHistory["many2many"]){
        chattingHistory["many2many"] = new History();
    }
    chattingHistory["many2many"].changeHistory("many2many");
    changeMany2ManySendMessageClickBind(myNick);
    $('#Chattingus').modal('setting', 'offset', 350).modal('setting', 'closable', false).modal('show').modal({inverted: true});
}

function changeMany2ManySendMessageClickBind(myNick){
    var userId = $("#userIdSpan").text();
    //要先解绑，在绑定，否则无法绑定
    $("#sendMessage").unbind("click").click(function(){
        var messageContent = $("#messageContent").val();
        if(messageContent != ""){
            var appendStr = "<div class='sixteen wide column' style='margin-left:4px;margin-top: 8px;margin-bottom: 8px;text-align:right;'><div class='image'><div class='ui right pointing red basic label' style='text-align:left;'><pre>"+messageContent+"</pre></div><label style='font-size: 12px;'>"+myNick+"</label><img class='ui avatar image' src='/src/image/man.jpg'></div></div>"
            $("#chatHistorymany2many").append(appendStr);
            //同时向服务器发消息
            websocket.send(JSON.stringify({
                messageType : 2,
                userId : userId,
                myNick : myNick,
                messageContent : messageContent
            }));
            $("#messageContent").val("").focus();
            //滑到底部
            goToHistoryBottom();
        }
    });
}

function startOne2OneChatting(e, status, userId, friendId){
    var nick = ($(e).find("a").text());
    var myNick = $("#nickSpan").text();
    $("#tipsContent").html("<span><span style='color: red;'>"+nick+"</span>当前不在线，他不能立马收到你的消息，是否给他留言。</span>");
    $("#headerContent").html("<span>你正在和<span style='color: red;'>"+nick+"</span>聊天中</span>");
    $("#clickClose").click(function(){
        $('#Chattingus').modal('hide');
    });
    if(!chattingHistory[friendId+""]){
        chattingHistory[friendId+""] = new History();
    }
    chattingHistory[friendId+""].changeHistory(friendId);
    //改变发送消息到哪里的事件
    changeSendMessageClickBind(userId, friendId, myNick);
    if(status == 0){
        $('#notOnlineTip').modal('show').modal({inverted: true});
        $("#giveUp").click(function(){
            $('#notOnlineTip').modal('hide');
        });
        $("#ok").click(function(){
            //聊天面板
            $('#Chattingus').modal('setting', 'offset', 350).modal('setting', 'closable', false).modal('show').modal({inverted: true});
        });
    }else{
        $('#Chattingus').modal('setting', 'offset', 350).modal('setting', 'closable', false).modal('show').modal({inverted: true});
    }
}

function changeSendMessageClickBind(userId, friendId, myNick){
    //要先解绑，在绑定，否则无法绑定
    $("#sendMessage").unbind("click").click(function(){
        var messageContent = $("#messageContent").val();
        if(messageContent != ""){
            var appendStr = "<div class='sixteen wide column' style='margin-left:4px;margin-top: 8px;margin-bottom: 8px;text-align:right;'><div class='image'><div class='ui right pointing red basic label' style='text-align:left;'><pre>"+messageContent+"</pre></div><label style='font-size: 12px;'>"+myNick+"</label><img class='ui avatar image' src='/src/image/man.jpg'></div></div>"
            $("#chatHistory"+friendId).append(appendStr);
            //同时向服务器发消息
            websocket.send(JSON.stringify({
                messageType : 1,
                currentUserId : userId,
                friendId : friendId,
                myNick : myNick,
                messageContent : messageContent
            }));
            $("#messageContent").val("").focus();
            //滑到底部
            goToHistoryBottom();
        }
    });
};

function receiveOne2OneMessage(fromUserId, fromUserNick, toUserId,messageContent){
    var friendId = fromUserId;
    var myNick = $("#nickSpan").text();
    if(!chattingHistory[friendId+""]){
        chattingHistory[friendId+""] = new History();
    }
    chattingHistory[friendId+""].changeHistory(friendId);
    changeSendMessageClickBind(toUserId, friendId, myNick);
    var appendStr = "<div class='sixteen wide column' style='margin-left:4px;margin-top: 8px;margin-bottom: 8px;'><div class='image'><img class='ui avatar image' src='/src/image/man.jpg'><label style='font-size: 12px;'>"+fromUserNick+"</label><div class='ui left pointing gray basic label'><pre>"+messageContent+"</pre></div></div></div>";
    $("#chatHistory"+friendId).append(appendStr);
    //将聊天框弹出来
    $('#Chattingus').modal('setting', 'offset', 350).modal('setting', 'closable', false).modal('show').modal({inverted: true});
    //滑到底部
    goToHistoryBottom();
}

function receiveMany2ManyMessage(fromUserNick, messageContent){
    var myNick = $("#nickSpan").text();
    chattingHistory["many2many"].changeHistory("many2many");
    changeMany2ManySendMessageClickBind(myNick);
    var appendStr = "<div class='sixteen wide column' style='margin-left:4px;margin-top: 8px;margin-bottom: 8px;'><div class='image'><img class='ui avatar image' src='/src/image/man.jpg'><label style='font-size: 12px;'>"+fromUserNick+"</label><div class='ui left pointing gray basic label'><pre>"+messageContent+"</pre></div></div></div>";
    $("#chatHistorymany2many").append(appendStr);
    //滑到底部
    goToHistoryBottom();
}

function goToHistoryBottom(){
//    $('#histories').animate({scrollTop:$('#histories').offset().top}, 'slow');
    $('#histories').scrollTop($('#histories').scrollHeight);
}

$(function(){
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
//        websocket = new WebSocket("ws://192.168.59.4/messageWebSocket");
//        websocket = new WebSocket("ws://172.16.10.5/messageWebSocket");
//        websocket = new WebSocket("ws://localhost/messageWebSocket");
//        websocket = new WebSocket("ws://192.168.0.103/messageWebSocket");
        websocket = new WebSocket("ws://119.23.235.251/messageWebSocket");
    } else {
        setMessageInnerHTML('当前浏览器 Not support websocket')
    }
    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        setMessageInnerHTML("服务连接成功");
        //连接服务器成功后告诉服务器当前用户身份
        var currentUserId = $("#userIdSpan").text();
        websocket.send(JSON.stringify({
            messageType : 0,
            currentUserId : currentUserId
        }));
    };
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        //解析json
        var message = JSON.parse(event.data);
        var messageType = message.data.serverMessageType;
        //0-------初始化验证消息和好友消息的消息
        //1-------在线发送验证消息
        //2-------一对一聊天消息
        //3-------群聊消息
        if(messageType == 0){
            initMessage(message);
        }else if(messageType == 1){
            sendOnlineConfirmMessage(message);
        }else if(messageType == 2){
            var fromUserId = message.data.fromUserId;
            var toUserId = message.data.toUserId;
            var messageContent = message.data.messageContent;
            var fromUserNick = message.data.fromUserNick;
            receiveOne2OneMessage(fromUserId, fromUserNick, toUserId,messageContent);
        }else if(messageType == 3){
            var messageContent = message.data.messageContent;
            var fromUserNick = message.data.fromUserNick;
            receiveMany2ManyMessage(fromUserNick, messageContent);
        }
//        setMessageInnerHTML(event.data);
    };

    //初始化消息
    function initMessage(message){
        //初始化验证消息
        var confirmMessages = message.data.confirmMessage;
        var confirmMessagesTemp = "{{each confirmMessages as confirmMessage i}}<div class='item' onclick='submitAddFriend(this,{{confirmMessage.mId}}, {{confirmMessage.fromUserId}},{{confirmMessage.toUserId}})'><img class='ui avatar image' src='/src/image/man.jpg'><div class='content'><a class='header'>{{confirmMessage.fromUserId}}</a><div class='description'><a><b>请求添加你为好友，点击本条消息通过他的申请</b></a></div></div></div>{{/each}}";
        var data1={
            confirmMessages : confirmMessages
        }
        var templateRender1 = template.compile(confirmMessagesTemp);
        var confirmMessagesStr = templateRender1(data1);
        $("#confirmMessageDiv").html(confirmMessagesStr);
        //初始化好友消息
//        var friendMessages = message.data.friendMessage;
//        var friendMessagesTemp = "{{each friendMessages as friendMessage i}}<div class='item' onclick='submitAddFriend({{friendMessage.mId}}, {{friendMessage.fromUserId}},{{friendMessage.toUserId}},this)'><img class='ui avatar image' src='/src/image/man.jpg'><div class='content'><a class='header'>{{friendMessage.fromUserId}}</a><div class='description'><a><b>请求添加你为好友，点击本条消息通过他的申请</b></a></div></div></div>{{/each}}";
//        var data2={
//            friendMessages : friendMessages
//        }
//        var templateRender2 = template.compile(friendMessagesTemp);
//        var friendMessagesStr = templateRender2(data2);
//        $("#friendMessageDiv").html(friendMessagesStr);
        //初始化好友列表
        var friends = message.data.friends;
        var friendsTemp = "{{each friends as friend i}}<div class='item' onclick='startOne2OneChatting(this, {{friend.user.status}},{{friend.userId}},{{friend.friendId}});'><img {{if friend.user.status == '0'}} style='-webkit-filter:grayscale(1)'{{/if}} class='ui avatar image' src='/src/image/man.jpg'><div class='content'><a class='header'>{{friend.user.nick}}</a></div></div>{{/each}}";
        var data3={
            friends : friends
        }
        var templateRender3 = template.compile(friendsTemp);
        var friendsStr = templateRender3(data3);
        $("#friendDiv").html(friendsStr);
        //初始化聊天界面
        if($("#histories").html() == ""){
            var historiesTemp = "{{each friends as friend i}}<div class='ui tab clearfix' id='chatHistory{{friend.friendId}}'><div class='description'><div class='sixteen wide column' style='margin-left:4px;margin-top: 8px;margin-bottom: 8px;'><div class='image'><img class='ui avatar image' style='-webkit-filter:grayscale(1);' src='/src/image/man.jpg'><label style='font-size: 12px;'>李风</label><div class='ui left pointing gray basic label'><pre>了才能看了多少吃你的市场的fsdddddfdsfdsf来吃饭!</pre></div></div></div></div></div>{{/each}}";
            var templateRender4 = template.compile(historiesTemp);
            var historiesStr = templateRender4(data3);
            var many2manyHistoryStr = "<div class='ui tab clearfix' id='chatHistorymany2many'><div class='description'><div class='sixteen wide column' style='margin-left:4px;margin-top: 8px;margin-bottom: 8px;'><div class='image'><img class='ui avatar image' style='-webkit-filter:grayscale(1);' src='/src/image/man.jpg'><label style='font-size: 12px;'>李风</label><div class='ui left pointing gray basic label'><pre>了才能看了多少吃你的市场的fsdddddfdsfdsf来吃饭!</pre></div></div></div></div></div>";
            $("#histories").html(historiesStr+many2manyHistoryStr);
        }
    };

    //给在线用户发送验证消息
    function sendOnlineConfirmMessage(message){
        var confirmMessages = message.data.confirmMessage;
        var confirmMessagesTemp = "{{each confirmMessages as confirmMessage i}}<div class='item' onclick='submitAddFriend(this, {{confirmMessage.mId}}, {{confirmMessage.fromUserId}},{{confirmMessage.toUserId}})'><img class='ui avatar image' src='/src/image/man.jpg'><div class='content'><a class='header'>{{confirmMessage.fromUserId}}</a><div class='description'><a><b>请求添加你为好友，点击本条消息通过他的申请</b></a></div></div></div>{{/each}}";
        var data1={
            confirmMessages : confirmMessages
        }
        var templateRender1 = template.compile(confirmMessagesTemp);
        var confirmMessagesStr = templateRender1(data1);
        $("#confirmMessageDiv").prepend(confirmMessagesStr);
    };


    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    };

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    };

    //将消息显示在网页上
    function setMessageInnerHTML(innerhtml) {
        document.getElementById('message').innerHTML += innerhtml + '<br/>';
    };

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    };

    //发送消息
    $("#sendButton").click(function(){
        var message = document.getElementById('text').value;
        websocket.send(message);
    });

    $("#confirmMessageNameDiv").click(function(){
        var nameTemp = $("#confirmMessageNameDiv");
        var contentTemp = $("#confirmMessageContentDiv");
        if(nameTemp.hasClass("active")){
            nameTemp.removeClass("active");
            contentTemp.removeClass("active");
        }else{
            nameTemp.addClass("active");
            contentTemp.addClass("active");
        }
    });

    $("#friendMessageNameDiv").click(function(){
        var nameTemp = $("#friendMessageNameDiv");
        var contentTemp = $("#friendMessageContentDiv");
        if(nameTemp.hasClass("active")){
            nameTemp.removeClass("active");
            contentTemp.removeClass("active");
        }else{
            nameTemp.addClass("active");
            contentTemp.addClass("active");
        }
    });
});