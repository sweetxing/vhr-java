package glue.pudding.mapper;

import glue.pudding.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EmployeeMapper {

    List<Employee> getEmployeeByPage(@Param("page") Integer page, @Param("size") Integer size,
                                     @Param("employee") Employee employee, @Param("beginDateScope") Date[] beginDateScope);

    Long getTotal(@Param("employee") Employee employee, @Param("beginDateScope") Date[] beginDateScope);

    Integer insert(Employee employee);
}
