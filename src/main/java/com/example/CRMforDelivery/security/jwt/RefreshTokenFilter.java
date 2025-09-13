package com.example.CRMforDelivery.security.jwt;
import com.example.CRMforDelivery.entity.dto.AccAndReferJwtTokenResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.function.Function;

@Setter
public class RefreshTokenFilter extends OncePerRequestFilter {
    private RequestMatcher requestMatcher =
           PathPatternRequestMatcher.pathPattern( HttpMethod.POST,"/jwt/refresh");

    private SecurityContextRepository securityContextRepository=
            new RequestAttributeSecurityContextRepository();;

    private Function<Token, Token> accessTokenFactory=new DefAccessTokenFactory();

    private Function<Token, String> accessTokenStringSerializer = Object::toString;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (this.requestMatcher.matches(request)) {
            if (this.securityContextRepository.containsContext(request)) {
                var context = this.securityContextRepository.loadDeferredContext(request).get();
                if (context != null && (context.getAuthentication()  instanceof PreAuthenticatedAuthenticationToken) &&
                    context.getAuthentication().getPrincipal() instanceof TokenUser user &&
                context.getAuthentication().getAuthorities()
                        .contains(new SimpleGrantedAuthority("JWT_REFRESH"))){

                    var accesToken=this.accessTokenFactory.apply(user.getToken());
                    response.setStatus(HttpStatus.OK.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    this.objectMapper.writeValue(response.getWriter(),
                            new AccAndReferJwtTokenResponseDto(this.accessTokenStringSerializer.apply(accesToken),
                                    accesToken.expiresAt().toString(),null,null));
                }
            }
                throw new AccessDeniedException("User must be authenticated with JWT");
        }
        filterChain.doFilter(request,response);

    }


}
