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
        val interest = interests.map {
            val id = InterestId(targetId, it)
            Interest(id)
        }.toList()

        interestRepository.saveAll(interest)
    }
}