package com.kashbug.kashbugbackend.domain.interest

import org.springframework.data.jpa.repository.JpaRepository

interface InterestRepository : JpaRepository<Interest, Long> {
}