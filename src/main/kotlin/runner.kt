import cleaner.Cleaner
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Options

fun runCommand(program: String, options: Options, cmd: CommandLine?) {
    when (program) {
        "help" -> displayHelp(options)
        "clean" -> Cleaner().run(cmd)
        else -> displayHelp(options)
    }
}

// get Commands <command> to <description>
fun commands() = mapOf<String, String>(
    "help" to "displays help",
    "clean" to "Remove tracks too short"
)