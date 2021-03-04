package glue.pudding.service;

import glue.pudding.entity.Position;
import glue.pudding.mapper.PositionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GUIXu on 2020/8/7.
 */
@Service
public class PositionService {

    PositionMapper positionMapper;

    public PositionService(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    public Position getPositionById(Integer id) {
        return positionMapper.selectByPrimaryKey(id);
    }
}
