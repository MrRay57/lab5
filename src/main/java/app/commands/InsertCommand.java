package app.commands;

import app.managers.CollectionManager;
import app.io.ApplicationInputManager;
import app.io.MovieBuilder;
import app.models.Movie;

public class InsertCommand implements Command {
    private final CollectionManager collectionManager;
    private final ApplicationInputManager inputManager;

    public InsertCommand(CollectionManager collectionManager, ApplicationInputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public String getName() { return "insert"; }

    @Override
    public String getDescription() { return "добавить новый элемент с заданным ключом (формат: insert <key>)"; }

    @Override
    public void execute(String argument) {
        if (argument.isEmpty()) {
            System.err.println("Ошибка: необходимо указать ключ (число).");
            return;
        }
        try {
            Long key = Long.parseLong(argument);
            boolean interactive = !inputManager.isScripting();
            Movie newMovie = new MovieBuilder(inputManager, interactive).build();

            if (collectionManager.insert(key, newMovie)) {
                System.out.println("Элемент успешно добавлен.");
            } else {
                System.err.println("Ошибка: элемент с таким ключом уже существует или данные невалидны!");
            }
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: ключ должен быть целым числом!");
        } catch (Exception e) {
            System.err.println("Ошибка при создании элемента: " + e.getMessage());
        }
    }
}