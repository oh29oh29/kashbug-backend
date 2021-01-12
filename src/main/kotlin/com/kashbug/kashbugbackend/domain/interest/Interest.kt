package com.kashbug.kashbugbackend.domain.interest

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Interest(

    @EmbeddedId
    val interestId: InterestId

) {

    @Column(name = "register_datetime")
    val registerAt: LocalDateTime = LocalDateTime.now()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Interest
        return interestId == other.interestId
    }

    override fun hashCode(): Int {
        return interestId.hashCode()
    }
}