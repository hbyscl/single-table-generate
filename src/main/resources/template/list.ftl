<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">${title}管理</h3>
                <div class="box-tools pull-right">
                    <a onclick="${humpName}ToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg"
                       title="123" href="/${flatName}/add">添加</a>
                </div>
            </div>
            <div class="box-body">
                <div class="clearfix">
                    <div class="col-md-4">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-search"></i></span>
                            <input type="text" class="form-control" id="${humpName}Premise" placeholder="根据名称搜索...">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <button type="submit" onclick="${humpName}Reload();" class="btn btn-primary">搜索</button>
                    </div>
                </div>
                <table id="${humpName}_tab" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                    <tr>
                        <th>序号</th>
                        <#list fieldList as field>
                            <#if field.isPk == false>
                        <th>${field.title}</th>
                            </#if>
                        </#list>
                        <th>操作</th>
                    </tr>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var ${humpName}_tab;
    $(function () {
        //初始化表格
        var No = 0;
        ${humpName}_tab = $('#${humpName}_tab').DataTable({
            "dom": 'itflp',
            "processing": true,
            "searching": false,
            "serverSide": true, //启用服务器端分页
            "bInfo": false,
            "language": {"url": "plugins/datatables/language.json"},
            "ajax": {"url": "/${flatName}/page", "type": "post"},
            "columns": [
                {"data": null},
                <#list fieldList as field>
                    <#if field.isPk == false>
                {"data": ${field.humpName},
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
                    "render": function (data) {
                        var btn = '<a class="btn btn-xs btn-primary" target="modal" modal="lg" href="/${flatName}/view?id=' + data.${pk.humpName} + '">查看</a> &nbsp;'
                                + '<a class="btn btn-xs btn-info" onclick="${humpName}ToListAjax();" target="modal" modal="lg" href="/${flatName}/edit?id=' + data.${pk.humpName} + '">修改</a> &nbsp;'
                                + '<a class="btn btn-xs btn-default" callback="${humpName}Reload();" data-body="确认要删除吗？" target="ajaxTodo" href="/${flatName}/delete?id=' + data.${pk.humpName} + '">删除</a>';
                        return btn;
                    }
                }]
        }).on('preXhr.dt', function (e, settings, data) {
            No = 0;
        });

        $("#${humpName}Seek").on("click", function () {
            reloadTable(${humpName}_tab, "", "#${humpName}Premise");
        });
    });

    function ${humpName} Reload() {
        reloadTable(${humpName}_tab, "", "#${humpName}Premise");
    }

    function ${humpName} ToListAjax() {
        list_ajax = ${humpName}_tab;
        console.log(list_ajax);
    }
</script>