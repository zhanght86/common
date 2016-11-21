<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="${basePath}" />
<title>page_content_right</title>
	<link rel="stylesheet" type="text/css" href="css/demo.css" />
	<link rel="stylesheet" type="text/css" href="css/zTreeStyle/zTreeStyle.css" />
	<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.16.custom.css" />
	<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
	<script type='text/javascript' src="js/jquery-ui-1.8.16.custom.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript">
		<!--
		var setting = {
			async: {
				enable: true,
				//url:"../asyncData/getNodes.php",
				url:"admin/common/PageContentManageAction!findJSON.action",
				autoParam:["databaseId" ],
				otherParam : {
					"versionId":"${versionId}"
				},
				dataFilter : filter
			},
			view : {
				expandSpeed : "",
				addHoverDom : addHoverDom,
				removeHoverDom : removeHoverDom,
				selectedMulti : false
			},
			edit : {
				enable : true,
				showRemoveBtn: setRemoveBtn,
				showRenameBtn: setRenameBtn
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {//回调函数，在这里可做一些回调处理 
				beforeRemove : beforeRemove,
				beforeRename : beforeRename,
				onRemove : onRemove,
				onRename : onRename,
				onClick: onClick
			}
		};

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes)
				return null;
			for ( var i = 0, l = childNodes.length; i < l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		
		// 点击节点
		function onClick(event,treeId, treeNode) {
			$("#win").attr("src",treeNode.url);
		}
		
		// 删除节点是否显示
		function setRemoveBtn(treeId, treeNode) {
			return !treeNode.readOnly;
		}
		
		// 删除节点前确认
		function beforeRemove(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
		}
		//如果用户设置了 beforeRemove 回调函数，并返回 false，将无法触发 onRemove 事件回调函数。
		function onRemove(event, treeId, treeNode) {
			//操作数据库，删除相应节点并返回到本页面
			alert("删除节点--" + treeNode.name + "成功！");
			// ajax 删除节点
			urlStrOption = "admin/common/PageContentManageAction!delNode.action";
			var params = "databaseId=" + treeNode.databaseId;
			zTreeOption(urlStrOption, params);
		}
		
		// 重命名节点是否显示
		function setRenameBtn(treeId, treeNode) {
			return !treeNode.readOnly;
		}
		
		// 重命名节点名称前确认
		function beforeRename(treeId, treeNode, newName) {
			if (newName.length == 0) {
				alert("节点名称不能为空.");
				return false;
			}
			return true;
		}
		//操作数据库，进行节点重命名
		function onRename(event, treeId, treeNode) {
			// ajax重命名节点
			urlStrOption = "http://test.51web.com/admin/common/PageContentManageAction!reNameNode.action";
			var params = "databaseId=" + treeNode.databaseId
						+ "&versionId=" + treeNode.versionId
						+ "&name=" + treeNode.name;
			zTreeOption(urlStrOption, params);
		}

		// 获取焦点添加节点按钮显示
		function addHoverDom(treeId, treeNode) {
			if (treeNode.readOnly == true) {
				return;
			}
			var sObj = $("#" + treeNode.tId + "_span");
			if ($("#addBtn_" + treeNode.id).length > 0)
				return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.id
					+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.append(addStr);
			var btn = $("#addBtn_" + treeNode.id);
			if (btn)
				btn.bind("click", function() {
					// ajax 添加节点
					urlStrOption = "admin/common/PageContentManageAction!addNode.action";
					var params = "databaseId=" + treeNode.databaseId + "&versionId=" + treeNode.versionId;
					var json = zTreeOption(urlStrOption, params);
					//　树形节点增加	
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					zTree.addNodes(treeNode, {
						databaseId : json.databaseId,
						versionId : json.versionId,
						name : json.name,
						url : json.url
					});
				});
		};
		// 失去焦点添加节点按钮不显示
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_" + treeNode.id).unbind().remove();
		};

		$(document).ready(function() {
			$.fn.zTree.init($("#treeDemo"), setting);
		});
		
		//对树结构进行增删改的异步处理ajax//返回节点在数据库的id
		function zTreeOption(doUrl, backParams) {
			var tempdata;
			$.ajax({
				async : false,
				url : doUrl,
				type : 'post',
				dataType : 'json',
				data : backParams,
				success : function(data) {
					tempdata = data;
				}
			});
			return tempdata;
		}
		
		var action = "admin/common/PageContentManageAction!turnToIndex.action";
		
		
		$(function() {
			// 复制版本
			$("#copyVersion").click(function() {
				$("#dialogDiv").load("admin/common/PageContentManageAction!preCopyVersion.action", {
					'versionId' : "${versionId }"
				}, function() {
					//对话框
					$("#postForm").dialog({
						title : "复制版本",
						width : 460,
						modal : true,
						autoOpen : true,
						show : "explode",
						hide : "explode",
						close : function() {
							$(this).dialog("destroy").remove();
						},
						buttons : {
							"确定" : function() {
								$("#postForm").submit();
								
								/* $.post("admin/common/PageContentManageAction!copyVersion.action", $(this).serializeArray(), function(re) {
										//window.self.location = action + "?versionId=" + re;
								}, "text"); */
							},
							"取消" : function() {
								$(this).dialog("close").remove();
							}
						}
					});
				});
			});
			
			// 推送缓存
			$("#pushContent").click(function() {
				if (!confirm('请确定是否推送缓存,这将改变页面显示')) {
					return;
				} else {
					$.post("admin/common/PageContentManageAction!pushContent.action",{versionId : "${versionId}"},function(re) {
						alert(re);
					},"text");
				}
			});
			
			// 预览首页
			$("#previewIndex").click(function() {
				$.post("admin/common/PageContentManageAction!pushPreview.action",{versionId : "${versionId}"},function(re) {
					var result = re.split("❤");
					if ("success" == result[0]) {
						var open = window.open(result[1] + "?officialPageContnet=2");
						
						if (open.document.readyState=="complete") {
							// 清除预览缓存
							//setTimeout($.post("admin/common/PageContentManageAction!pushDestroyPreview.action",{versionId : "${versionId}"},function(re){
							//},"text"),15000);
						}
					} else {
						alert(re);
					}
				},"text");
			});
			
			// 设置高度
		});
	//-->
	</script>
	
	<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
