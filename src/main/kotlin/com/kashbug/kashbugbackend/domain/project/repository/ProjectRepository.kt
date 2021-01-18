package com.kashbug.kashbugbackend.domain.project.repository

import com.kashbug.kashbugbackend.domain.project.entity.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, String> {
}