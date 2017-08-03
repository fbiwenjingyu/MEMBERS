//dy  11-23 添加好友绑定上一页和取消按钮操作  暂未用到
//$(function () {
//  $('form .btn-ok').on('click', function(){
//    var index = parseInt($(this).parent().attr('data-index'));
//    if(index==3){return;}
//    $('.steps').find('li').eq(index).addClass('finished');
//    changeClass($('.step-wrap'), index, 'step-current');
//  });
//  $('form .btn-cancel').on('click', function(){
//    var index = parseInt($(this).parent().attr('data-index'))-1;
//    if(index==0){return;}
//    $('.steps').find('li').filter(function(i){return i >= index}).removeClass('finished');
//    changeClass($('.step-wrap'), index-1, 'step-current');
//  });
//
//  function changeClass($obj, index, oriCls){
//    console.log(index);
//    $obj.removeClass(oriCls).eq(index).addClass(oriCls);
//  }
//})