.kzmb_table_3{height:35px; line-height:35px;background:url(images/kzbmbg.png) repeat-x 0px -78px;}
.kzmb_table_3 table tr td a{display:block;text-decoration:none;float:left;text-align:center}
.kzmb_a{width:50px;height:35px;border-right:1px #3C6E31 solid;}
.kzmb_b{width:50px;height:35px;}
.kzmb_table_3 table tr td a:hover{background:none;color:#3C6E31}

	</style>
</head>
<body>
<div style="width: 100%; margin: 0 auto;border-right:1px #c0e1f3 solid;">
	<div class="kzmb_table_3">
		 <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr class="kzmb_bottomLine">
	          	<td><a href="admin/common/PageContentManageAction!list4Version.action"  class="kzmb_a">返回</a></td>
	            <td><a href="javascript:location.href=location.href"  class="kzmb_a">刷新</a></td>
	      	 	<td><a id="copyVersion" href="javascript:void(0)"  class="kzmb_a">复制版本</a></td>
	      	 	<s:if test="versionManageBean.enableMarkup == @com.sjdf.platform.dictionary.bean.WhetherState@YES">
	      	 		<td><a id="pushContent" href="javascript:void(0)"  class="kzmb_a">推送缓存</a></td>
	      	 	</s:if>
	      	 	<s:else>
	      	 		<td><a id="pushContentReadOnly" href="javascript:alert('当前版本不是启用版本')"  class="kzmb_a">推送缓存</a></td>
	      	 	</s:else>
	      	 	<td><a id="previewIndex" href="javascript:void(0)"  class="kzmb_b">预览首页</a></td>
	      	 </tr>
	     </table>
	</div>
	<!-- 树形展示 -->
	<div class="zTreeDemoBackground">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</div>
<div id="dialogDiv"></div>
</body>
</html>