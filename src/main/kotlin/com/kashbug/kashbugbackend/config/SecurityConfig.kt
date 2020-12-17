package com.kashbug.kashbugbackend.config

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        // static 자원에 대한 Security 설정을 적용하지 않음
        web
                .ignoring()
                .requestMatchers(
                        PathRequest
                                .toStaticResources()
                                .atCommonLocations()
                )

    }

    override fun configure(http: HttpSecurity) {
        http
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증 (세션 사용 X)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers().permitAll()   // 나머지 요청은 누구나 접근 가능
    }
}