

$(function(){

    $('select.dropdown').dropdown();

    $("#username").blur(function(){
        checkUsername();
    });

    var usernameSupport = false;

    function checkUsername(){
        var username = $("#username").val();
        if(username.length < 6){
            $("#usernameTip").html("<span style='color:red;font-weight: bold;'>用户名不可用</span>");
            return;
        }
        $.ajax({
            url: '/register/checkUsername',
            data: "username="+username,
            type: "get",
            headers: {
                'X-HTTP-Method-Override': "get",
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function(json) {
                if(json.success){
                    $("#usernameTip").html("<span style='color:green;font-weight: bold;'>用户名可用</span>");
                    usernameSupport = true;
                }else{
                    $("#usernameTip").html("<span style='color:red;font-weight: bold;'>用户名不可用</span>");
                }
            }
        });
    }

    function check(){
        checkUsername();
        var username = $("#username").val();
        var nick = $("#nick").val();
        var password = $("#password").val();
        var password2 = $("#password2").val();
        var gender = $("#gender").val();
        if(password != password2 || password.length < 6){
            alert("两次密码须一致且大于6位");
        }else if(username == ""){
            alert("用户名不能为空");
        }else if(nick == ""){
            alert("昵称不能为空");
        }else{
            if(usernameSupport){
                register();
            }
        }

    }

    function register() {
        $('#registerForm').submit();
    }

    $("#registerButton").click(function(){
        check();
    });

    $("body").keydown(function (e) {
        if(e.keyCode == 13) {
            check();
        }
    });

});

