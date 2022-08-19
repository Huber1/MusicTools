import com.formdev.flatlaf.FlatLightLaf
import helper.Arguments
import ui.ToolsUI

fun main(args: Array<String>) {
    parseArguments(args)
}

fun parseArguments(args: Array<String>) {
    if (args.isEmpty()) {
        FlatLightLaf.setup()
        ToolsUI().apply {
            pack()
            setLocationRelativeTo(null)
            isVisible = true
        }
    } else {
        val arguments = Arguments

        var i = 0
        while (i < args.size) {
            when (args[i][0]) {
                '-' -> {
                    require(args[i].length >= 2) { "not a valid argument: " + args[i] }
                    if (args[i][1] == '-') { // --opt
                        require(args[i].length >= 3) { "not a valid argument: " + args[i] }
                        TODO("no double args atm")
                    } else {
                        require(args[i].length - 1 != i) { "Expected argument after: " + args[i] }
                        arguments.opts[args[i]] = args[i + 1]
                        i++
                    }
                }

                else -> arguments.args.add(args[i])
            }
            i++
        }
        runCommand(arguments.args.elementAt(0))
    }

}