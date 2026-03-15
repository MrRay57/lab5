package app.commands;

import app.managers.CommandManager;
import java.util.Map;

public class HelpCommand implements Command {
    private final CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public String getName() { return "help"; }

    @Override
    public String getDescription() { return "вывести справку по доступным командам"; }

    @Override
    public void execute(String argument) {
        if (!argument.isEmpty()) {
            System.err.println("Команда help не принимает аргументов.");
            return;
        }
        System.out.println("--- Список доступных команд ---");
        for (Map.Entry<String, Command> entry : commandManager.getCommands().entrySet()) {
            System.out.printf("%-30s : %s%n", entry.getKey(), entry.getValue().getDescription());
        }
    }
}