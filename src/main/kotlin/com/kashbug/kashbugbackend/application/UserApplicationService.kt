package com.kashbug.kashbugbackend.application

import com.kashbug.kashbugbackend.domain.project.service.BugService
import com.kashbug.kashbugbackend.domain.user.service.MemberService
import com.kashbug.kashbugbackend.domain.user.service.RewardService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class UserApplicationService(
    private val memberService: MemberService,
    private val bugService: BugService,
    private val rewardService: RewardService
) {

    fun getMyInfo(
        userId: String
    ) {
        // TODO: 등록한 버그 개수, 등록된 버그 리스트 표시
        val totalBugs = bugService.getByUserId(
            userId,
            PageRequest.of(
                0,
                10,
                Sort.by("registerAt").descending()
            )
        )

        val rewards = rewardService.getByUserId(userId)
        val totalRewardsPrice = rewards.sumBy { it.price }


    }

    fun updateAccountNumber(
        userId: String,
        accountNumber: String
    ) {
        memberService.updateAccountNumber(userId, accountNumber)
    }

}