$(init);

function init(){
    getValidCode();
}

function getValidCode(){
    var canSend = true;
    $("#getValid").click(function(){
        var $this = $(this),
            mobile = $("input[name='mobile']").val(),
            verify = $("input[name='verify']").val();
        if(verify==""){
            wx.alert("请输入图片验证码");
            return;
        }  
        if(mobile==""){
            wx.alert("请输入手机号");
            return;
        }else if(!(/^1[3|4|5|7|8][0-9]\d{8}$/.test(mobile))){
            wx.alert("请输入正确的手机号");
            return;
        }        
        
        if(!canSend){
            wx.alert("请稍后再获取");
            return;
        }
        wx.sendData('/verify/sendsms','mobile='+mobile+'&verify='+verify,function(data){
            if(data.errno !== 0){
                wx.alert(data.usercode);
                $("#codeImg").attr("src",'/verify/code?t='+new Date().getTime());
            }
            else{
                canSend = false;
                var interId,time=60;
                interId = setInterval(function(){
                  time--;
                  $this.text(time);
                  if(time === 0){
                    clearInterval(interId);
                    $this.text('获取');
                    canSend = true;
                  }
                },1000);
                wx.alert("验证码已发送");
            }
        });
    });
}

function loginForm_before(data){
    if(!$('.verifity-code input').prop('disabled')){
        if($('.verifity-code input').val().length){
            return true;
        }else{
            wx.alert("验证码不能为空");
            return false;
        };
    }else{
            return true;
    }
}
function loginForm(data){
    if(data.errno !== 0){
        wx.alert(data.usercode);
        $('.verifity-code').show();
        $('.verifity-code input').prop('disabled',false);
        $("#codeImg").attr("src",'/verify/code?t='+new Date().getTime());
    } else {
        location.href=data.jump;
    }
}
function registerForm(data){
    if(data.errno !== 0){
        wx.alert(data.usercode);
        $("#codeImg").attr("src",'/verify/code?t='+new Date().getTime());
    } else {
        wx.alert(data.usercode,data.jump);
    }
}
