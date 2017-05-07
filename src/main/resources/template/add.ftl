<div class="row">
    <div class="col-md-12">
        <form id="${humpName}AddForm">
            <div class="modal-body">
            <#list fieldList as field>
                <#if field.isPk == false && field.isSystemField == false || field.type == "String">
                    <div class="form-group">
                        <label id="${field.humpName}Label">${field.title}</label>
                        <input type="text" class="form-control" name="${field.humpName}" id="${field.humpName}"
                               placeholder="输入${field.title}...">
                    </div>
                </#if>
            </#list>
            </div>
            <div class="modal-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button type="button" class="btn btn-primary btn-sm" onclick="${pascalName}.save();"><i
                            class="fa fa-save"></i>保存
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>