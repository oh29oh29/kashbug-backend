package com.kashbug.kashbugbackend.domain.project

import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, String> {
}