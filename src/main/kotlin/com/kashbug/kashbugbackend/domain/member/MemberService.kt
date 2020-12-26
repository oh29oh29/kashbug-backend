package com.kashbug.kashbugbackend.domain.member

import com.kashbug.kashbugbackend.domain.member.data.GenderType
import com.kashbug.kashbugbackend.domain.member.data.SignUpType
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import org.springframework.stereotype.Service

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
        val duplicatedMember = memberRepository.findById(id)
        if (duplicatedMember.isEmpty) return false
        return true
    }

    fun get(id: String): Member {
        val member = memberRepository.findById(id)

        if (member.isEmpty) throw KashbugException(ResponseCode.NOT_FOUND_MEMBER)
        return member.get()
    }
}