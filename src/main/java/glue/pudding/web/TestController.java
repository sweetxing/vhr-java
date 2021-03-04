package glue.pudding.web;

import com.alibaba.fastjson.JSONObject;
import glue.pudding.entity.Hr;
import glue.pudding.mapper.HrMapper;
import glue.pudding.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by GUIXu on 2020/4/27.
 */
@RestController
public class TestController {

    @Autowired
    HrMapper hrMapper;

    @Autowired
    HrService hrService;

    @RequestMapping(value = "/")
    public Hr test() {

        return hrMapper.selectByPrimaryKey(5);
    }

    @RequestMapping(value = "/employee/advanced/hello")
    public String test1() {
        return "Hello Stupid world!";
    }

}
