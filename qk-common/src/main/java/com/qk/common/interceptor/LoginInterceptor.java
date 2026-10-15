package com.qk.common.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.qk.common.util.QKJwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MrZhao
 *
 *  登录的拦截器：校验请求头 Token，未登录返回 401
 *
 */
@SuppressWarnings("all")
public class LoginInterceptor implements HandlerInterceptor {

    // 路径匹配器 支持通配符
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 请求到达 Controller 之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 1. 放行登录请求
        String requestPath = request.getRequestURI();
        if (pathMatcher.match("/login", requestPath)) {
            return true;
        }

        // 2. 是否携带 token
        String token = request.getHeader("Token");
        // 3. 未携带 token 拒绝
        if (StrUtil.isBlank(token)) {
            return reject(response);
        }

        // 4. 校验 token
        boolean verified = QKJwts.verifyJWTToken(token);
        if (!verified) {
            return reject(response);
        }

        // 5. 校验通过，可将用户信息放入请求域供后续使用
        JSONObject userMap = QKJwts.parseJWTToken(token);
        Object id = userMap.get("id");
        request.setAttribute("userId", id);
        return true;
    }

    private boolean reject(HttpServletResponse response) throws Exception {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":0,\"msg\":\"未登录或登录已过期\"}");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
    }
}
