package com.kashbug.kashbugbackend.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val userIdArgumentResolver: UserIdArgumentResolver
): WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userIdArgumentResolver)
    }

    @Component
    class UserIdArgumentResolver : HandlerMethodArgumentResolver {

        override fun supportsParameter(parameter: MethodParameter): Boolean {
            return parameter.parameterType == String::class.java &&
                parameter.parameterName == "userId"
        }

        override fun resolveArgument(
            parameter: MethodParameter,
            mavContainer: ModelAndViewContainer?,
            webRequest: NativeWebRequest,
            binderFactory: WebDataBinderFactory?
        ): String {
            val authentication = SecurityContextHolder.getContext().authentication
            return authentication.name
        }
    }
}