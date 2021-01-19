package com.kashbug.kashbugbackend.domain.project.value.converter

import javax.persistence.AttributeConverter

class BooleanConverter : AttributeConverter<Boolean, Char> {

    override fun convertToDatabaseColumn(attribute: Boolean) = if (attribute) 'Y' else 'N'

    override fun convertToEntityAttribute(dbData: Char) = dbData == 'Y'

}