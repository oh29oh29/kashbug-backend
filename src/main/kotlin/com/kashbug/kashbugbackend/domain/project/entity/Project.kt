package com.kashbug.kashbugbackend.domain.project.entity

import com.kashbug.kashbugbackend.domain.project.value.StatusType
import com.kashbug.kashbugbackend.domain.project.value.converter.StatusTypeConverter
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Project(

    @Column
    var name: String,

    @Column
    val ownerId: String,

    @Column
    var contents: String,

    @Column
    var reward: Int,

    @Column
    var rewardDuration: Int,

    @Column
    var url: String?,

    @Column
    var imageUrl: String?,

    @Column
    @Convert(converter = StatusTypeConverter::class)
    var status: StatusType,

    @Column(name = "start_datetime")
    var startAt: LocalDateTime?,

    @Column(name = "deadline_datetime")
    var deadlineAt: LocalDateTime

) {

    @Id
    val id: String = UUID.randomUUID().toString()

    @Column(name = "register_datetime")
    val registerAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "modifiaction_datetime")
    var modificationAt: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Project

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Project(" +
            "id='$id', " +
            "name='$name', " +
            "ownerId='$ownerId', " +
            "contents='$contents', " +
            "reward='$reward', " +
            "rewardDuration='$rewardDuration', " +
            "url=$url, " +
            "imageUrl=$imageUrl, " +
            "status='$status', " +
            "startAt='$startAt', " +
            "deadlineAt='$deadlineAt', " +
            "registerAt=$registerAt, " +
            "modificationAt=$modificationAt" +
            ")"
    }
}