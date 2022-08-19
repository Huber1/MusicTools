package cleaner

import getAudioFiles
import getConfirmation
import helper.Arguments
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.audio.mp3.MP3File
import java.io.File

val AUDIO_FORMATS = listOf("mp3", "mp4", "m4a", "m4p", "ogg", "flac", "wav", "dsf", "wma")

fun clean() {
    val args = Arguments
    val directory = System.getProperty("user.dir")

    val audios = getAudioFiles(directory)

    val toRemove = ArrayList<File>()

    audios?.forEach {
        val mp3 = AudioFileIO.read(it) as MP3File
        if (mp3.mP3AudioHeader.trackLength < 40)
            toRemove.add(mp3.file)
    }

    if (toRemove.size >= 1 ) {
        val remove = if (!args.opts.containsKey("f")) getConfirmation("Delete ${toRemove.size} songs? [Y/N]") else true

        if (remove) toRemove.forEach {
            it.delete()
        }
    } else println("Nothing to remove")
}


// Underground länge: 270s
