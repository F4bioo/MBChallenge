package com.fappslab.mbchallenge.libraries.arch.extension

fun Boolean?.orFalse(): Boolean = this ?: false

fun Boolean?.orTrue(): Boolean = this ?: true
