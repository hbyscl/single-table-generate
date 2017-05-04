package ${packageName}.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${packageName}.dao.basic.${pascalName}Mapper;
import ${packageName}.entity.${pascalName};
import ${packageName}.entity.${pascalName}Example;
import ${packageName}.service.${pascalName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * ${pascalName}服务实现
 * @author li.cheng
 * @version 1.0.0
 */
@Service
public class ${pascalName}ServiceImpl implements ${pascalName}Service {
    @Autowired
    private ${pascalName}Mapper mapper;

    @Override
    public ${pascalName} get(${pk.type} pk){
        return mapper.selectByPrimaryKey(pk);
    }

    @Override
    public Boolean add(${pascalName} bean) {
        bean.setCreateTime(new Date());
        return mapper.insert(bean) > 0;
    }

    @Override
    public Boolean del(${pk.type} pk) {
        return mapper.deleteByPrimaryKey(pk) > 0;
    }

    @Override
    public Boolean modify(${pascalName} bean) {
        bean.setUpdateTime(new Date());
        return mapper.updateByPrimaryKeySelective(bean) > 0;
    }

    @Override
    public PageInfo<${pascalName}> query(${pascalName} bean, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        ${pascalName}Example example = new ${pascalName}Example();
        ${pascalName}Example.Criteria criteria = example.createCriteria();

        <#list fieldList as field>
        if(null != bean.get${field.pascalName}()){
            criteria.and${field.pascalName}EqualTo(bean.get${field.pascalName}());
        }

        </#list>
        return new PageInfo<>(mapper.selectByExample(example));
    }


}
