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
    val name: String,

    @Column
    val ownerId: String,

    @Column
    val contents: String,

    @Column
    val reward: Int,

    @Column
    val rewardDuration: Int,

    @Column
    val url: String?,

    @Column
    val imageUrl: String?,

    @Column
    @Convert(converter = StatusTypeConverter::class)
    val status: StatusType,

    @Column(name = "start_datetime")
    val startAt: LocalDateTime?,

    @Column(name = "deadline_datetime")
    val deadlineAt: LocalDateTime

) {

    @Id
    val id: String = UUID.randomUUID().toString()

    @Column(name = "modifiaction_datetime")
    val modificationAt: LocalDateTime? = null

    @Column(name = "register_datetime")
    val registerAt: LocalDateTime = LocalDateTime.now()

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