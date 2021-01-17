package com.kashbug.kashbugbackend.domain.project

import com.kashbug.kashbugbackend.domain.project.data.StatusType
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
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
    ) {
        projectRepository.save(
            Project(
                name,
                ownerId,
                contents,
                reward,
                rewardDuration,
                url,
                toImageUrl(imageUrl),
                status,
                startAt,
                deadlineAt
            )
        )
    }

    private fun toImageUrl(imageUrl: List<String>?) = imageUrl?.joinToString { "," }

    fun get(): List<Project> {
        return projectRepository.findAll()
    }

    fun get(
        ownerId: String,
        projectId: String
    ): Project {
        val project = projectRepository.findByIdOrNull(projectId) ?: throw KashbugException(ResponseCode.BAD_REQUEST)
        if (project.ownerId != ownerId) throw KashbugException(ResponseCode.NOT_ALLOWED_USER)

        return project
    }
}