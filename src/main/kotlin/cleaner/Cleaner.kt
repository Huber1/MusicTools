package cleaner

import getAudioFiles
import getConfirmation
import helper.Command
import org.apache.commons.cli.CommandLine
import org.jaudiotagger.audio.AudioFile
import org.jaudiotagger.audio.AudioFileIO

class Cleaner : Command {
    override fun run(opts: CommandLine?) {
        val directory = opts?.getOptionValue("d") ?: System.getProperty("user.dir")
        val toRemove = shorterThan(directory, opts?.getOptionValue("ml")?.toInt() ?: 40)
        if (toRemove.isNotEmpty()) {
            val remove = if (opts?.hasOption("f") == false) getConfirmation("Delete ${toRemove.size} songs?") else true

            if (remove) toRemove.forEach {
                it.file.delete()
            }
        } else println("Nothing to remove")
        println(opts?.hasOption("f"))
    }

    private fun shorterThan(directory: String, treshold: Int): List<AudioFile> {
        val toRemove = mutableListOf<AudioFile>()
        getAudioFiles(directory)?.forEach {
            val file = AudioFileIO.read(it)
            if (file.audioHeader.trackLength < treshold) toRemove.add(file)
        }
        return toRemove
    }
}