function ownerTypeChange(num) {
    $("#itemCheckBox" + num).remove();
    var typeValue = $("#clazz" + num).find("option:selected").val();

    var checkBoxListHtml = "";
    var checkBoxName = typeValue + num;
    if (typeValue != 0) {
        $.post('admin/common/DictionaryAction!getDictionaryListAjax.action', {clazz: typeValue}, function (res) {
            if (res['bool']) {
                checkBoxListHtml = "<tr id='itemCheckBox" + num + "'><td></td><td><input type='checkbox' name='" + checkBoxName + "' id='selectAll" + num + "' value='0'>全选<br />";
                var result = res['result'];
                for (var i = 0; i < result.length; i++) {
                    checkBoxListHtml += "<input type='checkbox' name='" + checkBoxName + "' value='" + result[i].attr + "'>" + result[i].name + "<br />";
                }
                checkBoxListHtml += "</td></tr>";
                $("#ownerType" + num).after(checkBoxListHtml);

                //全选或全不选
                $("#selectAll" + num).click(function () {//当点击全选框时
                    $("input[name='" + checkBoxName + "']").attr("checked", this.checked);//选中或者取消选中
                });

                //如果全部选中勾上全选框，全部选中状态时取消了其中一个则取消全选框的选中状态
                $("input[name='" + checkBoxName + "']").each(function () {
                    $(this).click(function () {
                        if ($("input[name='" + checkBoxName + "']:checked").length == $("input[name='" + checkBoxName + "']").length) {
                            $("#selectAll" + num).attr("checked", "checked");
                        }
                        else $("#selectAll" + num).removeAttr("checked");
                    });
                });
            }
        }, 'json');
    }
}
function createOwnerType(num) {
    $("#createOwnerType" + num).remove();
    var clazzSelectString = "com.sjdf.platform.dictionary.bean.DisplayInfoType; ";
    $("select[name='dictionary.clazz']").each(function () {
        clazzSelectString += $(this).val() + "; ";
    });
    var editTable = $("#editTable");
    var currentNum = num + 1;
    var ownerTypeHtml = "<tr id='ownerType" + currentNum + "'><td align='right'>所属类别:</td><td><select name='dictionary.clazz' id='clazz" + currentNum + "' onchange='ownerTypeChange(" + currentNum + ");'><option value='0'>====请选择====</option>";
    $.post('admin/common/DictionaryAction!getSortedClazzName.action', {}, function (res) {
        if (res['bool']) {
            var result = res['result'];
            for (var i = 0; i < result.length; i++) {
                ownerTypeHtml += "<option value='" + result[i]["key"] + "'>" + result[i]["value"] + "</option>";
            }
            ownerTypeHtml += "</select><input id='createOwnerType" + currentNum + "' type='button' value='添加' onclick='createOwnerType(" + currentNum + ");' ></td></tr>";
            $(editTable).append($(ownerTypeHtml));
            $('#clazz' + currentNum).sfilter();
        }
    }, 'json');
}
function setDefaultChecked(name, index) {
    $("input[name='" + name + "']").each(function () {
        if ($(this).val() == index) {
            $(this).attr("checked", "checked");
        }
    });
}
function beforeSubmit() {
    var ownerTypeInfoValue = "";
    $("select[name='dictionary.clazz']").each(function () {
        var selectId = $(this).attr("id");
        var num = selectId.substring(selectId.lastIndexOf("z") + 1, selectId.length);
        var checkBoxName = $(this).val() + num;
        var checkBoxCheckedValue = "";
        if (parseInt($(this).val()) != 0) {
            $("input[name='" + checkBoxName + "']:[checked]").each(function (index) {
                if ($(this).val() != 0) {
                    checkBoxCheckedValue += $(this).val() + ",";
                }
            });
            checkBoxCheckedValue = checkBoxCheckedValue.substring(0, checkBoxCheckedValue.length - 1);
            ownerTypeInfoValue += $(this).val() + ":" + checkBoxCheckedValue + "; ";
        }
    });
    ownerTypeInfoValue = ownerTypeInfoValue.substring(0, ownerTypeInfoValue.lastIndexOf("; "));
    return ownerTypeInfoValue;
}