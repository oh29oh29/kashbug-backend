package com.kashbug.kashbugbackend.domain.user.repository

import com.kashbug.kashbugbackend.domain.user.entity.Reward
import org.springframework.data.jpa.repository.JpaRepository

interface RewardRepository : JpaRepository<Reward, String> {

    fun findByWriterId(writerId: String): List<Reward>
}