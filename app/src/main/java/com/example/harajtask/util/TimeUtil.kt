package com.example.harajtask.util

import org.apache.commons.lang3.time.DurationFormatUtils
import java.text.SimpleDateFormat
import java.util.*


fun getTimeDifference(timeStamp: Long): String {
    val differenceSeconds = System.currentTimeMillis() / 1000 - timeStamp
    val day = 86400
    val week = 604800
    val month = week * 4

    return when {
        differenceSeconds < day -> {
            "Since ${differenceSeconds / 3600} hours"
        }
        differenceSeconds in day until week -> {
            if (differenceSeconds < day * 2) "Since ${fromSecondsToDays(differenceSeconds)} day"
            else "Since ${fromSecondsToDays(differenceSeconds)} days"
        }
        differenceSeconds in week until month -> {
            if (differenceSeconds < week * 2) "Since ${fromSecondsToWeeks(differenceSeconds)} week"
            else "Since ${fromSecondsToWeeks(differenceSeconds)} weeks"
        }
        else -> {
            if (differenceSeconds < month * 2) "Since ${fromSecondsToMonths(differenceSeconds)} month"
            else "Since ${fromSecondsToMonths(differenceSeconds)} months"
        }

    }
}

fun fromSecondsToDays(seconds: Long) = seconds / 3600 / 60 / 24
fun fromSecondsToWeeks(seconds: Long) = seconds / 604800
fun fromSecondsToMonths(seconds: Long) = (seconds / (2.628 * 1000000)).toInt()

val format_dd_MM_YYYY_HH_mm = SimpleDateFormat("yyyy/MM/dd HH:mm a", Locale.ENGLISH)
