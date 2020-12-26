package com.kashbug.kashbugbackend.config.jwt

import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")
        if (!authorizationHeader.isNullOrBlank() && authorizationHeader.startsWith("Bearer")) {
            val accessToken = authorizationHeader.substring(7)
            authenticate(accessToken)
        }

        filterChain.doFilter(request, response)
    }

    private fun authenticate(accessToken: String) {
        if (jwtTokenProvider.validate(accessToken)) {
            jwtTokenProvider.getAuthentication(accessToken).let {
                SecurityContextHolder.getContext().authentication = it
            }
        }
    }

}