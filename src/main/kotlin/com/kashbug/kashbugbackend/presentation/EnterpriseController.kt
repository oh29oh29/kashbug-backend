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
    fun registerProject(userId: String, @Validated @RequestBody request: EnterpriseRequest.RegisterProject): OkResponse<Void> {
        enterpriseApplicationService.registerProject(userId, request)
        return OkResponse()
    }

    @GetMapping("/projects")
    fun getProjects(
        @RequestParam(required = false, defaultValue = "0") page: String,
        @RequestParam(required = false, defaultValue = "10") size: String,
        @RequestParam(required = false, defaultValue = "registerAt") sortBy: String
    ): OkResponse<EnterpriseResponse.GetProjects> {
        val pageRequest = PageRequest.of(page.toInt(), size.toInt(), Sort.by(sortBy).descending())
        return OkResponse(enterpriseApplicationService.getProjects(pageRequest))
    }

    @GetMapping("/project/{projectId}")
    fun getProject(userId: String, @PathVariable projectId: String): OkResponse<EnterpriseResponse.GetProject> {
        return OkResponse(enterpriseApplicationService.getProject(userId, projectId))
    }

    @PostMapping("/project/bug")
    fun registerBug(userId: String, @Validated @RequestBody request: EnterpriseRequest.RegisterBug): OkResponse<Void> {
        enterpriseApplicationService.registerBug(userId, request)
        return OkResponse()
    }
}