package com.kashbug.kashbugbackend.domain.project.repository

import com.kashbug.kashbugbackend.domain.project.entity.Bug
import org.springframework.data.jpa.repository.JpaRepository

interface BugRepository : JpaRepository<Bug, String> {
}