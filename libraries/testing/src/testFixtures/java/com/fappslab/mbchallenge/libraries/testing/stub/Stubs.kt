package com.fappslab.mbchallenge.libraries.testing.stub

import com.fappslab.mbchallenge.core.domain.model.Exchange

fun exchangeStub() = Exchange(
    exchangeId = "BINANCE",
    name = "Binance",
    volume1hrsUsd = "246,0M",
    volume1dayUsd = "6,1B",
    volume1mthUsd = "333,8B",
    website = "https://www.binance.com/",
    iconUrl = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_64/74eaad903814407ebdfc3828fe5318ba.png"
)

fun exchangesStub() = listOf(
    exchangeStub()
)
