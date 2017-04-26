package ${packageName}.controller;

import ${packageName}.entity.${pascalName};
import ${packageName}.service.basic.${pascalName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ${packageName}.util.StringUtil;
import java.util.*;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月24日
 */
@Controller
@RequestMapping("/${flatName}")
public class ${pascalName}Controller {
    @Autowired
    private ${pascalName}Service service;

    @RequestMapping("")
    public String page() {
        return "${flatName}";
    }

    @RequestMapping("/get")
    @ResponseBody
    public DataVo get${pascalName}(
            @RequestParam ${pk.type} ${pk.humpName}) {
        return DataVo.set(service.get${pascalName}(${pk.humpName}));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DataVo delete${pascalName}(
            @RequestParam ${pk.type} ${pk.humpName}) {
        return DataVo.set(service.delete${pascalName}(${pk.humpName}));
    }

    @RequestMapping("/add")
    @ResponseBody
    public DataVo add${pascalName}(
        <#list fieldList as field>
            <#if field.isSystemField == false && field.isPk == false>
        @RequestParam(required = ${field.isNullable?string("true","false")}) <#if field.type == "Date">String<#else>${field.type}</#if> ${field.humpName},
            </#if>
        </#list>
        Model model
        ) {
        ${pascalName} ${humpName} = new ${pascalName}();
        <#list fieldList as field>
            <#if field.isSystemField == false && field.isPk == false>
                <#if field.type == "Date">
        ${humpName}.set${field.pascalName}(StringUtil.toDate(${field.humpName}));
                <#else>
        ${humpName}.set${field.pascalName}(${field.humpName});
                </#if>
            </#if>
        </#list>

        return DataVo.set(service.add${pascalName}(${humpName}));
    }

    @RequestMapping("/update")
    @ResponseBody
    public DataVo update${pascalName}(
        <#list fieldList as field>
            <#if field.isSystemField == false && field.isPk == false>
        @RequestParam(required = ${field.isNullable?string("true","false")}) <#if field.type == "Date">String<#else>${field.type}</#if> ${field.humpName},
            </#if>
        </#list>
        @RequestParam ${pk.type} ${pk.humpName}
        ) {
        ${pascalName} ${humpName} = new ${pascalName}();
        ${humpName}.set${pk.pascalName}(${pk.humpName});
        <#list fieldList as field>
            <#if field.isSystemField == false && field.isPk == false>
                <#if field.type == "Date">
        ${humpName}.set${field.pascalName}(StringUtil.toDate(${field.humpName}));
                <#else>
        ${humpName}.set${field.pascalName}(${field.humpName});
                </#if>
            </#if>
        </#list>
        return DataVo.set(service.update${pascalName}(${humpName}));
    }

    @RequestMapping("/query")
    @ResponseBody
    public DataVo query${pascalName}(
        <#list fieldList as field>
            <#if field.isPk == false>
            @RequestParam <#if field.type == "Date">String<#else>${field.type}</#if> ${field.humpName},
            </#if>
        </#list>
        @RequestParam(defaultValue = "1") int pageNum,
        @RequestParam(defaultValue = "10") int pageSize
        ) {
        ${pascalName} ${humpName} = new ${pascalName}();
        <#list fieldList as field>
            <#if field.isPk == false>
                <#if field.type == "Date">
        ${humpName}.set${field.pascalName}(StringUtil.toDate(${field.humpName}));
                <#else>
        ${humpName}.set${field.pascalName}(${field.humpName});
                </#if>
            </#if>
        </#list>
        return DataVo.set(service.query${pascalName}(${humpName},pageNum,pageSize));
    }



}
