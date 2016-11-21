<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="my" uri="/sjdf-tags" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${basePath}"/>
    <title>配置库列表</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.16.custom.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/tools_css.css"/>
    <script type='text/javascript' src="${staticPath}/js/jquery-1.7.1.min.js"></script>
    <script type='text/javascript' src="js/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="${staticPath}/js/layer/layer.min.js"></script>
    <script type="text/javascript" src="${staticPath}/js/layer/alert.js"></script>
    <script type='text/javascript' src="${staticPath}/js/jquery.blockUI.js"></script>
    <script type='text/javascript' src="${staticPath}/js/blockui.js"></script>
    <style type="text/css">
        .kzmb_nr td {
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            var list = "admin/common/DictionaryAction!list.action";
            //查询选定的配置数据
            $("#clazz").change(function () {
                var clazz = $(this).find("option:selected").val();
                window.self.location = list + "?dictionary.clazz=" + clazz;
            });

            <my:permission code="06010102">
            //添加
            $("#add").click(function () {
                $("#dialogDiv").load("admin/common/DictionaryAction!preAdd.action", {
                    'dictionary.clazz': $("#clazz option:selected").val()
                }, function () {
                    //绑定触发事件
                    $("#refClass").change(changeClass);
                    //对话框
                    $("#postForm").dialog({
                        title: "添加",
                        width: 480,
                        modal: true,
                        autoOpen: true,
                        show: "explode",
                        hide: "explode",
                        close: function () {
                            $(this).dialog("destroy").remove();
                        },
                        buttons: {
                            "添加": function () {
                                blockUI();
                                $.post("admin/common/DictionaryAction!add.action", $(this).serializeArray(), function (re) {
                                    unblockUI();
                                    if (re == "success") {
                                        window.self.location = list + "?dictionary.clazz=" + $("#clazzName option:selected").val();
                                    } else {
                                        jFail(re);
                                    }
                                }, "text");
                            },
                            "取消": function () {
                                $(this).dialog("close").remove();
                            }
                        }
                    });
                });
            });
            </my:permission>

            <my:permission code="06010104">
            //更新和删除
            $("tr.content").dblclick(function () {
                var id = $(this).attr("id");
                $("#dialogDiv").load("admin/common/DictionaryAction!edit.action", {
                    'idx': id
                }, function () {
                    //绑定触发事件
                    $("#refClass").change(changeClass);
                    //对话框
                    $("#postForm").dialog({
                        title: "修改",
                        width: 480,
                        modal: true,
                        autoOpen: true,
                        show: "explode",
                        hide: "explode",
                        close: function () {
                            $(this).dialog("destroy").remove();
                        },
                        buttons: {
                            <my:permission code="06010106">
                            "删除": function () {
                                jConfirm("确定删除吗?",function(){
                                    blockUI();
                                    $.post("admin/common/DictionaryAction!del.action", {idx: id}, function () {
                                        unblockUI();
                                        window.self.location = list + "?dictionary.clazz=" + $("#clazzName option:selected").val();
                                    });
                                })
                            },
                            </my:permission>
                            <my:permission code="06010105">
                            "更新": function () {
                                blockUI();
                                $.post("admin/common/DictionaryAction!update.action", $(this).serializeArray(), function (re) {
                                    unblockUI();
                                    if (re == "success") {
                                        window.self.location = list + "?dictionary.clazz=" + $("#clazzName option:selected").val();
                                    } else {
                                        jFail(re);
                                    }
                                }, "text");
                            },
                            </my:permission>
                            "取消": function () {
                                $(this).dialog("close").remove();
                            }
                        }
                    });
                });
            });
            </my:permission>

            function changeClass() {
                var typeId = $("#refClass").find("option:selected").val();
                var options = '<option value="0">=====请选择=====</option>';
                if (typeId != "") {
                    $.post("admin/common/DictionaryAction!getDictionaryListAjax.action", {clazz: typeId}, function (res) {
                        var r = res['result'];
                        for (var i = 0; i < r.length; i++) {
                            options += '<option value="' + r[i]['id'] + '">' + r[i]['name'] + '</option>';
                        }
                        $('#refList').empty().append(options);
                    }, "json");
                }
            }
        });
    </script>
</head>
<body>
<my:permission code="06010101">
	<span style="margin:7px 0 0 7px; float:left">
	    <my:filterSelect id="clazz" name="dictionary.clazz" list="clazzNameList" listKey="key" listValue="value" headerKey="" headerValue="====请选择===="/>
		<button type="button" id="add">添加</button>
	</span>
    <div class="kzmb_table_3" style="float:left;padding-left:7px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr class="kzmb_nm_3">
                <td>类别</td>
                <td>属性</td>
                <td>值</td>
                <td>名称(en)</td>
                <td>名称(cn)</td>
                <td>显示顺序</td>
                <td>引用</td>
                <td>操作动作</td>
                <td>功能大类</td>
                <td>子系统</td>
                <td>系统类别</td>
                <td>名称语言类别</td>
                <td>有效标记</td>
            </tr>
            <s:iterator value="dictionaryList">
                <tr class="content kzmb_nr" id='<s:property value="id"/>'>
                    <td><s:property value="className"/></td>
                    <td><s:property value="attr"/></td>
                    <td class="tip"><s:property value="value"/></td>
                    <td><s:property value="enName"/></td>
                    <td><s:property value="cnName"/></td>
                    <td><s:property value="orderBy"/></td>
                    <td><s:property value="directRef"/></td>
                    <td><s:property value="operateActionRef"/></td>
                    <td><s:property value="functionClassRef"/></td>
                    <td><s:property value="subsystemRef"/></td>
                    <td><s:property value="systemTypeRef"/></td>
                    <td><s:property value="langName"/></td>
                    <td><s:if test="isValid()">有效</s:if> <s:else>无效</s:else></td>
                </tr>
            </s:iterator>
        </table>
        <my:page/>
    </div>
    <div id="dialogDiv"></div>
</my:permission>
</body>
</html>