<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="add">
<div class="dialogPage">
	<div class="om-panel-header">新增</div>
	<div class="editDiv">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>用户编号：</td>
			<td><input type="text" id="userId" name="userId" onblur="getLevel()" class="easyui-validatebox"  data-options="validType:['checkMember']"></td>
		</tr>
		<tr>
			<td id="msg"></td>
		</tr>
		<tr>	
			<td><span class="required">*</span>请选择调整后的等级：</td>
			<td><input type="combo" id="newLevel" name="newLevel"></td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button id="submit" type="submit" class="button">&nbsp;提交&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	$('#newLevel').combobox({  
    	data:JSON.parse('${level}'),
    	editable:false,
    	panelHeight:'auto',
    		value:"0"
    });
	
	$.extend($.fn.validatebox.defaults.rules, {
		checkMember:{
        	validator: function(value, param){
        		var v = $.trim(value);
        		if(v != ''){
        			var flag = false;
	        		$.ajax({
	    				url:'checkMember',
	    				async: false,
	    				data:{"accountNo" : v},
	    				success:function(data){
	    					if(data.success){
	    						flag = true;
	    					}else{
	    						flag = false;
	    					}
	    				}
	    			});
	        		return flag;
        		}else{
        			return false;
        		}
    		},
    		message: '该用户不存在'
        },
        phone: {
    		validator: function(value, param){
    			var reg= /^(133|153|177|180|181|189|134|135|136|137|138|139|150|151|152|157|158|159|178|182|183|184|187|188|130|131|132|155|156|176|185|186|145|147|170)\d{8}[A-Z]{0,2}$/;
   	            if(!reg.test(value)){  
   	                return false;  
   	            }else{
   	            	return true;
   	            }
    		},
    		message: '格式不正确,输入正确的手机号'
        }
	
    });
	
	$("#submit").click(function(){
		if(!$("#form1").form('validate')){
			return false;
		}
	});
});

function getLevel(){
	if($("#userId").val()==''){
		$("#msg").html('');
	}else{
		$.ajax({
			url:'getlevel',
			dataType:'json',
			data:{'userNo':$("#userId").val()},
			success:function(data){
				$("#msg").html(data.msg);
			},
			error:function(req,err,ecp){
				$.messager.alert('提示:','系统异常','error'); 
			}
		});
	}
}
</script>
</html>