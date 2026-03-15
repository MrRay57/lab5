package app.commands;

import app.managers.CollectionManager;

public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() { return "clear"; }

    @Override
    public String getDescription() { return "очистить коллекцию"; }

    @Override
    public void execute(String argument) {
        if (!argument.isEmpty()) {
            System.err.println("Команда clear не принимает аргументов.");
            return;
        }
        collectionManager.clear();
        System.out.println("Коллекция успешно очищена.");
    }
}