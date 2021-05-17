// 账号校验
function validateUserName(){
    var name = $("#userName").val();
    var msg = $("#nameMsg");
    if (name == "" || name == null){
        $(msg).html("账号不能为空！").css("color","red");
        return false;
    } else if (name.length < 8) {
        $(msg).html("账号名不能少于8位！").css("color","red");
        return false;
    }
    $(msg).html("");
    return true;
}

function validateUserPass(){
    var pass = $("#userPass").val();
    var msg = $("#passMsg");
    if (pass == "" || pass == null){
        $(msg).html("密码不能为空！").css("color","red");
        return false;
    } else if (pass.length < 8) {
        $(msg).html("个人密码不能少于8位！").css("color","red");
        return false;
    }
    $(msg).html("");
    return true;
}

$(function (){
   $("#userName").blur(function (){
       validateUserName();
    });
   $("#userPass").blur(function (){
       validateUserPass();
   });

   $("#myForm").submit(function (){
       return validateUserName() && validateUserPass();
   });
});