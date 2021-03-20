package com.afair.auth.filter;

import com.afair.auth.commons.constants.SecurityConstants;
import com.afair.auth.commons.utils.JwtTokenUtils;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author WangBing
 * @date 2021/3/20
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token=request.getHeader(SecurityConstants.TOKEN_HEADER);
        if(token==null||!token.startsWith(SecurityConstants.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }
        String tokenValue = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        UsernamePasswordAuthenticationToken authentication=null;
        try {
            String userId = JwtTokenUtils.getId(tokenValue);
            authentication = JwtTokenUtils.getAuthentication(tokenValue);
        }catch (JwtException e){
            log.info("invalid jwt: {}",e.getMessage());
        }

        super.doFilterInternal(request, response, chain);
    }
}
