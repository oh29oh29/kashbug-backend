package com.kashbug.kashbugbackend.domain.project.value.converter

import com.kashbug.kashbugbackend.domain.project.value.BugType
import javax.persistence.AttributeConverter

class BugTypeConverter : AttributeConverter<BugType, String> {

    override fun convertToDatabaseColumn(attribute: BugType?) = attribute?.name

    override fun convertToEntityAttribute(dbData: String?) = BugType.of(dbData)

}