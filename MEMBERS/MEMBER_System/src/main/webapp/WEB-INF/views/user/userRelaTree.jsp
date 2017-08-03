<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户关系图</title>
<script id="code">
	function init() {
    	var $ = go.GraphObject.make;
    	
    	myDiagram = $(go.Diagram, "myDiagram",
			{
				initialAutoScale: go.Diagram.UniformToFill,
				layout: $(go.TreeLayout, { comparer: go.LayoutVertex.smartComparer })
        	}
    	);
    	
    	function combText(node){
    		return "用户编号："+node.ACCOUNT_NO+"\n"+"用户姓名："+node.REAL_NAME+"\n"+"用户电话："+node.PHONE;
    	}
    	
    	var bluegrad = $(go.Brush, "Linear", { 0: "rgb(60, 204, 254)", 1: "rgb(70, 172, 254)" });
        var pinkgrad = $(go.Brush, "Linear", { 0: "rgb(255, 192, 203)", 1: "rgb(255, 142, 203)" });
    	function brushConverter(brush){
			if (brush%2 == 0) return bluegrad;
			if (brush%2 == 1) return pinkgrad;
			return "orange";
    	}
    	
		myDiagram.nodeTemplate = $(go.Node, "Auto",
			{deletable: false},
			new go.Binding("text", "phone"),
			$(go.Shape, "Rectangle",
				{
					fill: "lightgray",
					stroke: "black",
					stretch: go.GraphObject.Fill,
					alignment: go.Spot.Center
				},
				new go.Binding("fill", "parent", brushConverter)),
			$(go.TextBlock,
				{
					font: "bold 8pt Helvetica, bold Arial, sans-serif",
					textAlign: "left",
					margin: 8, maxSize: new go.Size(150, NaN)
				},
				new go.Binding("text", "", combText))
		);

		myDiagram.linkTemplate =$(go.Link,
			{ 
				routing: go.Link.Orthogonal,
          		selectable: false 
          	},
        	$(go.Shape, {strokeWidth: 3, stroke: "#333"}));
		
		var nodeDataArray = [];
		myDiagram.model = new go.TreeModel(nodeDataArray);
		
		myDiagram.startTransaction("change Layout");
		var lay = myDiagram.layout;
		lay.treeStyle = go.TreeLayout.StyleLayered;
		lay.layerStyle = go.TreeLayout.LayerIndividual;
		lay.angle = 90;
		lay.alignment = go.TreeLayout.AlignmentCenterChildren;
		myDiagram.commitTransaction("change Layout");
		
		document.getElementById("searchTree").addEventListener("click", function() {
			var memberNo=jQuery("#memberNo").val();
			memberNo=memberNo.replace(/\s+/g,"");
			if(memberNo==""){
				jQuery.messager.confirm('提示:','确认从顶层开始查询？',function(e){ 
					if(e){
						rebuildGraph(memberNo);
					}
				});
			}else{
				rebuildGraph(memberNo);
			}
	    });
		
		function rebuildGraph(memberNo){
			jQuery.ajax({
	    		type:"POST",
	    		async: false,
	    		url:"${ctx}/user/queryUserRela",
	    		data:{memberNo:memberNo},
	    		dataType:"json",
	    		success:function(result){
		    		data = [];
		    		data=data.concat(result);
		    		myDiagram.model = new go.TreeModel(data);
		    	}
	    	});
		}
	}
</script>
</head>
<body onload="init();">
	<div id="toolbar">
<form id="list">
<input type="text" style="display: none;">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
				<table class="searchTable">
					<tr>
						<td>用户编号：</td>
						<td><input type="text" id="memberNo" name="memberNo"></td>
						<td><button id="searchTree" type="button" class="button">查询</button></td>
					</tr>
				</table>
			</div>
		</form>
		<div class="operate">
			<div class="om-panel-header">用户关系图</div>
			<div class="icon">
				<ul>
		    		
				</ul>
			</div>
		</div>
	</div>
	<div id="sample">
		<div id="myDiagram" style="background-color:#fff;border-top: solid 1px #eee;border-bottom: solid 1px #eee;width: 100%; height: 500px"></div>
	</div>
</body>
</html>