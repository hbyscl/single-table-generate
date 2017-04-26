package ${packageName}.service.basic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${packageName}.dao.basic.${pascalName}Mapper;
import ${packageName}.entity.${pascalName};
import ${packageName}.entity.${pascalName}Example;
import ${packageName}.util.BeanEntityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月24日
 * @since soter 1.0.0
 */
@Service
public class ${pascalName}Service {
    @Autowired
    private ${pascalName}Mapper mapper;

    public ${pascalName} add${pascalName}(${pascalName} ${humpName}) {
        ${humpName}.setCreateTime(new Date());
        mapper.insert(${humpName});
        return ${humpName};
    }

    public boolean delete${pascalName}(${pk.type} ${pk.humpName}) {
        return mapper.deleteByPrimaryKey(${pk.humpName}) > 0;
    }

    public ${pascalName} update${pascalName}(${pascalName} ${humpName}) {
        ${humpName}.setUpdateTime(new Date());
        ${pascalName} old = mapper.selectByPrimaryKey(${humpName}.get${pk.pascalName}());
        BeanEntityHelper.copyNotNull(${humpName}, old);
        mapper.updateByPrimaryKey(old);
        return ${humpName};
    }

    public ${pascalName} get${pascalName}(${pk.type} ${pk.humpName}) {
        return mapper.selectByPrimaryKey(${pk.humpName});
    }

    public PageInfo<${pascalName}> query${pascalName}(${pascalName} ${humpName}, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ${pascalName}Example example = new ${pascalName}Example();
        ${pascalName}Example.Criteria criteria = example.createCriteria();

        <#list fieldList as field>
        if (null != ${humpName}.get${field.pascalName}()) {
            criteria.and${field.pascalName}EqualTo(${humpName}.get${field.pascalName}());
        }
        </#list>

        List<${pascalName}> ${humpName}s = mapper.selectByExample(example);
        return new PageInfo<>(${humpName}s);
    }
}
