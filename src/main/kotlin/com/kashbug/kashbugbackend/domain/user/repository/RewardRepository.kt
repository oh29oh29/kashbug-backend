package com.kashbug.kashbugbackend.domain.user.repository

import com.kashbug.kashbugbackend.domain.user.entity.Reward
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository

interface RewardRepository : JpaRepository<Reward, String> {

    fun findByWriterId(writerId: String, pageRequest: PageRequest): Page<Reward>
}