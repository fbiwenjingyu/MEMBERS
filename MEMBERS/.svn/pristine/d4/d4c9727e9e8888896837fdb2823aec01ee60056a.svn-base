<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
</head>
<body id="person">
<!-- #include file="header.jsp"-->
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">

  <jsp:include page="../memu.jsp">
  		<jsp:param name="on" value="3"></jsp:param>
  </jsp:include>
  
  <div class="container">
    <jsp:include page="../membermanage/tab.jsp">
   		<jsp:param name="on" value="3"></jsp:param>
    </jsp:include>
    <div class="wrap_cont">
      <div class="ibox white">
        <div class="title">我的信息</div>
        <div class="subtitle">登录信息</div>
        <div class="desc">
          <span>登录密码</span>
          <span>******</span>
          <a href="#loginModal" data-toggle="modal">【修改】</a>
        </div>
        <div class="desc">
          <span>支付密码</span>
          <span>******</span>
          <a href="#payModal" data-toggle="modal">【修改】</a>
        </div>
        <div class="subtitle">银行卡信息</div>
        <div class="desc spedesc">
          <span id="c1">开户人： ${(model.cardName == "" || model.cardName == null)? "暂未添加" : model.cardName}</span><br>
          <span id="c2">开户行： ${(model.bankName == "" || model.bankName == null)? "暂未添加" : model.bankName}</span><br>
          <span id="c3">开户支行：${(model.subbranchBank == "" || model.subbranchBank == null)? "暂未添加" : model.subbranchBank}</span><br>
          <span id="c4">银行卡号：${(model.cardNo == "" || model.cardNo == null)? "暂未添加" : model.cardNo}</span>
          <a href="#cardModal" data-toggle="modal">【修改】</a>
        </div>
        <div class="subtitle">基本资料信息</div>
        <div class="desc spedesc">
          <span id="b1">姓名： ${(model.realName == "" || model.realName == null)? "暂未添加" : model.realName}</span><br>
          <span id="b2">联系电话： ${(model.phone == "" || model.phone == null)? "暂未添加" : model.phone}</span><br>
          <span id="b3">地址：<span id="dz">${model.province}${model.city}${model.area}${model.fullAddress}</span></span>
          <a href="#basicModal" data-toggle="modal">【修改】</a><br>
          <%-- <span>邀请人：${model.recommendNo}</span><br>
          <span>等级：${level}</span> --%>
        </div>
      </div>
    </div>
  </div>
</div>
<div id="payModal" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade window_box">
  <div class="window_cont">
    <h4>请输入支付密码</h4>
    <form id="payForm" class="sui-form sui-validate" onsubmit="return false;">
       <div class="control-group ">
          <label for="" class="control-label">
            <i class="star">*</i>原支付密码</label>
          <div class="controls">
            	<input  type="text"  onfocus="this.type='password'" autocomplete="off"  id="oldPayPwd">
          </div>
        </div>
        <div class="control-group ">
          <label for="" class="control-label">
            <i class="star">*</i>新支付密码</label>
          <div class="controls">
           		<input  type="text"  onfocus="this.type='password'" autocomplete="off" id="payPwd"  data-rules="required|minlength=6|maxlength=24" data-error-msg="密码必须是6-24位">
          </div>
        </div>
        <div class="control-group ">
          <div class="controls">
            <button type="submit"  class="sui-btn btn-primary" >确定</button>
            <button type="button" data-dismiss="modal" class="btn btn-cancel">取消</button>
          </div>
        </div>
        <p class="error" id="payError"></p>
    </form>
  </div>
</div>
<div id="loginModal" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade window_box">
  <div class="window_cont">
    <h4>请输入登录密码</h4>
    <form id="pwdFrom" onsubmit="return false;" class="sui-form sui-validate">
       <div class="control-group ">
          <label for="" class="control-label">
            <i class="star">*</i>原登录密码</label>
          <div class="controls">
            <input  type="text"  onfocus="this.type='password'" autocomplete="off"  id="oldPwd"  data-rules="required" data-error-msg="密码必须是6-24位">
          </div>
        </div>
        <div class="control-group ">
          <label for="" class="control-label">
            <i class="star">*</i>新登录密码</label>
          <div class="controls">
            <input  type="text"  onfocus="this.type='password'" autocomplete="off"  id="pwd" type="password"  data-rules="required|minlength=6|maxlength=24" data-error-msg="密码必须是6-24位">
          </div>
        </div>
        <div class="control-group ">
          <div class="controls">
           <button type="submit"  class="sui-btn btn-primary" >确定</button>
            <button type="button" data-dismiss="modal" class="btn btn-cancel">取消</button>
          </div>
        </div>
         <p class="error" id="error"></p>
    </form>
  </div>
