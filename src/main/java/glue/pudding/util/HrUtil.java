package glue.pudding.util;

import glue.pudding.entity.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by GUIXu on 2020/5/4.
 */
public class HrUtil {

    public static Hr getCurrentHr() {
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
