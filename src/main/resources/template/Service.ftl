package ${package}.service.basic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${package}.dao.basic.${entity}Mapper;
import ${package}.entity.${entity};
import ${package}.entity.${entity}Example;
import ${package}.util.BeanEntityHelper;
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
public class ${entity}Service {
    @Autowired
    private ${entity}Mapper mapper;

    public ${entity} add${entity}(${entity} ${entityObj}) {
        ${entityObj}.setCreateTime(new Date());
        mapper.insert(${entityObj});
        return ${entityObj};
    }

    public boolean delete${entity}(${pkType} ${pk}) {
        return mapper.deleteByPrimaryKey(${pk}) > 0;
    }

    public ${entity} update${entity}(${entity} ${entityObj}) {
        ${entityObj}.setUpdateTime(new Date());
        ${entity} old = mapper.selectByPrimaryKey(${entityObj}.get${pkUp}());
        BeanEntityHelper.copyNotNull(${entityObj}, old);
        mapper.updateByPrimaryKey(old);
        return ${entityObj};
    }

    public ${entity} get${entity}(${pkType} ${pk}) {
        return mapper.selectByPrimaryKey(${pk});
    }

    public PageInfo<${entity}> query${entity}(${entity} ${entityObj}, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ${entity}Example example = new ${entity}Example();
        ${entity}Example.Criteria criteria = example.createCriteria();

        <#list fields as field>
        if (null != ${entityObj}.get${field}()) {
            criteria.and${field}EqualTo(${entityObj}.get${field}());
        }
        </#list>

        List<${entity}> ${entityObj}s = mapper.selectByExample(example);
        return new PageInfo<>(${entityObj}s);
    }
}
