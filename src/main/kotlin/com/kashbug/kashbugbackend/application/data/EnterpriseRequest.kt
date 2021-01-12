package com.kashbug.kashbugbackend.application.data

import com.kashbug.kashbugbackend.domain.interest.data.InterestCode
import com.kashbug.kashbugbackend.domain.project.data.StatusType

class EnterpriseRequest {

    data class RegisterProject(
        val name: String,
        val category: List<InterestCode>?,
        val contents: String,
        val reward: Int,
        val rewardDuration: Int,
        val url: String?,
        val status: StatusType,
        val startAt: String?,
        val deadlineAt: String
    )

    data class GetProjects(
        val ownerId: String
    )
}