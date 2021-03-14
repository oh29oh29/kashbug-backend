package com.kashbug.kashbugbackend.domain.user.service

import com.kashbug.kashbugbackend.domain.user.entity.Reward
import com.kashbug.kashbugbackend.domain.user.repository.RewardRepository
import org.springframework.stereotype.Service

@Service
class RewardService(
    private val rewardRepository: RewardRepository
) {

    /**
     * 보상 리스트 조회 by 사용자 ID
     * */
    fun getByUserId(userId: String): List<Reward> {
        return rewardRepository.findByWriterId(userId)
    }
}