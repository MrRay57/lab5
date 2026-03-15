package app.exceptions;

/**
 * Исключение, выбрасываемое при попытке скрипта вызвать самого себя (предотвращение рекурсии).
 */
public class ScriptRecursionException extends RuntimeException {
    public ScriptRecursionException(String message) {
        super(message);
    }
}