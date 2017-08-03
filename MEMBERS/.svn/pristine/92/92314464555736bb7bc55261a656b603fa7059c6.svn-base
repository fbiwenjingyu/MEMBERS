$(function(){
	var b= $("#balance").html();
	$("#balance").html(fmoney(b,2) +"元");
	$("#generalizeScore").html(score($("#generalizeScore").html())+"分");
	$("#shoppingScore").html(score($("#shoppingScore").html())+"个");
});

//点击按钮分页 ，远程获取数据
function doQuery(pageNumber) {
	pageNumber = pageNumber || 1;

	var _url = ctx + 'assetes/getTransformList?pageNumber=' + pageNumber
			+ '&pageSize=' + pageSize;

	$.ajax({
		url : _url,
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			if (data.result == false) {
				alert(data.msg);
				return;
			}
			if (data.total === 0) {//如果没有记录则隐藏分页插件
				$('.table_ui').empty();
				$(".notice_none").show();	
				$('.page_box').hide();
				return;
			} else {
				$(".notice_none").hide();	
				$('.page_box').show();
			}
			$('#sum').html(data.total);

			//自定义解析数据
			parseHtml(data.rows);

			if (pageNumber === 1) {
				sum = data.total;
				pageInit(sum, pageSize);
			}
		},
		error : function(text, xhr) {
			console.log(xhr);
		}
	});
}


function parseHtml(data) {
	var list = data, htm = '<tr><th width="30%">转换时间</th><th>转换类型</th><th width="25%">转换数值</th><th width="25%">获得数值</th></tr>';
	for ( var key in list) {
		var obj = list[key];
			
			if(obj.convertType==1){
				htm += '<tr>' 
					+ '<td>' + obj.createTime + '</td>' 
					+ '<td>' + '[博豆] 转 [积分]' + '</td>'
					+ '<td>' + obj.convertAmount + '博豆</td> '
					+ '<td>' + obj.getAmount + '积分</td> '
				 + '</tr>';
			}else if(obj.convertType==2){
				htm += '<tr>' 
					+ '<td>' + obj.createTime + '</td>' 
					+ '<td>' + '[现金币] 转 [博豆]' + '</td>'
					+ '<td>' + obj.convertAmount + '元</td> '
					+ '<td>' + obj.getAmount + '博豆</td> '
				 + '</tr>';
			}
	}
	$('.table_ui').empty().append(htm);
}
function fmoney(s, n) {
	  if(s == ''){
		  return 0.00;
	 }else if(s == 0){
		 return s;
	 }else{
	  n = n > 0 && n <= 20 ? n : 2;
	  f = s < 0 ? "-" : ""; //判断是否为负数
	  s = s/100;
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
function score(s){
	if(s == ''){
		return 0;
	}else{
		return s;
	}
}
