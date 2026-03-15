package app.commands;

import app.managers.CollectionManager;

public class RemoveKeyCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveKeyCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() { return "remove_key"; }

    @Override
    public String getDescription() { return "удалить элемент из коллекции по его ключу (формат: remove_key <key>)"; }

    @Override
    public void execute(String argument) {
        if (argument.isEmpty()) {
            System.err.println("Ошибка: необходимо указать ключ (число).");
            return;
        }
        try {
            Long key = Long.parseLong(argument);
            if (collectionManager.removeKey(key)) {
                System.out.println("Элемент с ключом " + key + " успешно удален.");
            } else {
                System.err.println("Ошибка: элемент с таким ключом не найден.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: ключ должен быть целым числом!");
        }
    }
}