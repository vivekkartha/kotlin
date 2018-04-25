/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license 
 * that can be found in the license/LICENSE.txt file.
 */

package kotlin

import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or
import kotlin.experimental.xor

inline class UShort internal constructor(private val data: Short) : Comparable<UShort> {

    companion object {
        /**
         * A constant holding the minimum value an instance of Int can have.
         */
        public /*const*/ val MIN_VALUE: UShort = UShort(0)

        /**
         * A constant holding the maximum value an instance of Int can have.
         */
        public /*const*/ val MAX_VALUE: UShort = UShort(-1)
    }

//    /**
//     * Compares this value with the specified value for order.
//     * Returns zero if this value is equal to the specified other value, a negative number if it's less than other,
//     * or a positive number if it's greater than other.
//     */
//    public operator fun compareTo(other: Byte): Int

    /**
     * Compares this value with the specified value for order.
     * Returns zero if this value is equal to the specified other value, a negative number if it's less than other,
     * or a positive number if it's greater than other.
     */
    public operator fun compareTo(other: UInt): Int = this.toLong().compareTo(other.toLong())

//    /**
//     * Compares this value with the specified value for order.
//     * Returns zero if this value is equal to the specified other value, a negative number if it's less than other,
//     * or a positive number if it's greater than other.
//     */
//    public operator fun compareTo(other: ULong): Int

    override fun compareTo(other: UShort): Int = this.toInt().compareTo(other.toInt())

    /** Adds the other value to this value. */
//    public operator fun plus(other: UByte): UShort

    /** Adds the other value to this value. */
//    public operator fun plus(other: UShort): UShort

    /** Adds the other value to this value. */
    public operator fun plus(other: UShort): UShort = UShort((this.data + other.data).toShort())

    /** Adds the other value to this value. */
//    public operator fun plus(other: ULong): ULong

//    /** Subtracts the other value from this value. */
//    public operator fun minus(other: UByte): UShort
//
//    /** Subtracts the other value from this value. */
//    public operator fun minus(other: UShort): UShort

    /** Subtracts the other value from this value. */
    public operator fun minus(other: UShort): UShort = UShort((this.data - other.data).toShort())

//    /** Subtracts the other value from this value. */
//    public operator fun minus(other: ULong): ULong
//
//    /** Multiplies this value by the other value. */
//    public operator fun times(other: UByte): UShort
//
//    /** Multiplies this value by the other value. */
//    public operator fun times(other: UShort): UShort

    /** Multiplies this value by the other value. */
    public operator fun times(other: UShort): UShort = (this.toInt() * other.toInt()).toUShort()

//    /** Multiplies this value by the other value. */
//    public operator fun times(other: Long): Long

//    /** Divides this value by the other value. */
//    public operator fun div(other: Byte): Int
//
//    /** Divides this value by the other value. */
//    public operator fun div(other: Short): Int

    /** Divides this value by the other value. */
    public operator fun div(other: UShort): UShort = (this.toInt() / other.toInt()).toUShort()

//    /** Divides this value by the other value. */
//    public operator fun div(other: Long): Long

//    /** Calculates the remainder of dividing this value by the other value. */
//    public operator fun rem(other: Byte): Int
//
//    /** Calculates the remainder of dividing this value by the other value. */
//    public operator fun rem(other: Short): Int

    /** Calculates the remainder of dividing this value by the other value. */
    public operator fun rem(other: UShort): UShort = (this.toInt() % other.toInt()).toUShort()

//    /** Calculates the remainder of dividing this value by the other value. */
//    public operator fun rem(other: Long): Long

    /** Increments this value. */
    public operator fun inc(): UShort = UShort((data + 1).toShort())

    /** Decrements this value. */
    public operator fun dec(): UShort = UShort((data - 1).toShort())

    /** Returns this value. */
    public operator fun unaryPlus(): UShort = this

    /** Returns the negative of this value. */
    public operator fun unaryMinus(): Int = this.toInt().unaryMinus()

//    /** Creates a range from this value to the specified [other] value. */
//    public operator fun rangeTo(other: Byte): IntRange
//
//    /** Creates a range from this value to the specified [other] value. */
//    public operator fun rangeTo(other: Short): IntRange
//
//    /** Creates a range from this value to the specified [other] value. */
//    public operator fun rangeTo(other: Int): IntRange
//
//    /** Creates a range from this value to the specified [other] value. */
//    public operator fun rangeTo(other: Long): LongRange

    /** Shifts this value left by the [bitCount] number of bits. */
    public infix fun shl(bitCount: Int): UShort = UShort((data.toInt() shl bitCount).toShort())

    /** Shifts this value right by the [bitCount] number of bits, filling the leftmost bits with zeroes. */
    public infix fun shr(bitCount: Int): UShort = UShort((data.toInt() ushr bitCount).toShort())

    /** Performs a bitwise AND operation between the two values. */
    public infix fun and(other: UShort): UShort = UShort(this.data and other.data)

    /** Performs a bitwise OR operation between the two values. */
    public infix fun or(other: UShort): UShort = UShort(this.data or other.data)

    /** Performs a bitwise XOR operation between the two values. */
    public infix fun xor(other: UShort): UShort = UShort(this.data xor other.data)

    /** Inverts the bits in this value. */
    public fun inv(): UShort = UShort(data.inv())

    public fun toByte(): Byte = data.toByte()
    public fun toShort(): Short = data
    public fun toInt(): Int = data.toInt() and ((1 shl 16) - 1)
    public fun toLong(): Long = data.toLong() and ((1 shl 16) - 1)

    //    public fun toUByte(): UByte
    public fun toUShort(): UShort = this
    public fun toUInt(): UInt = this.toInt().toUInt()
    public fun toULong(): ULong = this.toLong().toULong()

}

fun Byte.toUShort(): UShort = (this.toInt() and 0xFF).toUShort()
fun Short.toUShort(): UShort = UShort(this)
fun Int.toUShort(): UShort = this.toShort().toUShort()
fun Long.toUShort(): UShort = this.toShort().toUShort()
