package com.kashbug.kashbugbackend.domain.interest.data.converter

import com.kashbug.kashbugbackend.domain.interest.data.InterestCode
import javax.persistence.AttributeConverter

class InterestCodeConverter : AttributeConverter<InterestCode, String> {

    override fun convertToDatabaseColumn(attribute: InterestCode?) = attribute?.name

    override fun convertToEntityAttribute(dbData: String?) = InterestCode.of(dbData)

}