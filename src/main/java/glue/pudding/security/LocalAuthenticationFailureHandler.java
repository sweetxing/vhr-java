package glue.pudding.security;

import com.alibaba.fastjson.JSONObject;
import glue.pudding.entity.ResponseBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by GUIXu on 2020/4/29.
 */
public class LocalAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseBean responseBean = null;
        e.printStackTrace();
        if (e instanceof LockedException) {
            responseBean = ResponseBean.error("账户被锁定，登录失败！");
        } else if (e instanceof BadCredentialsException) {
            responseBean = ResponseBean.error("账户名或密码输入错误，登录失败！");
        } else if (e instanceof DisabledException) {
            responseBean = ResponseBean.error("账户已过期，登陆失败！");
        } else if (e instanceof CredentialsExpiredException) {
            responseBean = ResponseBean.error("密码已过期，登录失败！");
        } else {
            responseBean = ResponseBean.error("登陆失败");
        }
        responseBean.setStatus(401);
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(responseBean));
        out.flush(); out.close();
    }
}
