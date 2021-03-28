package com.kashbug.kashbugbackend.domain.project.repository

import com.kashbug.kashbugbackend.domain.project.entity.Project
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, String> {

    fun findByOwnerId(ownerId: String, pageable: Pageable): Page<Project>

    fun findByIdIn(id: List<String>): List<Project>

}