package app.commands;

import app.io.ApplicationInputManager;
import app.exceptions.ScriptRecursionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScriptCommand implements Command {
    private final ApplicationInputManager inputManager;

    public ExecuteScriptCommand(ApplicationInputManager inputManager) {
        this.inputManager = inputManager;
    }

    @Override
    public String getName() { return "execute_script"; }

    @Override
    public String getDescription() { return "считать и исполнить скрипт из указанного файла"; }

    @Override
    public void execute(String argument) {
        if (argument.isEmpty()) {
            System.err.println("Ошибка: необходимо указать имя файла скрипта.");
            return;
        }

        File file = new File(argument);
        String absolutePath = file.getAbsolutePath();

        if (inputManager.isScriptActive(absolutePath)) {
            throw new ScriptRecursionException("Обнаружена рекурсия! Скрипт " + argument + " пытается вызвать сам себя.");
        }

        if (!file.exists() || !file.canRead()) {
            System.err.println("Ошибка: файл скрипта не существует или недоступен для чтения.");
            return;
        }

        try {
            Scanner fileScanner = new Scanner(file);
            inputManager.pushScanner(fileScanner, absolutePath);
            System.out.println("Начало выполнения скрипта: " + file.getName());
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка при открытии скрипта: " + e.getMessage());
        }
    }
}