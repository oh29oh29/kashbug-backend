package com.kashbug.kashbugbackend.domain.member

import com.kashbug.kashbugbackend.domain.member.data.GenderType
import com.kashbug.kashbugbackend.domain.member.data.SignUpType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun save(
        id: String,
        name: String,
        password: String,
        gender: GenderType,
        email: String,
        contact: String,
        profileImageUrl: String?,
        birthYear: String,
        signUpType: SignUpType
    ) {
        memberRepository.save(
            Member(
                id,
                name,
                password,
                gender,
                email,
                contact,
                profileImageUrl,
                birthYear,
                signUpType
            )
        )
    }

    fun isDuplicatedMemberId(id: String): Boolean {
        val duplicatedMember = memberRepository.findByIdOrNull(id)
        return !Objects.isNull(duplicatedMember)
    }

    fun get(id: String): Member? {
        return memberRepository.findByIdOrNull(id)
    }
}