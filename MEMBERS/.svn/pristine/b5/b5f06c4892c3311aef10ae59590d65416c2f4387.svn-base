<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
  <script type="text/javascript" src="${js}/animatebackground-plugin.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <div class="container">
   <jsp:include page="../memu.jsp">
 		<jsp:param value="4" name="on"/>
 	</jsp:include>
 	<jsp:include page="tab.jsp">
 		<jsp:param value="6" name="on"/>
 	</jsp:include>
 	
    <div class="wrap_cont">
        <div class="ibox white">
            <h4 class="ibox_tit">
            	当前可用现金：<b id="balance">${user.balance }</b>元&nbsp;&nbsp;&nbsp;&nbsp;
            	可用博豆：<b id="shoppingScore">${user.shoppingScore }</b>博豆&nbsp;&nbsp;&nbsp;&nbsp;
            	可预支博豆：<b id="advances">${advances }</b>博豆
            </h4>
        </div>
        
        <div class="ibox white" style="position: relative;">
            <h4 class="ibox_tit">可兑换物品<a href="toExchangeList" class="tit_a">兑换记录</a></h4>
            <div class="yundie">
              <h4>云碟——从此生意火起来</h4>
              <p>
              	大博云碟是一款上网速度快、使用简单、功能多样的公用智能WiFi设备。
              	商家在门店内铺设云碟后，顾客可得到免费WiFi服务，
              	商家可以看到云碟分析的客流量数据、消费结果统计、经营策略建议。
              </p>
              	<i>${exchangeAmount}博豆/台</i>
            </div>
            
         	<div class="zhijia">
              <h4>大博智家套装</h4>
              <p>手机端实时监控<br>及时发现异常情况<br>保留证据报警<br><i>￥1999</i><br>
              <b id="cashAndJifen"></b>
              </p>
            </div> 
             
             <!-- 左右导航切换 -->
            <div class="nav_ex" title="点击切换"></div>
            
            <form action="toExchangeForm" class="sui-form form-horizontal sui-validate" id="tForm">
              <input type="text" style="display: none;">
              <input type="hidden" name="gType" id="gType" value="1">
              <div class="control-group">
                <label for="exNum" class="control-label"><b>*</b>兑换数量</label>
                <div class="controls">
                  <input type="text" id="exNum" name="exNum" placeholder="请输入兑换数量" data-rules="required|digits|gt=0|lt=101" 
                  		data-error-msg="请输入1到100之间的正整数"  autocomplete="off">
                </div>
              </div>
              <div class="control-group sui-inf">
              	当前可兑<b id="tNum" style="color:red;"></b>台，预支积分兑换后，将不再分期返还，此操作不可逆
              </div>
              <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                  <button type="submit" class="sui-btn">提交</button>
               <%--    <button type="reset" class="sui-btn" onclick="window.location.href='<c:url value = '/toIndex' />'">返回</button> --%>
                </div>
              </div>
            </form>
        </div>
    </div>
  </div>
</div>
<div class="sui-msg msg-large msg-error" id="result" style="display:none;z-index:100;position:fixed;left:50%;top:50%;width:auto;height:46px;margin:-23px 0 0 -65px;">
  	<div class="msg-con" style="padding-left: 50px;background-color: #000;color: #fff;"></div>
 	 <s class="msg-icon"></s>
 </div>
<script type="text/javascript">
var yunNum=zhiNum=null;
var count=1;
$(function(){
	//我的资产
	$("#shoppingScore").html(score($("#shoppingScore").html()));
	$("#advances").html(score($("#advances").html()));
	$("#balance").html(fmoney($("#balance").html(),2));
	
	var exchangeAmount=parseInt('${exchangeAmount}');//多少积分 兑换一台云蝶
	var exchangeCash=parseFloat('${exchangeCash}');//多少现金 兑换智家套装
	var exchangeJifen=parseInt('${exchangeJifen}');//多少积分 兑换智家套装
	$("#cashAndJifen").html("￥"+exchangeCash.toFixed(2)+"+"+exchangeJifen+"博豆");
	
	var shoppingScore=parseInt($("#shoppingScore").html());//可用博豆
	var advances=parseInt($("#advances").html());//可预支积分
	var balance=parseFloat(('${user.balance }')/100); //可用 现金
	
	//可兑换多少台云蝶
	yunNum=parseInt((shoppingScore+advances)/exchangeAmount);
	
	//可兑换多少台安防套装
	zhiNum= parseInt(balance/exchangeCash) < parseInt((shoppingScore+advances)/exchangeJifen) ? parseInt(balance/exchangeCash) : parseInt((shoppingScore+advances)/exchangeJifen);
	
	$("#tNum").html(yunNum);
	
	//点击切换下一个
	$(".nav_ex").click(function(){
		count++;
		if(count%2 == 0){//双数
			//animate backgroundPosition 在火狐下没有动画效果，使用自定义的插件animatebackground-plugin.js
			$(this).stop(true,false).animate({backgroundPosition:"4px -57px"},300); 
			$(".yundie").stop(true,false).animate({"left":-1500},300); 
			$(".zhijia").stop(true,false).animate({"left":0},300); 
			$("#tNum").html(zhiNum);
		}else{
			$(this).stop(true,false).animate({backgroundPosition:"-26px -57px"},300); 
			$(".yundie").stop(true,false).animate({"left":0},300); 
			$(".zhijia").stop(true,false).animate({"left":1500},300);
			$("#tNum").html(yunNum);
		}
		$("#gType").val(count);
		//console.log(count);
	});
	
});

$("#tForm").validate({
	success: function() {
		var exNum=parseInt($("#exNum").val());
		if(exNum>parseInt($("#tNum").html())){
			
			$("#result .msg-con").html("可兑换数量不足！");
			$("#result").fadeIn();
			$("#exNum").focus();
			setTimeout(function(){$("#result").fadeOut();},2000);
			
			$("#exNum").val("");
			return false;
		}
		$("#tForm").submit();
	}
});

function score(s){
	if(s == ''){
		return 0;
	}else{
		return s;
	}
}
function fmoney(s, n) {
	  if(s == ''){
		  return 0.00;
	 }else if(s == 0){
		 return s;
	 }else{
	  n = n > 0 && n <= 20 ? n : 2;
	  f = s < 0 ? "-" : ""; //判断是否为负数
	  s = s/100; //分转换成元
	  s = parseFloat((Math.abs(s) + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";//取绝对值处理, 更改这里n数也可确定要保留的小数位
	  var l = s.split(".")[0].split("").reverse(),
	  r = s.split(".")[1];
	  t = "";
	  for(var i = 0; i < l.length; i++ ) {
	     t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	  }
	  return f + t.split("").reverse().join("") + "." + r.substring(0,2);//保留2位小数  如果要改动 把substring 最后一位数改动就可
	}
}
</script>
</body>
</html>