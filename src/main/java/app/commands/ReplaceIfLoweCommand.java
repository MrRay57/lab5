package app.commands;

import app.managers.CollectionManager;
import app.io.ApplicationInputManager;
import app.io.MovieBuilder;
import app.models.Movie;

public class ReplaceIfLoweCommand implements Command {
    private final CollectionManager collectionManager;
    private final ApplicationInputManager inputManager;

    public ReplaceIfLoweCommand(CollectionManager collectionManager, ApplicationInputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public String getName() { return "replace_if_lowe"; }

    @Override
    public String getDescription() { return "заменить значение по ключу, если новое меньше старого"; }

    @Override
    public void execute(String argument) {
        if (argument.isEmpty()) {
            System.err.println("Ошибка: необходимо указать ключ (число).");
            return;
        }
        try {
            Long key = Long.parseLong(argument);
            if (!collectionManager.getCollection().containsKey(key)) {
                System.err.println("Ошибка: элемент с таким ключом не найден.");
                return;
            }

            System.out.println("Создание нового элемента для сравнения...");
            boolean interactive = !inputManager.isScripting();
            Movie newMovie = new MovieBuilder(inputManager, interactive).build();

            if (collectionManager.replaceIfLowe(key, newMovie)) {
                System.out.println("Значение по ключу " + key + " успешно заменено (новое меньше старого).");
            } else {
                System.out.println("Замена не выполнена: новое значение не меньше старого.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: ключ должен быть целым числом.");
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении команды: " + e.getMessage());
        }
    }
}