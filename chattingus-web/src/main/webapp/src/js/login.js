

$(function(){
    function submit(data) {
        $.ajax({
            url: '/login/in',
            data: data,
            type: "post",
            headers: {
                'X-HTTP-Method-Override': "post",
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function (json) {
                if(json.success) {
                    window.location.href = '/user/message';
                } else {
                    alert(json.subMsg);
                }
            }
        });
    }

    //登录的函数
    function login(){
        var username = $("input[name = 'username']");
        var password = $("input[name = 'password']");
        if(username.val() == ''){
            alert("用户名不能为空");
        }else if(password.val() == ''){
            alert("密码不能为空");
        }else{
            submit(JSON.stringify({
                username:username.val(),
                password:password.val()
            }));
        }
    }

    $("#submit").click(function(){
        login();
    });

    $("body").keydown(function (e) {
        if(e.keyCode == 13) {
            login();
        }
    });

});