package glue.pudding.mapper;

import glue.pudding.entity.Menu;

import java.util.List;

public interface MenuMapper {

    List<Menu> getAllMenus();

    List<Menu> getAllMenusWithRole();

    List<Menu> getMenusByHrId(Integer id);
}