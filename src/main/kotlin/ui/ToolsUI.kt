package ui

import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.*

private val INSETS = Insets(8, 8, 8, 8)

class ToolsUI : JFrame() {
    private var titleC = JLabel("Choose tool")
    private var buttonClean = JButton("clean")

    init {
        rootPane.contentPane = JPanel(GridBagLayout()).apply {
            title = "Music Tools"
            defaultCloseOperation = EXIT_ON_CLOSE
            add(titleC, GridBagConstraints().apply {
                insets = INSETS
            })
            addWide(JPanel().apply {
                add(buttonClean)
                add(JButton("hi"))
                add(JButton("ho"))
            })
            addWideSeparator()

            addListeners()
        }
    }

    private fun addListeners() {
        buttonClean.addActionListener {
            CleanUI().apply {
                pack()
                setLocationRelativeTo(null)
                isVisible = true
            }
            dispose()
        }
    }
}

fun JPanel.addWide(component: JComponent, constraints: GridBagConstraints.() -> Unit = {}) {
    add(component, GridBagConstraints().apply {
        gridx = 0
        gridwidth = 2
//        insets = INSETS
        constraints()
    })
}

fun JPanel.addWideSeparator() {
    addWide(JSeparator()) {
        fill = GridBagConstraints.HORIZONTAL
    }
}