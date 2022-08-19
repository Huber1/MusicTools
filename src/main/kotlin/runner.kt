import cleaner.clean

fun runCommand(program: String) {
    when (program) {
        "clean" -> clean()
    }
}