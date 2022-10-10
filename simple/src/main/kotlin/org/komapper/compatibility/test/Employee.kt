package org.komapper.compatibility.test

import org.komapper.annotation.KomapperCreatedAt
import org.komapper.annotation.KomapperEmbedded
import org.komapper.annotation.KomapperEntity
import org.komapper.annotation.KomapperId
import org.komapper.annotation.KomapperUpdatedAt
import org.komapper.annotation.KomapperVersion
import java.time.LocalDateTime

@KomapperEntity
data class Employee(
    @KomapperId
    val id: Int,
    val name: String,
    @KomapperEmbedded
    val address: Address,
    @KomapperVersion
    val version: Int,
    @KomapperCreatedAt
    val createdAt: LocalDateTime = LocalDateTime.MIN,
    @KomapperUpdatedAt
    val updatedAt: LocalDateTime = LocalDateTime.MIN,
)

data class Address(val street: String, val city: String)
