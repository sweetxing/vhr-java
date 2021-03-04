package glue.pudding.service;

import glue.pudding.entity.JobLevel;
import glue.pudding.mapper.JobLevelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GUIXu on 2020/8/7.
 */
@Service
public class JobLevelService {

    JobLevelMapper jobLevelMapper;

    public JobLevelService(JobLevelMapper jobLevelMapper) {
        this.jobLevelMapper = jobLevelMapper;
    }

    public List<JobLevel> getAllJobLevels() {
        return jobLevelMapper.getAllJobLevel();
    }
}
