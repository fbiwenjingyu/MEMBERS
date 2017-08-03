$(function(){
	
	$("#sureSubmit").on("click",function(){
		
		addReply();
	})
	
});
//检查用户添加回复时的输入
function checkInput(){
	var value = trim($("#replyComtent").val());
	if(isEmpty(value)){
		$("#alertInfo font").html("内容不能为空");
		return false;
	}else if(value.length > 500){
		$("#alertInfo font").html("内容不能超过500个字");
		return false;
    }else{
		return true;
	}
	
}
//提交表单
function addReply(){
	
	if(checkInput()){
		
		$("form").submit();
	}
}