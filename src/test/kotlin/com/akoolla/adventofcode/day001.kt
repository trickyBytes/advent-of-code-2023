package com.akoolla.adventofcode

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

val calibrationDocContents = listOf(
    "1abc2",
    "pqr3stu8vwx",
    "a1b2c3d4e5f",
    "treb7uchet",
)

fun List<String>.decodeAndSum(): Int = this
    .sumOf { it.getDigitsFromLine() }

fun String.getDigitsFromLine(): Int = this
    .filter { it.isDigit() }
    .toList()
    .run { "" + this.first() + this.last() }
    .run { Integer.parseInt(this)}

class CalibrationDecoderTests {

    @Test
    fun canGetDigitsFromLine(){
        Assertions.assertEquals(12, "1abc2".getDigitsFromLine())
        Assertions.assertEquals(38, "pqr3stu8vwx".getDigitsFromLine())
        Assertions.assertEquals(15, "a1b2c3d4e5f".getDigitsFromLine())
        Assertions.assertEquals(77, "treb7uchet".getDigitsFromLine())
    }

    @Test
    fun canSumDigitsFromMultipleLines(){
        Assertions.assertEquals(142, calibrationDocContents.decodeAndSum())
    }
}
