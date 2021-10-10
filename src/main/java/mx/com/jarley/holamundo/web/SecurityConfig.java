package mx.com.jarley.holamundo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // para q spring lo lea como clase de configuracion
@EnableWebSecurity // para q sepa las configguraciones de seguridad
public class SecurityConfig extends WebSecurityConfigurerAdapter { // extiende de esta para configurar usuarios
    
    //para agregar nuevos usuarios
//    @Override
//    protected  void configure (AuthenticationManagerBuilder AMB) throws Exception{
//    
//        AMB.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{noop}12345")
//                .roles("ADMIN", "USER")
//                .and()
//                .withUser("user")
//                .password("{noop}54321")
//                .roles("USER");
//    }
    
    
    // ahora usaremos jpa para cargar los usuarios esde la base de datos
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
    }
    
    // para utilizar userservice y el tipo de codificacion
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception{
    
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    
    // para restringir urls
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        
        http.authorizeRequests()
                .antMatchers("/editar/**","/agregar/**","/eliminar")
                .hasRole("ADMIN")
                .antMatchers("/")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/Errores/403");
        
                
    }
        
}
