package cleaner

import getAudioFiles
import getConfirmation
import helper.Arguments
import org.jaudiotagger.audio.AudioFileIO
import java.io.File

val AUDIO_FORMATS = listOf("mp3", "mp4", "m4a", "m4p", "ogg", "flac", "wav", "dsf", "wma")

fun clean() {
    val args = Arguments
    val directory = System.getProperty("user.dir")

    val toRemove = whichToDelete(directory)

    if (toRemove.isNotEmpty()) {
        val remove = if (!args.opts.containsKey("f")) getConfirmation("Delete ${toRemove.size} songs? [Y/N]") else true

        if (remove) toRemove.forEach {
            it.delete()
        }
    } else println("Nothing to remove")
}

fun whichToDelete(directory: String): List<File> {
    val audios = getAudioFiles(directory)
    val toRemove = mutableListOf<File>()
    audios?.forEach {
        val file = AudioFileIO.read(it)
        if (file.audioHeader.trackLength < 40) toRemove.add(file.file)
    }
    return toRemove
}

// Underground länge: 270s
