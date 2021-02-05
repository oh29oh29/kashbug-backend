package com.kashbug.kashbugbackend.presentation

import com.kashbug.kashbugbackend.application.UserApplicationService
import com.kashbug.kashbugbackend.application.data.UserRequest
import com.kashbug.kashbugbackend.presentation.data.OkResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userApplicationService: UserApplicationService
) {

    @PutMapping
    fun updateUser(
        userId: String,
        @Validated @RequestBody request: UserRequest.UpdateUser
    ): OkResponse<Void> {
        userApplicationService.update(userId, request)
        return OkResponse()
    }

}