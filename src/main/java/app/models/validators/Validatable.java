package app.models.validators;

/**
 * Интерфейс для объектов, поддерживающих проверку валидности своих полей.
 */
public interface Validatable {
    boolean validate();
}