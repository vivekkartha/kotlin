// IGNORE_BACKEND: JS_IR
// EXPECTED_REACHABLE_NODES: 1117
// MODULE: lib
// FILE: lib.kt

object O {
    fun bar() = "OK"
}

inline fun foo() = O.bar()

// MODULE: main(lib)
// FILE: main.kt
// CHECK_CONTAINS_NO_CALLS: box except=bar

fun box() = foo()