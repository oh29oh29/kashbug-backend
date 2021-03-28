package com.kashbug.kashbugbackend.presentation

import com.kashbug.kashbugbackend.application.UserApplicationService
import com.kashbug.kashbugbackend.application.data.UserResponse
import com.kashbug.kashbugbackend.presentation.data.OkResponse
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userApplicationService: UserApplicationService
) {

    /**
     * 계좌번호 등록가능 은행 조회
     * */
    @GetMapping("/account/bank")
    fun getBanks(): List<String> {
        return listOf(
            "국민은행"
        )
    }

    /**
     * 계좌번호 수정
     * */
    @PutMapping("/account/number")
    fun updateAccountNumber(
        userId: String,
        bankName: String,       // TODO: enum
        accountNumber: String
    ): OkResponse<Void> {
        userApplicationService.updateAccountInfo(userId, bankName, accountNumber)
        return OkResponse()
    }

    /**
     * 마이페이지 용 버그 리스트 조회
     *
     * 등록 일자
     * 보상 가격
     * 버그 유형
     * 버그 내용
     * 상태
     * */
    @GetMapping("/my/bugs")
    fun getBugsForMyPage(
        userId: String,
        @RequestParam(required = false, defaultValue = "0") page: String,
        @RequestParam(required = false, defaultValue = "10") size: String,
        @RequestParam(required = false, defaultValue = "registerAt") sortBy: String
    ): OkResponse<UserResponse.GetBugsForMyPage> {
        return OkResponse(
            userApplicationService.getBugsForMyPage(
                userId,
                PageRequest.of(
                    page.toInt(),
                    size.toInt(),
                    Sort.by(sortBy).descending()
                )
            )
        )
    }

    /**
     * 마이페이지 용 보상 내역 리스트 조회
     *
     * 보상 일자
     * 보상 가격
     * 버그 유형
     * 버그 내용
     * 상태
     * */
    @GetMapping("/my/rewards")
    fun getRewardsForMyPage(
        userId: String,
        @RequestParam(required = false, defaultValue = "0") page: String,
        @RequestParam(required = false, defaultValue = "10") size: String,
        @RequestParam(required = false, defaultValue = "registerAt") sortBy: String
    ): OkResponse<UserResponse.GetRewardsForMyPage> {
        return OkResponse(
            userApplicationService.getRewardsForMyPage(
                userId,
                PageRequest.of(
                    page.toInt(),
                    size.toInt(),
                    Sort.by(sortBy).descending()
                )
            )
        )
    }

    @GetMapping("/my/account")
    fun getAccountInfo(userId: String): OkResponse<UserResponse.GetAccountInfo> {
        return OkResponse(
            userApplicationService.getAccountInfo(userId)
        )
    }
}