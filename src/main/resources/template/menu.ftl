<#list l as t>
<li>
    <a target="navTab" href="$\{ctx}/${t.flatName}">
        <i class="fa fa-folder"></i> <span>${t.title}</span>
    </a>
</li>
</#list>
