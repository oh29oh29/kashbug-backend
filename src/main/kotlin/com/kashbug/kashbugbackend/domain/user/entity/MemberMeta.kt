package com.kashbug.kashbugbackend.domain.user.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class MemberMeta(

    @Id
    val id: String,

    @Column
    var accountNumber: String

) {

    @Column(name = "register_datetime")
    val registerAt: LocalDateTime = LocalDateTime.now()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemberMeta

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "MemberMeta(id='$id', accountNumber='$accountNumber', registerAt=$registerAt)"
    }
}