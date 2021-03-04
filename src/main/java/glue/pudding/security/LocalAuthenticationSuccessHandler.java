package glue.pudding.security;

import com.alibaba.fastjson.JSONObject;
import glue.pudding.util.HrUtil;
import glue.pudding.entity.ResponseBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by GUIXu on 2020/4/29.
 */
public class LocalAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseBean responseBean = ResponseBean.ok("登陆成功", HrUtil.getCurrentHr());
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(responseBean));
        out.flush(); out.close();
    }
}
