package com.kashbug.kashbugbackend.domain.project.service

import com.kashbug.kashbugbackend.domain.project.entity.Project
import com.kashbug.kashbugbackend.domain.project.repository.ProjectRepository
import com.kashbug.kashbugbackend.domain.project.value.StatusType
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.joinToStringWithRest
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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

    @Transactional
    fun update(
        projectId: String,
        contents: String
    ) {
        val project = projectRepository.findByIdOrNull(projectId)?.apply {
            // TODO: 수정 필드들의 형태는?

        } ?: throw KashbugException(ResponseCode.BAD_REQUEST)
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