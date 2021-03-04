package glue.pudding.service;

import glue.pudding.entity.Nation;
import glue.pudding.mapper.NationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GUIXu on 2020/8/7.
 */
@Service
public class NationService {

    NationMapper nationMapper;

    public NationService(NationMapper nationMapper) {
        this.nationMapper = nationMapper;
    }

    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
