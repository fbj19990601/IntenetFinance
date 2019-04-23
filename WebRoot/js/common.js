$(function(){
  card();
  dropDown();
  switchTab();
  // menu();
});

function dropDown(){
	$("#Js-header-loginBtn").hover(function(){
      $(this).children().addClass('active');
       $(".menu-down").show();
	},function(){
      $(this).children().removeClass('active');
       $(".menu-down").hide();
	});
}

 
// function menu(){
//     $("#Js-header-loginBtn").hover(function(){
//         $(".menu-down").show();
//     },function(){
//         $(".menu-down").hide();
//     });
// }


function card(){
    $(".s-num,.s-heart").hover(function(){
        $(this).removeClass('unlike');
        $(this).addClass('like');
    },function(){
        $(this).removeClass('like');
        $(this).addClass('unlike');
    });
}

function switchTab(){
    $("#Js-switch-tab").delegate('li', 'click', function(event) {
        $(".Js-switch-list").hide();
        $(this).addClass('selected').siblings().removeClass('selected');
        $("#"+$(this).data("id")).show();
    });
}
//search
function se_before($form,$target){
     if(!$('#se input').val()){
         location.href="/deal/list/";
     }else{
         location.href="/deal/list/q/"+$('#se input').val();
     }
 }
