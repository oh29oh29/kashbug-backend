package com.kashbug.kashbugbackend.domain.project

import com.kashbug.kashbugbackend.domain.project.data.StatusType
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProjectService(
    private val projectRepository: ProjectRepository
) {

    fun save(
        name: String,
        ownerId: String,
        contents: String,
        reward: Int,
        rewardDuration: Int,
        url: String?,
        status: StatusType,
        startAt: LocalDateTime?,
        deadlineAt: LocalDateTime
    ) {
        projectRepository.save(
            Project(
                name,
                ownerId,
                contents,
                reward,
                rewardDuration,
                url,
                status,
                startAt,
                deadlineAt
            )
        )
    }

    fun get(): List<Project> {
        return projectRepository.findAll()
    }

    fun get(
        ownerId: String?
    ): List<Project> {
        return listOf()
    }
}