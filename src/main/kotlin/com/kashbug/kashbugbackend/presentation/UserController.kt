package com.kashbug.kashbugbackend.presentation

import com.kashbug.kashbugbackend.application.UserApplicationService
import com.kashbug.kashbugbackend.presentation.data.OkResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userApplicationService: UserApplicationService
) {

    @GetMapping("/individual")
    fun getMyInfo(userId: String) {

    }

    /**
     * 계좌번호 수정
     * */
    @PutMapping("/account/number")
    fun updateAccountNumber(
        userId: String,
        accountNumber: String
    ): OkResponse<Void> {
        userApplicationService.updateAccountNumber(userId, accountNumber)
        return OkResponse()
    }
}