package app.managers;

import app.models.Movie;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Менеджер для управления коллекцией объектов Movie.
 * Инкапсулирует логику работы с TreeMap, сортировку и фильтрацию.
 */
public class CollectionManager {
    private TreeMap<Long, Movie> collection;
    private final ZonedDateTime initializationDate;

    public CollectionManager() {
        this.collection = new TreeMap<>();
        this.initializationDate = ZonedDateTime.now();
    }

    public void setCollection(TreeMap<Long, Movie> collection) {
        this.collection = collection;
        this.collection.values().removeIf(movie -> !movie.validate());
    }

    public TreeMap<Long, Movie> getCollection() {
        return collection;
    }

    public ZonedDateTime getInitializationDate() {
        return initializationDate;
    }

    public void clear() {
        collection.clear();
    }

    public boolean insert(Long key, Movie movie) {
        if (collection.containsKey(key)) {
            return false;
        }
        movie.setId(IdGenerator.generateId(collection));
        movie.setCreationDate(ZonedDateTime.now());

        if (movie.validate()) {
            collection.put(key, movie);
            return true;
        }
        return false;
    }

    public boolean removeKey(Long key) {
        return collection.remove(key) != null;
    }

    public boolean update(Long id, Movie newMovie) {
        for (Map.Entry<Long, Movie> entry : collection.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                newMovie.setId(id);
                newMovie.setCreationDate(entry.getValue().getCreationDate());

                if (newMovie.validate()) {
                    collection.put(entry.getKey(), newMovie);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public int removeLower(Movie referenceMovie) {
        int initialSize = collection.size();
        Iterator<Map.Entry<Long, Movie>> iterator = collection.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Movie> entry = iterator.next();
            if (entry.getValue().compareTo(referenceMovie) < 0) {
                iterator.remove();
            }
        }
        return initialSize - collection.size();
    }

    public boolean replaceIfGreater(Long key, Movie newMovie) {
        Movie oldMovie = collection.get(key);
        if (oldMovie != null && newMovie.compareTo(oldMovie) > 0) {
            newMovie.setId(oldMovie.getId());
            newMovie.setCreationDate(oldMovie.getCreationDate());
            collection.put(key, newMovie);
            return true;
        }
        return false;
    }

    public boolean replaceIfLowe(Long key, Movie newMovie) {
        Movie oldMovie = collection.get(key);
        if (oldMovie != null && newMovie.compareTo(oldMovie) < 0) {
            newMovie.setId(oldMovie.getId());
            newMovie.setCreationDate(oldMovie.getCreationDate());
            collection.put(key, newMovie);
            return true;
        }
        return false;
    }

    public double getAverageOscarsCount() {
        if (collection.isEmpty()) return 0;
        long sum = 0;
        for (Movie movie : collection.values()) {
            sum += movie.getOscarsCount();
        }
        return (double) sum / collection.size();
    }

    public long countLessThanLength(int targetLength) {
        return collection.values().stream()
                .filter(movie -> movie.getLength() < targetLength)
                .count();
    }

    public List<Integer> getDescendingLengths() {
        List<Integer> lengths = new ArrayList<>();
        for (Movie movie : collection.values()) {
            lengths.add(movie.getLength());
        }
        lengths.sort(Collections.reverseOrder());
        return lengths;
    }
}