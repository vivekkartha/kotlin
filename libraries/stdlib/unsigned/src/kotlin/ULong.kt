/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package kotlin

inline class ULong internal constructor(private val data: Long) : Comparable<ULong> {

    companion object {
        /**
         * A constant holding the minimum value an instance of Int can have.
         */
        public /*const*/ val MIN_VALUE: ULong = ULong(0)

        /**
         * A constant holding the maximum value an instance of Int can have.
         */
        public /*const*/ val MAX_VALUE: ULong = ULong(-1)
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
//    public operator fun compareTo(other: UShort): Int = this.toLong().compareTo(other.toLong())

//    /**
//     * Compares this value with the specified value for order.
//     * Returns zero if this value is equal to the specified other value, a negative number if it's less than other,
//     * or a positive number if it's greater than other.
//     */
//    public operator fun compareTo(other: ULong): Int

    override fun compareTo(other: ULong): Int = TODO()

    /** Adds the other value to this value. */
//    public operator fun plus(other: UByte): UInt

    /** Adds the other value to this value. */
    public operator fun plus(other: UShort): ULong = this + other.toULong()

    /** Adds the other value to this value. */
    public operator fun plus(other: UInt): ULong = this + other.toULong()

    /** Adds the other value to this value. */
    public operator fun plus(other: ULong): ULong = ULong(this.data + other.data)

//    /** Subtracts the other value from this value. */
//    public operator fun minus(other: UByte): UInt
//
    /** Subtracts the other value from this value. */
    public operator fun minus(other: UShort): ULong = this - other.toULong()

    /** Subtracts the other value from this value. */
    public operator fun minus(other: UInt): ULong = this - other.toULong()

    /** Subtracts the other value from this value. */
    public operator fun minus(other: ULong): ULong = ULong(this.data - other.data)
//
//    /** Multiplies this value by the other value. */
//    public operator fun times(other: UByte): UInt
//
//    /** Multiplies this value by the other value. */
//    public operator fun times(other: UShort): UInt

    /** Multiplies this value by the other value. */
    public operator fun times(other: UInt): ULong = this * other.toULong()

    /** Multiplies this value by the other value. */
    public operator fun times(other: ULong): ULong = (this.data * other.data).toULong()

//    /** Divides this value by the other value. */
//    public operator fun div(other: Byte): Int
//
//    /** Divides this value by the other value. */
//    public operator fun div(other: Short): Int

    /** Divides this value by the other value. */
    public operator fun div(other: UInt): UInt = TODO() // (this.toLong() / other.toLong()).toUInt()

    /** Divides this value by the other value. */
    public operator fun div(other: ULong): ULong = TODO()

//    /** Calculates the remainder of dividing this value by the other value. */
//    public operator fun rem(other: Byte): Int
//
//    /** Calculates the remainder of dividing this value by the other value. */
//    public operator fun rem(other: Short): Int

    /** Calculates the remainder of dividing this value by the other value. */
    public operator fun rem(other: UInt): UInt = TODO() // (this.toLong() % other.toLong()).toUInt()

    /** Calculates the remainder of dividing this value by the other value. */
    public operator fun rem(other: ULong): ULong = TODO()

    /** Increments this value. */
    public operator fun inc(): ULong = ULong(data + 1)

    /** Decrements this value. */
    public operator fun dec(): ULong = ULong(data - 1)

    /** Returns this value. */
    public operator fun unaryPlus(): ULong = this

//    /** Returns the negative of this value. */
//    public operator fun unaryMinus(): Long = this.toLong().unaryMinus()

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
    public infix fun shl(bitCount: Int): ULong = ULong(data shl bitCount)

    /** Shifts this value right by the [bitCount] number of bits, filling the leftmost bits with zeroes. */
    public infix fun shr(bitCount: Int): ULong = ULong(data ushr bitCount)

    /** Performs a bitwise AND operation between the two values. */
    public infix fun and(other: ULong): ULong = ULong(this.data and other.data)

    /** Performs a bitwise OR operation between the two values. */
    public infix fun or(other: ULong): ULong = ULong(this.data or other.data)

    /** Performs a bitwise XOR operation between the two values. */
    public infix fun xor(other: ULong): ULong = ULong(this.data xor other.data)

    /** Inverts the bits in this value. */
    public fun inv(): ULong = ULong(data.inv())

    public fun toByte(): Byte = data.toByte()
    public fun toShort(): Short = data.toShort()
    public fun toInt(): Int = data.toInt()
    public fun toLong(): Long = data

    //    public fun toUByte(): UByte
    public fun toUShort(): UShort = data.toUShort()
    public fun toUInt(): UInt = data.toUInt()
    public fun toULong(): ULong = this

}


fun Byte.toULong(): ULong = (this.toLong() and 0xFF).toULong()
fun Short.toULong(): ULong = (this.toLong() and 0xFFFF).toULong()
fun Int.toULong(): ULong = (this.toLong() and 0xFFFF_FFFF).toULong()
fun Long.toULong(): ULong = ULong(this)