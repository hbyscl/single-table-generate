<div class="row">
    <div class="box-body  no-padding">
        <table class="table table-striped">
        <#list fieldList as field>
            <#if field.isPk == false>
                <tr>
                    <td>${field.title}：</td>
                    <td style="width: 90%">${r"${bean."}${field.humpName}!}</td>
                </tr>
            </#if>
        </#list>
        </table>
        <div class="box-footer">
            <div class="pull-right">
                <button type="button" class="btn btn-default btn-sm" id="close" data-dismiss="modal"><i
                        class="fa fa-close"></i>关闭
                </button>
            </div>
        </div>
    </div>
</div>