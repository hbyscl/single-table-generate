<#list l as t>
<li>
    <a target="content" href="${r"$"}{ctx}/sys/${t.flatName}" data-toggle="button">
        <i class="fa fa-folder"></i> <span>${t.title}</span>
    </a>
</li>
</#list>
