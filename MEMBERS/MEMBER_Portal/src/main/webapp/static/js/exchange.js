
//点击按钮分页 ，远程获取数据
function doQuery(pageNumber) {
	pageNumber = pageNumber || 1;

	var _url = ctx + 'assetes/getExchangeList?pageNumber=' + pageNumber
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
	var list = data, htm = '<tr><th width="17%">兑换时间</th><th width="13%">兑换物品</th><th width="10%">兑换数量</th><th width="28%">消费所需金额</th><th width="17%" >发货状态</th><th>其他</th></tr>';
	for ( var key in list) {
		var obj = list[key];
		htm += '<tr><td>' + obj.applyTime + '</td>' ;
		
		if(obj.goodType == 1){
			htm += '<td>云碟</td><td>' + obj.exchangeNum + '</td><td>';
				if(obj.beans){
					htm += obj.beans+'博豆' ;
				}
				if(obj.overdraftBeans){
					htm += '+' + obj.overdraftBeans + '预支积分' ;
				}
				htm += '</td>';
		}else if(obj.goodType == 2){
			htm += '<td>智家套装</td><td>' + obj.exchangeNum + '</td><td>'+ (obj.cash/100)+ '现金';
			if(obj.overdraftBeans){
				htm +='+' + obj.overdraftBeans + '预支积分' ;
			}
			if(obj.beans){
				htm +='+' + obj.beans+'博豆' ;
			}
			htm += '</td>';
		}
				
		htm += '<td>' + stateFormat(obj.state) + '</td>'
				+ '<td>详情</td>'
			+ '</tr>'
			+ '<tr class="tr_detail">'
				+ '<td colspan="6">' 
					+'<p class="pdetail">'
						+'<span>收货人姓名：'+ obj.recepitUserName +'</span>'
						+'<span>收货人电话：'+ obj.recepitPhone +'</span>'
						+'<span>发货时间：'+ obj.sendTime +'</span>'
						+'<span>快递公司：'+ obj.expressCompany +'</span>'
						+'<span>发货单号：'+ obj.expressOrder +'</span>'
					+'</p>'
					+'<p class="pdetail">收货人地址：'+ obj.province +obj.city +obj.area +obj.fullAddress +'</p>'
				+'</td></tr>';
	}	
	
	$('.table_ui').empty().append(htm);
	
	 $(".table_ui tr").click(function(){
	      $(this).next(".tr_detail").toggle();
	 });
}

function stateFormat(v) {
	
	var map = [{
		"value" : "1",
		"text" : "<b style='color:#ff5722;'>兑换申请中</b>"
	}, {
		"value" : "2",
		"text" : "<b style='color:#02CDC1;'>已发货</b>"
	}, {
		"value" : "3",
		"text" : "<b>取消发货</b>"
	}];
	for ( var m in map) {
		if (map[m].value == v) {
			return map[m].text;
		}
	}
	return "";
}
