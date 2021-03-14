package com.kashbug.kashbugbackend.domain.user.repository

import com.kashbug.kashbugbackend.domain.user.entity.MemberMeta
import org.springframework.data.jpa.repository.JpaRepository

interface MemberMetaRepository : JpaRepository<MemberMeta, String> {

}