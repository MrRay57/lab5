package app.commands;

import app.managers.CollectionManager;
import app.managers.DumpManager;
import app.managers.FileManager;

public class SaveCommand implements Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;
    private final DumpManager dumpManager;

    public SaveCommand(CollectionManager collectionManager, FileManager fileManager, DumpManager dumpManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        this.dumpManager = dumpManager;
    }

    @Override
    public String getName() { return "save"; }

    @Override
    public String getDescription() { return "сохранить коллекцию в файл"; }

    @Override
    public void execute(String argument) {
        if (!argument.isEmpty()) {
            System.err.println("Команда save не принимает аргументов.");
            return;
        }
        String json = dumpManager.writeCollection(collectionManager.getCollection());
        fileManager.write(json);
    }
}