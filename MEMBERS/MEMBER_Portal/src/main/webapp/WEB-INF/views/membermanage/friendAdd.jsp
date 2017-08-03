<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>大博智家会员系统</title>
</head>
<body id="member">
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="wrap">
		<jsp:include page="../memu.jsp">
			<jsp:param name="on" value="3"></jsp:param>
		</jsp:include>
		<div class="container">
			<jsp:include page="tab.jsp">
				<jsp:param name="on" value="4"></jsp:param>
			</jsp:include>
		</div>
		<div class="wrap_cont">
			<div class="ibox white">
				<ul class="steps">
					<li class="finished">填写登录信息<i class="next">&gt;</i></li>
					<li>填写银行卡信息<i class="next">&gt;</i></li>
					<li>填写基本资料</li>
				</ul>
				<div class="steps-content">
					<form onsubmit="return false;" id="form1" class="sui-form form-horizontal sui-validate"> 
						<input type="text" style="display:none">
            			<input type="password" style="display:none">
						<div class="step-wrap step-current">
							<div class="control-group ">
								<label for="" class="control-label"> <i class="star">*</i>登录帐号
								</label>
								<div class="controls">
									<input id="accountNo" name="accountNo" type="text" data-rules="required|phone|maxlength=11|checkMember"> 
								</div>
							</div>
							<div class="control-group ">
								<label for="" class="control-label"> <i class="star">*</i>登录密码
								</label>
								<div class="controls">
									<input id="password" name="password" type="password" data-rules="required|minlength=6|maxlength=24">
								</div>
							</div>
							<div class="control-group ">
								<label for="" class="control-label"> <i class="star">*</i>支付密码
								</label>
								<div class="controls">
									<input id="payPassword" name="payPassword" type="password" data-rules="required|minlength=6|maxlength=24">
								</div>
							</div>
							<div class="control-group ">
								<label for="" class="control-label"> <i class="star">*</i>注册单数
								</label>
								<div class="controls">
									<input id="regNum" name="regNum" type="text" data-rules="required|digits|gt=0|lt=20"> 
								</div>
							</div>
							<c:if test="${user.type==3}">
								<div class="control-group ">
									<label for="" class="control-label"> <i class="star">*</i>邀请人
									</label>
									<div class="controls">
										<input id="recommendNo" name="recommendNo" type="text" data-rules="required|minlength=2|maxlength=12|checkRecommend" value="${user.accountNo}"> 
									</div>
								</div>
							</c:if>
							<div class="control-group ">
								<div class="controls" data-index="1">
									<button id="step1" class="sui-btn btn-primary" type="submit" style="width: 125px;">完善个人资料</button>
									<button id="sub1" class="sui-btn btn-primary" type="submit"  onclick="change(2);"  style="width: 125px;">立即提交</button>
								</div>
							</div>
						</div>
					</form>
					<form onsubmit="return false;" id="form2"
						class="sui-form form-horizontal sui-validate">
						<div class="step-wrap">
							<div class="control-group ">
								<label for="" class="control-label">开户人</label>
								<div class="controls">
									<input id="cardName" name="cardName" type="text" data-rules="checkRealName">
								</div>
							</div>
							<div class="control-group ">
								<label for="" class="control-label">开户行</label>
								<div class="controls">
									<select name="bankName" id="bankName"></select>
								</div>
							</div>
							<div class="control-group">
								<label for="" class="control-label">开户支行</label>
								<div class="controls">
									<input id="subbranchBank" name="subbranchBank" type="text">
								</div>
							</div>
							<div class="control-group ">
								<label for="" class="control-label">银行卡号</label>
								<div class="controls">
									<input id="cardNo" name="cardNo" type="text" data-rules="number|minlength=16|maxlength=20">
								</div>
							</div>
							<div class="control-group ">
								<div class="controls" data-index="2">
									<button class="btn btn-cancel" type="button">上一步</button>
									<button id="step2" class="sui-btn btn-primary" type="submit">下一步</button>
								</div>
							</div>
						</div>
					</form>
					<form onsubmit="return false;" id="form3"
						class="sui-form form-horizontal sui-validate">
						<div class="step-wrap">
							<div class="control-group ">
								<label for="" class="control-label">姓名</label>
								<div class="controls">
									<input id="realName" name="realName" type="text" data-rules="checkRealName">
								</div>
							</div>
							<div class="control-group ">
								<label for="" class="control-label">联系电话</label>
								<div class="controls">
									<input id="phone" name="phone" type="text" data-rules="required|mobile|maxlength=11">
								</div>
							</div>
						 	<div class="control-group ">
						        <label for="" class="control-label">地址</label>
						        <div class="controls address">
						          		<select id="selectProvince" ></select>
										<select id="selectCity" ></select>
										<select id="selectArea"  style="display: none;"></select><br>
								</div>
						     </div>
							<div class="control-group ">
								<label for="" class="control-label">详细地址</label>
								<div class="controls">
									<input id="fullAddress" name="fullAddress" type="text">
								</div>
							</div>
							
							<div class="control-group ">
								<div class="controls" data-index="3">
									<button class="btn btn-cancel" type="button">上一步</button>
									<button id="sub2" class="sui-btn btn-primary" type="submit">提交</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="<c:url  value='/static/js/areaData.js' />" ></script>
