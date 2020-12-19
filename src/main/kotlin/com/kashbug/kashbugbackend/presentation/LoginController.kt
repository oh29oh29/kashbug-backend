package com.kashbug.kashbugbackend.presentation

import com.kashbug.kashbugbackend.presentation.data.OkResponse
import com.kashbug.kashbugbackend.service.LoginService
import com.kashbug.kashbugbackend.service.data.LoginRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class LoginController(
    private val loginService: LoginService
) {

    @PostMapping("/join")
    fun join(@Validated @RequestBody request: LoginRequest.Join): OkResponse<Void> {
        loginService.join(request)
        return OkResponse()
    }
}