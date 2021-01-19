package com.kashbug.kashbugbackend.domain.project.entity

import com.kashbug.kashbugbackend.domain.project.value.BugType
import com.kashbug.kashbugbackend.domain.project.value.converter.BooleanConverter
import com.kashbug.kashbugbackend.domain.project.value.converter.BugTypeConverter
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Bug(

    @Column
    val projectId: String,

    @Column
    val writerId: String,

    @Column
    @Convert(converter = BugTypeConverter::class)
    val type: BugType,

    @Column
    val title: String,

    @Column
    val contents: String,

    @Column
    val imageUrl: String?,

    @Column
    @Convert(converter = BooleanConverter::class)
    val isAccepted: Boolean = false

) {
    @Id
    val id: String = UUID.randomUUID().toString()

    @Column(name = "register_datetime")
    val registerAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "modifiaction_datetime")
    val modificationAt: LocalDateTime? = null

    @Column(name = "adoption_datetime")
    val adoptAt: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bug

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Bug(" +
            "projectId='$projectId', " +
            "writerId='$writerId', " +
            "type=$type, " +
            "title='$title', " +
            "contents='$contents', " +
            "imageUrl='$imageUrl', " +
            "isAccepted=$isAccepted, " +
            "id='$id', " +
            "registerAt=$registerAt, " +
            "modificationAt=$modificationAt, " +
            "adoptAt=$adoptAt" +
            ")"
    }


}