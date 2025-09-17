package com.example.CRMforDelivery.security.jwt;

import com.example.CRMforDelivery.repository.DeactivatedTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

public class TokenAuthUserDetailService
        implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final DeactivatedTokenRepository tokenRepository;

    public TokenAuthUserDetailService(DeactivatedTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken authenticationToken)
            throws UsernameNotFoundException {
        if (authenticationToken.getPrincipal() instanceof Token token) {
            return new TokenUser(
                    token.subject(),
                    "nopassword",
                    true,
                    true,
                    tokenRepository.findById(token.id()).isEmpty()
                            &&
                            token.expiresAt().isAfter(Instant.now()),
                    true,
                    token.authorities().stream().map(SimpleGrantedAuthority::new).toList(),
                    token);
        }
        throw new UsernameNotFoundException("Principal must be of type Token");
    }
}
