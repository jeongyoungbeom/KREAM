package com.project.kream.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 인증을 무시하기 위한 설정(정적 파일 static)
        web.ignoring().antMatchers( "/lib/**", "/api/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 토큰검사를 비활성화 (페이지 url)
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/", "/join", "/login/find_email", "/login/find_password"
                            , "/about", "/notice", "/notice_24"
                            , "/auth_polic", "/auth_polic/str", "/auth_polic/acc", "/auth_polic/ele", "/auth_polic/life"
                            , "/faq", "/faq/policy", "/faq/common", "/faq/buying", "/faq/selling"
                            ,"/search", "/product/**", "/api/**"
                            ,"/social/trending", "/social/tags/**", "/social/popular", "/social/following/empty"
                            , "/social/profile/**", "/social/following", "/pages/**"
                    ).permitAll()   // 모두 접근 가능
                    .antMatchers().hasRole("USER")   // USER, ADMIN 만 접근 가능
//                    .antMatchers(
//                            "/pages/postm_info/**","/pages/notice/postmange","/pages/content/manage"
//                            ,"/pages/inquire/product", "/pages/product_info/**", "/pages/product_edit/**", "/pages/inquire/customer"
//                            ,"/pages/customer_info/**", "/pages/product_edit/**", "/pages/order/search","/pages/search_info/**"
//                            ,"/pages/order/paycheck","/pages/order/delivery","/pages/order/bidcheck","/pages/order/sales"
//                            ,"/pages/sales_info/**","/pages/product/regist","/pages/product/check","/pages/product/detail/**",
//                            "/pages/product/edit/**","/pages","/pages/member/all","/pages/member/withdrawal"
//                            ,"/pages/member/leave","/pages/rank/**","/pages/info/**","/pages/black/**","/pages/black_info/**"
//                    ).hasRole("ADMIN") // ADMIN만 접근 가능
                    .anyRequest().authenticated()   // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .formLogin()
                    .loginPage("/login") // 로그인 페이지 링크
                    .loginProcessingUrl("/login_proc")  // 로그인 action url
                    .usernameParameter("email")
                    .passwordParameter("userpw")
                    .successHandler(new LoginSuccessHandler())
                    .failureHandler(new LoginfailureHandler())
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                    .permitAll()
                .invalidateHttpSession(true); // 세션 날리기
    }
}