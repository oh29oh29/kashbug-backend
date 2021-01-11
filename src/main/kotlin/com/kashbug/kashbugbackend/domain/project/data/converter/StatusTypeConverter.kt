package com.kashbug.kashbugbackend.domain.project.data.converter

import com.kashbug.kashbugbackend.domain.project.data.StatusType
import javax.persistence.AttributeConverter

class StatusTypeConverter : AttributeConverter<StatusType, String> {

    override fun convertToDatabaseColumn(attribute: StatusType?) = attribute?.value

    override fun convertToEntityAttribute(dbData: String?) = StatusType.of(dbData)

}