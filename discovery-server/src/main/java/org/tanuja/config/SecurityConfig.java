package org.tanuja.config;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.util.List;

import static org.springframework.security.core.userdetails.User.withUsername;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    @Value("${eureka.username}")
    private  String username;

    @Value("${eureka.password}")
    private String password;


   // private final UserDetailsService userDetailsService;
    //@Override
    @Bean
    public SecurityFilterChain  configure(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer->Customizer.disable())
                .authorizeHttpRequests(request->request
                .anyRequest()
                .authenticated())
//                .and()
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();// Enable HTTP basic authentication
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user =
                User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .authorities("USER")
                .build();
        return new InMemoryUserDetailsManager(user); }

//    private PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }


}
