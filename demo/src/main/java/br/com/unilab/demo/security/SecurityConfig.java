package br.com.unilab.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private static final String[] PUBLIC_RESOURCES = {
                        "/css/**", "/js/**", "/images/**", "/webjars/**", "/h2-console/**"
        };

        private final UserDetailsService userDetailsService;
        private final CustomAuthenticationFailureHandler failureHandler;
        private final CustomAuthenticationSuccessHandler sucessHandler;

        public SecurityConfig(UserDetailsService userDetailsService,
                        CustomAuthenticationFailureHandler failureHandler, CustomAuthenticationSuccessHandler sucessHandler) {
                this.userDetailsService = userDetailsService;
                this.failureHandler = failureHandler;
                this.sucessHandler = sucessHandler;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder(10);
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                                .authorizeHttpRequests(auth -> auth
                                                // Recursos públicos
                                                .requestMatchers(PUBLIC_RESOURCES).permitAll()
                                                .requestMatchers("/", "/main", "/main/sobre", "/main/tutoriais",
                                                                "/main/suporte", "/login-admin", "/login-professor",
                                                                "/login")
                                                .permitAll()

                                                // Endpoints ADMIN
                                                .requestMatchers(
                                                                "/main/seja-bem-vindo-adm",
                                                                "/main/cadastrar-professor",
                                                                "/main/cadastrar-laboratorio",
                                                                "/main/atualizar-professor",
                                                                "/main/atualizar-laboratorio",
                                                                "/main/gerenciar-laboratorio",
                                                                "/main/gerenciar-professor",
                                                                "/main/historico",
                                                                "/cadastrar-laboratorio", "/deletar-laboratorio/**",
                                                                "/atualizar-laboratorio/**",
                                                                "/deletar-agendamento/**", "/atualizar-agendamento/**")
                                                .hasRole("ADMIN")

                                                // Endpoints PROFESSOR
                                                .requestMatchers(
                                                                "/novo-agendamento", "/main/seja-bem-vindo-professor",
                                                                "/main/meus-agendamentos", "/main/agendar-laboratorio",
                                                                "/solicitar-agendamento")
                                                .hasRole("PROFESSOR")

                                                // Todas as outras requisições precisam de autenticação
                                                .anyRequest().authenticated())
                                // Configuração de login
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .loginProcessingUrl("/login")
                                                .usernameParameter("email")
                                                .successHandler(sucessHandler)
                                                .failureHandler(failureHandler)
                                                .permitAll())

                                // Configuração de logout
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login")
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID")
                                                .permitAll())
                                .authenticationProvider(authenticationProvider());

                return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
                return authConfig.getAuthenticationManager();
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setUserDetailsService(userDetailsService);
                provider.setPasswordEncoder(passwordEncoder());
                return provider;
        }
}
