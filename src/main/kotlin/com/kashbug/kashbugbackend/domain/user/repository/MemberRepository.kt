package com.kashbug.kashbugbackend.domain.user.repository

import com.kashbug.kashbugbackend.domain.user.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, String> {
}