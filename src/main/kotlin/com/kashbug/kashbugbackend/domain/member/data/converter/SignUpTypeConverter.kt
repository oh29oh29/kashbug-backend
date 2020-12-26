package com.kashbug.kashbugbackend.domain.member.data.converter

import com.kashbug.kashbugbackend.domain.member.data.SignUpType
import javax.persistence.AttributeConverter

class SignUpTypeConverter : AttributeConverter<SignUpType, String> {

    override fun convertToDatabaseColumn(attribute: SignUpType?) = attribute?.value

    override fun convertToEntityAttribute(dbData: String?) = SignUpType.of(dbData)

}