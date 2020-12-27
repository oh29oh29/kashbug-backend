package com.kashbug.kashbugbackend.domain.member

import com.kashbug.kashbugbackend.domain.member.data.GenderType
import com.kashbug.kashbugbackend.domain.member.data.SignUpType
import com.kashbug.kashbugbackend.domain.member.data.converter.GenderTypeConverter
import com.kashbug.kashbugbackend.domain.member.data.converter.SignUpTypeConverter
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Member(

    @Id
    val id: String,

    @Column
    val name: String,

    @Column
    val password: String,

    @Column
    @Convert(converter = GenderTypeConverter::class)
    val gender: GenderType,

    @Column
    val email: String,

    @Column
    val contact: String,

    @Column
    val profileImageUrl: String?,

    @Column
    val birthYear: String,

    @Column
    @Convert(converter = SignUpTypeConverter::class)
    val signUpType: SignUpType,

    @Column
    val registrationDateTime: LocalDateTime,

    @Column
    val modificationDateTime: LocalDateTime?

) {

    constructor(
        id: String,
        name: String,
        password: String,
        gender: GenderType,
        email: String,
        contact: String,
        profileImageUrl: String?,
        birthYear: String,
        signUpType: SignUpType
    ) : this(
        id,
        name,
        password,
        gender,
        email,
        contact,
        profileImageUrl,
        birthYear,
        signUpType,
        LocalDateTime.now(),
        null
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Member(" +
            "id='$id', " +
            "name='$name', " +
            "password='$password', " +
            "gender=$gender, " +
            "email='$email', " +
            "contact='$contact', " +
            "profileImageUrl='$profileImageUrl', " +
            "birthYear=$birthYear, " +
            "signUpType=$signUpType, " +
            "registrationDateTime=$registrationDateTime, " +
            "modificationDateTime=$modificationDateTime" +
            ")"
    }
}