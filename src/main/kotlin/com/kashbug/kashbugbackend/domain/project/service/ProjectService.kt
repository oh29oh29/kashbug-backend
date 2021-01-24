package com.kashbug.kashbugbackend.domain.project.service

import com.kashbug.kashbugbackend.domain.project.entity.Project
import com.kashbug.kashbugbackend.domain.project.repository.ProjectRepository
import com.kashbug.kashbugbackend.domain.project.value.StatusType
import com.kashbug.kashbugbackend.joinToStringWithRest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
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
        imageUrl: List<String>?,
        status: StatusType,
        startAt: LocalDateTime?,
        deadlineAt: LocalDateTime
    ): Project {
        return projectRepository.save(
            Project(
                name,
                ownerId,
                contents,
                reward,
                rewardDuration,
                url,
                imageUrl?.joinToStringWithRest(),
                status,
                startAt,
                deadlineAt
            )
        )
    }

    fun get(
        ownerId: String,
        pageable: Pageable
    ): Page<Project> {
        return projectRepository.findByOwnerId(ownerId, pageable)
    }

    fun get(
        projectId: String
    ): Project? {
        return projectRepository.findByIdOrNull(projectId)
    }
}