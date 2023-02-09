package SpringSecurityExample.config;

import SpringSecurityExample.auth.MemberDetails;
import SpringSecurityExample.auth.MemberDetailsService;
import SpringSecurityExample.session.ApiSessionExpiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@RequiredArgsConstructor // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션 (본 클래스의 18번째 줄의 클래스를 가져오기 위함)
@Configuration //빈등록 (IoC관리)
@EnableWebSecurity //security 필터 등록
public class WebSecurityConfig {//extends WebSecurityConfigurerAdapter {

    private final AuthenticationFailureHandler customFailureHandler;
    private final DataSource dataSource;

    private final MemberDetailsService memberDetailsService;

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Bean
    public PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }



    @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.rememberMe()
               .rememberMeParameter("remember-me")
               .tokenValiditySeconds(3600)
               .userDetailsService(memberDetailsService)
                .alwaysRemember(false)
               .tokenRepository(tokenRepository())

               .and()
               .csrf().disable()
               .authorizeRequests()
               .antMatchers("/user/**").authenticated()
               .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
               .anyRequest().permitAll()
               .and()


               .formLogin() // form 로그인 인증 기능이 작동함
               .loginPage("/login") // 사용자 정의 로그인 페이지
               .usernameParameter("userID") // 아이디 파라미터명 설정 default: username ,만약 username 이외에 다른 변수명을 사용할 의도라면 필히 여기를 바꿔줘야함
               .passwordParameter("password") // 패스워드 파라미터명 설정, default: password , 만약 password 이외에 다른 변수명을 사용할 의도라면 필히 여기를 바꿔줘야함
               .loginProcessingUrl("/loginProc")
               .failureHandler(customFailureHandler) // 로그인 실패 핸들러
               .defaultSuccessUrl("/successlogin")



               .and()
               .logout()
               .logoutUrl("/logout") //로그아웃 처리 url(=form action url)
               .addLogoutHandler((request, response, authentication) -> {
                   HttpSession session = request.getSession();
                   if (session  != null){
                       session.invalidate();
                   }
               }) //로그아웃 핸들러 추가
               .logoutSuccessHandler((request, response, authentication) -> {
                   response.sendRedirect("/");
               }) //로그아웃 성공 핸들러
               .deleteCookies("remember-me")



               .and()
               .sessionManagement()
               .maximumSessions(600)
               .expiredUrl("/expired")
               .sessionRegistry(sessionRegistry())
               .maxSessionsPreventsLogin(true);
        return http.build();

   }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 세션 만료 캐치
     * @param e
     * @return
     */
    @ExceptionHandler(ApiSessionExpiredException.class)
    public ModelAndView handleApiSessionExpiredException(ApiSessionExpiredException e) {
        return new ModelAndView("expired");
    }


}