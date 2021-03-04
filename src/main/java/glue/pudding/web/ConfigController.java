package glue.pudding.web;

import glue.pudding.service.MenuService;
import glue.pudding.entity.ResponseBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by GUIXu on 2020/5/4.
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    MenuService menuService;

    public ConfigController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping("/sysmenu")
    public ResponseBean sysMenu() {
        return ResponseBean.ok("success load menu!", menuService.getMenuByHrId());
    }
}
