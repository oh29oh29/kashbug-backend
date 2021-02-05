package com.kashbug.kashbugbackend.domain.common.value.converter

import com.kashbug.kashbugbackend.domain.common.value.InterestCode
import javax.persistence.AttributeConverter

class InterestCodeConverter : AttributeConverter<InterestCode, String> {

    override fun convertToDatabaseColumn(attribute: InterestCode?) = attribute?.name

    override fun convertToEntityAttribute(dbData: String?) = InterestCode.of(dbData)

}