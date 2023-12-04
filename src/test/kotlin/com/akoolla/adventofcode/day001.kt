package com.akoolla.adventofcode

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

val testCalibration = listOf(
    "two1nine",
    "eightwothree",
    "abcone2threexyz",
    "xtwone3four",
    "4nineeightseven2",
    "zoneight234",
    "7pqrstsixteen",
)

val textToDigitDictionary = mapOf(
    Pair("one", "1"),
    Pair("two", "2"),
    Pair("six", "6"),
    Pair("four", "4"),
    Pair("five", "5"),
    Pair("nine", "9"),
    Pair("three", "3"),
    Pair("seven", "7"),
    Pair("eight", "8"),
)

fun String.findAndReplaceSpelledDigits() = this
    .toList()
    .map { it.toString() }
    .reduce { acc, s ->
        var line: String = acc + s
        textToDigitDictionary
            .keys
            .firstOrNull { line.contains(it) }
            ?.run {
                line = line.replace(this.substring(0, this.length - 1), textToDigitDictionary[this] ?: "x")
            }
        line
    }

fun List<String>.decodeAndSum(): Int = this
    .sumOf { it.getDigitsFromLine() }

fun String.getDigitsFromLine(): Int = this
    .findAndReplaceSpelledDigits()
    .filter { it.isDigit() }
    .let { "" + it.first() + it.last() }
    .run { Integer.parseInt(this) }

class CalibrationDecoderTests {
    @Test
    fun canFindAndReplaceTextDigitsWithDigits() {
        Assertions.assertEquals(18, "oneeight".getDigitsFromLine())
    }

    @Test
    fun canGetDigitsFromLine() {
        Assertions.assertEquals(12, "1abc2".getDigitsFromLine())
        Assertions.assertEquals(38, "pqr3stu8vwx".getDigitsFromLine())
        Assertions.assertEquals(15, "a1b2c3d4e5f".getDigitsFromLine())
        Assertions.assertEquals(77, "treb7uchet".getDigitsFromLine())
        Assertions.assertEquals(29, "two1nine".getDigitsFromLine())
        Assertions.assertEquals(29, "two1nine".getDigitsFromLine())
        Assertions.assertEquals(83, "eightwothree".getDigitsFromLine())
        Assertions.assertEquals(13, "abcone2threexyz".getDigitsFromLine())
        Assertions.assertEquals(24, "xtwone3four".getDigitsFromLine())
        Assertions.assertEquals(14, "zoneight234".getDigitsFromLine())
        Assertions.assertEquals(76, "7pqrstsixteen".getDigitsFromLine())
    }

    @Test
    fun canSumDigitsFromMultipleLines() {
        Assertions.assertEquals(281, testCalibration.decodeAndSum())
    }

    @Test
    fun canDecodeAndSumDay001File() {
        val calibrationValue: Int = File("src/test/resources/day001Input.txt")
            .useLines { it.toList() }
            .decodeAndSum()

        Assertions.assertEquals(54530, calibrationValue)
    }
}
