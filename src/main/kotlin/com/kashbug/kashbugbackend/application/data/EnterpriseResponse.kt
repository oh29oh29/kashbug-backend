package com.kashbug.kashbugbackend.application.data

import com.kashbug.kashbugbackend.domain.common.value.InterestCode
import com.kashbug.kashbugbackend.domain.project.value.BugType
import com.kashbug.kashbugbackend.domain.project.value.StatusType

class EnterpriseResponse {

    data class GetProjects(
        val totalCount: Long,
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
        val id: String,
        val name: String,
        val category: List<InterestCode>?,
        val contents: String,
        val reward: Int,
        val rewardDuration: Int,
        val url: String?,
        val imageUrl: List<String>?,
        val status: StatusType,
        val startAt: String?,
        val deadlineAt: String,
        val isOwn: Boolean
    )

    data class GetBugs(
        val totalCount: Long,
        val bugs: List<Bug>
    ) {
        data class Bug(
            val id: String,
            val writer: String,
            val title: String,
            val type: BugType,
            val registerAt: String
        )
    }

    data class GetBug(
        val id: String,
        val writer: String,
        val title: String,
        val contents: String,
        val type: BugType,
        val imageUrl: List<String>?,
        val registerAt: String,
        val isOwn: Boolean
    )

}