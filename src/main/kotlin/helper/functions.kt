import cleaner.AUDIO_FORMATS
import java.io.File

fun getAudioFiles(path: String): List<File>? =
    File(path).listFiles()?.filter { it.isFile && it.extension in AUDIO_FORMATS }

fun getConfirmation(message: String): Boolean {
    print("${message.trim()} ")
    val r = readLine()
    return when (r!!.lowercase()) {
        in listOf("yes", "y") -> true
        in listOf("no", "n") -> false
        else -> getConfirmation(message)
    }
}