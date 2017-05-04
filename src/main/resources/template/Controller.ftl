package ${packageName}.controller;

import ${packageName}.controller.base.BaseController;
import ${packageName}.controller.base.JsonDto;
import ${packageName}.entity.${pascalName};
import ${packageName}.service.${pascalName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author li.cheng
 * @version 1.0.0
 */
@Controller
@RequestMapping("/sys/${flatName}")
public class ${pascalName}Controller extends BaseController {

    @Autowired
    private ${pascalName}Service service;

    @RequestMapping
    public String list() {
        return "${flatName}/list";
    }

    @ResponseBody
    @RequestMapping("page")
    public JsonDto query(
            ${pascalName} bean,
            Integer pageNum,
            Integer pageSize
    ) {
        pageNum = pageNum > 0 ? pageNum : 1;
        pageSize = pageSize >0 ? pageSize : 10;
        return retData(service.query(bean, pageNum, pageSize));
    }

    @ResponseBody
    @RequestMapping("get")
    public JsonDto get(${pk.type} id){
        return retData(service.get(id));
    }

    @ResponseBody
    @RequestMapping("delete")
    public JsonDto delete(${pk.type} id){
        return retData(service.del(id));
    }

    @RequestMapping("add")
    public String add() {
        return "${flatName}/add";
    }

    @ResponseBody
    @RequestMapping("save")
    public JsonDto save(${pascalName} bean){
        return retData(service.add(bean));
    }

    @RequestMapping("edit")
    public String edit(
            ${pk.type} id,
            ModelMap model) {
        ${pascalName} bean = service.get(id);
        model.put("bean", bean);
        return "${flatName}/edit";
    }

    @ResponseBody
    @RequestMapping("update")
    public JsonDto update(${pascalName} bean){
        return retData(service.modify(bean));
    }

    @RequestMapping("view")
    public String view(
            ${pk.type} id,
            ModelMap model) {
        ${pascalName} bean = service.get(id);
        model.put("bean", bean);
        return "${flatName}/view";
    }

}
