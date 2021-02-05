package com.kashbug.kashbugbackend.domain.common.service

import com.kashbug.kashbugbackend.domain.common.entity.Interest
import com.kashbug.kashbugbackend.domain.common.repository.InterestRepository
import com.kashbug.kashbugbackend.domain.common.value.InterestCode
import org.springframework.stereotype.Service

@Service
class InterestService(
    private val interestRepository: InterestRepository
) {

    fun save(
        targetId: String,
        interests: List<InterestCode>
    ) {
        interestRepository.saveAll(
            interests.map {
                Interest(targetId, it)
            }.toList()
        )
    }

    fun deleteAll(targetId: String) {
        interestRepository.deleteByTargetId(targetId)
    }

    fun get(
        targetId: String
    ): List<Interest> {
        return interestRepository.findByTargetId(targetId)
    }
}