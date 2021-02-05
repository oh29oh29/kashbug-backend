package com.kashbug.kashbugbackend.domain.common.repository

import com.kashbug.kashbugbackend.domain.common.entity.Interest
import org.springframework.data.jpa.repository.JpaRepository

interface InterestRepository : JpaRepository<Interest, Long> {

    fun findByTargetId(targetId: String): List<Interest>

    fun deleteByTargetId(targetId: String): Void
}