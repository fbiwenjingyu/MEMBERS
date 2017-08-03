//去左右两边的空格
function trim(str){
	return str.replace(/^\s+|\s+$/g,"");
}
//是否为空
function isEmpty(s){
	return /^\s*$/.test(s);
}
//检测输入(前台添加回复)
function checkInput(){
	  
	  var word = $("#replyComtent").val();
	  var pass = true;
	  var obj = $("#check font");
	  if(isEmpty(trim(word))){
		  pass = false;
		  obj.html('输入不能为空');
		  obj.show();
	  }else if(trim(word).length > 300){
		  pass = false;
		  obj.html('输入不能超过300个字');
		  obj.show();
	  }
	  return pass;
	  
}