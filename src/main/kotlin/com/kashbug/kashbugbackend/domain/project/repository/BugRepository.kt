package com.kashbug.kashbugbackend.domain.project.repository

import com.kashbug.kashbugbackend.domain.project.entity.Bug
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BugRepository : JpaRepository<Bug, String> {

    fun findByProjectId(projectId: String, pageable: Pageable): Page<Bug>

    fun findByProjectIdInAndIsAdopted(projectId: List<String>, isAdopted: Boolean, pageable: Pageable): Page<Bug>

    fun countByProjectId(projectId: String): Int

    fun findByWriterId(writerId: String, pageable: Pageable): Page<Bug>

    fun findByIdIn(id: List<String>, pageable: Pageable): Page<Bug>

}