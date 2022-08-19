package ui

import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JPanel

private val INSETS = Insets(8, 8, 8, 8)

class CleanUI : JFrame() {
    private val buttonChooser = JButton("Choose Folder")

    init {
        rootPane.contentPane = JPanel(GridBagLayout()).apply {
            title = "Music Tools"
            defaultCloseOperation = EXIT_ON_CLOSE
            add(buttonChooser)
        }

        addListeners()
    }

    private fun addListeners() {
        buttonChooser.addActionListener {
            val fileChooser = JFileChooser()
            val response = fileChooser.showDialog(this, "open")
            println(fileChooser)
        }
    }
}
