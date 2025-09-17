package com.example.CRMforDelivery.config;

import com.example.CRMforDelivery.entity.User;
import com.example.CRMforDelivery.entity.UserAuthority;
import com.example.CRMforDelivery.repository.UserAuthorityRepository;
import com.example.CRMforDelivery.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
public class UserDetailsServiceConfig {

    @Bean
    @Transactional(readOnly = true)
    public UserDetailsService userDetailsService(UserRepository userRepository,
                                                 UserAuthorityRepository authorityRepository) {
        return username -> {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("No such User"));
            List<UserAuthority> authorityList = authorityRepository.findByUserId(user.getId());

            return org.springframework.security.core.userdetails.User.builder()
                    .username(username)
                    .password(user.getPassword())
                    .authorities(
                            authorityList.stream()
                                    .map(e -> {
                                        return new SimpleGrantedAuthority(e.getAuthority());
                                    })
                                    .toList()
                    )
                    .build();
        };
    }
}
