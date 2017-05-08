<div class="row">
    <div class="col-md-12">
        <form id="${humpName}EditForm">
            <#if pk.type != "String">
            <input type="hidden" id="${pk.humpName}" name="${pk.humpName}" value=${r"$"}{bean.${pk.humpName}}>
            </#if>
            <#list fieldList as field>
                <#if field.isPk == false && field.isSystemField == false || field.type == "String">
                    <div class="form-group">
                        <label id="${field.humpName}Label">${field.title}</label>
                        <input type="text" class="form-control" name="${field.humpName}" id="${field.humpName}"
                               value="${r"$"}{bean.${field.humpName}!}" placeholder="输入${field.title}...">
                    </div>
                </#if>
            </#list>
        </form>
    </div>
</div>