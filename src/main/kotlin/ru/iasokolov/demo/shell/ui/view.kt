package ru.iasokolov.demo.shell.ui

import org.jline.terminal.Terminal
import org.springframework.shell.component.message.ShellMessageBuilder
import org.springframework.shell.component.view.TerminalUI
import org.springframework.shell.component.view.control.BoxView
import org.springframework.shell.component.view.event.KeyEvent
import org.springframework.shell.component.view.screen.Screen
import org.springframework.shell.geom.Rectangle
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import java.util.function.BiFunction

@ShellComponent
class view(
    private val terminal: Terminal
) {
    @ShellMethod(
        key = ["ui"],
    )
    fun sample() {
        val ui = TerminalUI(terminal)
        val view = BoxView()
        ui.configure(view)
        ui.setRoot(view, true)

        view.drawFunction =
            BiFunction { screen: Screen, rect: Rectangle? ->
                screen.writerBuilder().build().text("hi", 0, 0)

                rect
            }

        val eventLoop = ui.eventLoop
        eventLoop.keyEvents()
            .subscribe { event: KeyEvent ->
                if (event.plainKey == KeyEvent.Key.q && event.hasCtrl()) {
                    eventLoop.dispatch(ShellMessageBuilder.ofInterrupt())
                }
            }
        ui.run()
    }
}