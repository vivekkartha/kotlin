// EXPECTED_REACHABLE_NODES: 1093

external interface Foo {
    var externalProperty: String?
        get() = definedExternally
        set(it) = definedExternally
}

interface Bar : Foo

class DDD : Bar {

}

// Note: since nashorn gets crashed if bug exists there is no need for specific body
fun box() = "OK"