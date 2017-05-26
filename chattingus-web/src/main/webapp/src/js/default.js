

$(function(){

    function changeToMessage() {
        $("#messageDivMenu").addClass("active").siblings().removeClass("active");
    }

    function changeToFriend(){
        $("#friendDivMenu").addClass("active").siblings().removeClass("active");
    }

    function changeToAddFriend() {
        $("#addFriendDivMenu").addClass("active").siblings().removeClass("active");
    }

    $(".ui.five.item.pointing.menu.header a").click(function(){
        var id = $(this).attr("id");
        $(this).addClass("active").siblings().removeClass("active")
        if(id == "messageA"){
            changeToMessage();
        }else if(id == "friendA"){
            changeToFriend();
        }else if(id == "addMessageA"){
            changeToAddFriend();
        }
    });


});