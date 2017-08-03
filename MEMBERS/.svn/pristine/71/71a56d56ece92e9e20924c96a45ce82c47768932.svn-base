<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.myDialog{  height: 100%; font-size: 14px;text-align: center;}
.diaTil {   border-bottom: 1px solid #ddd; padding: 6px 6px 10px;color: #333; font-weight: bold;  text-align: left;}
.diaCon {	margin: 8px 15px;}
.diaCon p {	padding: 5px 0;position: relative;}
.diaCon input[type='text'] {width: 190px; padding: 3px 2px; outline: none; }
.diaKuaidi {  padding: 4px 0px;  width: 195px; border: 1px solid #ddd; }
.myButton { width: 80px;  margin: 10px; font-weight: bold; line-height: 22px; background-color: #01CCC0;
	 outline: none; border: 1px solid #01CCC0; cursor: pointer;color: #fff;}
.errorTip{	color:red;position: absolute; left: 66px; font-size: 12px; bottom: -12px;display: none;  font-style: normal;}
</style>
</head>
<body>
	<form id="form1" action="confirmSend" method="POST" onsubmit="return validateFrom1();">
	<input type="hidden" value="${id }" name="id" id="id" />
    <input type="hidden" value="${opType}" name="opType" id="opType" />
    <input type="hidden" value="${version}" name="version" id="version" />
	<input type="hidden" name="expressCompany" id="expressCompany" />
		<div class="myDialog">
			<div class="diaTil om-panel-header">确认发货</div>
			<div>
				<div class="diaCon">
					<p>
						<select class="diaKuaidi" name="diaKuaidi" id="diaKuaidi"></select> 
						<i class="errorTip">请选择快递公司名称!</i>
					</p>
				</div>
				<div class="diaCon">
					<p>
						<input type="text" name="order" id="order"  placeholder="请填写物流单号信息" />
						<i class="errorTip">单号为数字或字母，长度为10到30位，不为空!</i>
					</p>
				</div>
			</div>
			<div style=" text-align: center;">
				<button id="confirmBtn" type="button" class="myButton">确&nbsp;定</button>
				<button onclick="javascript:art.dialog.close();" type="button" class="myButton">取&nbsp;消</button>
			</div>

		</div>
	</form>
</body>
<script type="text/javascript">
document.onkeydown = function(event) {  
    var target, code, tag;  
    if (!event) {  
        event = window.event; //针对ie浏览器  
        target = event.srcElement;  
        code = event.keyCode;  
        if (code == 13) {  
            tag = target.tagName;  
            if (tag == "TEXTAREA") { return true; }  
            else { return false; }  
        }  
    }  
    else {  
        target = event.target; //针对遵循w3c标准的浏览器，如Firefox  
        code = event.keyCode;  
        if (code == 13) {  
            tag = target.tagName;  
            if (tag == "INPUT") { return false; }  
            else { return true; }   
        }  
    }  
}; 

	$(function() {
		//提交表单
		$('#confirmBtn').click(function() {
			if(validateFrom1()){
				$('#form1').submit();
			}
		});
		
	    parseMoneyHtml();
		
	    //填充下拉框值
		function parseMoneyHtml(){
			var html=[];
			var data=JSON.parse('${kuaidiMap}');
			html.push('<option value="0">请选择快递公司</option>');
			for(var m in data)	{
				html.push('<option value="'+data[m].text+'">'+data[m].value+'</option>');
			}						
			$('#diaKuaidi').append(html.join(""));
		}
		
	    
		$("#diaKuaidi").focus(function(){
			$("#diaKuaidi").next(".errorTip").fadeOut();
			$("#diaKuaidi").removeAttr("style");
		}); 
		$("#order").blur(function(){
			var input=$.trim($(this).val());
			if(input.length>0){//如果输入有值
				$(this).val(input);
				var b = /^[0-9a-zA-Z]*$/g;
				if(b.test(input)){
					$("#order").next(".errorTip").fadeOut();
					$("#order").removeAttr("style");
				}else{
					$("#order").next(".errorTip").fadeIn();
					$("#order").css("border-color","red");
					return false;
				}
			}
		}); 
	});
	
	//校验表单
	function validateFrom1(){
		var flag=true;
		var kuaidi=$("#diaKuaidi").val();
		if(kuaidi == 0){
			$("#diaKuaidi").next(".errorTip").fadeIn();
			$("#diaKuaidi").css("border","1px solid red");
			flag=false;
		}else{
			$("#diaKuaidi").next(".errorTip").fadeOut();
			$("#diaKuaidi").removeAttr("style");
		}
		var b = /^[0-9a-zA-Z]*$/g;
		var input=$.trim($("#order").val());//快递单号
		//alert("["+input+"]----"+b.test(input));
		if(input == "" || input.length<10 ||input.length >30){//输入为空
			$("#order").css("border-color","red");//错误提示
			$("#order").next(".errorTip").fadeIn();
			$("#order").focus();
			flag=false;
			}else if(!b.test(input)){//输入格式不为数字和字母
				$("#order").css("border-color","red");//错误提示
			$("#order").next(".errorTip").fadeIn();
			$("#order").focus();
			flag=false;
		}else{
			$("#order").removeAttr("style");
			$("#order").next(".errorTip").fadeOut();
		}
		$("#expressCompany").val($("#diaKuaidi").find("option:selected").text());
		return flag;
	}

</script>
</html>