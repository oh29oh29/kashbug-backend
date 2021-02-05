package com.kashbug.kashbugbackend.domain.project.service

import com.kashbug.kashbugbackend.domain.project.entity.Bug
import com.kashbug.kashbugbackend.domain.project.repository.BugRepository
import com.kashbug.kashbugbackend.domain.project.value.BugType
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.joinToStringWithRest
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BugService(
    private val bugRepository: BugRepository
) {

    fun save(
        projectId: String,
        writerId: String,
        type: BugType,
        title: String,
        contents: String,
        imageUrl: List<String>?
    ) {

        bugRepository.save(
            Bug(
                projectId,
                writerId,
                type,
                title,
                contents,
                imageUrl?.joinToStringWithRest()
            )
        )
    }

    fun update(
         bugId: String,
         type: BugType?
    ) {
        bugRepository.findByIdOrNull(bugId)?.apply {
            // TODO: 수정 필드 정의
        } ?: throw KashbugException(ResponseCode.BAD_REQUEST)
    }

    fun get(
        projectId: String,
        pageable: Pageable
    ): Page<Bug> {
        return bugRepository.findByProjectId(projectId, pageable)
    }

    fun get(
        bugId: String
    ): Bug? {
        return bugRepository.findByIdOrNull(bugId)
    }

    fun count(projectId: String): Int {
        return bugRepository.countByProjectId(projectId)
    }
}