package app.commands;

import app.managers.CollectionManager;
import app.io.ApplicationInputManager;
import app.io.MovieBuilder;
import app.models.Movie;

public class RemoveLowerCommand implements Command {
    private final CollectionManager collectionManager;
    private final ApplicationInputManager inputManager;

    public RemoveLowerCommand(CollectionManager collectionManager, ApplicationInputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public String getName() { return "remove_lower"; }

    @Override
    public String getDescription() { return "удалить из коллекции все элементы, меньшие, чем заданный"; }

    @Override
    public void execute(String argument) {
        try {
            System.out.println("Создание эталонного элемента для сравнения...");
            boolean interactive = !inputManager.isScripting();
            Movie referenceMovie = new MovieBuilder(inputManager, interactive).build();

            int removedCount = collectionManager.removeLower(referenceMovie);
            System.out.println("Удалено элементов: " + removedCount);
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении команды: " + e.getMessage());
        }
    }
}