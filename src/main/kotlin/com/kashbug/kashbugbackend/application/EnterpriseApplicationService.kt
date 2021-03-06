package com.kashbug.kashbugbackend.application

import com.kashbug.kashbugbackend.application.data.EnterpriseRequest
import com.kashbug.kashbugbackend.application.data.EnterpriseResponse
import com.kashbug.kashbugbackend.domain.common.service.InterestService
import com.kashbug.kashbugbackend.domain.project.service.BugService
import com.kashbug.kashbugbackend.domain.project.service.ProjectService
import com.kashbug.kashbugbackend.domain.user.service.EnterpriseService
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import com.kashbug.kashbugbackend.toBasicString
import com.kashbug.kashbugbackend.toLocalDateTime
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EnterpriseApplicationService(
    private val enterpriseService: EnterpriseService,
    private val projectService: ProjectService,
    private val interestService: InterestService,
    private val bugService: BugService
) {

    @Transactional
    fun registerProject(
        userId: String,
        request: EnterpriseRequest.RegisterProject
    ) {
        if (!enterpriseService.existId(userId)) throw KashbugException(ResponseCode.NOT_ALLOWED_USER)

        val project = projectService.save(
            request.name,
            userId,
            request.contents,
            request.reward,
            request.rewardDuration,
            request.url,
            request.imageUrl,
            request.status,
            request.startAt?.toLocalDateTime(),
            request.deadlineAt.toLocalDateTime()
        )

        request.category?.let {
            interestService.save(
                project.id,
                it
            )
        }
    }

    @Transactional
    fun updateProject(
        userId: String,
        projectId: String,
        request: EnterpriseRequest.UpdateProject
    ) {
        projectService.update(
            projectId,
            request.name,
            request.contents,
            request.reward,
            request.rewardDuration,
            request.url,
            request.imageUrl,
            request.status,
            request.startAt?.toLocalDateTime(),
            request.deadlineAt.toLocalDateTime()
        )

        interestService.deleteAll(projectId)
        request.category?.let {
            interestService.save(
                projectId,
                it
            )
        }
    }

    @Transactional(readOnly = true)
    fun getProjects(
        ownerId: String,
        pageable: Pageable
    ): EnterpriseResponse.GetProjects {
        val projects = projectService.get(ownerId, pageable)

        return EnterpriseResponse.GetProjects(
            projects.totalElements,
            projects.map { project ->
                val interests = interestService.get(project.id).map { interest -> interest.code }
                val bugCount = bugService.count(project.id)

                EnterpriseResponse.GetProjects.Project(
                    project.id,
                    project.name,
                    interests,
                    bugCount,
                    project.startAt?.toBasicString(),
                    project.deadlineAt.toBasicString(),
                )
            }.toList()
        )
    }

    @Transactional(readOnly = true)
    fun getProject(
        userId: String,
        projectId: String
    ): EnterpriseResponse.GetProject {
        val project = projectService.get(projectId) ?: throw KashbugException(ResponseCode.BAD_REQUEST)
        val interests = interestService.get(projectId).map { it.code }
        val isOwn = project.ownerId == userId

        return EnterpriseResponse.GetProject(
            project.id,
            project.name,
            interests,
            project.contents,
            project.reward,
            project.rewardDuration,
            project.url,
            project.imageUrl?.split(","),
            project.status,
            project.startAt?.toBasicString(),
            project.deadlineAt.toBasicString(),
            isOwn
        )
    }

    @Transactional
    fun registerBug(
        userId: String,
        projectId: String,
        request: EnterpriseRequest.RegisterBug
    ) {
        bugService.save(
            projectId,
            userId,
            request.type,
            request.title,
            request.contents,
            request.imageUrl
        )
    }

    fun getBugs(
        projectId: String,
        pageable: Pageable
    ): EnterpriseResponse.GetBugs {
        val bugs = bugService.get(projectId, pageable)

        // TODO: 상태 필드 추가
        return EnterpriseResponse.GetBugs(
            bugs.totalElements,
            bugs.map {
                EnterpriseResponse.GetBugs.Bug(
                    it.id,
                    it.writerId,
                    it.title,
                    it.type,
                    it.registerAt.toBasicString()
                )
            }.toList()
        )
    }

    fun getBug(
        userId: String,
        bugId: String
    ): EnterpriseResponse.GetBug {
        val bug = bugService.get(bugId) ?: throw KashbugException(ResponseCode.BAD_REQUEST)
        val isOwn = bug.writerId == userId

        return EnterpriseResponse.GetBug(
            bug.id,
            bug.writerId,
            bug.title,
            bug.contents,
            bug.type,
            bug.imageUrl?.split(","),
            bug.registerAt.toBasicString(),
            isOwn
        )
    }

    fun updateBug(
        userId: String,
        bugId: String,
        request: EnterpriseRequest.UpdateBug
    ) {
        bugService.update(
            userId,
            bugId,
            request.type,
            request.title,
            request.contents,
            request.imageUrl
        )
    }

    @Transactional(readOnly = true)
    fun getAdoptedBugsByEnterprise(
        userId: String,
        pageRequest: PageRequest
    ): EnterpriseResponse.GetAdoptedBugs {
        val projects = projectService.get(userId, Pageable.unpaged())
        val bugs = bugService.get(
            projects.map { it.id }.toList(),
            true,
            pageRequest
        )

        return EnterpriseResponse.GetAdoptedBugs(
            bugs.totalElements,
            bugs.map {
                EnterpriseResponse.GetAdoptedBugs.Bug(
                    it.id,
                    it.writerId,
                    it.title,
                    it.type,
                    it.status,
                    it.registerAt.toBasicString()
                )
            }.toList()
        )
    }

    // TODO: 중복 제거
    @Transactional(readOnly = true)
    fun getAdoptedBugsByProject(
        userId: String,
        projectId: String,
        pageRequest: PageRequest
    ): EnterpriseResponse.GetAdoptedBugs {
        val bugs = bugService.get(
            listOf(projectId),
            true,
            pageRequest
        )

        return EnterpriseResponse.GetAdoptedBugs(
            bugs.totalElements,
            bugs.map {
                EnterpriseResponse.GetAdoptedBugs.Bug(
                    it.id,
                    it.writerId,
                    it.title,
                    it.type,
                    it.status,
                    it.registerAt.toBasicString()
                )
            }.toList()
        )
    }

    @Transactional(readOnly = true)
    fun getAdoptedBug(
        userId: String,
        bugId: String
    ): EnterpriseResponse.GetAdoptedBug {
        val bug = bugService.get(bugId) ?: throw KashbugException(ResponseCode.BAD_REQUEST)
        val project = projectService.get(bug.projectId) ?: throw KashbugException(ResponseCode.BAD_REQUEST)

        if (project.ownerId != userId) throw KashbugException(ResponseCode.NOT_ALLOWED_USER)

        return EnterpriseResponse.GetAdoptedBug(
            bug.id,
            project.name,
            bug.writerId,
            bug.title,
            bug.contents,
            bug.type,
            bug.imageUrl?.split(","),
            bug.registerAt.toBasicString()
        )
    }
}