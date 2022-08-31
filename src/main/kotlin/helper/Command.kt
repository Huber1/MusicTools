package helper

import org.apache.commons.cli.CommandLine

interface Command {
    fun run(opts: CommandLine?)
}