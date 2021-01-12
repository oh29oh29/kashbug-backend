package com.kashbug.kashbugbackend.application

import com.kashbug.kashbugbackend.application.data.EnterpriseRequest
import com.kashbug.kashbugbackend.domain.enterprise.EnterpriseService
import com.kashbug.kashbugbackend.domain.interest.InterestService
import com.kashbug.kashbugbackend.domain.project.ProjectService
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import com.kashbug.kashbugbackend.toLocalDateTime
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EnterpriseApplicationService(
    private val enterpriseService: EnterpriseService,
    private val projectService: ProjectService,
    private val interestService: InterestService
) {

    @Transactional
    fun registerProject(userId: String, request: EnterpriseRequest.RegisterProject) {

        if (!enterpriseService.existId(userId)) throw KashbugException(ResponseCode.NOT_ALLOWED_USER)

        projectService.save(
            request.name,
            userId,
            request.contents,
            request.reward,
            request.rewardDuration,
            request.url,
            request.status,
            request.startAt?.toLocalDateTime(),
            request.deadlineAt.toLocalDateTime()
        )

        request.category?.let {
            interestService.save(
                userId,
                it
            )
        }
    }

    fun getProjects(request: EnterpriseRequest.GetProjects) {
        projectService.get(request.ownerId)
    }
}