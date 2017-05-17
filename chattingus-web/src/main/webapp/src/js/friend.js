

$(function(){

    $("#friendNameDiv").click(function(){
        var nameTemp = $("#friendNameDiv");
        var contentTemp = $("#friendContentDiv");
        if(nameTemp.hasClass("active")){
            nameTemp.removeClass("active");
            contentTemp.removeClass("active");
        }else{
            nameTemp.addClass("active");
            contentTemp.addClass("active");
        }
    });

});