package com.kashbug.kashbugbackend.domain.interest

import com.kashbug.kashbugbackend.domain.interest.data.InterestCode
import com.kashbug.kashbugbackend.domain.interest.data.converter.InterestCodeConverter
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
class InterestId(

    @Column
    val targetId: String,

    @Column
    @Convert(converter = InterestCodeConverter::class)
    val code: InterestCode

) : Serializable {

    override fun toString(): String {
        return "InterestId(targetId='$targetId', code=$code)"
    }
}