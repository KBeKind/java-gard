package com.example.javagarden.security;
//
//
//import com.example.javagarden.data.RoleRepository;
//import com.example.javagarden.data.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter  {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll();
//    }
//
//}