<div class="row">
    <div class="col-md-12">
        <table class="table table-bordered">
        <#list fieldList as field>
            <#if field.isPk == false || field.type == "String">
            <tr>
                <td class="col-md-2">${field.title}</td>
                <td>${r"$"}{bean.${field.humpName}!}</td>
            </tr>
            </#if>
        </#list>
        </table>
    </div>
</div>