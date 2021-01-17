package com.kashbug.kashbugbackend.domain.interest

import com.kashbug.kashbugbackend.domain.interest.data.InterestCode
import com.kashbug.kashbugbackend.domain.interest.data.converter.InterestCodeConverter
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Interest(

    @Column
    val targetId: String,

    @Column
    @Convert(converter = InterestCodeConverter::class)
    val code: InterestCode

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "register_datetime")
    val registerAt: LocalDateTime = LocalDateTime.now()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Interest
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Interest(" +
            "targetId='$targetId', " +
            "code=$code, " +
            "id=$id, " +
            "registerAt=$registerAt" +
            ")"
    }
}