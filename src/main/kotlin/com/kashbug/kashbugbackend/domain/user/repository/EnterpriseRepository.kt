package com.kashbug.kashbugbackend.domain.user.repository

import com.kashbug.kashbugbackend.domain.user.entity.Enterprise
import org.springframework.data.jpa.repository.JpaRepository

interface EnterpriseRepository : JpaRepository<Enterprise, String>