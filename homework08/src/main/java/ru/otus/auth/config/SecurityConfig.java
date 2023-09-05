package ru.otus.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.otus.auth.AuthFilter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private @Value("${otus.auth.public}") String publicKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new AuthFilter(publicKey), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
