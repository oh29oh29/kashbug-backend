package com.kashbug.kashbugbackend.application.data

import com.kashbug.kashbugbackend.domain.project.data.StatusType

class EnterpriseRequest {

    data class RegisterProject(
        val name: String,
        val ownerId: String,
        val category: String?,
        val contents: String,
        val reward: Int,
        val rewardDuration: Int,
        val url: String?,
        val status: StatusType,
        val deadline: String
    )

    data class GetProjects(
        val ownerId: String
    )
}