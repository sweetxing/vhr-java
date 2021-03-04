package glue.pudding.service;

import glue.pudding.entity.Employee;
import glue.pudding.entity.ResponsePageBean;
import glue.pudding.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by GUIXu on 2020/6/20.
 */
@Service
public class EmployeeService {

    EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public ResponsePageBean getEmployeeByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotal(employee, beginDateScope);
        ResponsePageBean bean = new ResponsePageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addEmployee(Employee employee) {
//        if (employeeMapper.insert(employee) != null) {
//            return 1;
//        } else {
//            return 0;
//        }
        return 1;
    }
}
