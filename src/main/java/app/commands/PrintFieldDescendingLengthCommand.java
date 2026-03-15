package app.commands;

import app.managers.CollectionManager;
import java.util.List;

public class PrintFieldDescendingLengthCommand implements Command {
    private final CollectionManager collectionManager;

    public PrintFieldDescendingLengthCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() { return "print_field_descending_length"; }

    @Override
    public String getDescription() { return "вывести значения поля length всех элементов в порядке убывания"; }

    @Override
    public void execute(String argument) {
        List<Integer> lengths = collectionManager.getDescendingLengths();
        if (lengths.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        System.out.println("Значения поля length в порядке убывания:");
        for (Integer length : lengths) {
            System.out.println(length);
        }
    }
}