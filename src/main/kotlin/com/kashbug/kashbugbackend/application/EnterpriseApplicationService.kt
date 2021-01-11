package com.kashbug.kashbugbackend.application

import com.kashbug.kashbugbackend.application.data.EnterpriseRequest
import com.kashbug.kashbugbackend.domain.project.ProjectService
import com.kashbug.kashbugbackend.toLocalDateTime
import org.springframework.stereotype.Service

@Service
class EnterpriseApplicationService(
    private val projectService: ProjectService
) {

    fun registerProject(request: EnterpriseRequest.RegisterProject) {
        projectService.save(
            request.name,
            request.ownerId,
            request.contents,
            request.reward,
            request.rewardDuration,
            request.url,
            request.status,
            request.deadline.toLocalDateTime()
        )
    }

    fun getProjects(request: EnterpriseRequest.GetProjects) {
        projectService.get(request.ownerId)
    }
}