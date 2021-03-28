package com.kashbug.kashbugbackend.application

import com.kashbug.kashbugbackend.application.data.UserResponse
import com.kashbug.kashbugbackend.domain.project.service.BugService
import com.kashbug.kashbugbackend.domain.project.service.ProjectService
import com.kashbug.kashbugbackend.domain.user.service.MemberService
import com.kashbug.kashbugbackend.domain.user.service.RewardService
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import com.kashbug.kashbugbackend.toBasicString
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class UserApplicationService(
    private val memberService: MemberService,
    private val bugService: BugService,
    private val rewardService: RewardService,
    private val projectService: ProjectService
) {

    fun getMyInfo(userId: String) {

    }

    fun getBugsForMyPage(
        userId: String,
        pageRequest: PageRequest
    ): UserResponse.GetBugsForMyPage {
        val bugs = bugService.getByUserId(userId, pageRequest)
        if (bugs.isEmpty) return UserResponse.GetBugsForMyPage.ofEmpty()

        val projectIds = bugs.map { it.projectId }.toList()
        val projects = projectService.get(projectIds).map { it.id to it }.toMap()

        return UserResponse.GetBugsForMyPage(
            bugs.totalElements,
            bugs.map {
                val project = projects[it.projectId] ?: throw KashbugException(ResponseCode.BAD_REQUEST)
                UserResponse.GetBugsForMyPage.Bug(
                    it.id,
                    it.registerAt.toBasicString(),
                    project.reward,
                    it.type,
                    it.title,
                    "입금확인"
                )
            }.toList(),
            10
        )
    }

    /**
     * 마이페이지 용 보상 내역 리스트 조회
     *
     * 버그 아이디
     * 보상 일자
     * 보상 가격
     * 버그 유형
     * 버그 제목
     * 상태
     * */
    fun getRewardsForMyPage(
        userId: String,
        pageRequest: PageRequest
    ): UserResponse.GetRewardsForMyPage {
        val rewards = rewardService.getByUserId(userId, pageRequest)
        if (rewards.isEmpty) return UserResponse.GetRewardsForMyPage.ofEmpty()

        val totalRewardsPrice = rewards.sumBy { it.price }
        val bugIds = rewards.map { it.bugId }.toList()
        val bugs = bugService.get(bugIds, pageRequest).map { it.id to it }.toMap()

        return UserResponse.GetRewardsForMyPage(
            rewards.totalElements,
            rewards.map {
                val bug = bugs[it.bugId] ?: throw KashbugException(ResponseCode.BAD_REQUEST)
                UserResponse.GetRewardsForMyPage.Reward(
                    bug.id,
                    it.registerAt.toBasicString(),
                    it.price,
                    bug.type,
                    bug.title,
                    "입금확인"
                )
            }.toList(),
            totalRewardsPrice
        )
    }

    fun updateAccountNumber(
        userId: String,
        bankName: String,
        accountNumber: String
    ) {
        memberService.updateAccountNumber(userId, bankName, accountNumber)
    }

}