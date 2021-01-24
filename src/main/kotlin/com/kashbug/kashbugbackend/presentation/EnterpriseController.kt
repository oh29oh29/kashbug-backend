package com.kashbug.kashbugbackend.presentation

import com.kashbug.kashbugbackend.application.EnterpriseApplicationService
import com.kashbug.kashbugbackend.application.data.EnterpriseRequest
import com.kashbug.kashbugbackend.application.data.EnterpriseResponse
import com.kashbug.kashbugbackend.presentation.data.OkResponse
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/enterprise")
class EnterpriseController(
    private val enterpriseApplicationService: EnterpriseApplicationService
) {

    @PostMapping("/project")
    fun registerProject(
        userId: String,
        @Validated @RequestBody request: EnterpriseRequest.RegisterProject
    ): OkResponse<Void> {
        enterpriseApplicationService.registerProject(userId, request)
        return OkResponse()
    }

    @GetMapping("/projects")
    fun getProjects(
        userId: String,
        @RequestParam(required = false, defaultValue = "0") page: String,
        @RequestParam(required = false, defaultValue = "10") size: String,
        @RequestParam(required = false, defaultValue = "registerAt") sortBy: String
    ): OkResponse<EnterpriseResponse.GetProjects> {
        return OkResponse(
            enterpriseApplicationService.getProjects(
                userId,
                PageRequest.of(
                    page.toInt(),
                    size.toInt(),
                    Sort.by(sortBy).descending()
                )
            )
        )
    }

    @GetMapping("/project/{projectId}")
    fun getProject(
        userId: String,
        @PathVariable projectId: String
    ): OkResponse<EnterpriseResponse.GetProject> {
        return OkResponse(enterpriseApplicationService.getProject(userId, projectId))
    }

    @PostMapping("/project/{projectId}/bug")
    fun registerBug(
        userId: String,
        @PathVariable projectId: String,
        @Validated @RequestBody request: EnterpriseRequest.RegisterBug
    ): OkResponse<Void> {
        enterpriseApplicationService.registerBug(userId, projectId, request)
        return OkResponse()
    }

    @GetMapping("/project/{projectId}/bugs")
    fun getBugs(
        userId: String,
        @PathVariable projectId: String,
        @RequestParam(required = false, defaultValue = "0") page: String,
        @RequestParam(required = false, defaultValue = "10") size: String,
        @RequestParam(required = false, defaultValue = "registerAt") sortBy: String
    ): OkResponse<EnterpriseResponse.GetBugs> {
        return OkResponse(
            enterpriseApplicationService.getBugs(
                projectId,
                PageRequest.of(
                    page.toInt(),
                    size.toInt(),
                    Sort.by(sortBy).descending()
                )
            )
        )
    }

    @GetMapping("/project/bug/{bugId}")
    fun getBug(
        userId: String,
        @PathVariable bugId: String
    ): OkResponse<EnterpriseResponse.GetBug> {
        return OkResponse(
            enterpriseApplicationService.getBug(userId, bugId)
        )
    }
}