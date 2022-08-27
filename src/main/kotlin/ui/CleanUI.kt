package ui

import cleaner.whichToDelete
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.io.File
import javax.swing.*
import javax.swing.table.DefaultTableModel

private val INSETS = Insets(8, 8, 8, 8)
private val COLUMNS = arrayOf("Name", "Length")

class CleanUI : JFrame() {
    private val buttonChooser = JButton("Choose Folder")
    private val scrollPane = JScrollPane().apply {
        preferredSize = Dimension(400, 300)
    }
    private val songsModel = DefaultTableModel(COLUMNS, 0)
    private val buttonStart = JButton("Delete files").apply { isEnabled = false }

    private var choosenFolder: File? = null

    init {
        rootPane.contentPane = JPanel(GridBagLayout()).apply {
            title = "Music Tools"
            defaultCloseOperation = EXIT_ON_CLOSE
            add(buttonChooser, GridBagConstraints().apply {
                gridy = 0
                insets = INSETS
            })
            add(scrollPane, GridBagConstraints().apply {
                gridy = 1
                insets = INSETS
            })
            add(buttonStart, GridBagConstraints().apply {
                gridy = 2
                insets = INSETS
            })
        }

        addListeners()
    }

    private fun addListeners() {
        buttonChooser.addActionListener {
            val fileChooser = JFileChooser().apply {
                fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
            }
            val response = fileChooser.showDialog(this, "open")
            val toDelete = whichToDelete(fileChooser.selectedFile.path)
            updateList(listOf())
        }
    }

    private fun updateList(songss: List<Song>) {
        val songs = listOf(Song("name1", 5), Song("name2", 20))
        songsModel.setDataVector(songs.map {
            arrayOf(it.name, it.length)
        }.toTypedArray(), COLUMNS)
    }
}

data class Song(val name: String, val length: Int)