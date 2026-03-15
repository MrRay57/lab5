package app.commands;

import app.managers.CollectionManager;
import app.io.ApplicationInputManager;
import app.io.MovieBuilder;
import app.models.Movie;

public class UpdateCommand implements Command {
    private final CollectionManager collectionManager;
    private final ApplicationInputManager inputManager;

    public UpdateCommand(CollectionManager collectionManager, ApplicationInputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public String getName() { return "update"; }

    @Override
    public String getDescription() { return "обновить значение элемента коллекции, id которого равен заданному (формат: update <id>)"; }

    @Override
    public void execute(String argument) {
        if (argument.isEmpty()) {
            System.err.println("Ошибка: необходимо указать ID (число).");
            return;
        }
        try {
            Long id = Long.parseLong(argument);
            boolean interactive = !inputManager.isScripting();
            Movie updatedMovie = new MovieBuilder(inputManager, interactive).build();

            if (collectionManager.update(id, updatedMovie)) {
                System.out.println("Элемент с ID " + id + " успешно обновлен.");
            } else {
                System.err.println("Ошибка: элемент с ID " + id + " не найден или данные невалидны.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: ID должен быть целым числом!");
        } catch (Exception e) {
            System.err.println("Ошибка при обновлении элемента: " + e.getMessage());
        }
    }
}