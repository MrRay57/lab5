package app.commands;

import app.managers.CollectionManager;

public class CountLessLengthCommand implements Command {
    private final CollectionManager collectionManager;

    public CountLessLengthCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() { return "count_less_than_length"; }

    @Override
    public String getDescription() { return "вывести количество элементов, значение поля length которых меньше заданного"; }

    @Override
    public void execute(String argument) {
        if (argument.isEmpty()) {
            System.err.println("Ошибка: необходимо указать значение length (целое число).");
            return;
        }
        try {
            int targetLength = Integer.parseInt(argument);
            long count = collectionManager.countLessThanLength(targetLength);
            System.out.println("Количество фильмов с продолжительностью меньше " + targetLength + ": " + count);
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: аргумент должен быть целым числом.");
        }
    }
}