package com.kashbug.kashbugbackend.application.data

import com.kashbug.kashbugbackend.domain.project.value.BugType

class UserResponse {

    data class GetBugsForMyPage(
        val totalCount: Long,
        val bugs: List<Bug>,
        val totalAdoptedBugCount: Int
    ) {
        data class Bug(
            val id: String,
            val registerAt: String,
            val rewardPrice: Int,
            val type: BugType,
            val title: String,
            val status: String
        )

        companion object {
            fun ofEmpty() =
                GetBugsForMyPage(
                    0,
                    emptyList(),
                    0
                )
        }
    }

    data class GetRewardsForMyPage(
        val totalCount: Long,
        val rewards: List<Reward>,
        val totalRewardsPrice: Int
    ) {
        data class Reward(
            val bugId: String,
            val rewardAt: String,
            val price: Int,
            val bugType: BugType,
            val bugTitle: String,
            val status: String
        )

        companion object {
            fun ofEmpty() =
                GetRewardsForMyPage(
                    0,
                    emptyList(),
                    0
                )
        }
    }
}