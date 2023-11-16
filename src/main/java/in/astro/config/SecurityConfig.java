package in.astro.config;

import in.astro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig   {
    @Autowired
    private IUserService service;

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(encoder);
    }

    @Bean
    public SecurityFilterChain getFilter(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasRole("USER")
//                .antMatchers("/**").permitAll().and().formLogin().loginPage("/signin")
//                .loginProcessingUrl("/dologin")
//                .defaultSuccessUrl("/user/index")
                .anyRequest().authenticated().and().httpBasic()
                .and().csrf().disable();
//        http.formLogin().defaultSuccessUrl("/user/index",true);
        return http.build();
    }



}
