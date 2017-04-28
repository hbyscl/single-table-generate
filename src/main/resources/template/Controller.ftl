package ${packageName}.controller;

import ${packageName}.util.base.PageBean;
import ${packageName}.util.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
* Created by chengli on 2017/4/27.
*/
@Controller
@RequestMapping("/${flatName}")
public class ${pascalName}Controller extends BaseController {
@RequestMapping
public String ${humpName}List(Model model){
return "${flatName}/list";
}
/**
* 添加
*/
@RequestMapping(value = ADD, method = RequestMethod.GET)
public void add(ModelMap modelMap) {
return "${flatName}/add";
}

}
