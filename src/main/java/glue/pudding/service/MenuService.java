package glue.pudding.service;

import glue.pudding.entity.Menu;
import glue.pudding.mapper.MenuMapper;
import glue.pudding.util.HrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by GUIXu on 2020/4/28.
 */
@Service
@Transactional
@CacheConfig(cacheNames = "menus_cache")
public class MenuService {

    MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Cacheable(key = "#root.methodName")
    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenusWithRole();
    }

    public List<Menu> getMenuByHrId() {
        return menuMapper.getMenusByHrId(HrUtil.getCurrentHr().getId());
    }
}
