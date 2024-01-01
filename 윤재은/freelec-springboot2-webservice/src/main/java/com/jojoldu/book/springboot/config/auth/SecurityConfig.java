package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //springSecurity 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2 console 화면을 사용하기 위해 해당 옵션을 disable한다.
                .and()
                .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점.
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() //antMatchers: 권한 관리 대상을 지정하는 옵션. permitAll: 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // USER 권한을 가진 사람에게만 권한 부여.
                .anyRequest().authenticated() //anyRequest: 설정 이외의 나머지 URL. authenticated:인증된 사용자들에게 허용.
                .and()
                .logout()
                .logoutSuccessUrl("/") //로그아웃 기능에 대한 여러 설정의 진입점. 로그아웃 성공 시 "/" 주소로 이동
                .and()
                .oauth2Login() //Oauth2 로그인 기능에 대한 여러 설정의 진입점.
                .userInfoEndpoint() //Oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 진행할 UserService인터페이스의 구현체 등록.
    }


}
