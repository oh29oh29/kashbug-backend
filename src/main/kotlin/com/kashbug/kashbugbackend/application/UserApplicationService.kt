package com.kashbug.kashbugbackend.application

import com.kashbug.kashbugbackend.application.data.UserRequest
import com.kashbug.kashbugbackend.domain.user.service.MemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserApplicationService(
    private val memberService: MemberService
) {

    @Transactional
    fun update(
        userId: String,
        request: UserRequest.UpdateUser
    ) {
        memberService.update(
            userId,
            request.password
        )
    }

}