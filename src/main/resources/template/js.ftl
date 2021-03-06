var ${pascalName} = {};
$(function () {
    ${pascalName}.initTable = function () {
        //初始化表格
        var No = 0;
        ${pascalName}.table = $('#${humpName}_tab').DataTable({
            "dom": 'itflp',
            "processing": true,
            "searching": false,
            "serverSide": true, //启用服务器端分页
            "bInfo": false,
            "language": {"url": "plugins/datatables/language.json"},
            ajax: function (data, callback, settings) {
                var params = {
                    pageNum: data.start > 0 ? (data.start/data.length + 1) : 1,
                    pageSize: data.length
                };
                ${pascalName}.appendSearchParam(params);
                //ajax配置为function,手动调用异步查询
                $.ajax({
                    type: "GET",
                    url: "/sys/${flatName}/query",
                    cache: false, //禁用缓存
                    data: params, //传入已封装的参数
                    dataType: "json",
                    success: function (res) {
                        //封装返回数据，这里仅演示了修改属性名
                        var returnData = {};

                        returnData.draw = res.data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                        returnData.recordsTotal = res.data.total;
                        returnData.recordsFiltered = res.data.total;//后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = res.data.list;
                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                        callback(returnData);
                    }
                });
            },
            "columns": [
                {"data": null},
                <#list fieldList as field>
                <#if field.isPk == false || field.type == "String">
                {"data": "${field.humpName}"},
                </#if>
                </#list>
                {"data": null}
            ],
            "columnDefs": [
            {
                targets: 0,
                data: null,
                render: function (data) {
                    No = No + 1;
                    return No;
                }
            },
            {
                "targets": -1,
                "data": null,
                "render": ${pascalName}.getOptions
            }]
    }).on('preXhr.dt', function (e, settings, data) {
            No = 0;
        });

    };
    ${pascalName}.save = function () {
        $("span").remove(".errorClass");
        $("br").remove(".errorClass");
        var status = 1;
        <#list fieldList as field>
        <#if field.isPk == false && field.isSystemField == false && field.isNullable == true>
            if ($("#${field.humpName}").val() == "") {
                $("#${field.humpName}Label").prepend('<span class="errorClass" style="color:red">*${field.title}不能为空</span><br class="errorClass"/>');
                status = 0;
            }
        </#if>
        </#list>
        if (status > 0) {
            $.ajax({
                url: '/sys/${flatName}/save',
                type: 'post',
                dataType: 'text',
                data: $("#${humpName}AddForm").serialize(),
                success: function (data) {
                    $("#lgModal").modal('hide');
                    $.wise.alertMsg("添加成功");
                    ${pascalName}.reload();
                }
            });
        }
        else{
            return false;
        }
    };
    ${pascalName}.update = function () {
        $("span").remove(".errorClass");
        $("br").remove(".errorClass");
        var status = 1;
        <#list fieldList as field>
        <#if field.isPk == false && field.isSystemField == false && field.isNullable == true>
            if ($("#${field.humpName}").val() == "") {
                $("#${field.humpName}Label").prepend('<span class="errorClass" style="color:red">*${field.title}不能为空</span><br class="errorClass"/>');
                status = 0;
            }
        </#if>
        </#list>
        if (status> 0) {
            $.ajax({
                url: '/sys/${flatName}/update',
                type: 'post',
                dataType: 'text',
                data: $("#${humpName}EditForm").serialize(),
                success: function (data) {
                    $("#lgModal").modal('hide');
                    $.wise.alertMsg("修改成功");
                    ${pascalName}.reload();
                }
            });
        }
        else{
            return false;
        }
    };
    ${pascalName}.appendSearchParam = function (param) {
        var field = $("#${humpName}SearchField");
        var val = field.val();
        if(val){
            param[field.attr("name")] = val;
        }
    };
    ${pascalName}.getOptions = function(data){
        if(!${pascalName}.options){
            ${pascalName}.options = $("#${humpName}Options").html();
        }
        return ${pascalName}.options.replace(/\#\[id\]/g,data.${pk.humpName});
    };
    ${pascalName}.reload = function () {
        ${pascalName}.table.ajax.reload();
    };
    ${pascalName}.initTable();
});
