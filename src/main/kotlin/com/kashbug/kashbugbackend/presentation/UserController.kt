package com.kashbug.kashbugbackend.presentation

import com.kashbug.kashbugbackend.application.UserApplicationService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userApplicationService: UserApplicationService
) {


}