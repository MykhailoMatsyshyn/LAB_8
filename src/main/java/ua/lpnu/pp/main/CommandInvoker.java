package ua.lpnu.pp.main;

import ua.lpnu.pp.commands.Command;

public class CommandInvoker {
    public void executeCommand(Command command) {
        command.execute();
    }
}
