package com.MindHub.HomeBanking.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization{
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/assets/pages/home.html","/assets/pages/login.html","/assets/pages/signup.html","/assets/style/**","/assets/script/**","/assets/images/**","/api/login","/api/logout","/assets/pages/more-info.html").permitAll()
                .antMatchers(HttpMethod.POST, "/api/clients").permitAll()
                .antMatchers("/manager.html", "/h2-console","/api/clients").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/clients/current/accounts").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.POST, "/api/clients/current/cards").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.POST, "/api/transactions").hasAuthority("CLIENT")
                .antMatchers("/assets/pages/accounts.html",
                                        "/assets/pages/account.html",
                                        "/assets/pages/cards.html",
                                        "/assets/pages/create-cards.html",
                                        "/api/clients/current").hasAuthority("CLIENT")
                        .anyRequest().denyAll();
        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");
        http.logout().logoutUrl("/api/logout");
        // turn off checking for CSRF tokens
        http.csrf().disable();
        //disabling frameOptions so h2-console can be accessed
        http.headers().frameOptions().disable();
        // if user is not authenticated, just send an authentication failure response
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
        // if login is successful, just clear the flags asking for authentication
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));
        // if login fails, just send an authentication failure response
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
        // if logout is successful, just send a success response
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        return http.build();
    }
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
