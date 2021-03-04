package glue.pudding.service;

import glue.pudding.entity.Department;
import glue.pudding.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GUIXu on 2020/8/7.
 */
@Service
public class DepartmentService {

    DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = departmentMapper.getAllDepartmentsByParentId(-1);
        return departments;
    }

    public Department getDepartmentById(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }
}
