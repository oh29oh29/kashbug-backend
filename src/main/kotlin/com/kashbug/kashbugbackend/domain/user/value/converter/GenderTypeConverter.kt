package com.kashbug.kashbugbackend.domain.user.value.converter

import com.kashbug.kashbugbackend.domain.user.value.GenderType
import javax.persistence.AttributeConverter

class GenderTypeConverter : AttributeConverter<GenderType, String> {

    override fun convertToDatabaseColumn(attribute: GenderType?) = attribute?.value

    override fun convertToEntityAttribute(dbData: String?) = GenderType.of(dbData)

}