
package Grupo11.ProyectoEgg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //Todo esto son metodos y objeto de la clase websecurity
        http
                .authorizeRequests()
                     .antMatchers("/css/*", "/img/*", "/script/*").permitAll()
                     .antMatchers("/**").permitAll()
                .and()
                     .formLogin()
                     .loginPage("/login")
                        .permitAll()
//                     .and()
//                     .logout().permitall()
                .and()
                      .exceptionHandling().accessDeniedPage("/error-403")
                .and()
                    .csrf().disable();
    }
}
