package app.managers;

import java.io.*;
import java.util.Scanner;

/**
 * Менеджер для работы с файловой системой (чтение из файла и запись в файл).
 */
public class FileManager {
    private final String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public String read() {
        if (filePath == null || filePath.isEmpty()) {
            System.err.println("Путь к файлу не указан.");
            return "";
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("Файл по указанному пути не существует: " + filePath);
            return "";
        }
        if (!file.canRead()) {
            System.err.println("Нет прав на чтение файла: " + filePath);
            return "";
        }

        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден (возможно, был удален во время работы): " + e.getMessage());
        }
        return content.toString();
    }

    public void write(String data) {
        if (filePath == null || filePath.isEmpty()) {
            System.err.println("Путь к файлу не указан. Сохранение отменено.");
            return;
        }

        File file = new File(filePath);
        if (file.exists() && !file.canWrite()) {
            System.err.println("Нет прав на запись в файл: " + filePath);
            return;
        }

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] bytes = data.getBytes();
            bos.write(bytes);
            bos.flush();
            System.out.println("Коллекция успешно сохранена в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}