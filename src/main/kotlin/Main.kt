import com.formdev.flatlaf.FlatLightLaf
import org.apache.commons.cli.*
import ui.ToolsUI

fun main(args: Array<String>) {
    parseArguments(args.toMutableList())
}

fun parseArguments(args: MutableList<String>) {
    if (args.isEmpty()) {
        FlatLightLaf.setup()
        ToolsUI().apply {
            pack()
            setLocationRelativeTo(null)
            isVisible = true
        }
    } else {
        val options = Options()
        options.addOption("help", "print this message")
        options.addOption(Option("r", "recursive", false, "recursive"))
        options.addOption(Option("f", "force", false, "Don't ask for confirmation"))
        options.addOption(Option("d", "directory", true, "Specify directory to use"))
        options.addOption(Option("maxl", "maxlength", true, "Specify maximum length of song in seconds"))

        // first element: command => remove
        val command: String = args.elementAt(0)
        args.removeAt(0)
        var cmd: CommandLine? = null

        try {
            val parser: CommandLineParser = DefaultParser()
            cmd = parser.parse(options, args.toTypedArray())
        } catch (e: ParseException) {
            displayHelp(options)
        }

        runCommand(command, options, cmd)
    }

}