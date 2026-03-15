package app.commands;

/**
 * Базовый интерфейс для всех команд приложения.
 */
public interface Command {
    String getName();
    String getDescription();
    void execute(String argument);
}