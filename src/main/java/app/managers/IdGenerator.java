package app.managers;

import app.models.Movie;
import java.util.TreeMap;

/**
 * Утилитарный класс для автоматической генерации уникальных идентификаторов (ID).
 */
public class IdGenerator {
    public static Long generateId(TreeMap<Long, Movie> collection) {
        if (collection.isEmpty()) {
            return 1L;
        }
        long maxId = 0;
        for (Movie movie : collection.values()) {
            if (movie.getId() > maxId) {
                maxId = movie.getId();
            }
        }
        return maxId + 1;
    }
}