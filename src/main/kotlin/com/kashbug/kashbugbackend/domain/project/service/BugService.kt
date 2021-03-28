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
import java.time.LocalDateTime

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
        userId: String,
        bugId: String,
        type: BugType,
        title: String,
        contents: String,
        imageUrl: List<String>?
    ) {
        val bug = bugRepository.findByIdOrNull(bugId) ?: throw KashbugException(ResponseCode.BAD_REQUEST)
        if (bug.writerId != userId) throw KashbugException(ResponseCode.NOT_ALLOWED_USER)

        bug.apply {
            this.type = type
            this.title = title
            this.contents = contents
            this.imageUrl = imageUrl?.joinToStringWithRest()
            this.modificationAt = LocalDateTime.now()
        }

        bugRepository.save(bug)
    }

    fun get(
        bugId: String
    ): Bug? {
        return bugRepository.findByIdOrNull(bugId)
    }

    fun get(
        bugIds: List<String>,
        pageable: Pageable
    ): Page<Bug> {
        return bugRepository.findByIdIn(bugIds, pageable)
    }

    fun get(
        projectId: String,
        pageable: Pageable
    ): Page<Bug> {
        return bugRepository.findByProjectId(projectId, pageable)
    }

    fun get(
        projectId: List<String>,
        isAdopted: Boolean,
        pageable: Pageable
    ): Page<Bug> {
        return bugRepository.findByProjectIdInAndIsAdopted(projectId, isAdopted, pageable)
    }

    /**
     * 버그 리스트 조회 by 사용자 ID
     * */
    fun getByUserId(
        userId: String,
        pageable: Pageable
    ): Page<Bug> {
        return bugRepository.findByWriterId(userId, pageable)
    }

    fun count(projectId: String): Int {
        return bugRepository.countByProjectId(projectId)
    }
}