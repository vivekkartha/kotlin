/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package kotlin

inline class UInt internal constructor(private val data: Int) : Comparable<UInt> {

    companion object {
        /**
         * A constant holding the minimum value an instance of Int can have.
         */
        public /*const*/ val MIN_VALUE: UInt = UInt(0)

        /**
         * A constant holding the maximum value an instance of Int can have.
         */
        public /*const*/ val MAX_VALUE: UInt = UInt(-1)
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
    public operator fun compareTo(other: UShort): Int = this.toLong().compareTo(other.toLong())

    /**
     * Compares this value with the specified value for order.
     * Returns zero if this value is equal to the specified other value, a negative number if it's less than other,
     * or a positive number if it's greater than other.
     */
    public operator fun compareTo(other: ULong): Int = this.toULong().compareTo(other)

    override fun compareTo(other: UInt): Int = this.toLong().compareTo(other.toLong())

    /** Adds the other value to this value. */
//    public operator fun plus(other: UByte): UInt

    /** Adds the other value to this value. */
//    public operator fun plus(other: UShort): UInt

    /** Adds the other value to this value. */
    public operator fun plus(other: UInt): UInt = UInt(this.data + other.data)

    /** Adds the other value to this value. */
    public operator fun plus(other: ULong): ULong = toULong() + other

//    /** Subtracts the other value from this value. */
//    public operator fun minus(other: UByte): UInt
//
//    /** Subtracts the other value from this value. */
//    public operator fun minus(other: UShort): UInt

    /** Subtracts the other value from this value. */
    public operator fun minus(other: UInt): UInt = UInt(this.data - other.data)

//    /** Subtracts the other value from this value. */
//    public operator fun minus(other: ULong): ULong
//
//    /** Multiplies this value by the other value. */
//    public operator fun times(other: UByte): UInt
//
//    /** Multiplies this value by the other value. */
//    public operator fun times(other: UShort): UInt

    /** Multiplies this value by the other value. */
    public operator fun times(other: UInt): UInt = (this.toLong() * other.toLong()).toUInt()

//    /** Multiplies this value by the other value. */
//    public operator fun times(other: Long): Long

//    /** Divides this value by the other value. */
//    public operator fun div(other: Byte): Int
//
//    /** Divides this value by the other value. */
//    public operator fun div(other: Short): Int

    /** Divides this value by the other value. */
    public operator fun div(other: UInt): UInt = (this.toLong() / other.toLong()).toUInt()

//    /** Divides this value by the other value. */
//    public operator fun div(other: Long): Long

//    /** Calculates the remainder of dividing this value by the other value. */
//    public operator fun rem(other: Byte): Int
//
//    /** Calculates the remainder of dividing this value by the other value. */
//    public operator fun rem(other: Short): Int

    /** Calculates the remainder of dividing this value by the other value. */
    public operator fun rem(other: UInt): UInt = (this.toLong() % other.toLong()).toUInt()

//    /** Calculates the remainder of dividing this value by the other value. */
//    public operator fun rem(other: Long): Long

    /** Increments this value. */
    public operator fun inc(): UInt = UInt(data + 1)

    /** Decrements this value. */
    public operator fun dec(): UInt = UInt(data - 1)

    /** Returns this value. */
    public operator fun unaryPlus(): UInt = this

    /** Returns the negative of this value. */
    public operator fun unaryMinus(): Long = this.toLong().unaryMinus()

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
    public infix fun shl(bitCount: Int): UInt = UInt(data shl bitCount)

    /** Shifts this value right by the [bitCount] number of bits, filling the leftmost bits with zeroes. */
    public infix fun shr(bitCount: Int): UInt = UInt(data ushr bitCount)

    /** Performs a bitwise AND operation between the two values. */
    public infix fun and(other: UInt): UInt = UInt(this.data and other.data)

    /** Performs a bitwise OR operation between the two values. */
    public infix fun or(other: UInt): UInt = UInt(this.data or other.data)

    /** Performs a bitwise XOR operation between the two values. */
    public infix fun xor(other: UInt): UInt = UInt(this.data xor other.data)

    /** Inverts the bits in this value. */
    public fun inv(): UInt = UInt(data.inv())

    public fun toByte(): Byte = data.toByte()
    public fun toShort(): Short = data.toShort()
    public fun toInt(): Int = data
    public fun toLong(): Long = data.toLong() and ((1L shl 32) - 1)

//    public fun toUByte(): UByte
    public fun toUShort(): UShort = data.toUShort()
    public fun toUInt(): UInt = this
    public fun toULong(): ULong = data.toULong()

}


fun Byte.toUInt(): UInt = (this.toInt() and 0xFF).toUInt()
fun Short.toUInt(): UInt = (this.toInt() and 0xFFFF).toUInt()
fun Int.toUInt(): UInt = UInt(this)
fun Long.toUInt(): UInt = this.toInt().toUInt()

fun usage() {
    val u1 = UInt(1)
    val u2 = UInt(2)

    val u3 = u1 + u2
    val u4 = u1 - u2
    val u5 = u1 * u2
    val u6 = u1 / u2
    val u7 = u1 % u2

    val un = if (u7 > u1) u1 else null
    val an = u1 as Any

    un?.toLong()

}