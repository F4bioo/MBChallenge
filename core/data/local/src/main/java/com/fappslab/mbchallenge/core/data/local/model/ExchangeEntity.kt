package com.fappslab.mbchallenge.core.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange")
internal data class ExchangeEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("exchange_id")
    val exchangeId: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("volume_1hrs_usd")
    val volume1hrsUsd: String,
    @ColumnInfo("volume_1day_usd")
    val volume1dayUsd: String,
    @ColumnInfo("volume_1mth_usd")
    val volume1mthUsd: String,
    @ColumnInfo("website")
    val website: String,
    @ColumnInfo("icon_url")
    val iconUrl: String,
)
