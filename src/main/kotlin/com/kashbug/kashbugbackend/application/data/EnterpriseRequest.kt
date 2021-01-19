package com.kashbug.kashbugbackend.application.data

import com.kashbug.kashbugbackend.domain.common.value.InterestCode
import com.kashbug.kashbugbackend.domain.project.value.BugType
import com.kashbug.kashbugbackend.domain.project.value.StatusType

class EnterpriseRequest {

    data class RegisterProject(
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
    )

    data class GetProject(
        val projectId: String
    )

    class RegisterBug(
        val projectId: String,
        val title: String,
        val contents: String,
        val type: BugType,
        val imageUrl: List<String>?
    )
}