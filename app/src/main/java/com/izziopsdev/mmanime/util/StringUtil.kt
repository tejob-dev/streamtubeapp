package com.izziopsdev.mmanime.util

import android.annotation.SuppressLint

fun appendZeroBeforeNumber(num: Int): String {
    return if (num < 10) "0$num" else num.toString()
}

//var encodeUpdate = String(java.util.Base64.getEncoder().encode("https://raw.githubusercontent.com/tejob-dev/streamtubeapp/kotlin-mmstream/app/update-changelog.json".toByteArray()))