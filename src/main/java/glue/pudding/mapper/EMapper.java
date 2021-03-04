package glue.pudding.mapper;

import glue.pudding.entity.E;

public interface EMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(E record);

    int insertSelective(E record);

    E selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(E record);

    int updateByPrimaryKey(E record);
}