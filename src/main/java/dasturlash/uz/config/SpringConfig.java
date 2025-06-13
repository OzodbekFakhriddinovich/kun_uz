package dasturlash.uz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@Configuration
public class SpringConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "securityFilterChain1")
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/v1/**") // 🔹 faqat /v1 bilan boshlanadigan URL lar uchun
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/v1/auth/**", "/v1/category/lang", "/v1/region/lang", "/v1/section/lang", "/v1/profile/update").permitAll()
                        .requestMatchers("/v1/article/status").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers(HttpMethod.DELETE, "/v1/article/*").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers(HttpMethod.PATCH, "/v1/article/*").hasRole("PUBLISH")
                        .requestMatchers(HttpMethod.POST, "/v1/article").hasRole("PUBLISH")
                        .requestMatchers("/v1/category/**", "/v1/region/**", "/v1/section/**", "/v1/profile/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        return http.build();
    }
}

