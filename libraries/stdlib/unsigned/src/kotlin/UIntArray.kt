/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package kotlin

import kotlin.coroutines.experimental.buildIterator

inline class UIntArray internal constructor(private val storage: IntArray) : Collection<UInt> {

    /** Returns the array element at the given [index]. This method can be called using the index operator. */
    public operator fun get(index: Int): UInt = storage[index].toUInt()

    /** Sets the element at the given [index] to the given [value]. This method can be called using the index operator. */
    public operator fun set(index: Int, value: UInt): Unit {
        storage[index] = value.toInt()
    }

    /** Returns the number of elements in the array. */
    public override val size: Int get() = storage.size

    /** Creates an iterator over the elements of the array. */
    public override operator fun iterator(): Iterator<UInt> = buildIterator {
        for (e in storage) yield(e.toUInt())
    }

    override fun contains(element: UInt): Boolean = storage.contains(element.toInt())

    override fun containsAll(elements: Collection<UInt>): Boolean = elements.all { storage.contains(it.toInt()) }

    override fun isEmpty(): Boolean = this.storage.size == 0
}

public /*inline*/ fun UIntArray(size: Int, init: (Int) -> UInt): UIntArray {
    return UIntArray(IntArray(size) { index -> init(index).toInt() })
}

public fun uintArrayOf(vararg elements: UInt): UIntArray {
    return UIntArray(elements.size) { index -> elements[index] }
}


fun usageArray() {
    val array = uintArrayOf(UInt(1), UInt(2))
    array.size
}