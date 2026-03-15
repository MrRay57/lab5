package app.managers;

import app.commands.Command;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Менеджер для регистрации и диспетчеризации команд.
 */
public class CommandManager {
    private final Map<String, Command> commands = new LinkedHashMap<>();

    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public void executeCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        String[] tokens = input.trim().split("\\s+", 2);
        String commandName = tokens[0].toLowerCase();
        String argument = tokens.length > 1 ? tokens[1] : "";

        Command command = commands.get(commandName);
        if (command == null) {
            System.err.println("Неизвестная команда: '" + commandName + "'. Введите 'help' для получения списка команд.");
        } else {
            try {
                command.execute(argument);
            } catch (Exception e) {
                System.err.println("Ошибка при выполнении команды: " + e.getMessage());
            }
        }
    }
}