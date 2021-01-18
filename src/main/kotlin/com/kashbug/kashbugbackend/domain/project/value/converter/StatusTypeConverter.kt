package com.kashbug.kashbugbackend.domain.project.value.converter

import com.kashbug.kashbugbackend.domain.project.value.StatusType
import javax.persistence.AttributeConverter

class StatusTypeConverter : AttributeConverter<StatusType, String> {

    override fun convertToDatabaseColumn(attribute: StatusType?) = attribute?.name

    override fun convertToEntityAttribute(dbData: String?) = StatusType.of(dbData)

}