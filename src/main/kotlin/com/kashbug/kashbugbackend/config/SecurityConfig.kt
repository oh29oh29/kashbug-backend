package com.kashbug.kashbugbackend.config

import com.kashbug.kashbugbackend.config.jwt.JwtAuthenticationEntryPoint
import com.kashbug.kashbugbackend.config.jwt.JwtAuthenticationFilter
import com.kashbug.kashbugbackend.config.jwt.JwtTokenProvider
import com.kashbug.kashbugbackend.config.jwt.JwtUserDetailService
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtUserDetailService: JwtUserDetailService
) : WebSecurityConfigurerAdapter() {

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
            .headers().frameOptions().sameOrigin()
            .and()
            .httpBasic().disable()
            .csrf().disable()
            .formLogin().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증 (세션 사용 X)
            .and()
            .authorizeRequests()
            .antMatchers("/api/v1/join", "/api/v1/login", "/h2-console/**").permitAll()
            .antMatchers("/**").authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(JwtAuthenticationEntryPoint())
            .and()
            .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(jwtUserDetailService)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

}