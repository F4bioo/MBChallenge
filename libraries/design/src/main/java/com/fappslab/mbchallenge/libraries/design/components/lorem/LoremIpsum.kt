package com.fappslab.mbchallenge.libraries.design.components.lorem

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

fun loremIpsum(words: () -> Int): String {
    return LoremIpsum(words()).values.first()
}
