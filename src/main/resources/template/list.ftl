<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">${title}管理</h3>

            </div>
            <div class="box-body">
                <div class="clearfix">
                    <div class="col-md-4">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-search"></i></span>
                            <input type="text" class="form-control" id="${humpName}SearchField" name="${pk.humpName}" placeholder="根据ID搜索...">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <button type="submit" onclick="${pascalName}.reload();" class="btn btn-primary"><i class="fa fa-search"/>搜索</button>
                    </div>
                    <div class="box-tools pull-right">
                        <a class="btn btn-sm btn-success" target="modal" callback="${pascalName}.save()"
                           modal-title="添加${title}" href="/sys/${flatName}/add"><i class="fa fa-plus"/> 添加</a>
                    </div>
                </div>
                <table id="${humpName}_tab" class="table table-bordered table-striped" style="margin-top: 10px;">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <#list fieldList as field>
                            <#if field.isPk == false || field.type == "String">
                        <th>${field.title}</th>
                            </#if>
                        </#list>
                        <th>操作</th>
                    </tr>
                    </thead>
                </table>
                <#--table 操作菜单项-->
                <div id="${humpName}Options" style="display: none">
                    <a class="btn btn-xs btn-info" target="modal" modal-title="查看${title}" href="/sys/${flatName}/view?id=#[id]">查看</a> &nbsp;
                    <a class="btn btn-xs btn-warning" target="modal" modal-title="修改${title}" callback="${pascalName}.update()" href="/sys/${flatName}/edit?id=#[id]">修改</a> &nbsp;
                    <a class="btn btn-xs btn-danger" callback="${pascalName}.reload()" data-body="确认要删除吗？" target="ajaxTodo" href="/sys/${flatName}/delete?id=#[id]">删除</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="/wise/${flatName}.js"/>
