package com.kashbug.kashbugbackend.presentation

import com.kashbug.kashbugbackend.application.LoginApplicationService
import com.kashbug.kashbugbackend.application.data.LoginRequest
import com.kashbug.kashbugbackend.application.data.LoginResponse
import com.kashbug.kashbugbackend.presentation.data.OkResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class LoginController(
    private val loginApplicationService: LoginApplicationService
) {

    @PostMapping("/join")
    fun join(@Validated @RequestBody request: LoginRequest.Join): OkResponse<Void> {
        loginApplicationService.join(request)
        return OkResponse()
    }

    @PostMapping("/login")
    fun login(@Validated @RequestBody request: LoginRequest.Login): OkResponse<LoginResponse.Login> {
        return OkResponse(loginApplicationService.login(request))
    }

}