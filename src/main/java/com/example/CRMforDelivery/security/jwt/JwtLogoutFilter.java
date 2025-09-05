package com.example.CRMforDelivery.security.jwt;

import com.example.CRMforDelivery.entity.DeactivatedToken;
import com.example.CRMforDelivery.repository.DeactivatedTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtLogoutFilter extends OncePerRequestFilter {


    private RequestMatcher requestMatcher =
            PathPatternRequestMatcher.pathPattern(HttpMethod.POST, "/jwt/logout");

    private SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();

    private final DeactivatedTokenRepository tokenRepository;

    public JwtLogoutFilter(DeactivatedTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (this.requestMatcher.matches(request)) {
            if (this.securityContextRepository.containsContext(request)) {
                var context = this.securityContextRepository.loadDeferredContext(request).get();
                if (context != null && context.getAuthentication() instanceof PreAuthenticatedAuthenticationToken &&
                        context.getAuthentication().getPrincipal() instanceof TokenUser user &&
                        context.getAuthentication().getAuthorities()
                                .contains(new SimpleGrantedAuthority("JWT_LOGOUT"))) {
                    tokenRepository.save(new DeactivatedToken(
                            user.getToken().id(),
                            user.getToken().expiresAt()
                    ));
//                    this.jdbcTemplate.update("insert into t_deactivated_token (id, c_keep_until) values (?, ?)",
//                            user.getToken().id(), Date.from(user.getToken().expiresAt()));
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    return;
                }
            }

            throw new AccessDeniedException("User must be authenticated with JWT");
        }

        filterChain.doFilter(request, response);
    }


    public void setRequestMatcher(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }

    public void setSecurityContextRepository(SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
    }
}