package com.timistired.androidofflinecapability.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class AppleEntity(
    @PrimaryKey(autoGenerate = false) val id: UUID,
    val color: AppleColor,
    val price: Double,
    var synced: Boolean = false
) {
    enum class AppleColor {
        RED,
        GREEN,
        YELLOW
    }

    fun toDTO(): AppleDTO = AppleDTO(id = id, color = color.name, price = price)
}