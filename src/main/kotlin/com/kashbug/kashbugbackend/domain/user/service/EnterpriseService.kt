package com.kashbug.kashbugbackend.domain.user.service

import com.kashbug.kashbugbackend.domain.user.entity.Enterprise
import com.kashbug.kashbugbackend.domain.user.repository.EnterpriseRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class EnterpriseService(
    private val enterpriseRepository: EnterpriseRepository
) {

    fun save(
        id: String,
        name: String,
        password: String,
        email: String,
        serial: String?,
        contact: String,
        profileImageUrl: String?,
        homepageUrl: String?,
        introduce: String
    ) {
        enterpriseRepository.save(
            Enterprise(
                id,
                name,
                password,
                email,
                serial,
                contact,
                profileImageUrl,
                homepageUrl,
                introduce
            )
        )
    }

    fun existId(id: String): Boolean {
        val existEnterprise = enterpriseRepository.findByIdOrNull(id)
        return !Objects.isNull(existEnterprise)
    }

    fun get(id: String): Enterprise? {
        return enterpriseRepository.findByIdOrNull(id)
    }
}