<script type="text/javascript" src="<c:url  value='/static/js/jquery.extend.self.js' />"></script>
<script type="text/javascript">
$(function(){
	var bankNameList = ["中国工商银行","中国农业银行","中国银行","中国建设银行"];
	  
	  var bankObject = $("#bankName");
	 
	  for(var i in bankNameList){
		 var name = bankNameList[i];
		 bankObject.append("<option value='" + name + "'> " + name + "</option>");
	  }
	  
	  var data = {
				aId:['selectProvince','selectCity','selectArea']
			};
	  $.areaSelect(data);
	  
	  var match = function(value, param){
  		var v = $.trim(value);
		if(v != ''){
			var flag = false;
    		$.ajax({
				url:'checkMember',
				dataType:'json',
				async: false,
				data:{"accountNo" : v},
				success:function(data){
					flag=data.success;
				}
			});
    		return flag;
		}else{
			return false;
		}
	  }
	  
	  var match1 = function(value, param){
	  		var v = $.trim(value);
			if(v != ''){
				var flag = false;
	    		$.ajax({
					url:'checkRecommend',
					dataType:'json',
					async: false,
					data:{"recommendNo" : v},
					success:function(data){
						flag=data.success;
					}
				});
	    		return flag;
			}else{
				return false;
			}
		  }
	  
	  
	  var match2 = function(value, param){
		  var reg = /^([a-z]|[A-Z]|[\u4e00-\u9fa5]){2,12}$/;
           if(!reg.test(value)){  
               return false;  
           }else{
           	return true;
           }
		  }
	  
	  var match3 = function(value, param){
		  var reg= /^(133|153|177|180|181|189|134|135|136|137|138|139|150|151|152|157|158|159|178|182|183|184|187|188|130|131|132|155|156|176|185|186|145|147|170)\d{8}$/;
           if(!reg.test(value)){  
               return false;  
           }else{
           	return true;
           }
		  }
	  
	  $.validate.setRule("checkMember", match, '账号已经存在');
	  $.validate.setRule("checkRecommend", match1, '该账号未激活或不存在');
	  $.validate.setRule("checkRealName", match2, '姓名必须2至12个中文或英文字符');
	  $.validate.setRule("phone", match3, '格式不正确,输入正确的手机号');
	  //点击返回按钮操作
	  $('form .btn-cancel').on('click', function(){
	    var index = parseInt($(this).parent().attr('data-index'))-1;
	    if(index==0){return;}
	    $('.steps').find('li').filter(function(i){return i >= index}).removeClass('finished');
	    changeClass($('.step-wrap'), index-1, 'step-current');
	  });
	  
});

//提交标识
var subFlag = 1;
function change(f){
	subFlag = f;
}

var selectValue = function(value, element){
    if(element.is(":visible") && value == 0){  
        return false;  
    }else{
    	return true;
    }
};

Validate.setRule("selectValue", selectValue, '请选择一项');


		$("#form1").validate({
			success : function() {
				$("#phone").val($("#accountNo").val());
				if(subFlag == 1){
					submitstep(1);
				}else{
					submitform();
				}
				return false;
			}
		});
		
		$("#form2").validate({
			success : function() {
				submitstep(2);
				$("#realName").val($("#cardName").val());
				return false;
			}
		});
		
		$("#form3").validate({
			success : function() {
				submitform();
				return false;
			}
		});

		function submitform(){
			$("#sub1").attr("disabled","disabled");
			$("#sub2").attr("disabled","disabled");
			var formdata =$("#form1").serialize()+"&"+$("#form2").serialize()+"&"+$("#form3").serialize();
			var area,
				province,
				city;
			
			if($("#selectProvince").val() != 0){
				var province = $("#selectProvince  option:selected").text();
				formdata += "&province=" + province;
				if($("#selectCity").val() != 0){
		    		var city = $("#selectCity  option:selected").text();
		    		formdata +=  "&city=" + city ;
		    		if($("#selectArea").is(":visible")){
			    		area = $("#selectArea  option:selected").text();
			    		formdata += "&area=" + area;
			    	}
				}
			}
			$.ajax({
				url:'addMember',
				dataType:'json',
				data:formdata,
				success:function(data){
					if(data.success){
						window.location.href='toMemberAdd';
					}else{
						$("#sub1").attr("disabled","true");
						$("#sub2").attr("disabled","true");
					}
				},
				error:function(req,err,ecp){
					$("#sub1").attr("disabled","true");
					$("#sub2").attr("disabled","true");
				}
			});
		}
		
		function submitstep(index) {
			if (index == 3) {
				return;
			}
			$('.steps').find('li').eq(index).addClass('finished');
			changeClass($('.step-wrap'), index, 'step-current');
		}

		function changeClass($obj, index, oriCls) {
			//console.log(index);
			$obj.removeClass(oriCls).eq(index).addClass(oriCls);
		}
	</script>
</body>
</html>