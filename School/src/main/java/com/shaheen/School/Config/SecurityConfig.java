package com.shaheen.School.Config;

import com.shaheen.School.Service.User.Impl.UserSecurityService;
import com.shaheen.School.Utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author lts
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserSecurityService userSecurityService;

    private BCryptPasswordEncoder passwordEncoder() {
        return SecurityUtility.passwordEncoder();
    }

    private static final String[] PUBLIC_MATCHERS = {
        "/css/**",
        "/js/**",
        "/image/**",
        "/fonts/**",
        "/Reports/**",
        "/",
        "/NotFoundPage",
        "/login",
        "/newUser",
        "/forgetPassword",
        "/confirm",
        "/Class",
        "/error"

    };


    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http .csrf().disable(); 
            http.antMatcher("/api/**").authorizeRequests().anyRequest().
                    hasAnyRole("ADMIN").and().httpBasic();
            
//        http.cors().and().csrf().disable()
//            .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//            .authorizeRequests()
//                .antMatchers("/api/").permitAll()
//                .anyRequest().authenticated()
//            .and().httpBasic();
                
        }
    }

    @Configuration
    @Order(2)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests().
                    /*	antMatchers("/**").*/
                    antMatchers(PUBLIC_MATCHERS).
                    permitAll().anyRequest().authenticated();

//        http.authorizeRequests()
//                //                .antMatchers("/").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
//                .antMatchers("/Admin/**").access("hasRole('ROLE_ADMIN')")
//                .and()
//                .formLogin()
//                .loginPage("/user/login")
//                //                .loginPage("/Admin/login")
//                .defaultSuccessUrl("/default")
//                .failureUrl("/loginPage?error")
////                .usernameParameter("username").passwordParameter("password")
//                .and()
//                .logout().logoutSuccessUrl("/login?logout");
            http
                    .authorizeRequests()
                    .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                    .antMatchers("/Admin/**").access("hasRole('ROLE_ADMIN')");

            http
                    .csrf().disable().cors().disable()
                    .formLogin().failureUrl("/login?error")
                    .defaultSuccessUrl("/default")
                    .loginPage("/login").permitAll()
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
                    .and()
                    .rememberMe();

        }
//          @Override
//            protected void configure(HttpSecurity http) throws Exception {
//                http.authorizeRequests().anyRequest().authenticated().and().formLogin();
//            }

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }
}
