package com.kashbug.kashbugbackend.domain.user.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Enterprise(

    @Id
    override val id: String,

    @Column
    val name: String,

    @Column
    override val password: String,

    @Column
    val email: String,

    @Column
    val serial: String?,

    @Column
    val contact: String,

    @Column
    val profileImageUrl: String?,

    @Column
    val homepageUrl: String?,

    @Column
    val introduce: String,

    @Column
    val registrationDateTime: LocalDateTime,

    @Column
    val modificationDateTime: LocalDateTime?

) : User {
    constructor(
        id: String,
        name: String,
        password: String,
        email: String,
        serial: String?,
        contact: String,
        profileImageUrl: String?,
        homepageUrl: String?,
        introduce: String
    ) : this(
        id,
        name,
        password,
        email,
        serial,
        contact,
        profileImageUrl,
        homepageUrl,
        introduce,
        LocalDateTime.now(),
        null
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Enterprise

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Enterprise(" +
            "id='$id', " +
            "name='$name', " +
            "password='$password', " +
            "email='$email', " +
            "serial='$serial', " +
            "contact='$contact', " +
            "profileImageUrl=$profileImageUrl, " +
            "homepageUrl=$homepageUrl, " +
            "introduce='$introduce', " +
            "registrationDateTime=$registrationDateTime, " +
            "modificationDateTime=$modificationDateTime" +
            ")"
    }

}