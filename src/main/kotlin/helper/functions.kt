import helper.AUDIO_FORMATS
import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.Options
import java.io.File

fun getAudioFiles(path: String): List<File>? =
    File(path).listFiles()?.filter { it.isFile && it.extension in AUDIO_FORMATS }

fun getConfirmation(message: String): Boolean {
    print("${message.trim()} [Y/N] ")
    val r = readLine()
    return when (r!!.lowercase()) {
        in listOf("yes", "y") -> true
        in listOf("no", "n") -> false
        else -> getConfirmation(message)
    }
}

fun displayHelp(options: Options) {
    val formatter = HelpFormatter()
    println("usage MusicTools:")
    println("musictools [command]")
    for (command in commands()) println("${command.key} - ${command.value}")
    println()
    formatter.printHelp("Options", options)
}