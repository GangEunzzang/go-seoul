package seoul.go.ggo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import seoul.go.ggo.security.handler.GgoLoginSuccessHandler;


@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // BCryptPasswordEncoder사용

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // URL별 접근권한 설정. 로그인폼설정. csrf토큰 비활성화. 로그아웃폼설정
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //이거 대신에 @PreAuthorize 사용추천. 이걸사용하면 모든주소마다 다등록해야함.
//        http.authorizeRequests()
//                .antMatchers("/security/all").permitAll()
//                .antMatchers("/security/member").hasRole("USER");

        http.formLogin();
        http.formLogin().loginPage("/security/loginCustom").loginProcessingUrl("/login")
                .successHandler(successHandler());
       http.csrf().disable();

       http.oauth2Login().successHandler(successHandler());

        http.logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/index")  //로그아웃 성공시 index페이지로 이동
                .invalidateHttpSession(true);


    }
    @Bean
    public GgoLoginSuccessHandler successHandler() {
        return new GgoLoginSuccessHandler(passwordEncoder());
    }


}
