package com.kashbug.kashbugbackend.config.jwt

import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    private val jwtUserDetailService: JwtUserDetailService
) {
    private val log = LoggerFactory.getLogger(JwtTokenProvider::class.java)

    companion object {
        const val SECRET_KEY: String = "kashbug"
        const val TOKEN_EXPIRATION: Long = 15 * 60_000L
    }

    fun issue(id: String): String {
        val claims = Jwts.claims().setSubject(id)
        val now = Date()
        return Jwts
            .builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + TOKEN_EXPIRATION))
            .signWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
            .compact()
    }

    fun validate(accessToken: String?): Boolean {
        if (accessToken.isNullOrBlank()) return false

        try {
            Jwts
                .parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
                .build()
                .parse(accessToken)
        } catch (e: ExpiredJwtException) {
            throw KashbugException(ResponseCode.EXPIRED_JWT_TOKEN)
        } catch (e: MalformedJwtException) {
            throw KashbugException(ResponseCode.INVALID_JWT_TOKEN)
        } catch (e: SignatureException) {
            throw KashbugException(ResponseCode.INVALID_JWT_SIGNATURE)
        } catch (e: IllegalArgumentException) {
            throw KashbugException(ResponseCode.NOT_NULL_JWT_CLAIM)
        }

        return true
    }

    fun getAuthentication(accessToken: String): UsernamePasswordAuthenticationToken {
        val userId = extractUserId(accessToken)
        val user = jwtUserDetailService.loadUserByUsername(userId)

        return UsernamePasswordAuthenticationToken(user, user.password, user.authorities)
    }

    private fun extractUserId(accessToken: String): String {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build()
            .parseClaimsJws(accessToken)
            .body
            .subject
    }


}