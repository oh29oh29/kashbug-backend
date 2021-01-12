package com.kashbug.kashbugbackend.presentation

import com.kashbug.kashbugbackend.application.EnterpriseApplicationService
import com.kashbug.kashbugbackend.application.data.EnterpriseRequest
import com.kashbug.kashbugbackend.presentation.data.OkResponse
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
    fun getProjects(userId: String): OkResponse<Void> {
        println(userId)
        return OkResponse()
    }
}