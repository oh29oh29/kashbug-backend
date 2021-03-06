package com.kashbug.kashbugbackend.domain.project.entity

import com.kashbug.kashbugbackend.domain.project.value.BugStatusType
import com.kashbug.kashbugbackend.domain.project.value.BugType
import com.kashbug.kashbugbackend.domain.project.value.converter.BugStatusTypeConverter
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
    var type: BugType,

    @Column
    var title: String,

    @Column
    var contents: String,

    @Column
    var imageUrl: String?,

    @Column
    @Convert(converter = BugStatusTypeConverter::class)
    var status: BugStatusType = BugStatusType.UNCONFIRMED

) {
    @Id
    val id: String = UUID.randomUUID().toString()

    @Column(name = "register_datetime")
    val registerAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "modifiaction_datetime")
    var modificationAt: LocalDateTime? = null

    @Column(name = "adoption_datetime")
    var adoptAt: LocalDateTime? = null

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
            "status=$status, " +
            "id='$id', " +
            "registerAt=$registerAt, " +
            "modificationAt=$modificationAt, " +
            "adoptAt=$adoptAt" +
            ")"
    }


}