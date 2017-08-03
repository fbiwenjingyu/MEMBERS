<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
   <style type="text/css">
  	#duiModal i{
  		font-family:"iconfont" !important;font-size:30px;font-style:normal;
  		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;color: #36D7CD;
		line-height: 52px;    margin-right: 10px;
		transition:all .3s ease;-webkit-transition:all .3s ease;
	}
  </style>
</head>
<body id="vip">
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
        <div class="ibox white" id="yundie_address">
            <h4 class="ibox_tit">请填写收货信息</h4>
            <form onsubmit="return false;" class="sui-form sui-validate" id="tForm">
             <input value="${exNum }" name="exNum" type="hidden" />
             <input value="${gType }" name="gType" type="hidden" />
              <div class="control-group ">
                <label for="shName" class="control-label">
                  <i class="star">*</i>收货人姓名</label>
                <div class="controls">
                  <input type="text" data-rules="required|minlength=2|maxlength=12|checkRealName" id="shName" name="shName">
                </div>
              </div>
              <div class="control-group ">
                <label for="shPhone" class="control-label">
                  <i class="star">*</i>收货人联系电话</label>
                <div class="controls">
                  <input type="text" data-rules="required|mobile|maxlength=11"  id="shPhone" name="shPhone">
                </div>
              </div>
              
              <div class="control-group ">
			        <label for="" class="control-label">
			          <i class="star">*</i>选择地区</label>
			        <div class="controls address">
			          		<select id="selectProvince" data-rules="selectValue"></select>
							<select id="selectCity" data-rules="selectValue"></select>
							<select id="selectArea"  style="display: none;"  data-rules="selectValue"></select>
					</div>
			   </div>
			   <div class="control-group ">
					<label for="fullAddress" class="control-label"> <i class="star">*</i>详细地址</label>
					<div class="controls">
						<input id="fullAddress" name="fullAddress" type="text" data-rules="required|minlength=5|maxlength=100" data-error-msg="请输入5到100之间的字符" placeholder="请填写详细的街道信息">
					</div>
				</div>
              
              <div class="control-group ">
                <div class="controls">
                  <button type="submit" data-ok="modal" class="sui-btn btn-ok">确定</button>
                  <button type="button" data-dismiss="modal" class="sui-btn btn-cancel" onclick="window.location.href='<c:url value = '/assetes/toExchange' />'">返回</button>
                </div>
              </div>
            </form>

        </div>

    </div>
  </div>
</div>

<div id="duiModal" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade window_box">
  <div class="window_cont">
    <h4>温馨提示</h4>
    <form  class="sui-form sui-validate">
      <label class="resultMesg"></label>
      <div class="control-group ">
        <div class="controls">
          <button type="button" data-ok="modal" class="sui-btn btn-ok" onclick="window.location.href='<c:url value = '/assetes/toExchange' />'">确定</button>
        </div>
      </div>
    </form>
  </div>
</div>

<script type="text/javascript" src="<c:url  value='/static/js/areaData.js' />" ></script>
<script type="text/javascript" src="<c:url  value='/static/js/jquery.extend.self.js' />"></script>
<script type="text/javascript">
$(function(){
	 var data = {
				aId:['selectProvince','selectCity','selectArea']
			};
	  $.areaSelect(data);
	
});
var match2 = function(value, param){
	  var reg = /^([a-z]|[A-Z]|[\u4e00-\u9fa5]){2,12}$/;
   if(!reg.test(value)){  
       return false;  
   }else{
   	return true;
   }
	  };

$.validate.setRule("checkRealName", match2, '姓名必须2至12个中文或英文字符');

var selectValue = function(value, element){
  if(element.is(":visible") && value == 0){  
      return false;  
  }else{
  	return true;
  }
};

Validate.setRule("selectValue", selectValue, '请选择一项');

$("#tForm").validate({
	success: function(){
			
		var formdata =$("#tForm").serialize();
		var province = $("#selectProvince  option:selected").text();
	  	var city = $("#selectCity  option:selected").text();
	  	var area = '';
	  	if($("#selectArea").is(":visible")){
	  		area = $("#selectArea  option:selected").text();
	  	}
	  	formdata += "&province=" + province + "&city=" + city + "&area=" + area;
	    
		$.ajax({
			url:'doExchange',
			dataType:'json',
			data:formdata,
			success:function(data){
				if(data.result){
					$('#duiModal .resultMesg').html("<i>&#xe601;</i>"+data.msg);	
				}else{
					$('#duiModal .resultMesg').html('<i style="color: red;">&#xe600;</i>'+data.msg);
				}
				$('#duiModal').modal('show');
			},
			error:function(req,err,ecp){
				alert(err); 
			}
		});
		return false;	
	}
});


</script>
</body>
</html>