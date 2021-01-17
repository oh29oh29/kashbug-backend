package com.kashbug.kashbugbackend.domain.interest

import com.kashbug.kashbugbackend.domain.interest.data.InterestCode
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

    fun get(
        targetId: String
    ): List<Interest> {
        return interestRepository.findByTargetId(targetId)
    }
}