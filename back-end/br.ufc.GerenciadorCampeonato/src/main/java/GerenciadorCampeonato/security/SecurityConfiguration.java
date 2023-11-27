package GerenciadorCampeonato.security;

import GerenciadorCampeonato.Model.Usuario;
import GerenciadorCampeonato.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/usuario").permitAll()    
                .antMatchers(HttpMethod.GET, "/api/usuario/register").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuario/register").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuario/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/usuario/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/competicao").permitAll()
                .antMatchers(HttpMethod.POST, "/api/competicao/*").permitAll()
                
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().authenticationProvider(authProvider());
                    

        return http.build();
    }
    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Usuario user = usuarioRepository.findUserByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException(username));
                return user;
            }

        });
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
