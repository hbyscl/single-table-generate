<div class="row">
    <div class="col-md-12">
        <form id="${humpName}EditForm">
            <input type="hidden" id="${pk.humpName}" name="${pk.humpName}" value=${r"${"}.${pk.humpName}}>
            <div class="modal-body">

            <#list fieldList as field>
                <#if field.isPk == false && field.isSystemField == false>
                    <div class="form-group">
                        <label id="${field.humpName}Label">名称</label>
                        <input type="text" class="form-control" name="${field.humpName}" id="${field.humpName}"
                               value=${r"${bean."}${field.humpName}!} disabled placeholder="输入${field.title}...">
                    </div>
                </#if>
            </#list>

            </div>
            <div class="modal-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button type="button" class="btn btn-primary btn-sm" onclick="${humpName}Save();"><i
                            class="fa fa-save"></i>保存
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    function ${humpName}Save() {
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
                url: '${r"$"}{ctx}/sys/${flatName}/update',
                type: 'post',
                dataType: 'text',
                data: $("#${humpName}EditForm").serialize(),
                success: function (data) {
                    $("#lgModal").modal('hide');
                    alertMsg("修改成功", "success");
                    reloadTable();
                }
            });
        }
    }
</script>