</div>
<div id="cardModal" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade window_box">
  <div class="window_cont">
    <h4>修改银行卡信息</h4>
    <form  id="cardForm" onsubmit="return false;" class="sui-form sui-validate">
       <div class="control-group ">
          <label for="" class="control-label">
            <i class="star">*</i>开户人</label>
          <div class="controls">
           		<input type="text" id="cardName" name="cardName" data-rules="required|nameFormat"  value="${model.cardName}"/>
          </div>
        </div>
        <div class="control-group ">
          <label for="" class="control-label">
            <i class="star">*</i>开户行</label>
          <div class="controls">
          	 <select name="bankName" id="bankName" ></select>
          </div>
        </div>
        <div class="control-group ">
          <label for="" class="control-label">
            <i class="star">*</i>开户支行</label>
          <div class="controls">
             <input type="text" id="subbranchBank" name="subbranchBank" data-rules="required|minlength=4|maxlength=40" data-error-msg="长度必须是4-40位" value="${model.subbranchBank}"/>
          </div>
        </div>
        <div class="control-group ">
          <label for="" class="control-label">
            <i class="star">*</i>银行卡号</label>
          <div class="controls">
            	<input type="text" id="cardNo" name="cardNo" data-rules="required|cardNoFormat" value="${model.cardNo}"/>
          </div>
        </div>
        <div class="control-group ">
          <div class="controls">
           	<button type="submit"  class="sui-btn btn-primary" >确定</button>
            <button type="button" data-dismiss="modal" class="btn btn-cancel">取消</button>
          </div>
        </div>
        <p class="error" id="cardError"></p>
    </form>
  </div>
</div>
<div id="basicModal" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade window_box">
  <div class="window_cont">
    <h4>修改基本资料信息</h4>
    <form id="basicForm" onsubmit="return false;" class="sui-form sui-validate">
      <div class="control-group ">
        <label for="" class="control-label">
          <i class="star">*</i>姓名</label>
        <div class="controls">
          <input type="text" id="realName"  data-rules="required|nameFormat" value="${model.realName}"/>
        </div>
      </div>
      <div class="control-group ">
        <label for="" class="control-label">
          <i class="star">*</i>联系电话</label>
        <div class="controls">
          <input type="text" id="phone"  data-rules="required|phone" value="${model.phone}"/>
        </div>
      </div>
      <div class="control-group ">
        <label for="" class="control-label">
          <i class="star">*</i>地址</label>
        <div class="controls address">
          		<select id="selectProvince" data-rules="selectValue"></select>
				<select id="selectCity" data-rules="selectValue"></select>
				<select id="selectArea"  style="display: none;"  data-rules="selectValue"></select><br>
		</div>
      </div>
      <div class="control-group ">
        <label for="" class="control-label">
          <i class="star">*</i>详细地址</label>
        <div class="controls address">
				<textarea id="fullAddress" data-rules="required|minlength=4|maxlength=40" data-error-msg="长度必须是4-40位" >${model.fullAddress}</textarea>
        </div>
      </div>
      <div class="control-group ">
        <div class="controls">
         <button type="submit"  class="sui-btn btn-primary" >确定</button>
          <button type="button" data-dismiss="modal" class="btn btn-cancel">取消</button>
        </div>
      </div>
      <p class="error" id="basicError"></p>
    </form>
  </div>
</div>
<div class="sui-msg msg-large msg-success" id="sucess" style="display:none;z-index:100;position:absolute;left:50%;top:50%;width:130px;height:46px;margin:-23px 0 0 -65px;">
  	<div class="msg-con">操作成功</div>
 </div>
