$(function(){
	
	$("#sureSubmit").on("click",function(){
		
		addReply();
	})
});
//检查用户添加回复时的输入
function checkInput(){
	var value = trim($("#replyComtent").val());
	if(isEmpty(value)){
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