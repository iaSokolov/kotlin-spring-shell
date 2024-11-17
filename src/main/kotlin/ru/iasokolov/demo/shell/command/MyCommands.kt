package ru.iasokolov.demo.shell.command

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
class MyCommands {
    @ShellMethod(
        key = ["hello-world"],
    )
    fun hello(@ShellOption name: String) = "Hello, $name"
}