package app.commands;

import app.managers.CollectionManager;
import app.models.Movie;
import java.util.Map;

public class ShowCommand implements Command {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() { return "show"; }

    @Override
    public String getDescription() { return "вывести все элементы коллекции в строковом представлении"; }

    @Override
    public void execute(String argument) {
        if (!argument.isEmpty()) {
            System.err.println("Команда show не принимает аргументов.");
            return;
        }
        Map<Long, Movie> collection = collectionManager.getCollection();
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        System.out.println("--- Элементы коллекции ---");
        for (Map.Entry<Long, Movie> entry : collection.entrySet()) {
            System.out.println("Ключ: " + entry.getKey() + " -> " + entry.getValue().toString());
        }
    }
}