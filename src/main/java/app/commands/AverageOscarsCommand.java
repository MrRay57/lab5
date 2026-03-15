package app.commands;

import app.managers.CollectionManager;

public class AverageOscarsCommand implements Command {
    private final CollectionManager collectionManager;

    public AverageOscarsCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() { return "average_of_oscars_count"; }

    @Override
    public String getDescription() { return "вывести среднее значение поля oscarsCount для всех элементов коллекции"; }

    @Override
    public void execute(String argument) {
        double average = collectionManager.getAverageOscarsCount();
        if (average == 0) {
            System.out.println("Коллекция пуста или сумма равна нулю. Среднее значение: 0");
        } else {
            System.out.printf("Среднее количество Оскаров: %.2f%n", average);
        }
    }
}