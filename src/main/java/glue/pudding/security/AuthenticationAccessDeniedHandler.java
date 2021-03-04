package glue.pudding.security;

import com.alibaba.fastjson.JSONObject;
import glue.pudding.entity.ResponseBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by GUIXu on 2020/4/28.
 */
@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ResponseBean error = ResponseBean.error("权限不足，请联系管理员");
        out.write(JSONObject.toJSONString(error));
        out.flush(); out.close();
    }
}
