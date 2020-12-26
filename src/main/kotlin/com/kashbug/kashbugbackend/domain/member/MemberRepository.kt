package com.kashbug.kashbugbackend.domain.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, String> {
}