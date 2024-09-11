//package app.aniMonster.api.config.security;
//
//import kr.moonsh.oauthjwt.jwt.JWTFilter;
//import kr.moonsh.oauthjwt.jwt.JWTUtil;
//import kr.moonsh.oauthjwt.oauth2.CustomSuccessHandler;
//import kr.moonsh.oauthjwt.service.CustomOAuth2UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@AllArgsConstructor
//public class SecurityConfig {
//
//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final CustomSuccessHandler customSuccessHandler;
//    private final JWTUtil jwtUtil;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                //csrf disable
//                .csrf((auth) -> auth.disable())
//                //From 로그인방식 disable
//                .formLogin((auth) -> auth.disable())
//                //HTTP Basic 인증 방식 disable
//                .httpBasic((auth) -> auth.disable())
//                //JWTFilter 추가
//                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
//                //OAuth2
//                .oauth2Login((oautn2) -> oautn2
//                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
//                                .userService(customOAuth2UserService)
//
//                        )
//                        .successHandler(customSuccessHandler)
//                )
//                //경로별 인가작업
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/", "/login").permitAll()
////                        .requestMatchers("/admin").hasRole("ADMIN")
////                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
//                        .anyRequest().authenticated()
//                )
//                //세션 설정: STATLESS
//                .sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                ;
//        return http.build();
//    }
//}