<script type="text/javascript" src="<c:url  value='/static/js/areaData.js' />" ></script>
<script type="text/javascript" src="<c:url  value='/static/js/jquery.extend.self.js' />"></script>
<script type="text/javascript">
$(function(){
	if($("#dz").text() == ''){
		$("#dz").text("暂未添加")
	}
	//初始化银行
	 var bankNameList = ["中国工商银行","中国农业银行","中国银行","中国建设银行"];
	  
	  var bankObject = $("#bankName");
	  
	  for(var i in bankNameList){
		  var name = bankNameList[i];
		  bankObject.append("<option value='" + name + "'> " + name + "</option>");
	  }
	  //银行
	  $("#bankName").val("${model.bankName}");
	//初始化区域
	var data = {
			aId:['selectProvince','selectCity','selectArea']
		};
	$.areaSelect(data);
	 var province = '${model.province}';
	 if(province != ''){
		 $("#selectProvince option:contains('${model.province}')").attr("selected", true);
		 $("#selectProvince").change(); 
		 $("#selectCity option:contains('${model.city}')").attr("selected", true);
		 $("#selectCity").change();
		 $("#selectArea option:contains('${model.area}')").attr("selected", true);
	 }
	 
});
$("#pwdFrom").validate({
    success: function() {
    	$.post("updatePwd", {"oldPwd" : $("#oldPwd").val(),"pwd" : $("#pwd").val(), "type" : 0}, function(data) {
			if (data.success) {
				$('#loginModal').modal('hide');
				$("#sucess").fadeIn();
				setTimeout(function(){
					$("#sucess").fadeOut();
				},2000);
			} else {
				$("#error").html(data.msg);
			}
		}, "json");
        return false;
      }
});

$('#loginModal').on('show', function(e){
	$("#error").html("");
});

$("#payForm").validate({
    success: function() {
    	$.post("updatePwd", {"oldPwd" : $("#oldPayPwd").val(),"pwd" : $("#payPwd").val(), "type" : 1}, function(data) {
			if (data.success) {
				$('#payModal').modal('hide');
				$("#sucess").fadeIn();
				setTimeout(function(){
					$("#sucess").fadeOut();
				},2000);
			} else {
				$("#payError").html(data.msg);
			}
		}, "json");
        return false;
      }
});

$('#payModal').on('show', function(e){
	$("#payError").html("");
});

var nameFormat = function(value,element, param){
	var reg = /^([a-z]|[A-Z]|[\u4e00-\u9fa5]){2,12}$/;
    if(!reg.test(value)){  
        return false;  
    }else{
    	return true;
    }
};
Validate.setRule("nameFormat", nameFormat, '姓名必须2至12个中文或英文字符'); 

var cardNoFormat =  function(value){
	var reg = /(^\d{16,20}$)/; 
    if(!reg.test(value)){  
        return false;  
    }else{
    	return true;
    }
};
Validate.setRule("cardNoFormat", cardNoFormat, '银行卡号格式不正确'); 

var phone = function(value, param){
	var reg= /^(133|153|177|180|181|189|134|135|136|137|138|139|150|151|152|157|158|159|178|182|183|184|187|188|130|131|132|155|156|176|185|186|145|147|170)\d{8}$/;
    if(!reg.test(value)){  
        return false;  
    }else{
    	return true;
    }
};
Validate.setRule("phone", phone, '格式不正确,输入正确的手机号'); 

var selectValue = function(value, element){
     if(element.is(":visible") && value == 0){  
         return false;  
     }else{
     	return true;
     }
};

Validate.setRule("selectValue", selectValue, '请选择一项');

$("#cardForm").validate({
    success: function() {
    	//序列化表单内容
    	var formData = $("#cardForm").serialize();
    	$.post("update", formData, function(data) {
			if (data.success) {
				$('#cardModal').modal('hide');
				$("#sucess").fadeIn();
			
				$("#c1").html("开户人：" + $("#cardName").val());
				$("#c2").html("开户行：" + $("#bankName").val());
				$("#c3").html("开户支行：" + $("#subbranchBank").val());
				$("#c4").html("银行卡号：" + $("#cardNo").val());
				setTimeout(function(){
					$("#sucess").fadeOut();
				},2000);
			
			} else {
				$("#cardError").html(data.msg);
			}
		}, "json");
        return false;
      }
});

$("#basicForm").validate({
    success: function() {
    	var province = $("#selectProvince  option:selected").text();
    	var city = $("#selectCity  option:selected").text();
    	var area = '';
    	if($("#selectArea").is(":visible")){
    		area = $("#selectArea  option:selected").text();
    	}
    	var realName = $("#realName").val();
    	var phone = $("#phone").val();
    	var fullAddress = $("#fullAddress").val();
    	$.post("update",{"fullAddress":fullAddress,"province" : province, "city" : city, "area" : area, "realName" : realName, "phone" : phone}, function(data) {
			if (data.success) {
				$('#basicModal').modal('hide');
				$("#sucess").fadeIn();
				$("#b1").html("姓名：" + realName);
				$("#b2").html("联系电话：" + phone);
				$("#b3").html("地址：" + province + city + area + fullAddress);
				setTimeout(function(){
					$("#sucess").fadeOut();
				},2000);
			} else {
				$("#basicError").html(data.msg);
			}
		}, "json");
        return false;
      }
});


</script>
</body>
</html>

