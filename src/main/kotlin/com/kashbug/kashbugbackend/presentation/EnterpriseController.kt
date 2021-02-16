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

    /**
     * 프로젝트 등록
     * */
    @PostMapping("/project")
    fun registerProject(
        userId: String,
        @Validated @RequestBody request: EnterpriseRequest.RegisterProject
    ): OkResponse<Void> {
        enterpriseApplicationService.registerProject(userId, request)
        return OkResponse()
    }

    /**
     * 프로젝트 리스트 조회
     * */
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

    /**
     * 프로젝트 상세 조회
     * */
    @GetMapping("/project/{projectId}")
    fun getProject(
        userId: String,
        @PathVariable projectId: String
    ): OkResponse<EnterpriseResponse.GetProject> {
        return OkResponse(enterpriseApplicationService.getProject(userId, projectId))
    }

    /**
     * 프로젝트 수정
     * */
    @PutMapping("/project/{projectId}")
    fun updateProject(
        userId: String,
        @PathVariable projectId: String,
        @Validated @RequestBody request: EnterpriseRequest.UpdateProject
    ): OkResponse<Void> {
        enterpriseApplicationService.updateProject(userId, projectId, request)
        return OkResponse()
    }

    /**
     * 버그 등록
     * */
    @PostMapping("/project/{projectId}/bug")
    fun registerBug(
        userId: String,
        @PathVariable projectId: String,
        @Validated @RequestBody request: EnterpriseRequest.RegisterBug
    ): OkResponse<Void> {
        enterpriseApplicationService.registerBug(userId, projectId, request)
        return OkResponse()
    }

    /**
     * 버그 리스트 조회
     * */
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

    /**
     * 버그 상세 조회
     * */
    @GetMapping("/project/bug/{bugId}")
    fun getBug(
        userId: String,
        @PathVariable bugId: String
    ): OkResponse<EnterpriseResponse.GetBug> {
        return OkResponse(
            enterpriseApplicationService.getBug(userId, bugId)
        )
    }

    /**
     * 버그 수정
     * */
    @PutMapping("/project/bug/{bugId}")
    fun updateBug(
        userId: String,
        @PathVariable bugId: String,
        @Validated @RequestBody request: EnterpriseRequest.UpdateBug
    ): OkResponse<Void> {
        enterpriseApplicationService.updateBug(userId, bugId, request)
        return OkResponse()
    }

}