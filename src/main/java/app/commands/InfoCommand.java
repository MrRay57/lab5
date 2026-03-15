package app.commands;

import app.managers.CollectionManager;

public class InfoCommand implements Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() { return "info"; }

    @Override
    public String getDescription() { return "вывести информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)"; }

    @Override
    public void execute(String argument) {
        if (!argument.isEmpty()) {
            System.err.println("Команда info не принимает аргументов.");
            return;
        }
        System.out.println("--- Информация о коллекции ---");
        System.out.println("Тип коллекции: " + collectionManager.getCollection().getClass().getName());
        System.out.println("Дата инициализации: " + collectionManager.getInitializationDate());
        System.out.println("Количество элементов: " + collectionManager.getCollection().size());
    }
}