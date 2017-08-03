<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>

<div class="page_box">
	<table width="100%">
	<tr>
	<td align="right"><em id="page-desc">共<span id="sum"></span>条记录</em><div id="paging" class="page"></div></td>
	</tr></table>
</div>
<script type="text/javascript">

	var pageSize = 10 , sum; //每页显示多少条记录
	
    /*
	 * 查询分页入口函数
	 */
	pageHandler(1);
	 //初始化分页
	  function pageInit(sum, pageSize){
	    $("#paging").pagination({
	        items: sum,//总记录数
	        itemsOnPage: pageSize,   
	        //currentPage: parseInt($('#page').val()) || 1,		  //当前页
	        cssStyle: 'compact-theme',							  //插件样式
	        onPageClick: pageHandler							  //点击分页按钮
	     });
	}
	
	function pageHandler(pageNumber){
		//自定义处理查询分页函数
		doQuery(pageNumber);
	}
	
</script>
 
   
	
	
 