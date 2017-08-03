<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<div class="page_box">
	<table width="100%">
	<tr>
	<td align="right"><em id="page-desc">共<span id="sum"></span>条记录</em><div id="paging" class="page"></div></td>
	</tr></table>
</div>
<script type="text/javascript">
	var page="${param.page}"-0;
	var pageSize="${param.pageSize}"-0;
	var sum="${param.sum}"-0;
	var url="${param.url}";
	
	if(page==0){
		page=1;
	}
	if(pageSize==0){
		pageSize=10;
	}
	
	$(function(){
		if(sum==0){
			$('#paging').hide();
	    	$('#page-desc').hide();
	    }else{
	    	$('#sum').html(sum);
	    	pageInit(sum,pageSize);
	    }
	});
	
	function pageInit(sum, pageSize){
	    $("#paging").pagination({
	        items: sum,					//总记录数
	        itemsOnPage: pageSize,   
	        currentPage: page,		  	//当前页
	        cssStyle: 'compact-theme',	//插件样式
	        onPageClick: pageHandler	//点击分页按钮
	     });
	}
	
	function pageHandler(pageNumber){
		if(url!=""){
			if(url.indexOf("?")>0){
				window.location.href=url+"&page="+pageNumber+"&rows="+pageSize;
			}else{
				window.location.href=url+"?page="+pageNumber+"&rows="+pageSize;
			}
		}
	}
	
</script>