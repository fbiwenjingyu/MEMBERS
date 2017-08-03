//点击按钮分页 ，远程获取数据
function doQuery(pageNumber) {
	pageNumber = pageNumber || 1;

	var _url = ctx + 'memberManage/getSubAccountList?pageNumber=' + pageNumber
				+ '&pageSize=' + pageSize + '&startDate='
				+ $('#startDate').val() + '&endDate=' + $('#endDate').val();

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
	var list = data, htm = '<tr><th width="30%">注册账号</th><th width="30%">注册金额</th><th>购买时间</th></tr>';
	for ( var key in list) {
		var obj = list[key];
		htm += '<tr>' 
			+ '<td>' + obj.accountNo + '</td>' 
			+ '<td>' + obj.registerMoney + '元</td>'
			+ '<td>' + obj.createTime + '</td>'
			+ '</tr>';
	}
	
	$('.table_ui').empty().append(htm);
}







