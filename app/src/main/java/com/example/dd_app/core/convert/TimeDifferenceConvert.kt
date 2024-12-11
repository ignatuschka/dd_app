package com.example.dd_app.core.convert

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.Duration

class TimeDifferenceConvert {

    fun timeAgo(from: LocalDateTime, to: LocalDateTime, addingText: String = "", underSecond: String): String {
        val years = ChronoUnit.YEARS.between(from, to)
        if (years > 0) return pluralize(years, "год", "года", "лет") + addingText

        val months = ChronoUnit.MONTHS.between(from, to)
        if (months > 0) return pluralize(months, "месяц", "месяца", "месяцев") + addingText

        val weeks = ChronoUnit.WEEKS.between(from, to)
        if (weeks > 0) return pluralize(weeks, "неделю", "недели", "недель") + addingText

        val days = ChronoUnit.DAYS.between(from, to)
        if (days > 0) return pluralize(days, "день", "дня", "дней") + addingText

        val hours = ChronoUnit.HOURS.between(from, to)
        if (hours > 0) return pluralize(hours, "час", "часа", "часов") + addingText

        val minutes = ChronoUnit.MINUTES.between(from, to)
        if (minutes > 0) return pluralize(minutes, "минуту", "минуты", "минут") + addingText

        val seconds = ChronoUnit.SECONDS.between(from, to)
        if (seconds > 0) return pluralize(seconds, "секунду", "секунды", "секунд") + addingText

        return underSecond
    }

    fun timeDifference(from: LocalDateTime, to: LocalDateTime): String {
        val duration = Duration.between(from, to).abs()

        val days = duration.toDays()
        val hours = duration.toHours() % 24
        val minutes = duration.toMinutes() % 60
        val seconds = duration.seconds % 60

        val parts = mutableListOf<String>()

        if (days > 0) parts.add(pluralize(days, "день", "дня", "дней"))
        if (hours > 0) parts.add(pluralize(hours, "час", "часа", "часов"))
        if (minutes > 0 && days < 1) parts.add(pluralize(minutes, "минута", "минуты", "минут"))
        if ((seconds > 0 && days < 1 && hours < 1) || parts.isEmpty()) parts.add(
            pluralize(
                seconds,
                "секунда",
                "секунды",
                "секунд"
            )
        )

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
}