package app.commands;

public class ExitCommand implements Command {
    @Override
    public String getName() { return "exit"; }

    @Override
    public String getDescription() { return "завершить программу (без сохранения в файл)"; }

    @Override
    public void execute(String argument) {
        if (!argument.isEmpty()) {
            System.err.println("Команда exit не принимает аргументов.");
            return;
        }
        System.out.println("Завершение работы программы...");
        System.exit(0);
    }
}