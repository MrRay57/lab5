package app;

import app.commands.*;
import app.managers.*;
import java.util.Scanner;

/**
 * Главный класс приложения. Содержит точку входа в программу и управляет базовой инициализацией.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Ошибка: необходимо передать имя файла в качестве аргумента командной строки!");
            System.err.println("Пример: java -jar app.jar data.json");
            System.exit(1);
        }

        String filePath = args[0];

        FileManager fileManager = new FileManager(filePath);
        DumpManager dumpManager = new DumpManager();
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();

        System.out.println("Попытка загрузки коллекции из файла: " + filePath);
        String fileContent = fileManager.read();
        if (!fileContent.isEmpty()) {
            collectionManager.setCollection(dumpManager.readCollection(fileContent));
            System.out.println("Коллекция успешно загружена. Количество элементов: " + collectionManager.getCollection().size());
        } else {
            System.out.println("Файл пуст или не прочитан. Создана новая пустая коллекция.");
        }

        System.out.println("\nДобро пожаловать в систему управления коллекцией фильмов!");
        System.out.println("Введите 'help' для просмотра списка доступных команд.");

        try (Scanner scanner = new Scanner(System.in)) {
            app.io.ApplicationInputManager appInput = new app.io.ApplicationInputManager(scanner);

            commandManager.register(new HelpCommand(commandManager));
            commandManager.register(new InfoCommand(collectionManager));
            commandManager.register(new ShowCommand(collectionManager));
            commandManager.register(new ClearCommand(collectionManager));
            commandManager.register(new ExitCommand());
            commandManager.register(new InsertCommand(collectionManager, appInput));
            commandManager.register(new UpdateCommand(collectionManager, appInput));
            commandManager.register(new RemoveKeyCommand(collectionManager));
            commandManager.register(new SaveCommand(collectionManager, fileManager, dumpManager));
            commandManager.register(new ExecuteScriptCommand(appInput));
            commandManager.register(new RemoveLowerCommand(collectionManager, appInput));
            commandManager.register(new ReplaceIfGreaterCommand(collectionManager, appInput));
            commandManager.register(new ReplaceIfLoweCommand(collectionManager, appInput));
            commandManager.register(new AverageOscarsCommand(collectionManager));
            commandManager.register(new CountLessLengthCommand(collectionManager));
            commandManager.register(new PrintFieldDescendingLengthCommand(collectionManager));

            while (true) {
                if (!appInput.isScripting()) {
                    System.out.print("\n> ");
                }

                String input = appInput.readLine();

                if (input == null) {
                    if (!appInput.isScripting()) {
                        System.out.println("\nОбнаружен конец ввода. Завершение работы...");
                        break;
                    }
                    continue;
                }

                if (!input.isEmpty()) {
                    if (appInput.isScripting()) {
                        System.out.println("Выполнение команды из скрипта: " + input);
                    }
                    try {
                        commandManager.executeCommand(input);
                    } catch (app.exceptions.ScriptRecursionException e) {
                        System.err.println(e.getMessage());
                        while(appInput.isScripting()) {
                            appInput.popScanner();
                        }
                    }
                }
            }
        }
    }
}