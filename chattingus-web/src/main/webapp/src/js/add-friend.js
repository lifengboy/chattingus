
//当用户点击点我添加按钮
function doAddFriend(userId){
    $.ajax({
        url: '/message/sendAddFriendMessage',
        data: "userId="+userId,
        type: "get",
        headers: {
            'X-HTTP-Method-Override': "get",
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (json) {
            alert(json.subMsg);
        }
    });
}

$(function(){
    $("#closetips").click(function(){
        $("#tips").hide();
    });

    $("#searchbutton").click(function(){
        search();
    });

    function search(){
        var searchdata = $("#searchcontent").val();
        if(searchdata == ""){
            alert("输入不能为空");
        }else{
            submit(searchdata);
        }
    }

    function submit(searchdata) {
        $.ajax({
            url: '/user/getUser',
            data: 'userId='+searchdata,
            type: "get",
            headers: {
                'X-HTTP-Method-Override': "get",
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function (json) {
                if(json.success) {
                    $('#friendtable').html("<thead><tr><th><center>US号</center></th><th><center>昵称</center></th><th><center>状态</center></th><th><center>添加</center></th></tr></thead>");
                    var userId = json.data.user.userId;
                    var nick = json.data.user.nick;
                    var colorClass;
                    var status;
                    if(json.data.user.status == 0){
                        var statusTemp = "离线";
                        colorClass = "warning";
                        status = "<i class='icon close'></i>"+statusTemp;
                    }else{
                        var statusTemp = "在线";
                        colorClass = "positive";
                        status = "<i class='icon checkmark'></i>"+statusTemp;
                    }
                    $('#friendtable').append("<tbody id='friendtbody'><tr><td>"+"<center>"+userId+"</center></td><td><center>"+nick+"</center></td><td class="+colorClass+"><center>"+status+"</center></td><td><center><a href='javascript:void(0);' onclick='doAddFriend("+userId+")'>点击添加</a></center></td></tr></tbody>");
                } else {
                    alert(json.subMsg);
                }
            }
        });
    }


    $("body").keydown(function (e) {
        if(e.keyCode == 13) {
            search();
        }
    });


});