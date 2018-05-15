// !LANGUAGE: +MultiPlatformProjects
// EXPECTED_REACHABLE_NODES: 1102
// IGNORE_BACKEND: JS_IR

// FILE: lib.kt
expect interface I {
    fun f(p: String = "OK"): String
}

expect interface U {
    fun f(p: String = "OK"): String
}

// FILE: main.kt
actual interface I {
    actual fun f(p: String): String
}

actual interface U {
    actual fun f(p: String): String

//    fun g(p: String = "OK") = p
}

interface E: U {
    override fun f(p: String) = p
}

class UU: E {

}

class Impl : I {
    override fun f(p: String) = p
}

fun box() = UU().f()
