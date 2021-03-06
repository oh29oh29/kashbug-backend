package com.kashbug.kashbugbackend.domain.project.value.converter

import com.kashbug.kashbugbackend.domain.project.value.BugStatusType
import javax.persistence.AttributeConverter

class BugStatusTypeConverter : AttributeConverter<BugStatusType, String> {

    override fun convertToDatabaseColumn(attribute: BugStatusType?) = attribute?.name

    override fun convertToEntityAttribute(dbData: String?) = BugStatusType.of(dbData)

}