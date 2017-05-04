<#list l as t>
<li>
    <a target="navTab" href="${r"$"}{ctx}/sys/${t.flatName}">
        <i class="fa fa-folder"></i> <span>${t.title}</span>
    </a>
</li>
</#list>
