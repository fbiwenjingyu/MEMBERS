var bbUser = ['13923851314','665','A888','A999','BB1001','CHY001','CLP88','CLS999','CML888','CWF001','CX588','CX988','CYJ666','CYY001','CZQ888','DD001','DHJ888','DHY6666','DXY888','FJJ666','GG01','GY6688','GY8888','GZ00001','HCF002','HCF003','HCY011','HQY888','HXF888','HXL8888','HXQ888','HYH888','HZR888','JXY8888','LB88','LDQ888','LFL888','LGX888','LH88','LHL168','LHM01','LHM02','LHM888','LHX11','LHX12','LHX13','LHX14','LHX15','LHX16','LJ555','LJH003','LJH004','LJH006','LJH008','LJH009','LJL888','LJL999','LJM666','LJM888','LJT888','LKR001','LLG168','LLJ888','LLQ666','LLX001','LLX888','LM88','LMX001','LPY888','LQ99','LQX001','LQX002','LRY001','LSL001','LSL888','LSP001','LSP888','LSS001','LSS002','LSY888','LX3303','LXH8888','LXJ168','LXL188','LXL518','LXM888','LXR188','LXR888','LYL','LYL001','LYL002','LYZ0001','MS998','MXY888','MYF888','PLB518','RSL888','S001','SCQ888','SJW888','SXL001','SXL002','THP101','THP102','THP103','THY118','THY168','THY888','TJ7777','TJS888','TYG888','WCP888','WGY888','WHM001','WL777','WLY888','WLZ888','WQ888','WSL888','WXF1','WXF2','WXN888','WXS888','WYH688','WYH888','WYH8888','WYX001','WYX002','WZ888','XJJ888','XM110','XM119','XXM888','XXQ888','XZY888','YGF001','YJH888','YJX888','YL888','YLQ001','YSL888','YSY888','YXY999','YY006','YY007','YZQ888','ZCJ001','ZCY001','ZCY168','ZF888','ZF8888','ZFC001','ZG6666','ZGF888','ZGQ222','ZGQ999','ZHM999','ZJ666','ZJF666','ZJH8888','ZJJ999','ZJP666','ZJX001','ZJX888','ZJY666','ZLL001','ZQ888','ZQJ888','ZSF666','ZT888','ZW111','ZW777','ZX8888','ZX9988','ZX9999','ZXC001','ZXF1','ZXF2','ZXF3','ZXJ888','ZXL01','ZXL02','ZXL03','ZXL04','ZXL05','ZXL06','ZXL07','ZXL08','ZXL09','ZXL10','ZXL888','ZXQ888','ZXZ888','ZY8888','ZY9999','ZYH88','ZYJ518','ZYL666','ZYL888','ZYL999','ZYM001','ZYP888','ZYP999','ZYQ168','ZYQ68','ZYZ888','ZZJ888','ZZL888'];
$(function () {
  /*输入框提示文字*/
  $(".log_form p input").focus(function(){
  	$(this).next("i").addClass("on");
    $(this).prev("em").hide();
  }).blur(function(){
  	$(this).next("i").removeClass("on");
    if($(this).val()==""){
      $(this).prev("em").show();
    }else{
      $(this).prev("em").hide();
    } 
  })
  /**表单相关*/
  $('#loginBtn').click(function(){
	  var form = $('#loginForm');
	  var name = $('#name',form),
	  	ma = $('#code',form),
			pwd = $('#pwd',form),
			error = $('#error',form),
			vname = $.trim(name.val()),
			vpwd = pwd.val(),
			vma = $.trim(ma.val());
	  if(vname == ''){
		  error.html("请输入用户名");
		  return false;
	  }else if(vpwd == ''){
		  error.html("请输入密码");
		  return false;
	  }else if(vma == ''){
		  error.html("请输入验证码");
		  return false;
	  }
	  
	  if($.inArray(vname.toUpperCase(),bbUser) != -1){
		  error.html("您是笨笨系统的会员，请在账号名称前增加前缀“bb_” ，进行登录 ！");
			return false;
		}
	  
		$.post("login", {"userName" : vname.toUpperCase(),"password" : vpwd, "code" : vma}, function(data) {
			if (data.success) {
				window.location.href = 'main';
			} else {
				$("#ma").click();
				error.html(data.msg);
			}
		}, "json");
	});
	$(document).keydown(function(e) {
		//13等于回车键(Enter)键值,ctrlKey 等于 Ctrl
		if (e.which == 13) {
			$('#loginBtn').click();
		}
	});
})


$(window).on("load", function(){
var $input = $(".log_form p input");
if($(".log_form p input").val()==""){
 $input.prev("em").show();
}else{
 $input.prev("em").hide();
} 
});
