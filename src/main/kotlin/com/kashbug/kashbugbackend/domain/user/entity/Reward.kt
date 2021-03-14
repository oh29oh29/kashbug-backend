package com.kashbug.kashbugbackend.domain.user.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Reward(

    @Column
    val writerId: String,

    @Column
    val providerId: String,

    @Column
    val bugId: String,

    @Column
    val price: Int

) {

    @Id
    val id: String = UUID.randomUUID().toString()

    @Column(name = "register_datetime")
    val registerAt: LocalDateTime = LocalDateTime.now()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Reward

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Reward(writerId='$writerId', providerId='$providerId', bugId='$bugId', price=$price, id='$id', registerAt=$registerAt)"
    }
}