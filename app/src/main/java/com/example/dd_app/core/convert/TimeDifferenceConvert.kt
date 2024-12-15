package com.example.dd_app.core.convert

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.Duration

fun LocalDateTime.timeAgo(to: LocalDateTime, suffix: String = "", underSecond: String): String {
    val units = listOf(
        ChronoUnit.YEARS to { pluralize(ChronoUnit.YEARS.between(this, to), "год", "года", "лет") },
        ChronoUnit.MONTHS to { pluralize(ChronoUnit.MONTHS.between(this, to), "месяц", "месяца", "месяцев") },
        ChronoUnit.WEEKS to { pluralize(ChronoUnit.WEEKS.between(this, to), "неделю", "недели", "недель") },
        ChronoUnit.DAYS to { pluralize(ChronoUnit.DAYS.between(this, to), "день", "дня", "дней") },
        ChronoUnit.HOURS to { pluralize(ChronoUnit.HOURS.between(this, to), "час", "часа", "часов") },
        ChronoUnit.MINUTES to { pluralize(ChronoUnit.MINUTES.between(this, to), "минуту", "минуты", "минут") },
        ChronoUnit.SECONDS to { pluralize(ChronoUnit.SECONDS.between(this, to), "секунду", "секунды", "секунд") }
    )
    for ((unit, format) in units) {
        val time = unit.between(this, to)
        if (time > 0) return format() + suffix
    }
    return underSecond
}

fun LocalDateTime.timeDifference(to: LocalDateTime): String {
    val duration = Duration.between(this, to).abs()

    val days = duration.toDays()
    val hours = duration.toHours() % 24
    val minutes = duration.toMinutes() % 60
    val seconds = duration.seconds % 60

    val parts = mutableListOf<String>().apply {
        if (days > 0) add(pluralize(days, "день", "дня", "дней"))
        if (hours > 0) add(pluralize(hours, "час", "часа", "часов"))
        if (minutes > 0 && days < 1) add(pluralize(minutes, "минута", "минуты", "минут"))
        if ((seconds > 0 && days < 1 && hours < 1) || isEmpty()) add(pluralize(seconds, "секунда", "секунды", "секунд"))
    }
    return if (parts.isEmpty()) "менее секунды" else parts.joinToString(" ")
}

private fun pluralize(number: Long, one: String, few: String, many: String): String {
    val absNumber = number % 100
    return when {
        absNumber in 11..19 -> "$number $many"
        absNumber % 10 == 1L -> "$number $one"
        absNumber % 10 in 2..4 -> "$number $few"
        else -> "$number $many"
    }
}