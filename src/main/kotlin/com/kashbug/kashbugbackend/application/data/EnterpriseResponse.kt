package com.kashbug.kashbugbackend.application.data

import com.kashbug.kashbugbackend.domain.common.value.InterestCode
import com.kashbug.kashbugbackend.domain.project.value.StatusType

class EnterpriseResponse {

    data class GetProjects(
        val totalCount: Int,
        val projects: List<Project>
    ) {
        data class Project(
            val id: String,
            val name: String,
            val category: List<InterestCode>,
            val bugCount: Int,
            val startAt: String?,
            val deadlineAt: String
        )
    }

    data class GetProject(
        val name: String,
        val category: List<InterestCode>?,
        val contents: String,
        val reward: Int,
        val rewardDuration: Int,
        val url: String?,
        val imageUrl: List<String>?,
        val status: StatusType,
        val startAt: String?,
        val deadlineAt: String
    ) {

        data class Bugs(
            val type: String,
            val contents: String,
            val registeredAt: String,
            val status: String
        )
    }

}