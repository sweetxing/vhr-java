package glue.pudding.service;

import glue.pudding.entity.PoliticsStatus;
import glue.pudding.mapper.PoliticsStatusMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GUIXu on 2020/8/7.
 */
@Service
public class PoliticsStatusService {

    PoliticsStatusMapper politicsStatusMapper;

    public PoliticsStatusService(PoliticsStatusMapper politicsStatusMapper) {
        this.politicsStatusMapper = politicsStatusMapper;
    }

    public List<PoliticsStatus> getAllPoliticsStatusService() {
        return politicsStatusMapper.getAllPoliticsStatus();
    }
}
