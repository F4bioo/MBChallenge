package com.fappslab.mbchallenge.core.domain.stub

import com.fappslab.mbchallenge.core.domain.model.Exchange

internal fun exchangeStub() = Exchange(
    exchangeId = "BINANCE",
    website = "https://www.binance.com/",
    name = "Binance",
    dataQuoteStart = "2017-12-18T00:00:00.0000000Z",
    dataQuoteEnd = "2024-08-20T00:00:00.0000000Z",
    dataOrderBookStart = "2017-12-18T21:50:58.3910192Z",
    dataOrderBookEnd = "2023-07-07T00:00:00.0000000Z",
    dataTradeStart = "2017-07-14T00:00:00.0000000Z",
    dataTradeEnd = "2024-08-21T00:00:00.0000000Z",
    dataSymbolsCount = "2731",
    volume1hrsUsd = "246,0M",
    volume1dayUsd = "6,1B",
    volume1mthUsd = "333,8B",
    iconUrl = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_64/74eaad903814407ebdfc3828fe5318ba.png"
)

internal fun exchangesStub() = listOf(
    exchangeStub()
)
