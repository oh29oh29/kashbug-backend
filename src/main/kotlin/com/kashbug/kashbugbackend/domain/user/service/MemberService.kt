package com.kashbug.kashbugbackend.domain.user.service

import com.kashbug.kashbugbackend.domain.user.entity.Member
import com.kashbug.kashbugbackend.domain.user.entity.MemberMeta
import com.kashbug.kashbugbackend.domain.user.repository.MemberMetaRepository
import com.kashbug.kashbugbackend.domain.user.repository.MemberRepository
import com.kashbug.kashbugbackend.domain.user.value.GenderType
import com.kashbug.kashbugbackend.domain.user.value.SignUpType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberMetaRepository: MemberMetaRepository
) {

    fun createMember(
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

    fun existId(id: String): Boolean {
        return memberRepository.findByIdOrNull(id) != null
    }

    fun get(id: String): Member? {
        return memberRepository.findByIdOrNull(id)
    }

    fun createMeta(id: String) {
        memberMetaRepository.save(
            MemberMeta(id)
        )
    }

    /**
     * 계좌번호 수정
     * */
    @Transactional
    fun updateAccountNumber(
        id: String,
        bankName: String,
        number: String
    ) {
        memberMetaRepository.findByIdOrNull(id)?.apply {
            this.bankName = bankName
            this.accountNumber = number
            memberMetaRepository.save(this)
        }
    }
}