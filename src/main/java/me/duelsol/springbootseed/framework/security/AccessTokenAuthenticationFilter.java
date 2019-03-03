package me.duelsol.springbootseed.framework.security;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 冯奕骅
 */
public class AccessTokenAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String accessToken = AccessTokenManager.get((HttpServletRequest) request);
        return AccessTokenManager.validate(accessToken);
    }

}
