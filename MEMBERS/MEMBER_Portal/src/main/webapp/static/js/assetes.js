$(function(){
	var b= $("#balance").html();
	$("#balance").html(fmoney(b,2) +"元");
	$("#generalizeScore").html(score($("#generalizeScore").html())+"分");
	$("#shoppingScore").html(score($("#shoppingScore").html())+"个");
	
	//清空韩流馆业务类型
	$("#hlgbusinesstype").val("");
	
	//更改业务类型businesstype
	$("#businesstype").change(function(){
		var val=$(this).val();
		if(val == "hlg"){//如果类型为韩流馆，则显示具体的韩流馆业务选项
			$("#hlgbusinesstype").val("all");
			$("#hlgSelect").fadeIn();
		}else{
			$("#hlgSelect").fadeOut();
			$("#hlgbusinesstype").val("");
		}
	});
	
	//切换tableTil样式
	$(".tabTil li").click(function(){
		$(this).addClass("show_on").siblings().removeClass("show_on");
	});
});

//table  切换标志
var tabindex=0;

//点击按钮分页 ，远程获取数据
function doQuery(pageNumber) {
	pageNumber = pageNumber || 1;//当前页码
	
	var _url =  null;
	if(tabindex==0){
		//获取资产流水
		_url =  ctx + 'assetes/getAccountList?pageNumber=' + pageNumber	+ '&pageSize=' + pageSize 
		+ '&startDate='	+ $('#startDate').val() + '&endDate=' + $('#endDate').val()
		+ '&awardtype=' + $('#awardtype').val() + '&businesstype=' + $('#businesstype').val();
		
		$("#hlgbusinesstype").val() && (_url += '&hlgbusinesstype='+ $('#hlgbusinesstype').val());
		
	}else if(tabindex==1){
		//获取资产汇总
		_url =  ctx + 'assetes/getDayPointDetail?pageNumber=' + pageNumber	+ '&pageSize=' + pageSize 
		+ '&startDate='	+ $('#startDate').val() + '&endDate=' + $('#endDate').val();
	}

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

//解析表格数据
function parseHtml(data) {
	var list = data, htm = null;
	
	if(tabindex==0){//解析资产流水
		
		htm = '<tr><th width="20%">时间</th><th width="15%">奖项类型</th><th>业务内容</th><th width="15%">收入</th><th width="15%">支出</th></tr>';
		for ( var key in list) {
			var obj = list[key];
			htm += '<tr><td>' + obj.createTime + '</td>'  //时间
				+ '<td>' + typeFormat(obj.accountType) + '</td>' ; //奖项类型
			
			//业务内容
			if(obj.userType == 22){//如果是笨笨商城导入的数据，则不用拼接
				htm += '<td>'+obj.inoutDesc + '</td> ';
			}else if(obj.userType == 12){
				if(obj.tradeNo){
					htm += '<td>'+obj.inoutDesc + ',订单号'+hideToStarts(obj.tradeNo) +'</td> ';
				}else{
					htm += '<td>'+obj.inoutDesc + '</td> ';
				}
			}else{
				if(obj.inoutType == 201){
					htm += '<td>'+obj.inoutDesc + ',来自系统奖励</td> ';
				}else{
					if(obj.tradeNo){
						htm += '<td>'+obj.inoutDesc + ',来自用户'+hideToStarts(obj.tradeNo) +'</td> ';
					}else{
						htm += '<td>'+obj.inoutDesc + '</td> ';
					}
				}
			}
			
			var _amont=parseInt(obj.amount);
			var _amoutDes="";
			//收入
			if(_amont>=0 && (obj.inoutType==101||obj.inoutType==102||obj.inoutType==103||obj.inoutType==113||obj.inoutType==114||obj.inoutType==115||obj.inoutType==201||obj.inoutType==213||obj.inoutType==216||obj.inoutType==218||obj.inoutType==301)){
				if (obj.accountType == 1) {
					_amoutDes='+'+ (obj.amount / 100) + '元';
				} else if (obj.accountType == 2) {
					_amoutDes='+'+ obj.amount + '博豆';
				} else if (obj.accountType == 3) {
					_amoutDes='+'+ obj.amount + '积分';
				} else {
					_amoutDes= 'undefined';
				}
				htm += '<td><i style="color: #02CDC1;">'+_amoutDes+'</i></td><td>-</td>';
			}else if(_amont<0 && (obj.inoutType==124||obj.inoutType==125||obj.inoutType==127||obj.inoutType==224||obj.inoutType==225||obj.inoutType==226||obj.inoutType==228||obj.inoutType==324)){//支出
				//支出
				htm += '<td>-</td>';
				if (obj.accountType == 1) {
					_amoutDes= (obj.amount / 100) + '元';
				} else if (obj.accountType == 2) {
					_amoutDes= obj.amount + '博豆';
				} else if (obj.accountType == 3) {
					_amoutDes= obj.amount + '积分';
				} else {
					_amoutDes= 'undefined';
				}
				htm += '<td><i style="color: #ff5722;">'+_amoutDes+'</i></td>';
			}
			htm += '</tr>';
		}
		
	}else if(tabindex==1){//解析资产汇总
		htm = '<tr><th>时间</th><th width="12%">推荐奖励</th><th width="12%">管理奖励</th><th width="12%">辅导奖励</th><th width="10%">博豆</th><th width="10%">积分</th><th width="10%">实发</th><th width="12%">个人业绩</th><th width="12%">团队业绩</th></tr>';
		for ( var key in list) {
			var obj = list[key];
			htm += '<tr>' 
				+ '<td>' + (obj.collectDate).substring(0,10) + '</td>'  //时间
				+ '<td>' + formatPAmount(obj.rewardAmount) + '</td>'  //推荐奖励
				+ '<td>' + formatPAmount(obj.manageAmount) + '</td>'  //管理奖励
				+ '<td>' + formatPAmount(obj.coachAmount) + '</td>'   //辅导奖励
				+ '<td>' + obj.beansNum + '</td>'	//购物积分
				+ '<td>' + obj.pointAmount + '</td>'  //推广积分
				+ '<td><i style="color: #ff5722;">' + formatPAmount2(obj.pointAmount+obj.beansNum+obj.rewardAmount/100+obj.manageAmount/100+obj.coachAmount/100) + '</i></td>'  //实发
				+ '<td>' + formatPAmount2(obj.myMoney) + '</td>'		//个人业绩
				+ '<td>' + formatPAmount2(obj.teamMoney) + '</td></tr>';  //团队业绩
		}
	}
	
	$('.table_ui').empty().append(htm);
}



function switchDiv(f){
	//重置查询条件
	resetSearch();
	if (f == 1) {//切换到搜索页面
		$("#my_count").slideUp();
		$("#my_count_seach").slideDown();
		$("#hlgSelect").hide();	
	} else if (f == 0) {//切换到显示资产页面
		doQuery(1);
		$("#my_count").slideDown();
		$("#my_count_seach").slideUp();
	}
}

//切换资产数据显示
function changeTab(f){
	
	//重置搜索条件
	resetSearch();
	tabindex=f;
	doQuery(1);
	if(f==0 ){//点击查看资产流水需刷新数据
		$("#awardSelect,#businessSelect").show();
	}else if( f==1){//资产汇总，不用点击刷新(非实时数据)
		$("#awardSelect,#businessSelect,#hlgSelect").hide();
	}
	
}

//重置搜索条件
function resetSearch(){
	$("#startDate,#endDate,#businesstype,#awardtype").val("");
	$("#hlgbusinesstype").val("all");
}

function typeFormat(v) {
	var map = [ {
		"value" : 1,
		"text" : "现金币"
	}, {
		"value" : 2,
		"text" : "博豆"
	}, {
		"value" : 3,
		"text" : "积分"
	} ];
	for ( var m in map) {
		if (map[m].value == v) {
			return map[m].text;
		}
	}
}

function hideToStarts(v){
	  if(!v){
	    return v;
	  }  

	  var len=v.length; 
	  if(len<=5){
	    return v;
	  }else if(len<11){   
	    return plusXing(v,2,2);	   
	  }else{
	    //var reg = /(\d{3})\d{4}(\d{4})/;
	    //return v.replace(reg,"$1****$2");
	     return plusXing(v,3,4);
	  }  
	}

/*
 * str：字符串，frontLen：前面保留位数，endLen：后面保留位数。
 */
function plusXing (str,frontLen,endLen) { 
    //var len = str.length-frontLen-endLen;
	var len=4;
    var xing = '';
    for (var i=0;i<len;i++) {
        xing+='*';
    }
    return str.substr(0,frontLen)+xing+str.substr(str.length-endLen);
}

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

function formatPAmount(v){
	if(v){
		return (v/100).toFixed(2);
	}else{
		return 0;
	}
}
function formatPAmount2(v){
	if(v){
		return v.toFixed(2);
	}else{
		return 0;
	}
}

