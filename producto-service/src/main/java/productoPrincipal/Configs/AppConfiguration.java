package productoPrincipal.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import productoPrincipal.Filters.JwtRequestFilter;

@Configuration
public class AppConfiguration {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Me falta la dependencia de seguridad
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/producto/**").authenticated()
                        .anyRequest().permitAll())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {
                })
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
